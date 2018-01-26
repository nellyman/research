package com.cisco.asf.dataadvisor.organisation.model;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(of = {"id","name"})
@EqualsAndHashCode(of = "id")
public class Organisation implements DataAdvisorObject {

    @Id
    @Column(name = "ORG_ID")
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "{value.notnull}")
    @Size(message="{value.large}", max = 255)
    @Column(name = "NAME", length =255)
    private String name;

    @Column(name = "DESCRIPTION", length =1024)
    @Size(message="{value.large}", max = 1024)
    private String description;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Organisation parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Organisation> children= new ArrayList<>();

    @JsonProperty("defaultOrg")
    @Column(name = "DEFAULT_ORG")
    private boolean defaultOrg;

    public Organisation(){}

    public Organisation(String name, Organisation parent){
        this.name=name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organisation)) return false;
        if (!super.equals(o)) return false;

        Organisation that = (Organisation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

