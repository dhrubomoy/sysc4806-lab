package com;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "addressBook", path = "addressBook.json")
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {

}