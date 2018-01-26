package com.cisco.asf.dataadvisor.category.service.impl;

@Service("dataTypeSearchIndexService")
@Profile({"lcl", "dev", "stage", "prod"})
public class ScheduledDataTypeIndexService extends AbstractSearchIndexService<DataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledDataTypeIndexService.class);

    @Autowired
    private CommonsPropertyService<CommonsDatabaseProperty> databasePropertyService;

    @Autowired
    private DataTypeRepository dataTypeRepository;

    @Override
    protected DataType getObject(Long aLong) {
        return dataTypeRepository.findOne(aLong);
    }

    @Override
    protected Class getModelClass() {
        return DataType.class;
    }

    @Override
    protected DataType createEmptyObjectWithId(long l) {
        return new DataType(l, "");
    }


    /**
     * Called periodically to check the DB to see if any updates are required.
     */
    @Scheduled(fixedDelay = 60000L)
    @Transactional
    public void checkDBForNewUpdates() {
        super.checkDBForNewUpdates();
    }

    /**
     * If the Database has a rebuild index flag and it is set as TRUE, then this method will return
     * true, and the index should be rebuilt.
     * @return false = the index is ok. true the index should be rebuilt.
     */
    @Scheduled(fixedDelay = 100000L)
    @Transactional
    public void checkDBForReset() throws IOException{
        CommonsDatabaseProperty property = databasePropertyService.getProperty(
                IndexSyncService.REBUILD_INDEX_KEY);

        if (property !=null && "true".equalsIgnoreCase(property.getValue())){
            LOGGER.warn("FOUND '{}' SET AS TRUE, WILL RE-CREATE SEARCH INDEX!!", IndexSyncService.REBUILD_INDEX_KEY);
            super.createIndex();
        }
    }

    /**
     * Called on system startup, initial create of index.
     * @throws IOException
     */
    @PostConstruct
    @Transactional
    public void createIndex() throws IOException {
        super.createIndex();
    }
}

