package com.xat.core.rest;

import com.globits.core.Constants;
import com.globits.core.domain.FileDescription;
import com.globits.core.dto.FileDescriptionDto;
import com.globits.core.service.FileDescriptionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api/file"})
public class RestFileDescriptionController {
   public static final String ROLE_FILE_MANAGER = "ROLE_FILE_MANAGER";
   private static final Logger logger = LoggerFactory.getLogger(RestFileDescriptionController.class);
   @Autowired
   FileDescriptionService fileService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_FILE_MANAGER"})
   public Page<FileDescription> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<FileDescription> page = this.fileService.getList(pageIndex, pageSize);
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_FILE_MANAGER"})
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

   @Secured({"ROLE_ADMIN", "ROLE_FILE_MANAGER"})
   @RequestMapping(
      value = {"/download/{fileName}"},
      method = {RequestMethod.GET}
   )
   public ResponseEntity<InputStreamResource> download(@PathVariable("fileName") String fileName) throws IOException {
      System.out.println("Calling Download:- " + fileName);
      HttpHeaders headers = new HttpHeaders();
      headers.add("Access-Control-Allow-Origin", "*");
      headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
      headers.add("Access-Control-Allow-Headers", "Content-Type");
      headers.add("Content-Disposition", "filename=" + fileName);
      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
      headers.add("Pragma", "no-cache");
      headers.add("Expires", "0");
      String filePath = fileName;
      if (Constants.FileUploadPath != null) {
         filePath = Constants.FileUploadPath + fileName;
      }

      File file = new File(filePath);
      FileInputStream inputStream = new FileInputStream(new File(filePath));
      headers.setContentLength(file.length());
      ResponseEntity<InputStreamResource> response = new ResponseEntity(new InputStreamResource(inputStream), headers, HttpStatus.OK);
      return response;
   }

   @Secured({"ROLE_ADMIN", "ROLE_FILE_UPLOAD_ADMIN"})
   @RequestMapping(
      value = {"/upload"},
      method = {RequestMethod.POST},
      consumes = {"multipart/form-data"}
   )
   public FileDescriptionDto saveUploadFile(@RequestParam("uploadfile") MultipartFile file) {
      System.out.println(file.getOriginalFilename() + ":" + file.getContentType() + ":" + file.getSize());

      try {
         byte[] bytes = file.getBytes();
         FileDescription fileDesc = new FileDescription();
         fileDesc.setContentSize(file.getSize());
         fileDesc.setContentType(file.getContentType());
         String filePath = file.getOriginalFilename();
         if (Constants.FileUploadPath != null) {
            filePath = Constants.FileUploadPath + filePath;
         }

         File f = new File(filePath);
         if (!f.exists()) {
            f.createNewFile();
         }

         OutputStream out = new FileOutputStream(f);
         out.write(bytes);
         out.close();
         fileDesc.setFilePath(filePath);
         fileDesc.setName(file.getOriginalFilename());
         fileDesc = (FileDescription)this.fileService.save(fileDesc);
         return new FileDescriptionDto(fileDesc);
      } catch (IOException var7) {
         var7.printStackTrace();
         return null;
      }
   }

   @Secured({"ROLE_ADMIN", "ROLE_FILE_UPLOAD_ADMIN"})
   @RequestMapping(
      value = {"/uploadfile"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public ResponseEntity<FileDescriptionDto> uploadFile(@RequestParam("uploadfile") MultipartFile file) {
      System.out.println(file.getOriginalFilename() + ":" + file.getContentType() + ":" + file.getSize());

      try {
         byte[] bytes = file.getBytes();
         FileDescription fileDesc = new FileDescription();
         fileDesc.setContentSize(file.getSize());
         fileDesc.setContentType(file.getContentType());
         String filePath = file.getOriginalFilename();
         if (Constants.FileUploadPath != null) {
            filePath = Constants.FileUploadPath + filePath;
         }

         File f = new File(filePath);
         if (!f.exists()) {
            f.createNewFile();
         }

         OutputStream out = new FileOutputStream(f);
         out.write(bytes);
         out.close();
         fileDesc.setFilePath(filePath);
         fileDesc.setName(file.getOriginalFilename());
         fileDesc = (FileDescription)this.fileService.save(fileDesc);
         ResponseEntity<FileDescriptionDto> response = new ResponseEntity(new FileDescriptionDto(fileDesc), HttpStatus.OK);
         return response;
      } catch (IOException var8) {
         var8.printStackTrace();
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
   }
}
