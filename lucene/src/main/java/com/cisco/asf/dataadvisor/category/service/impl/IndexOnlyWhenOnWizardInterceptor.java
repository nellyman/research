package com.cisco.asf.dataadvisor.category.service.impl;

public class IndexOnlyWhenOnWizardInterceptor implements EntityIndexingInterceptor<DataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexOnlyWhenOnWizardInterceptor.class);

    /**
     * Will add into the index, or just skip, not adding the Type to the index..
     * @param entity the DataType being updated
     * @return The Override setting.
     */
    @Override
    public IndexingOverride onAdd(DataType entity) {
        return this.getIndexOperation(entity,IndexingOverride.SKIP );
    }

    /**
     * On an update will either add the dataType, or Remove it form the index..
     * @param entity the DataType being updated
     * @return The Override setting.
     */
    @Override
    public IndexingOverride onUpdate(DataType entity) {
        return this.getIndexOperation(entity,IndexingOverride.REMOVE );
    }

    /**
     * Will always perform the delete operation.
     * @param entity the DataType being updated
     * @return The Override setting, Apply-default
     */
    @Override
    public IndexingOverride onDelete(DataType entity) {
        return IndexingOverride.APPLY_DEFAULT;
    }

    /**
     * Either adds the DataType, or removes it.
     * @param entity the DataType being updated
     * @return The Override setting.
     */
    @Override
    public IndexingOverride onCollectionUpdate(DataType entity) {
        return this.getIndexOperation(entity, IndexingOverride.REMOVE);
    }

    /**
     * Checks the DataType addToIndex property and if true will applyDefault operation, or
     * if set as false apply the fail setting.
     * @param entity the DataType being updated
     * @param failSetting The override applied if the addToIndex is false.
     * @return The Override setting.
     */
    private IndexingOverride getIndexOperation(DataType entity, IndexingOverride failSetting){
        if (entity.isAddToIndex()){
            LOGGER.info("{} has addToIndex set true, adding to index", entity);
            return IndexingOverride.APPLY_DEFAULT;
        }
        LOGGER.info("{} has addToIndex set false, action- {}", entity, failSetting.toString());
        return failSetting;
    }
}

