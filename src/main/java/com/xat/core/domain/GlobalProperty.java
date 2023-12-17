package com.xat.core.domain;

import com.xat.core.auditing.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_global_property"
)
public class GlobalProperty extends AuditableEntity {
   private static final long serialVersionUID = 8191591866881769867L;
   @Id
   @Column(
      name = "property",
      nullable = false
   )
   private String property;
   @Column(
      name = "property_name"
   )
   private String propertyName;
   @Column(
      name = "property_value"
   )
   private String propertyValue;
   @Column(
      name = "description"
   )
   private String description;
   @Column(
      name = "data_type_name"
   )
   private String dataTypeName;

   public String getProperty() {
      return this.property;
   }

   public void setProperty(String property) {
      this.property = property;
   }

   public String getPropertyName() {
      return this.propertyName;
   }

   public void setPropertyName(String propertyName) {
      this.propertyName = propertyName;
   }

   public String getPropertyValue() {
      return this.propertyValue;
   }

   public void setPropertyValue(String propertyValue) {
      this.propertyValue = propertyValue;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDataTypeName() {
      return this.dataTypeName;
   }

   public void setDataTypeName(String dataTypeName) {
      this.dataTypeName = dataTypeName;
   }

   public GlobalProperty() {
   }

   public GlobalProperty(GlobalProperty globalProperty) {
      this.property = globalProperty.getProperty();
      this.propertyName = globalProperty.getPropertyName();
      this.propertyValue = globalProperty.getPropertyValue();
      this.description = globalProperty.getDescription();
   }
}
