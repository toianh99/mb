package com.globits.security.dto;

public class PhotoCropperDto {
   private UserDto user;
   private int x;
   private int y;
   private int w;
   private int h;

   public UserDto getUser() {
      return this.user;
   }

   public void setUser(UserDto user) {
      this.user = user;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getW() {
      return this.w;
   }

   public void setW(int w) {
      this.w = w;
   }

   public int getH() {
      return this.h;
   }

   public void setH(int h) {
      this.h = h;
   }
}
