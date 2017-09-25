package me.micopiira.hibernatetest.domain;


import me.micopiira.hibernatetest.framework.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
