package com.xat.core.security.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import com.xat.core.Constants;
import com.xat.core.domain.FileDescription;
import com.xat.core.dto.PersonDto;
import com.xat.core.security.domain.User;
import com.xat.core.security.dto.PasswordChangeDto;
import com.xat.core.security.dto.PhotoCropperDto;
import com.xat.core.security.dto.UserDto;
import com.xat.core.security.dto.UserFilterDto;
import com.xat.core.security.service.UserService;
import com.xat.core.service.FileDescriptionService;
import com.xat.core.utils.CommonUtils;
import com.xat.core.utils.FileUtils;
import com.xat.core.utils.ImageUtils;
import com.xat.core.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestUserController {
   @Autowired
   private UserService userService;
   @Autowired
   private Environment env;
   @Autowired
   FileDescriptionService fileService;

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping(
      path = {"/api/users"}
   )
   public UserDto findByUsername(@RequestParam(value = "username",required = true) String username) {
      return username == null ? null : this.userService.findByUsername(username);
   }

   @PreAuthorize("isAuthenticated()")
   @GetMapping(
      path = {"/api/users/e/{email}"}
   )
   public ResponseEntity<UserDto> findByEmail(@PathVariable("email") String email) {
      return CommonUtils.isEmpty(email) ? new ResponseEntity(new UserDto(), HttpStatus.BAD_REQUEST) : new ResponseEntity(this.userService.findByEmail(email), HttpStatus.OK);
   }

   @PreAuthorize("isAuthenticated()")
   @GetMapping({"/api/users/{userId}"})
   public UserDto getUser(@PathVariable("userId") String userId) {
      return this.userService.findByUserId(Long.valueOf(userId));
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping({"/api/users/{pageIndex}/{pageSize}/{username}"})
   public Page<UserDto> searchUsers(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable String username) {
      return this.userService.searchByPageBasicInfo(pageIndex, pageSize, "%" + username + "%");
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping({"/api/users/{pageIndex}/{pageSize}"})
   public Page<UserDto> getUsers(@PathVariable int pageIndex, @PathVariable int pageSize) {
      return this.userService.findByPage(pageIndex, pageSize);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping(
      path = {"/api/users/search/{pageIndex}/{pageSize}"}
   )
   public ResponseEntity searchUsers(@RequestBody UserFilterDto filter, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
      return filter == null ? new ResponseEntity(new PageImpl(new ArrayList(), PageRequest.of(pageIndex, pageSize), 0L), HttpStatus.BAD_REQUEST) : new ResponseEntity(this.userService.findAllPageable(filter, pageIndex, pageSize), HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping({"/api/users/simple/{pageIndex}/{pageSize}"})
   public Page<UserDto> findByPageBasicInfo(@PathVariable int pageIndex, @PathVariable int pageSize) {
      return this.userService.findByPageBasicInfo(pageIndex, pageSize);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping({"/api/org/users/simple/{orgId}/{pageIndex}/{pageSize}"})
   public Page<UserDto> findByPageBasicInfoByOrg(@PathVariable UUID orgId, @PathVariable int pageIndex, @PathVariable int pageSize) {
      return this.userService.findByPageBasicInfoByOrg(orgId, pageIndex, pageSize);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PutMapping(
      path = {"/api/users"}
   )
   public UserDto updateUser(@RequestBody UserDto user) {
      return this.userService.save(user);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @PostMapping(
      path = {"/api/users"}
   )
   public UserDto saveUser(@RequestBody UserDto user) {
      return this.userService.save(user);
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @DeleteMapping({"/api/users/{userId}"})
   public UserDto removeUser(@PathVariable("userId") String userId) {
      return this.userService.deleteById(Long.valueOf(userId));
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @DeleteMapping({"/api/users"})
   public ResponseEntity<UserDto> removeUser(@RequestBody UserDto dto) {
      if (dto != null && dto.getId() != null) {
         Long userId = dto.getId();
         dto = this.userService.deleteById(Long.valueOf(userId));
         return new ResponseEntity(dto, HttpStatus.OK);
      } else {
         return new ResponseEntity(new UserDto(), HttpStatus.BAD_REQUEST);
      }
   }

   @PreAuthorize("isAuthenticated()")
   @GetMapping({"/api/users/getCurrentUser"})
   public UserDto getCurrentUser() {
      UserDto user = this.userService.getCurrentUser();
      if (user != null) {
         user.setPassword((String)null);
      }

      return user;
   }

   @PreAuthorize("isAuthenticated()")
   @GetMapping({"/api/users/updateprofile"})
   public UserDto updateCurrentUser(UserDto userInfo) {
      UserDto user = this.userService.getCurrentUser();
      userInfo.setId(user.getId());
      this.userService.save(userInfo);
      return user;
   }

   @GetMapping({"/api/users/heartbeat"})
   public void ping() {
      System.out.println("User session monitored...");
   }

   @Secured({"ROLE_ADMIN", "ROLE_FILE_UPLOAD_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/api/users/updateavatar"},
      method = {RequestMethod.POST},
      consumes = {"multipart/form-data"}
   )
   public UserDto updateAvatar(@RequestParam("uploadfile") MultipartFile file) {
      User user = this.userService.getCurrentEntityUser();

      try {
         byte[] bytes = file.getBytes();
         FileDescription fileDesc = new FileDescription();
         fileDesc.setId(UUID.randomUUID());
         fileDesc.setContentSize(file.getSize());
         fileDesc.setContentType(file.getContentType());
         String filePath = fileDesc.getId().toString();
         if (Constants.FileUploadPath != null) {
            if (Constants.FileUploadPath.endsWith("/")) {
               filePath = Constants.FileUploadPath + filePath;
            } else {
               filePath = Constants.FileUploadPath + "/" + filePath;
            }
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
         if (user.getPerson() != null) {
            String imagePath = "/public/file/downloadbyid/" + fileDesc.getId();
            user.getPerson().setImagePath(imagePath);
            user = (User)this.userService.save(user);
            return new UserDto(user);
         } else {
            return null;
         }
      } catch (IOException var9) {
         var9.printStackTrace();
         return null;
      }
   }

   @GetMapping({"/public/users/photo/{username}"})
   public void getProfilePhoto(HttpServletResponse response, @PathVariable("username") String username) throws ServletException, IOException {
      byte[] data = this.userService.getProfilePhoto(username);
      response.setContentType("image/jpg");

      try {
         response.getOutputStream().write(data);
         response.flushBuffer();
         response.getOutputStream().close();
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @RequestMapping(
      path = {"/api/users/password"},
      method = {RequestMethod.PUT}
   )
   public ResponseEntity<UserDto> changePassword(@RequestBody UserDto user) {
      return new ResponseEntity(this.userService.changePassword(user), HttpStatus.OK);
   }

   @PreAuthorize("isAuthenticated()")
   @RequestMapping(
      path = {"/api/users/password/self"},
      method = {RequestMethod.PUT}
   )
   public ResponseEntity<UserDto> changeMyPassword(@RequestBody UserDto dto) {
      Boolean isValidated = dto.getPassword() != null || dto.getOldPassword() != null;
      isValidated = isValidated && dto.getConfirmPassword() != null;
      User user = SecurityUtils.getCurrentUser();
      if (user == null) {
         return new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN);
      } else {
         isValidated = isValidated && !StringUtils.isEmpty(dto.getConfirmPassword());
         isValidated = isValidated && !StringUtils.isEmpty(dto.getPassword());
         isValidated = isValidated && !StringUtils.isEmpty(dto.getOldPassword());
         isValidated = isValidated && dto.getPassword().equals(dto.getConfirmPassword());
         if (!isValidated) {
            return new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN);
         } else if (!user.getUsername().equals(dto.getUsername())) {
            return new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN);
         } else if (dto.getId() != null && !dto.getId().equals(user.getId())) {
            return new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN);
         } else {
            return !SecurityUtils.passwordsMatch(user.getPassword(), dto.getOldPassword()) ? new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN) : new ResponseEntity(this.userService.changePassword(dto), HttpStatus.OK);
         }
      }
   }

   @PreAuthorize("isAuthenticated()")
   @RequestMapping(
      path = {"/api/users/password/valid"},
      method = {RequestMethod.POST}
   )
   public ResponseEntity<Boolean> passwordValid(@RequestBody PasswordChangeDto dto) {
      if (dto == null) {
         return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
      } else {
         User user = SecurityUtils.getCurrentUser();
         if (user == null) {
            return new ResponseEntity(false, HttpStatus.FORBIDDEN);
         } else {
            Boolean matched = SecurityUtils.passwordsMatch(user.getPassword(), dto.getPassword());
            return new ResponseEntity(matched, HttpStatus.OK);
         }
      }
   }

   @PreAuthorize("isAuthenticated()")
   @PostMapping(
      path = {"/api/users/photo/upload"}
   )
   public ResponseEntity<UserDto> uploadProfilePhoto(@RequestParam("file") MultipartFile file) {
      User user = SecurityUtils.getCurrentUser();
      UserDto userDto = new UserDto();
      userDto.setId(user.getId());
      if (Constants.UserImageFolder == null) {
         Constants.UserImageFolder = this.env.getProperty("user.userImagefolder");
      }

      try {
         if (!file.isEmpty()) {
            int beginIndex = file.getOriginalFilename().indexOf(46);
            String extension = null;
            if (beginIndex > 0) {
               extension = file.getOriginalFilename().substring(beginIndex);
            }

            String filePath = Constants.UserImageFolder + user.getUsername();
            if (extension != null) {
               filePath = filePath + extension;
            }

            FileOutputStream stream = new FileOutputStream(filePath);

            try {
               stream.write(file.getBytes());
            } finally {
               stream.close();
            }

            User updateUser = this.userService.findEntityByUsername(user.getUsername());
            updateUser.getPerson().setImagePath(filePath);
            if (updateUser.getPerson().getCreateDate() == null) {
               updateUser.getPerson().setCreateDate(LocalDateTime.now());
            }

            this.userService.save(updateUser);
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return new ResponseEntity(userDto, HttpStatus.OK);
   }

   @PreAuthorize("isAuthenticated()")
   @RequestMapping(
      path = {"/api/users/photo/crop"},
      method = {RequestMethod.POST}
   )
   public ResponseEntity<UserDto> cropProfilePhoto(@RequestBody PhotoCropperDto dto) {
      User user = SecurityUtils.getCurrentUser();
      if (dto.getUser() != null && user.getUsername().equals(dto.getUser().getUsername())) {
         byte[] userPhoto = this.userService.getProfilePhoto(dto.getUser().getUsername());
         if (userPhoto != null && userPhoto.length > 0 && dto.getX() >= 0 && dto.getY() >= 0 && dto.getW() > 0 && dto.getH() > 0) {
            userPhoto = ImageUtils.crop(userPhoto, dto.getX(), dto.getY(), dto.getW(), dto.getH());
            if (userPhoto == null) {
               return new ResponseEntity(new UserDto(), HttpStatus.BAD_REQUEST);
            } else {
               UserDto userDto = this.userService.findByUserId(user.getId());
               PersonDto personDto = userDto.getPerson();
               if (personDto == null) {
                  return new ResponseEntity(userDto, HttpStatus.BAD_REQUEST);
               } else {
                  try {
                     User updateUser = this.userService.findEntityByUsername(user.getUsername());
                     FileUtils.saveFile(updateUser.getPerson().getImagePath(), userPhoto);
                     updateUser.setModifyDate(LocalDateTime.now());
                     this.userService.save(updateUser);
                  } catch (Exception var7) {
                     var7.printStackTrace();
                  }

                  return new ResponseEntity(userDto, HttpStatus.OK);
               }
            }
         } else {
            return new ResponseEntity(new UserDto(), HttpStatus.BAD_REQUEST);
         }
      } else {
         return new ResponseEntity(new UserDto(), HttpStatus.FORBIDDEN);
      }
   }

   @PreAuthorize("hasRole('ROLE_ADMIN')")
   @RequestMapping(
      path = {"/api/users/username/{username}/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   public ResponseEntity<Page<UserDto>> findByPageUsername(@PathVariable("username") String username, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
      return new ResponseEntity(this.userService.findByPageUsername(username, pageIndex, pageSize), HttpStatus.OK);
   }

   @PreAuthorize("isAuthenticated()")
   @PostMapping(
      path = {"/api/users/email_in_use"}
   )
   public ResponseEntity<Boolean> emailInUse(@RequestBody UserDto dto) {
      return dto != null && !CommonUtils.isEmpty(dto.getEmail()) ? new ResponseEntity(this.userService.emailAlreadyUsed(dto), HttpStatus.OK) : new ResponseEntity(Boolean.TRUE, HttpStatus.BAD_REQUEST);
   }
}
