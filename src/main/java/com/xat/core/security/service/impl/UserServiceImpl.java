package com.xat.core.security.service.impl;

import com.xat.core.Constants;
import com.xat.core.domain.Ethnics;
import com.xat.core.domain.Organization;
import com.xat.core.domain.Person;
import com.xat.core.dto.ActivityLogDto;
import com.xat.core.dto.PersonDto;
import com.xat.core.repository.EthnicsRepository;
import com.xat.core.repository.OrganizationRepository;
import com.xat.core.repository.PersonRepository;
import com.xat.core.service.ActivityLogService;
import com.xat.core.service.impl.GenericServiceImpl;
import com.xat.core.utils.CommonUtils;
import com.xat.core.utils.SecurityUtils;
import com.xat.core.utils.SerializableUtil;
import com.xat.core.security.domain.Role;
import com.xat.core.security.domain.User;
import com.xat.core.security.domain.UserGroup;
import com.xat.core.security.dto.RoleDto;
import com.xat.core.security.dto.UserDto;
import com.xat.core.security.dto.UserFilterDto;
import com.xat.core.security.dto.UserGroupDto;
import com.xat.core.security.repository.RoleRepository;
import com.xat.core.security.repository.UserGroupRepository;
import com.xat.core.security.repository.UserRepository;
import com.xat.core.security.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private UserGroupRepository groupRepos;
   @Autowired
   private RoleRepository roleRepos;
   @Autowired
   private PersonRepository personRepos;
   @Autowired
   private EthnicsRepository ethnicsRepos;
   @Autowired
   private OrganizationRepository organizationRepos;
   @Autowired
   private ActivityLogService activityLogService;

   @Transactional(
      readOnly = true
   )
   public UserDto findByUserId(Long userId) {
      User user = (User)this.userRepository.getOne(userId);
      return user != null ? new UserDto(user) : null;
   }

   public UserDto deleteById(Long userId) {
      User user = (User)this.userRepository.getOne(userId);
      if (user != null) {
         ActivityLogDto activityLog = new ActivityLogDto();
         String contentLog = "Delete User:" + user.getUsername();
         activityLog.setLogType(Constants.ActionLogTypeEnum.Delete.getValue());

         try {
            String objectContent = SerializableUtil.toString(user);
            if (objectContent != null && objectContent.length() < 10000) {
               contentLog = objectContent;
            }
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         activityLog.setContentLog(contentLog);
         this.activityLogService.saveActivityLog(activityLog);
         this.userRepository.delete(user);
         return new UserDto(user);
      } else {
         return null;
      }
   }

   @Transactional(
      readOnly = true
   )
   public UserDto findByUsername(String username) {
      User user = this.userRepository.findByUsername(username);
      return user != null ? new UserDto(user) : null;
   }

   @Transactional(
      readOnly = true
   )
   public UserDto findByEmail(String email) {
      User retUser = this.userRepository.findByEmail(email);
      if (retUser != null) {
         UserDto dto = new UserDto(retUser);
         dto.setPassword((String)null);
         return dto;
      } else {
         return null;
      }
   }

   @Transactional(
      readOnly = true
   )
   public Page<UserDto> findByPage(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<User> page = this.userRepository.findAll(pageable);
      List<User> _content = page.getContent();
      List<UserDto> content = new ArrayList();
      Iterator var7 = _content.iterator();

      while(var7.hasNext()) {
         User entity = (User)var7.next();
         content.add(new UserDto(entity));
      }

      return new PageImpl(content, pageable, page.getTotalElements());
   }

   @Transactional(
      readOnly = true
   )
   public Page<UserDto> findByPageBasicInfo(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<User> page = this.userRepository.findByPageBasicInfo(pageable);
      List<User> _content = page.getContent();
      List<UserDto> content = new ArrayList();
      Iterator var7 = _content.iterator();

      while(var7.hasNext()) {
         User entity = (User)var7.next();
         content.add(new UserDto(entity));
      }

      return new PageImpl(content, pageable, page.getTotalElements());
   }

   @Transactional(
      readOnly = true
   )
   public Page<UserDto> searchByPageBasicInfo(int pageIndex, int pageSize, String username) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<User> page = this.userRepository.searchByPageBasicInfo(pageable, username);
      List<User> _content = page.getContent();
      List<UserDto> content = new ArrayList();
      Iterator var8 = _content.iterator();

      while(var8.hasNext()) {
         User entity = (User)var8.next();
         content.add(new UserDto(entity));
      }

      return new PageImpl(content, pageable, page.getTotalElements());
   }

   @Transactional(
      readOnly = true
   )
   public Page<UserDto> findAllPageable(final UserFilterDto filter, int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      String sql = "from User u";
      String sqlCount = "select count(distinct u.id) from User u ";
      String clause = " where (1=1)";
      if (!CommonUtils.isEmpty(filter.getKeyword())) {
         clause = clause + " and (u.username like :keyword or u.email like :keyword or (u.person!=null and u.person.displayName like :keyword))";
      }

      if (filter.getActive() != null) {
         clause = clause + " and (u.active = :active)";
      }

      List<Long> roleIds = new ArrayList();
      int var11;
      if (filter.getRoles() != null && filter.getRoles().length > 0) {
         RoleDto[] var9 = filter.getRoles();
         int var10 = var9.length;

         for(var11 = 0; var11 < var10; ++var11) {
            RoleDto dto = var9[var11];
            if (CommonUtils.isPositive(dto.getId(), true)) {
               roleIds.add(dto.getId());
            }
         }

         sql = sql + " join fetch u.roles roles ";
         sqlCount = sqlCount + " join u.roles roles ";
         clause = clause + " and roles.id in :roleIds";
      }

      List<Long> groupIds = new ArrayList();
      int startPosition;
      if (filter.getGroups() != null && filter.getGroups().length > 0) {
         UserGroupDto[] var19 = filter.getGroups();
         var11 = var19.length;

         for(startPosition = 0; startPosition < var11; ++startPosition) {
            UserGroupDto dto = var19[startPosition];
            if (CommonUtils.isPositive(dto.getId(), true)) {
               groupIds.add(dto.getId());
            }
         }

         sql = sql + " join fetch u.groups groups";
         sqlCount = sqlCount + " join u.groups groups";
         clause = clause + " and groups.id in :groupIds";
      }

      sql = sql + clause;
      sqlCount = sqlCount + clause;
      TypedQuery<User> q = this.manager.createQuery(sql, User.class);
      Query qCount = this.manager.createQuery(sqlCount);
      if (!CommonUtils.isEmpty(filter.getKeyword())) {
         q.setParameter("keyword", '%' + filter.getKeyword() + '%');
         qCount.setParameter("keyword", '%' + filter.getKeyword() + '%');
      }

      if (filter.getActive() != null) {
         q.setParameter("active", filter.getActive());
         qCount.setParameter("active", filter.getActive());
      }

      if (filter.getRoles() != null && filter.getRoles().length > 0) {
         q.setParameter("roleIds", roleIds);
         qCount.setParameter("roleIds", roleIds);
      }

      if (filter.getGroups() != null && filter.getGroups().length > 0) {
         q.setParameter("groupIds", groupIds);
         qCount.setParameter("groupIds", groupIds);
      }

      startPosition = (pageIndex - 1) * pageSize;
      q.setFirstResult(startPosition);
      q.setMaxResults(pageSize);
      List<User> users = q.getResultList();
      Long numberResult = (Long)qCount.getSingleResult();
      List<UserDto> userDtos = new ArrayList();
      Iterator var16 = users.iterator();

      while(var16.hasNext()) {
         User u = (User)var16.next();
         userDtos.add(new UserDto(u));
      }

      return new PageImpl(userDtos, pageable, numberResult);
   }

   @Transactional(
      readOnly = true
   )
   public UserDto getCurrentUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Object principal = authentication.getPrincipal();
      String userName = null;
      if (principal instanceof UserDetails) {
         UserDetails userDetail = (UserDetails)principal;
         userName = userDetail.getUsername();
      } else {
         userName = principal.toString();
      }

      if (userName != null) {
         User entity = this.userRepository.findByUsernameAndPerson(userName);
         if (entity != null) {
            return new UserDto(entity);
         }
      }

      return null;
   }

   @Transactional(
      readOnly = true,
      isolation = Isolation.READ_UNCOMMITTED
   )
   public UserDto getKeyCloakCurrentUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Jwt jwt = null;
      jwt = (Jwt)authentication.getPrincipal();
      String userName = null;
      if (jwt.getClaims() != null && jwt.getClaims().get("preferred_username") != null) {
         userName = jwt.getClaims().get("preferred_username").toString();
      }

      User modifiedUser = this.userRepository.findByUsernameAndPerson(userName);
      return modifiedUser != null ? new UserDto(modifiedUser) : null;
   }

   @Transactional(
      readOnly = true
   )
   public User getCurrentEntityUser() {
      Object principal = SecurityUtils.getPrincipal();
      String userName = null;
      if (principal instanceof UserDetails) {
         UserDetails userDetail = (UserDetails)principal;
         userName = userDetail.getUsername();
      } else {
         userName = principal.toString();
      }

      if (userName != null) {
         User entity = this.userRepository.findByUsernameAndPerson(userName);
         return entity;
      } else {
         return null;
      }
   }

   @Transactional(
      readOnly = true
   )
   public boolean isUserInOrg(UUID orgId) {
      User user = this.getCurrentEntityUser();
      return user != null && user.getOrg() != null && user.getOrg().getId() != null && orgId != null ? user.getOrg().getId().equals(orgId) : false;
   }

   @Transactional(
      readOnly = true
   )
   public boolean isUserInOrg(User user, UUID orgId) {
      return user != null && user.getOrg() != null && user.getOrg().getId() != null && orgId != null ? user.getOrg().getId().equals(orgId) : false;
   }

   @Transactional(
      readOnly = true
   )
   public byte[] getProfilePhoto(String username) {
      if (username != null && !username.trim().isEmpty()) {
         User user = this.userRepository.findByUsernameAndPerson(username);
         if (user != null && user.getPerson() != null && user.getPerson().getPhoto() != null) {
            return user.getPerson().getPhoto();
         } else {
            if (user.getPerson() != null && user.getPerson().getImagePath() != null) {
               String filePath = user.getPerson().getImagePath();

               try {
                  File file = new File(filePath);
                  byte[] data = new byte[(int)file.length()];
                  InputStream is = new FileInputStream(file);
                  is.read(data);
                  is.close();
                  return data;
               } catch (FileNotFoundException var7) {
                  var7.printStackTrace();
               } catch (IOException var8) {
                  var8.printStackTrace();
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public User saveUser(UserDto userDto) {
      if (userDto == null) {
         throw new IllegalArgumentException();
      } else {
         User user = null;
         if (CommonUtils.isPositive(userDto.getId(), true)) {
            user = (User)this.userRepository.getOne(userDto.getId());
         }

         if (user == null) {
            user = userDto.toEntity();
            user.setJustCreated(true);
            if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
               user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
            }
         } else {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
               user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
            }
         }

         ArrayList gs;
         Iterator var4;
         if (userDto.getRoles() != null) {
            gs = new ArrayList();
            var4 = userDto.getRoles().iterator();

            while(var4.hasNext()) {
               RoleDto d = (RoleDto)var4.next();
               Role r = (Role)this.roleRepos.getOne(d.getId());
               if (r != null) {
                  gs.add(r);
               }
            }

            user.getRoles().clear();
            user.getRoles().addAll(gs);
         }

         if (userDto.getGroups() != null) {
            gs = new ArrayList();
            var4 = userDto.getGroups().iterator();

            while(var4.hasNext()) {
               UserGroupDto d = (UserGroupDto)var4.next();
               UserGroup g = (UserGroup)this.groupRepos.getOne(d.getId());
               if (g != null) {
                  gs.add(g);
               }
            }

            user.getGroups().clear();
            user.getGroups().addAll(gs);
         }

         PersonDto personDto = userDto.getPerson();
         Person person = null;
         if (personDto != null && personDto.getId() != null) {
            person = (Person)this.personRepos.getOne(personDto.getId());
         }

         if (person != null) {
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setDisplayName(personDto.getDisplayName());
            person.setBirthDate(personDto.getBirthDate());
            person.setBirthPlace(personDto.getBirthPlace());
            person.setEmail(personDto.getEmail());
            person.setEndDate(personDto.getEndDate());
            person.setPhoneNumber(personDto.getPhoneNumber());
            if (personDto.getEthnics() != null && personDto.getEthnics().getId() != null) {
               Ethnics e = (Ethnics)this.ethnicsRepos.getOne(personDto.getEthnics().getId());
               if (e != null) {
                  person.setEthnics(e);
               }
            }

            person.setGender(personDto.getGender());
            person.setIdNumber(personDto.getIdNumber());
            person.setIdCitizen(personDto.getIdCitizen());
            person.setIdNumberIssueBy(personDto.getIdNumberIssueBy());
            person.setIdNumberIssueDate(personDto.getIdNumberIssueDate());
         } else if (personDto != null) {
            person = personDto.toEntity();
         }

         if (person != null) {
            user.setPerson(person);
//            person.setUser(user);
         }

         if (userDto.getActive() != null) {
            user.setActive(userDto.getActive());
         } else {
            user.setActive(false);
         }

         if (userDto.getOrg() != null) {
            user.setOrg(new Organization());
            user.getOrg().setId(userDto.getOrg().getId());
         }

         user = (User)this.userRepository.save(user);
         return user;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public UserDto save(UserDto userDto) {
      if (userDto == null) {
         throw new IllegalArgumentException();
      } else {
         User user = null;
         if (userDto.getId() != null) {
            user = (User)this.userRepository.getOne(userDto.getId());
         }

         if (user == null) {
            user = userDto.toEntity();
            user.setJustCreated(true);
            if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
               user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
            }
         } else {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            if (userDto.getPassword() != null && userDto.getPassword().length() > 0 && userDto.getChangePass()) {
               user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
            }
         }

         if (userDto.getOrg() != null && userDto.getOrg().getId() != null) {
            Organization org = (Organization)this.organizationRepos.getOne(userDto.getOrg().getId());
            user.setOrg(org);
         }

         Iterator var4;
         ArrayList gs;
         if (userDto.getRoles() != null) {
            gs = new ArrayList();
            var4 = userDto.getRoles().iterator();

            while(var4.hasNext()) {
               RoleDto d = (RoleDto)var4.next();
               Role r = (Role)this.roleRepos.getOne(d.getId());
               if (r != null) {
                  gs.add(r);
               }
            }

            user.getRoles().clear();
            user.getRoles().addAll(gs);
         }

         if (userDto.getGroups() != null) {
            gs = new ArrayList();
            var4 = userDto.getGroups().iterator();

            while(var4.hasNext()) {
               UserGroupDto d = (UserGroupDto)var4.next();
               UserGroup g = (UserGroup)this.groupRepos.getOne(d.getId());
               if (g != null) {
                  gs.add(g);
               }
            }

            user.getGroups().clear();
            user.getGroups().addAll(gs);
         }

         PersonDto personDto = userDto.getPerson();
         Person person = null;
         if (personDto != null && personDto.getId() != null) {
            person = (Person)this.personRepos.getOne(personDto.getId());
         }

         if (person != null) {
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setDisplayName(personDto.getDisplayName());
            person.setBirthDate(personDto.getBirthDate());
            person.setBirthPlace(personDto.getBirthPlace());
            person.setEmail(personDto.getEmail());
            person.setEndDate(personDto.getEndDate());
            person.setPhoneNumber(personDto.getPhoneNumber());
            if (personDto.getEthnics() != null && personDto.getEthnics().getId() != null) {
               Ethnics e = (Ethnics)this.ethnicsRepos.getOne(personDto.getEthnics().getId());
               if (e != null) {
                  person.setEthnics(e);
               }
            }

            person.setGender(personDto.getGender());
            person.setIdNumber(personDto.getIdNumber());
            person.setIdCitizen(personDto.getIdCitizen());
            person.setIdNumberIssueBy(personDto.getIdNumberIssueBy());
            person.setIdNumberIssueDate(personDto.getIdNumberIssueDate());
         } else if (personDto != null) {
            person = personDto.toEntity();
         }

         user.setPerson(person);
         if (person != null) {
//            person.setUser(user);
         }

         user.setActive(userDto.getActive());
         ActivityLogDto activityLog = new ActivityLogDto();
         String contentLog = "Update User:" + user.getUsername();
         String entityObjectType = user.getClass().getName();
         activityLog.setEntityObjectType(entityObjectType);
         activityLog.setLogType(Constants.ActionLogTypeEnum.SaveOrUpdate.getValue());

         try {
            String objectContent = SerializableUtil.toString(user);
            if (objectContent != null && objectContent.length() < 10000) {
               contentLog = objectContent;
            }
         } catch (IOException var9) {
            var9.printStackTrace();
         }

         activityLog.setContentLog(contentLog);
         this.activityLogService.saveActivityLog(activityLog);
         user = (User)this.userRepository.save(user);
         return user != null ? new UserDto(user) : null;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public UserDto savePhoto(UserDto dto) {
      if (dto == null) {
         throw new RuntimeException();
      } else {
         User user = null;
         if (dto.getId() != null && dto.getId() > 0L) {
            user = (User)this.userRepository.getOne(dto.getId());
         }

         if (user == null) {
            throw new RuntimeException();
         } else {
            Person person = user.getPerson();
            if (person == null) {
               person = new Person();
            }

            person.setPhoto(dto.getPerson().getPhoto());
            person.setPhotoCropped(dto.getPerson().getPhotoCropped());
//            person.setUser(user);
            user.setPerson(person);
            ActivityLogDto activityLog = new ActivityLogDto();
            activityLog.setContentLog("Update user photo:" + user.getUsername());
            activityLog.setLogType(Constants.ActionLogTypeEnum.SaveOrUpdate.getValue());
            this.activityLogService.saveActivityLog(activityLog);
            user = (User)this.userRepository.save(user);
            if (user != null) {
               return new UserDto(user);
            } else {
               throw new RuntimeException();
            }
         }
      }
   }

   @Transactional(
      readOnly = true
   )
   public boolean passwordMatch(UserDto dto) {
      if (dto != null && CommonUtils.isPositive(dto.getId(), true)) {
         User user = (User)this.userRepository.getOne(dto.getId());
         return user != null && SecurityUtils.passwordsMatch(user.getPassword(), dto.getPassword());
      } else {
         return false;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public UserDto changePassword(UserDto dto) {
      if (dto != null && CommonUtils.isPositive(dto.getId(), true) && !CommonUtils.isEmpty(dto.getPassword())) {
         User user = (User)this.userRepository.getOne(dto.getId());
         if (user == null) {
            return null;
         } else {
            user.setPassword(SecurityUtils.getHashPassword(dto.getPassword()));
            ActivityLogDto activityLog = new ActivityLogDto();
            activityLog.setContentLog("Change password:" + user.getUsername());
            activityLog.setLogType(Constants.ActionLogTypeEnum.SaveOrUpdate.getValue());
            this.activityLogService.saveActivityLog(activityLog);
            user = (User)this.userRepository.save(user);
            return user == null ? null : new UserDto(user);
         }
      } else {
         return null;
      }
   }

   public Page<UserDto> findByPageUsername(String username, int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex, pageSize);
      Page<UserDto> page = this.userRepository.findByPageUsername(username, pageable);
      return page;
   }

   @Transactional(
      readOnly = true
   )
   public boolean emailAlreadyUsed(UserDto dto) {
      if (dto != null && !CommonUtils.isEmpty(dto.getEmail())) {
         User user = this.userRepository.findByEmail(dto.getEmail());
         return user != null;
      } else {
         return false;
      }
   }

   @Transactional
   public User updateUserLastLogin(Long userId) {
      User user = (User)this.userRepository.getOne(userId);
      user.setLastLoginTime(new Date());
      return (User)this.userRepository.save(user);
   }

   public User findEntityByUsername(String username) {
      User user = this.userRepository.findByUsername(username);
      return user;
   }

   public Page<UserDto> findByPageBasicInfoByOrg(UUID orgId, int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      return this.userRepository.findByPageBasicInfoByOrg(orgId, pageable);
   }

   public void recordLoginFailure(Authentication authentication) {
      User user = this.userRepository.findByUsername(authentication.getPrincipal().toString());
      LocalDateTime now = LocalDateTime.now();
      if (user != null) {
         Long totalLoginFailures = user.getTotalLoginFailures();
         if (totalLoginFailures == null) {
            totalLoginFailures = 0L;
         }

         totalLoginFailures = totalLoginFailures + 1L;
         user.setTotalLoginFailures(totalLoginFailures);
         user.setLastLoginTime(now.toDate());
         this.userRepository.save(user);
      }

   }
}
