package com.xat.core.dto;

public class DepartmentSearchDto {
   private String name;
   private String code;
   private Long parentId;
   private String orderBy;

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

   public Long getParentId() {
      return this.parentId;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public String getOrderBy() {
      return this.orderBy;
   }

   public void setOrderBy(String orderBy) {
      this.orderBy = orderBy;
   }
}
