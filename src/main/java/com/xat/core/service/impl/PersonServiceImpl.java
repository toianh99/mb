package com.xat.core.service.impl;

import com.xat.core.domain.Person;
import com.xat.core.repository.PersonRepository;
import com.xat.core.service.PersonService;
import com.xat.core.security.domain.User;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, UUID> implements PersonService {
   @Autowired
   private PersonRepository personRepository;

   public Person getPersonWithAddress(UUID personId) {
      return this.personRepository.getPersonWithAddress(personId);
   }

   public Person getFullPersonInfo(UUID personId) {
      return this.personRepository.getFullPersonInfo(personId);
   }

   public Person savePerson(Person person) {
      Person updatePerson = this.personRepository.getPersonWithAddress(person.getId());
      updatePerson.setAddress(person.getAddress());
      updatePerson.setBirthDate(person.getBirthDate());
//      User user = person.getUser();
//      if (user != null) {
//         User updateUser = updatePerson.getUser();
//         updateUser.setAccountNonExpired(user.isAccountNonExpired());
//         updateUser.setAccountNonLocked(user.isAccountNonLocked());
//         if (user.getRoles() != null) {
//            updateUser.setRoles(user.getRoles());
//         }
//
//         if (user.getUsername() != null) {
//            updateUser.setUsername(user.getUsername());
//         }
//
//         updatePerson.setUser(updateUser);
//      }

      return (Person)this.save(updatePerson);
   }

   public Page<Person> getListByPage(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      int firstResult = (pageIndex - 1) * pageSize;
      Long total = (Long)this.manager.createQuery("select count(*) from Person").getSingleResult();
      TypedQuery<Person> q = this.manager.createQuery("select new Person(p.id, p.firstName, p.lastName, p.displayName, p.birthDate, p.phoneNumber) from Person p", Person.class);
      q.setFirstResult(firstResult);
      q.setMaxResults(pageSize);
      List<Person> list = q.getResultList();
      Page<Person> page = new PageImpl(list, pageable, total);
      return page;
   }
}
