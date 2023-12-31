package com.xat.core.repository;

import java.util.UUID;

import com.xat.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
   @Query("select p from Person p left join fetch p.address left join fetch p.user where p.id = ?1")
   Person getFullPersonInfo(UUID personId);

   @Query("select p from Person p left join fetch p.address where p.id = ?1")
   Person getPersonWithAddress(UUID personId);

   @Transactional
   @Query("delete from PersonAddress pa where pa.person.id = ?1")
   int deletePersonAddress(UUID personId);
}
