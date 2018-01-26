package com.cisco.asf.dataadvisor.category.repository;

public class CategoryWizardFilters {

    private CategoryWizardFilters() {
        //empty
    }

    public static Specification<Category> onlyCategoriesForWizard(){
        return new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.where( cb.equal(root.get(Category_.hideFromWizard), false) );
                return query.getRestriction();
            }
        };
    }

    /**
     * Place at end of the specification filters to provide name sorting.
     * @return Sort object to sort on Name.
     */
    public static Sort sortByNameAsc() {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name");
        order = order.ignoreCase();
        return new Sort(order);
    }
}