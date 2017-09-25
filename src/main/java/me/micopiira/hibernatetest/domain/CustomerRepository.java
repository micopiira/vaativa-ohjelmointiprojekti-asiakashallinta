package me.micopiira.hibernatetest.domain;


import me.micopiira.hibernatetest.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
