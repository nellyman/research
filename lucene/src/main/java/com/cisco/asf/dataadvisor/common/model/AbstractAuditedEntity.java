package com.cisco.asf.dataadvisor.common.model;

import java.util.Date;

@Data
@ToString(of = {"id","name"})
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@AnalyzerDefs({
        @AnalyzerDef(name = "en",
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                                @Parameter(name = "language", value = "English")
                        })
                })
})
@MappedSuperclass
public abstract class AbstractAuditedEntity implements DataAdvisorObject{

    @JsonView(Core.class)
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", allocationSize = 1)
    @DocumentId
    public Long id;

    /**
     * Version helps on JPA but is also exposed in the Rest Controllers to
     * provide Header last-Updated information
     */
    @Version
    protected Long version;

    @JsonView(Core.class)
    @NotNull(message = "{name.notnull}")
    @Size(message="{name.large}", max = 255)
    @Column(nullable = false)
    @Audited
    @Field(store = YES)
    @Boost(value = 10.0f)
    @Analyzer(definition = "en")
    public String name;

    @JsonView(Summary.class)
    @Length(message="{description.large}", max = 2048)
    @Column(length = 2048)
    @Audited
    @Field(store = NO)
    @Boost(value = 5.0f)
    @Analyzer(definition = "en")
    public String description;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Audited
    protected Date updated;

    @LastModifiedBy
    @Audited
    protected String updater;

}

