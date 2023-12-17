package com.xat.core.dto;

public class CommonSearchDto {
   private String keyword;
   private Integer pageIndex;
   private Integer pageSize;

   public String getKeyword() {
      return this.keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public Integer getPageIndex() {
      return this.pageIndex;
   }

   public void setPageIndex(Integer pageIndex) {
      this.pageIndex = pageIndex;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }
}
