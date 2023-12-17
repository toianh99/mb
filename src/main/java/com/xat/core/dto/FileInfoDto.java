package com.xat.core.dto;

public class FileInfoDto {
   private String fileName;
   private long fileSize;

   public String getFileName() {
      return this.fileName;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public long getFileSize() {
      return this.fileSize;
   }

   public void setFileSize(long fileSize) {
      this.fileSize = fileSize;
   }
}
