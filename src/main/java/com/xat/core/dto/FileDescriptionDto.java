package com.xat.core.dto;

import com.globits.core.domain.FileDescription;

public class FileDescriptionDto extends BaseObjectDto {
   private String contentType;
   private Long contentSize;
   private String name;
   private String extension;
   private String filePath;

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public Long getContentSize() {
      return this.contentSize;
   }

   public void setContentSize(Long contentSize) {
      this.contentSize = contentSize;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getExtension() {
      return this.extension;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public String getFilePath() {
      return this.filePath;
   }

   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   public FileDescriptionDto() {
   }

   public FileDescriptionDto(FileDescription entity, Boolean isGetAll) {
      if (entity != null) {
         this.setId(entity.getId());
         this.name = entity.getName();
         this.contentSize = entity.getContentSize();
         this.contentType = entity.getContentType();
         this.extension = entity.getExtension();
         this.filePath = entity.getFilePath();
      }

   }

   public FileDescriptionDto(FileDescription entity) {
      this(entity, true);
   }
}
