package com.xat.core.exceptionhandler;

import java.io.Serializable;
import org.springframework.http.HttpHeaders;

public class MessageEntity implements Serializable {
   private static final long serialVersionUID = 5498689480625016163L;
   private String field;
   private int code;
   private String message;
   private MessageType type;
   private HttpHeaders httpHeaders;

   public MessageEntity(String field, String message, MessageType type) {
      this.field = field;
      this.message = message;
      this.type = type;
      this.httpHeaders = null;
   }

   public MessageEntity(String field, int code, String message, MessageType type) {
      this.field = field;
      this.code = code;
      this.message = message;
      this.type = type;
      this.httpHeaders = null;
   }

   public MessageEntity(String field, String message, HttpHeaders httpHeaders, MessageType type) {
      this.field = field;
      this.message = message;
      this.type = type;
      this.httpHeaders = httpHeaders;
   }

   public String getField() {
      return this.field;
   }

   public void setField(String field) {
      this.field = field;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public MessageType getType() {
      return this.type;
   }

   public void setType(MessageType type) {
      this.type = type;
   }

   public HttpHeaders getHttpHeaders() {
      return this.httpHeaders;
   }

   public void setHttpHeaders(HttpHeaders httpHeaders) {
      this.httpHeaders = httpHeaders;
   }
}
