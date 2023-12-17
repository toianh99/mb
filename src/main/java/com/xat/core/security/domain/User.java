package com.xat.core.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.xat.core.auditing.AuditableEntity;
import com.xat.core.domain.Organization;
import com.xat.core.domain.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
   name = "tbl_user"
)
public class User extends AuditableEntity implements UserDetails {
   private static final long serialVersionUID = 4572941405687566992L;
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      name = "username",
      length = 100,
      nullable = false,
      unique = true
   )
   private String username;
   @Column(
      name = "password",
      nullable = true
   )
   private String password;
   @Column(
      name = "just_created",
      nullable = false
   )
   private Boolean justCreated = true;
   @Column(
      name = "last_login_time",
      nullable = true
   )
   private Date lastLoginTime;
   @Column(
      name = "total_login_failures",
      nullable = true
   )
   private Long totalLoginFailures;
   @Column(
      name = "last_login_failures",
      nullable = true
   )
   private Long lastLoginFailures;
   @Column(
      name = "email",
      length = 150,
      nullable = true,
      unique = false
   )
   private String email;
   @ManyToMany(
      fetch = FetchType.EAGER
   )
   @Fetch(FetchMode.SELECT)
   @JoinTable(
      name = "tbl_user_role",
      joinColumns = {@JoinColumn(
   name = "user_id"
)},
      inverseJoinColumns = {@JoinColumn(
   name = "role_id"
)}
   )
   private Set<Role> roles = new HashSet();
   @ManyToMany(
      fetch = FetchType.EAGER
   )
   @Fetch(FetchMode.SELECT)
   @JoinTable(
      name = "tbl_user_usergroup",
      joinColumns = {@JoinColumn(
   name = "user_id"
)},
      inverseJoinColumns = {@JoinColumn(
   name = "group_id"
)}
   )
   private Set<UserGroup> groups = new HashSet();
   @OneToOne(
      mappedBy = "user",
      fetch = FetchType.EAGER,
      optional = true,
      cascade = {CascadeType.ALL}
   )
   private Person person;
   @ManyToOne
   @JoinColumn(
      name = "org_id"
   )
   private Organization org;
   @Transient
   private Boolean changePassword = false;
   @Transient
   private String confirmPassword;
   @Column(
      name = "active",
      nullable = false
   )
   private Boolean active = true;
   @Column(
      name = "account_non_expired",
      nullable = true
   )
   private Boolean accountNonExpired = true;
   @Column(
      name = "account_non_locked",
      nullable = true
   )
   private Boolean accountNonLocked = true;
   @Column(
      name = "credentials_non_expired",
      nullable = true
   )
   private Boolean credentialsNonExpired = true;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Boolean getJustCreated() {
      return this.justCreated;
   }

   public void setJustCreated(Boolean justCreated) {
      this.justCreated = justCreated;
   }

   public Date getLastLoginTime() {
      return this.lastLoginTime;
   }

   public void setLastLoginTime(Date lastLoginTime) {
      this.lastLoginTime = lastLoginTime;
   }

   public Long getTotalLoginFailures() {
      return this.totalLoginFailures;
   }

   public void setTotalLoginFailures(Long totalLoginFailures) {
      this.totalLoginFailures = totalLoginFailures;
   }

   public Long getLastLoginFailures() {
      return this.lastLoginFailures;
   }

   public void setLastLoginFailures(Long lastLoginFailures) {
      this.lastLoginFailures = lastLoginFailures;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }

   public Boolean getActive() {
      return this.active;
   }

   public Set<Role> getRoles() {
      return this.roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public Set<UserGroup> getGroups() {
      return this.groups;
   }

   public void setGroups(Set<UserGroup> groups) {
      this.groups = groups;
   }

   public Boolean getAccountNonExpired() {
      return this.accountNonExpired;
   }

   public Boolean getAccountNonLocked() {
      return this.accountNonLocked;
   }

   public Boolean getCredentialsNonExpired() {
      return this.credentialsNonExpired;
   }

   public void setAccountNonExpired(Boolean accountNonExpired) {
      this.accountNonExpired = accountNonExpired;
   }

   public void setAccountNonLocked(Boolean accountNonLocked) {
      this.accountNonLocked = accountNonLocked;
   }

   public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
      this.credentialsNonExpired = credentialsNonExpired;
   }

   @JsonIgnore
   @Transient
   public Collection<GrantedAuthority> getAuthorities() {
      Set<GrantedAuthority> authorities = new HashSet();
      authorities.addAll(this.roles);
      return authorities;
   }

   public boolean isAccountNonExpired() {
      return this.accountNonExpired;
   }

   public boolean isAccountNonLocked() {
      return this.accountNonLocked;
   }

   public boolean isCredentialsNonExpired() {
      return this.credentialsNonExpired;
   }

   public boolean isEnabled() {
      return this.active;
   }

   public Boolean getChangePassword() {
      return this.changePassword;
   }

   public void setChangePassword(Boolean changePassword) {
      this.changePassword = changePassword;
   }

   public String getConfirmPassword() {
      return this.confirmPassword;
   }

   public void setConfirmPassword(String confirmPassword) {
      this.confirmPassword = confirmPassword;
   }

   public Person getPerson() {
      return this.person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public Organization getOrg() {
      return this.org;
   }

   public void setOrg(Organization org) {
      this.org = org;
   }

   public User() {
   }

   public User(Long id, String username, String email, Boolean accountNonLocked) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.accountNonLocked = accountNonLocked;
   }

   public User(Long id, String username, String email, Boolean accountNonLocked, Boolean accountNonExpired) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.accountNonLocked = accountNonLocked;
      this.accountNonExpired = accountNonExpired;
   }

   public User(User user, boolean isSetPerson) {
      this.accountNonExpired = user.isAccountNonExpired();
      this.accountNonLocked = user.isAccountNonLocked();
      this.active = user.getActive();
      this.changePassword = user.changePassword;
      this.confirmPassword = user.confirmPassword;
      this.credentialsNonExpired = user.isCredentialsNonExpired();
      this.email = user.getEmail();
      this.justCreated = user.getJustCreated();
      this.lastLoginFailures = user.getLastLoginFailures();
      this.lastLoginTime = user.getLastLoginTime();
      this.password = user.getPassword();
      this.changePassword = user.getChangePassword();
      this.confirmPassword = user.getConfirmPassword();
      this.username = user.getUsername();
      this.setId(user.getId());
      this.setCreateDate(user.getCreateDate());
      this.setCreatedBy(user.getCreatedBy());
      this.roles = user.getRoles();
      if (user.getPerson() != null && isSetPerson) {
         this.person = new Person();
         if (user.getPerson().getId() != null) {
            this.person.setId(user.getPerson().getId());
         }

         this.person.setFirstName(user.getPerson().getFirstName());
         this.person.setLastName(user.getPerson().getLastName());
         this.person.setDisplayName(user.getPerson().getDisplayName());
         this.person.setBirthDate(user.getPerson().getBirthDate());
         this.person.setBirthPlace(user.getPerson().getBirthPlace());
         this.person.setCarrer(user.getPerson().getCarrer());
         this.person.setGender(user.getPerson().getGender());
         this.person.setEmail(user.getPerson().getEmail());
//         this.person.setUser(user);
      }

   }
}
