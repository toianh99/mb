package com.xat.core.security.service.impl;

import com.xat.core.utils.CommonUtils;
import com.xat.core.security.domain.UserGroup;
import com.xat.core.security.dto.UserGroupDto;
import com.xat.core.security.repository.UserGroupRepository;
import com.xat.core.security.service.UserGroupService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserGroupServiceImpl implements UserGroupService {
   @Autowired
   private UserGroupRepository repos;

   @Transactional(
      readOnly = true
   )
   public UserGroupDto findById(Long id) {
      if (!CommonUtils.isPositive(id, true)) {
         return null;
      } else {
         UserGroup entity = (UserGroup)this.repos.getOne(id);
         return entity == null ? null : new UserGroupDto(entity);
      }
   }

   @Transactional(
      readOnly = true
   )
   public Page<UserGroupDto> findList(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(new Order[]{new Order(Direction.ASC, "name")}));
      Page<UserGroup> page = this.repos.findAll(pageable);
      List<UserGroup> content = page.getContent();
      List<UserGroupDto> dtoContent = new ArrayList();

      for(int i = 0; i < content.size(); ++i) {
         dtoContent.add(new UserGroupDto((UserGroup)content.get(i)));
      }

      return new PageImpl(dtoContent, pageable, this.repos.count());
   }

   @Transactional(
      readOnly = true
   )
   public List<UserGroupDto> findAll() {
      List<UserGroup> list = this.repos.findAll();
      List<UserGroupDto> dtoList = new ArrayList();

      for(int i = 0; i < list.size(); ++i) {
         dtoList.add(new UserGroupDto((UserGroup)list.get(i)));
      }

      return dtoList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public UserGroupDto save(UserGroupDto dto) {
      if (dto == null) {
         throw new IllegalArgumentException();
      } else {
         UserGroup entity = null;
         if (CommonUtils.isPositive(dto.getId(), true)) {
            entity = (UserGroup)this.repos.getOne(dto.getId());
         }

         if (entity == null) {
            entity = dto.toEntity();
         } else {
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
         }

         entity = (UserGroup)this.repos.save(entity);
         return entity == null ? null : new UserGroupDto(entity);
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void delete(UserGroupDto[] dtos) {
      if (dtos != null && dtos.length > 0) {
         for(int i = 0; i < dtos.length; ++i) {
            UserGroup entity = (UserGroup)this.repos.getOne(dtos[i].getId());

            try {
               this.repos.delete(entity);
            } catch (Exception var5) {
               throw new RuntimeException();
            }
         }

      } else {
         throw new IllegalArgumentException();
      }
   }
}
