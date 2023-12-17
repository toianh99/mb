package com.xat.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Inheritance(
   strategy = InheritanceType.SINGLE_TABLE
)
@Table(
   name = "tbl_organization"
)
public class Organization extends BaseObject {
   private static final long serialVersionUID = -994850132471679163L;
   @Column(
      name = "code",
      unique = true
   )
   private String code;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "website"
   )
   private String website;
   @Column(
      name = "organization_type"
   )
   private Integer organizationType;
   private Integer level;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "parent_id"
   )
   private Organization parent;
   @JsonIgnore
   @OneToMany(
      mappedBy = "parent"
   )
   private Set<Organization> subOrganizations;
   @OneToMany(
      mappedBy = "organization",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   private Set<OrganizationUser> users;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getOrganizationType() {
      return this.organizationType;
   }

   public void setOrganizationType(Integer organizationType) {
      this.organizationType = organizationType;
   }

   public Organization getParent() {
      return this.parent;
   }

   public void setParent(Organization parent) {
      this.parent = parent;
   }

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public String getWebsite() {
      return this.website;
   }

   public void setWebsite(String website) {
      this.website = website;
   }

   public Set<Organization> getSubOrganizations() {
      return this.subOrganizations;
   }

   public void setSubOrganizations(Set<Organization> subOrganizations) {
      this.subOrganizations = subOrganizations;
   }

   public Set<OrganizationUser> getUsers() {
      return this.users;
   }

   public void setUsers(Set<OrganizationUser> users) {
      this.users = users;
   }

   public Organization() {
   }

   public Organization(UUID id, String name, String code, Integer organizationType) {
      this.setId(id);
      this.setCode(code);
      this.setName(name);
      this.organizationType = organizationType;
   }
}
