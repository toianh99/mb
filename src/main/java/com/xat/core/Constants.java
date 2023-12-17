package com.xat.core;

public class Constants {
   public static final String ROLE_ADMIN = "ROLE_ADMIN";
   public static final String ROLE_ORG_ADMIN = "ROLE_ORG_ADMIN";
   public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
   public static final String ROLE_FILE_UPLOAD_ADMIN = "ROLE_FILE_UPLOAD_ADMIN";
   public static final String ROLE_USER = "ROLE_USER";
   public static final String USER_ADMIN_USERNAME = "admin";
   public static String UserImageFolder = null;
   public static String FileUploadPath = null;

   public static enum ActionLogTypeEnum {
      View(0),
      Create(1),
      Update(2),
      Delete(3),
      SaveOrUpdate(4),
      Login(5);

      private int value;

      private ActionLogTypeEnum(int value) {
         this.value = value;
      }

      public int getValue() {
         return this.value;
      }

      // $FF: synthetic method
      private static ActionLogTypeEnum[] $values() {
         return new ActionLogTypeEnum[]{View, Create, Update, Delete, SaveOrUpdate, Login};
      }
   }

   public static enum MaritalStatusEnum {
      Single(1),
      Married(2),
      ReMarried(3),
      Divorced(4),
      Separated(5),
      SingleHasChildren(6);

      private int value;

      private MaritalStatusEnum(int value) {
         this.value = value;
      }

      public int getValue() {
         return this.value;
      }

      // $FF: synthetic method
      private static MaritalStatusEnum[] $values() {
         return new MaritalStatusEnum[]{Single, Married, ReMarried, Divorced, Separated, SingleHasChildren};
      }
   }

   public static enum PersonAddressType {
      RegisterAddress(1),
      CurrentAddress(2);

      private int value;

      private PersonAddressType(int value) {
         this.value = value;
      }

      public int getValue() {
         return this.value;
      }

      // $FF: synthetic method
      private static PersonAddressType[] $values() {
         return new PersonAddressType[]{RegisterAddress, CurrentAddress};
      }
   }
}
