package com.xat.core.dto;

import com.globits.core.domain.Organization;
import com.globits.core.domain.OrganizationUser;
import com.globits.security.dto.UserDto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class OrganizationDto {
   private UUID id;
   private String name;
   private String code;
   private String website;
   private Integer organizationType;
   private Integer level;
   private OrganizationDto parent;
   private List<OrganizationDto> subDepartments;
   private List<OrganizationUserDto> users;
   private Boolean isActive = true;
   private UUID parentId;

   public OrganizationDto() {
   }

   public OrganizationDto(Organization c, Boolean basicInfo) {
      this.id = c.getId();
      this.code = c.getCode();
      this.name = c.getName();
      this.organizationType = c.getOrganizationType();
      if (!basicInfo) {
         if (c.getParent() != null) {
            this.parent = new OrganizationDto();
            this.parent.setCode(c.getParent().getCode());
            this.parent.setName(c.getParent().getName());
            this.parent.setOrganizationType(c.getParent().getOrganizationType());
            this.parent.setId(c.getParent().getId());
         }

         Iterator var3;
         if (c.getSubOrganizations() != null && c.getSubOrganizations().size() > 0) {
            this.subDepartments = new ArrayList();
            var3 = c.getSubOrganizations().iterator();

            while(var3.hasNext()) {
               Organization o = (Organization)var3.next();
               OrganizationDto cDto = new OrganizationDto(o, false);
               this.subDepartments.add(cDto);
            }
         }

         if (c.getUsers() != null && c.getUsers().size() > 0) {
            this.users = new ArrayList();
            var3 = c.getUsers().iterator();

            while(var3.hasNext()) {
               OrganizationUser o = (OrganizationUser)var3.next();
               OrganizationUserDto cDto = new OrganizationUserDto();
               cDto.setId(o.getId());
               cDto.setIsAdminUser(o.getIsAdminUser());
               cDto.setUser(new UserDto(o.getUser()));
               cDto.setOrganization(new OrganizationDto());
               cDto.getOrganization().setId(o.getOrganization().getId());
               cDto.getOrganization().setName(o.getOrganization().getName());
               cDto.getOrganization().setCode(o.getOrganization().getCode());
               this.users.add(cDto);
            }
         }
      }

   }

   public OrganizationDto(Organization c) {
      this.id = c.getId();
      this.code = c.getCode();
      this.name = c.getName();
      this.level = c.getLevel();
      this.organizationType = c.getOrganizationType();
      if (c.getParent() != null) {
         this.parent = new OrganizationDto();
         this.parent.setCode(c.getParent().getCode());
         this.parent.setName(c.getParent().getName());
         this.parent.setOrganizationType(c.getParent().getOrganizationType());
         this.parent.setId(c.getParent().getId());
      }

      Iterator var2;
      if (c.getSubOrganizations() != null && c.getSubOrganizations().size() > 0) {
         this.subDepartments = new ArrayList();
         var2 = c.getSubOrganizations().iterator();

         while(var2.hasNext()) {
            Organization o = (Organization)var2.next();
            OrganizationDto cDto = new OrganizationDto();
            cDto.setId(o.getId());
            cDto.setCode(o.getCode());
            cDto.setName(o.getName());
            cDto.setOrganizationType(o.getOrganizationType());
            this.subDepartments.add(cDto);
         }
      }

      if (c.getUsers() != null && c.getUsers().size() > 0) {
         this.users = new ArrayList();
         var2 = c.getUsers().iterator();

         while(var2.hasNext()) {
            OrganizationUser o = (OrganizationUser)var2.next();
            OrganizationUserDto cDto = new OrganizationUserDto();
            cDto.setId(o.getId());
            cDto.setIsAdminUser(o.getIsAdminUser());
            cDto.setUser(new UserDto(o.getUser()));
            cDto.setOrganization(new OrganizationDto());
            cDto.getOrganization().setId(o.getOrganization().getId());
            cDto.getOrganization().setName(o.getOrganization().getName());
            cDto.getOrganization().setCode(o.getOrganization().getCode());
            this.users.add(cDto);
         }
      }

   }

   public Organization toEntity() {
      Organization entity = new Organization();
      entity.setId(this.id);
      entity.setCode(this.code);
      entity.setName(this.name);
      entity.setOrganizationType(this.organizationType);
      if (this.parent != null) {
         Organization pEntity = new Organization();
         pEntity.setId(this.parent.getId());
         pEntity.setCode(this.parent.getCode());
         pEntity.setName(this.parent.getName());
         pEntity.setOrganizationType(this.parent.getOrganizationType());
         entity.setParent(pEntity);
      }

      return entity;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public Integer getOrganizationType() {
      return this.organizationType;
   }

   public void setOrganizationType(Integer organizationType) {
      this.organizationType = organizationType;
   }

   public String getWebsite() {
      return this.website;
   }

   public void setWebsite(String website) {
      this.website = website;
   }

   public OrganizationDto getParent() {
      return this.parent;
   }

   public void setParent(OrganizationDto parent) {
      this.parent = parent;
   }

   public List<OrganizationDto> getSubDepartments() {
      return this.subDepartments;
   }

   public void setSubDepartments(List<OrganizationDto> subDepartments) {
      this.subDepartments = subDepartments;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<OrganizationUserDto> getUsers() {
      return this.users;
   }

   public void setUsers(List<OrganizationUserDto> users) {
      this.users = users;
   }

   public Boolean getIsActive() {
      return this.isActive;
   }

   public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
   }

   public UUID getParentId() {
      return this.parentId;
   }

   public void setParentId(UUID parentId) {
      this.parentId = parentId;
   }

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }
}
