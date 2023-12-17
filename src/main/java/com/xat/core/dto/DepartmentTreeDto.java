package com.xat.core.dto;

import com.globits.core.domain.Department;
import java.io.Serializable;

public class DepartmentTreeDto implements Serializable {
   private static final long serialVersionUID = -991560624348607019L;
   private String id;
   private String parent;
   private String icon;
   private String text;

   public DepartmentTreeDto(Department department) {
      if (department != null) {
         this.id = department.getId().toString();
         this.text = department.getName() + " - " + department.getCode();
         this.icon = "fa fa-building";
         if (department.getParent() != null) {
            this.parent = department.getParent().getId().toString();
         } else {
            this.parent = "#";
         }

      }
   }

   public String getId() {
      return this.id;
   }

   public String getIcon() {
      return this.icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getParent() {
      return this.parent;
   }

   public void setParent(String parent) {
      this.parent = parent;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }
}
