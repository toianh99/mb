package com.xat.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
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
