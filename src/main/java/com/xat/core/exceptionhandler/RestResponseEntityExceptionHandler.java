package com.xat.core.exceptionhandler;

import java.util.Locale;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
   @Autowired
   private MessageSource msgSource;

   @ExceptionHandler({AccessDeniedException.class})
   protected ResponseEntity<MessageEntity> processSecurityError(Exception ex, Locale locale) {
      ex.printStackTrace();
      String bodyOfResponse = this.msgSource.getMessage("edu.errors.access_denied", (Object[])null, locale);
      MessageEntity msg = new MessageEntity((String)null, bodyOfResponse, MessageType.ERROR);
      return new ResponseEntity(msg, HttpStatus.FORBIDDEN);
   }

   @ExceptionHandler({MethodArgumentNotValidException.class})
   protected ResponseEntity<MessageEntity> processValidationError(MethodArgumentNotValidException ex, Locale locale) {
      BindingResult result = ex.getBindingResult();
      FieldError error = result.getFieldError();
      return new ResponseEntity(this.processFieldError(error, locale), HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler({EntityNotFoundException.class})
   protected ResponseEntity<MessageEntity> handleNotFound(final RuntimeException ex, Locale locale) {
      ex.printStackTrace();
      String bodyOfResponse = this.msgSource.getMessage("edu.errors.404", (Object[])null, locale);
      MessageEntity msg = new MessageEntity((String)null, bodyOfResponse, MessageType.ERROR);
      return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
   protected ResponseEntity<MessageEntity> handleConflict(final RuntimeException ex, Locale locale) {
      ex.printStackTrace();
      String bodyOfResponse = this.msgSource.getMessage("edu.errors.409", (Object[])null, locale);
      MessageEntity msg = new MessageEntity((String)null, bodyOfResponse, MessageType.ERROR);
      return new ResponseEntity(msg, HttpStatus.CONFLICT);
   }

   @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
   public ResponseEntity<MessageEntity> handleInternal(final RuntimeException ex, Locale locale) {
      ex.printStackTrace();
      String bodyOfResponse = this.msgSource.getMessage("edu.errors.500", (Object[])null, locale);
      MessageEntity msg = new MessageEntity((String)null, bodyOfResponse, MessageType.ERROR);
      return new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   private MessageEntity processFieldError(FieldError error, Locale locale) {
      MessageEntity message = null;
      if (error != null) {
         String msg = this.msgSource.getMessage(error.getDefaultMessage(), (Object[])null, locale);
         message = new MessageEntity(error.getField(), msg, MessageType.ERROR);
      }

      return message;
   }
}
