package com.xat.core.dto;

public class CommonSearchObjectDto {
   private String keyword;
   private int pageIndex;
   private int pageSize;

   public String getKeyword() {
      return this.keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public int getPageIndex() {
      return this.pageIndex;
   }

   public void setPageIndex(int pageIndex) {
      this.pageIndex = pageIndex;
   }

   public int getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }
}
