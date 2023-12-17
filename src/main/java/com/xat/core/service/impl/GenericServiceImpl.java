package com.xat.core.service.impl;

import com.xat.core.service.GenericService;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericServiceImpl<T, Idt extends Serializable> implements GenericService<T, Idt> {
   @Autowired
   public JpaRepository<T, Idt> repository;
   @PersistenceContext
   public EntityManager manager;

   public T delete(Idt id) {
      T result = this.repository.getOne(id);
      if (result != null) {
         this.repository.deleteById(id);
      }

      return result;
   }

   public T save(T t) {
      T result = this.repository.save(t);
      return result;
   }

   public Page<T> getList(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.repository.findAll(pageable);
   }

   public T findById(Idt id) {
      return this.repository.getOne(id);
   }
}
