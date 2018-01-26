package com.cisco.asf.dataadvisor.category.model;

import com.cisco.asf.dataadvisor.organisation.model.Organisation;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
/**The indexing interceptor is used to only index DataTypes that have hideFromIndex set as false**/
@Indexed(interceptor = IndexOnlyWhenOnWizardInterceptor.class)
public class DataType extends AbstractAuditedEntity implements  Comparable<DataType>, IndexedObject{

    @Setter
    @JsonView(Wizard.class)
    @Audited
    /** Field describing if the type has privacy concerns **/
    private boolean privacy;

    @JsonView(Wizard.class)
    @Setter
    @ManyToOne
    @Audited
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "classification")
    private Classification classification;

    @Field(store = NO)
    @JsonView(Wizard.class)
    @Boost(value = 8.0f)
    @Analyzer(definition = "en")
    private String keywords;

    @ManyToOne
    @JsonIgnore
    private Category category;

    /**
     * correctTime is Long Date timestamp
     * as the updated is polluted with timezone rubbish
     * making acurate comparisons between JVM's impossible.
     * Set this as the time when the Type is updated.
     */
    private Long updatedTime;

    /**
     * This property holds whether the DataType needs to be in
     * the index or not. The {@link IndexOnlyWhenOnWizardInterceptor}
     * will examine this value and determine whether to add into the wizard.
     *
     * This value is the negative of the Category ''hideFromWizard' property.
     * i.e. when hideFromWizard is set true then any of it's dataType's are
     * set addToIndex is set false.
     *
     * On a category update any change to the hideFromWizard Property
     * needs reflecting in its DataTypes.
     */
    @Column(name = "ADD_TO_INDEX")
    @JsonIgnore
    private boolean addToIndex;

    /**
     * Holds the retention property for the Data Type.
     */
    @JsonView(Wizard.class)
    private String retention;

    /**
     * Organisations for which this DataType is linked to
     */
    @ManyToMany
    @JoinTable(name="DATA_TYPE_ORG",
            joinColumns = @JoinColumn(name = "TYPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORG_ID"))
    @OrderBy(" name ASC")
    @IndexedEmbedded
    private final Set<Organisation> orgs = new HashSet<>();

    /**
     * Constructor with Id and Name.
     * @param id the DataType Id
     * @param name The DataType Name.
     */
    public DataType(Long id, String name){
        this.id=id;
        this.name=name;
    }

    /**
     * Populated Constructor.
     * @param name The name to set.
     * @param classification The classification.
     */
    public DataType(String name, Classification classification){
        super.name=name;
        this.classification=classification;
    }

    /**
     * Populated Constructor.
     * @param name The name to set.
     * @param description The Description
     * @param classification The classification.
     */
    public DataType(String name, Classification classification, String description){
        super.name=name;
        super.description = description;
        this.classification=classification;
    }

    public DataType(String name, Classification classification, String description, String retention){
        this(name, classification, description);
        this.retention = retention;
    }

    public void setClassification(Classification classification){
        this.classification = classification;
    }

    public boolean isPrivacy(){
        return this.privacy;
    }

    public Classification getClassification(){
        return this.classification;
    }

    public void setPrivacy(boolean isPrivate){
        this.privacy =isPrivate;
    }

    public DataType addOrg(Organisation org){
        this.orgs.add(org);
        return this;
    }

    public DataType setOrgs(Set<Organisation> orgs){
        this.orgs.clear();
        if (orgs!=null){
            this.orgs.addAll(orgs);
        }
        return this;
    }


    @Override
    public int compareTo(DataType o) {
        if (o==null || this.getName()==null || o.getName()==null){
            return -1;
        }
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        DataType dataType = (DataType) o;

        if (id !=null && id.equals(dataType.getId())){
            return true;
        }

        if (dataType.getId() != null) {
            return false;
        }
        if (name != null ? !name.equals(dataType.name) : dataType.name != null) {
            return false;
        }
        return category == dataType.getCategory();
    }

    @Override
    public int hashCode() {

        if (id!=null){
            return id.hashCode();
        }
        int result = 31 * (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataType{" +
                ", id=" + this.id +
                ", name='" + this.name + '\'' +
                '}';
    }

}

