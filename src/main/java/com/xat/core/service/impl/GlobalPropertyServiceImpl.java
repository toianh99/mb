package com.xat.core.service.impl;

import com.globits.core.domain.GlobalProperty;
import com.globits.core.dto.GlobalPropertyDto;
import com.globits.core.repository.GlobalPropertyRepository;
import com.globits.core.service.GlobalPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GlobalPropertyServiceImpl implements GlobalPropertyService {
   @Autowired
   private GlobalPropertyRepository globalPropertyRepository;

   public Page<GlobalProperty> findByPage(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.globalPropertyRepository.findAll(pageable);
   }

   public GlobalProperty findByProperty(String property) {
      return this.globalPropertyRepository.findByProperty(property);
   }

   public GlobalPropertyDto findDtoByProperty(String property) {
      return this.globalPropertyRepository.findDtoByProperty(property);
   }

   public GlobalProperty save(GlobalProperty userDto) throws RuntimeException {
      GlobalProperty glbal = new GlobalProperty();
      glbal.setDescription(userDto.getDescription());
      glbal.setPropertyName(userDto.getPropertyName());
      glbal.setPropertyValue(userDto.getPropertyValue());
      glbal.setProperty(userDto.getProperty());
      glbal = (GlobalProperty)this.globalPropertyRepository.save(glbal);
      return new GlobalProperty(glbal);
   }

   public GlobalProperty delete(String property) throws RuntimeException {
      GlobalProperty user = this.globalPropertyRepository.findByProperty(property);
      this.globalPropertyRepository.delete(user);
      return user;
   }

   public GlobalProperty updateGlobalProperty(GlobalProperty userDto) throws RuntimeException {
      GlobalProperty updateUser = this.globalPropertyRepository.findByProperty(userDto.getProperty());
      if (updateUser != null) {
         if (userDto.getDescription() != null) {
            updateUser.setDescription(userDto.getDescription());
         }

         if (userDto.getPropertyName() != null) {
            updateUser.setPropertyName(userDto.getPropertyName());
         }

         if (userDto.getPropertyValue() != null) {
            updateUser.setPropertyValue(userDto.getPropertyValue());
         }

         if (userDto.getProperty() != null) {
            updateUser.setProperty(userDto.getProperty());
         }

         updateUser = (GlobalProperty)this.globalPropertyRepository.save(updateUser);
         return new GlobalProperty(updateUser);
      } else {
         return null;
      }
   }
}
