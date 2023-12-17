package com.xat.core.service;

import com.globits.core.domain.Person;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface PersonService extends GenericService<Person, UUID> {
   Person getPersonWithAddress(UUID personId);

   Person savePerson(Person person);

   Person getFullPersonInfo(UUID personId);

   Page<Person> getListByPage(int pageIndex, int pageSize);
}
