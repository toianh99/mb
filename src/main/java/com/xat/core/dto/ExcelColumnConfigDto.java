package com.xat.core.dto;

public class ExcelColumnConfigDto {
   private String columnName;
   private String objectName;
   private int columnIndex;

   public String getColumnName() {
      return this.columnName;
   }

   public void setColumnName(String columnName) {
      this.columnName = columnName;
   }

   public String getObjectName() {
      return this.objectName;
   }

   public void setObjectName(String objectName) {
      this.objectName = objectName;
   }

   public int getColumnIndex() {
      return this.columnIndex;
   }

   public void setColumnIndex(int columnIndex) {
      this.columnIndex = columnIndex;
   }

   public ExcelColumnConfigDto(String columnName, String objectName, int columnIndex) {
      this.columnName = columnName;
      this.objectName = objectName;
      this.columnIndex = columnIndex;
   }
}
