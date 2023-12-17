package com.xat.core.exceptionhandler;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootErrorReplacer implements ErrorController {
   private static final String PATH = "/error";
   @Autowired
   private MessageSource msgSource;

   @RequestMapping({"/error"})
   public ResponseEntity<MessageEntity> error(HttpServletRequest request, HttpServletResponse response, Locale locale) {
      HttpHeaders responseHeaders = new HttpHeaders();
      HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
      String bodyOfResponse = this.msgSource.getMessage("edu.errors.500", (Object[])null, locale);
      MessageEntity msg = new MessageEntity((String)null, bodyOfResponse, responseHeaders, MessageType.ERROR);
      return new ResponseEntity(msg, httpStatus);
   }
}
