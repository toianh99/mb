package com.xat.core.rest;

import com.globits.core.domain.FileDescription;
import com.globits.core.dto.CommonSearchDto;
import com.globits.core.dto.FileDescriptionDto;
import com.globits.core.service.FileDescriptionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping({"/public/file"})
public class RestFilePublicController {
   private static final Logger logger = LoggerFactory.getLogger(RestFileDescriptionController.class);
   @Autowired
   FileDescriptionService fileService;

   @RequestMapping(
      value = {"/downloadbyid/{id}"},
      method = {RequestMethod.GET}
   )
   public void downloadFileById(@PathVariable UUID id, WebRequest request, HttpServletResponse response) {
      try {
         FileDescription fileDesc = (FileDescription)this.fileService.findById(id);
         String filePath = null;
         if (fileDesc != null) {
            filePath = fileDesc.getFilePath();
         }

         FileInputStream reader = new FileInputStream(new File(filePath));
         int available = reader.available();
         byte[] data = new byte[available];
         reader.read(data, 0, available);
         ServletOutputStream out = response.getOutputStream();
         response.setHeader("Content-Disposition", "attachment; filename=" + fileDesc.getName());
         response.setContentType(fileDesc.getContentType());
         out.write(data);
         response.flushBuffer();
      } catch (FileNotFoundException var10) {
         var10.printStackTrace();
      } catch (IOException var11) {
         var11.printStackTrace();
      }

   }

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   public Page<FileDescription> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<FileDescription> page = this.fileService.getList(pageIndex, pageSize);
      return page;
   }

   @RequestMapping(
      value = {"/search"},
      method = {RequestMethod.POST}
   )
   public Page<FileDescriptionDto> getList(@RequestBody CommonSearchDto searchObject) {
      Page<FileDescriptionDto> page = this.fileService.search(searchObject);
      return page;
   }

   @RequestMapping(
      value = {"/getbyid/{id}"},
      method = {RequestMethod.GET}
   )
   public ResponseEntity<InputStreamResource> downloadFileById(@PathVariable UUID id, HttpServletRequest request) throws IOException {
      FileDescription fileDesc = (FileDescription)this.fileService.findById(id);
      String filePath = null;
      if (fileDesc != null) {
         filePath = fileDesc.getFilePath();
      }

      FileInputStream file = new FileInputStream(new File(filePath));
      HttpHeaders headers = new HttpHeaders();
      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
      headers.add("Pragma", "no-cache");
      headers.add("Expires", "0");
      return ((BodyBuilder)ResponseEntity.ok().headers(headers)).contentLength(fileDesc.getContentSize()).contentType(MediaType.parseMediaType(fileDesc.getContentType())).body(new InputStreamResource(file));
   }
}
