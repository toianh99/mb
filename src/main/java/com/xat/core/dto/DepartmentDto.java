package com.xat.core.dto;

import com.globits.core.domain.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DepartmentDto implements Serializable {
   private static final long serialVersionUID = 1L;
   private UUID id;
   private String code;
   private String name;
   private int departmentType;
   private UUID parentId;
   private DepartmentDto parent;
   private List<DepartmentDto> subDepartments;
   private List<DepartmentDto> children;
   private boolean isDuplicate;
   private String dupName;
   private String dupCode;
   private String displayOrder;
   private Integer level;
   private String linePath;
   private String shortName;

   public DepartmentDto() {
   }

   private List<DepartmentDto> getSubRecursive(Department root) {
      List<DepartmentDto> ret = new ArrayList();
      if (root.getSubDepartments() != null && root.getSubDepartments().size() > 0) {
         Iterator var3 = root.getSubDepartments().iterator();

         while(var3.hasNext()) {
            Department c = (Department)var3.next();
            DepartmentDto cDto = new DepartmentDto();
            cDto.setId(c.getId());
            cDto.setCode(c.getCode());
            cDto.setName(c.getName());
            cDto.setDepartmentType(c.getDepartmentType());
            cDto.setDisplayOrder(c.getDisplayOrder());
            cDto.setLevel(c.getLevel());
            cDto.setLinePath(c.getLinePath());
            List<DepartmentDto> childs = this.getSubRecursive(c);
            cDto.setSubDepartments(childs);
            cDto.setChildren(childs);
            ret.add(cDto);
         }
      }

      return ret;
   }

   public DepartmentDto(Department entity, Boolean isGetFull) {
      if (entity != null) {
         this.code = entity.getCode();
         this.departmentType = entity.getDepartmentType();
         this.id = entity.getId();
         this.name = entity.getName();
         this.displayOrder = entity.getDisplayOrder();
         this.level = entity.getLevel();
         this.linePath = entity.getLinePath();
         this.shortName = entity.getShortName();
         if (entity.getParent() != null) {
            this.parent = new DepartmentDto();
            this.parent.setCode(entity.getParent().getCode());
            this.parent.setDepartmentType(entity.getParent().getDepartmentType());
            this.parent.setName(entity.getParent().getName());
            this.parent.setId(entity.getParent().getId());
         }

         this.setSubDepartments(this.getSubRecursive(entity));
         this.setChildren(this.getSubRecursive(entity));
      }
   }

   public DepartmentDto(Department entity) {
      this(entity, true);
   }

   public Department toEntity() {
      Department entity = new Department();
      entity.setId(this.id);
      entity.setCode(this.code);
      entity.setName(this.name);
      entity.setDepartmentType(this.departmentType);
      entity.setShortName(this.getShortName());
      if (this.parent != null) {
         Department parentEntity = new Department();
         parentEntity.setId(this.parent.getId());
         parentEntity.setCode(this.parent.getCode());
         parentEntity.setName(this.parent.getName());
         parentEntity.setDepartmentType(this.parent.getDepartmentType());
         entity.setParent(parentEntity);
      }

      return entity;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public int getDepartmentType() {
      return this.departmentType;
   }

   public void setDepartmentType(int departmentType) {
      this.departmentType = departmentType;
   }

   public UUID getParentId() {
      return this.parentId;
   }

   public void setParentId(UUID parentId) {
      this.parentId = parentId;
   }

   public List<DepartmentDto> getChildren() {
      return this.children;
   }

   public void setChildren(List<DepartmentDto> children) {
      this.children = children;
   }

   public List<DepartmentDto> getSubDepartments() {
      return this.subDepartments;
   }

   public void setSubDepartments(List<DepartmentDto> subDepartments) {
      this.subDepartments = subDepartments;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public DepartmentDto getParent() {
      return this.parent;
   }

   public void setParent(DepartmentDto parent) {
      this.parent = parent;
   }

   public boolean isDuplicate() {
      return this.isDuplicate;
   }

   public void setDuplicate(boolean isDuplicate) {
      this.isDuplicate = isDuplicate;
   }

   public String getDupName() {
      return this.dupName;
   }

   public void setDupName(String dupName) {
      this.dupName = dupName;
   }

   public String getDupCode() {
      return this.dupCode;
   }

   public void setDupCode(String dupCode) {
      this.dupCode = dupCode;
   }

   public String getDisplayOrder() {
      return this.displayOrder;
   }

   public void setDisplayOrder(String displayOrder) {
      this.displayOrder = displayOrder;
   }

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public String getLinePath() {
      return this.linePath;
   }

   public void setLinePath(String linePath) {
      this.linePath = linePath;
   }

   public String getShortName() {
      return this.shortName;
   }

   public void setShortName(String shortName) {
      this.shortName = shortName;
   }
}
