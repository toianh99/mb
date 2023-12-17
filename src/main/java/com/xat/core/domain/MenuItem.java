package com.xat.core.domain;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(
   name = "tbl_menu_item"
)
public class MenuItem extends BaseObject {
   private static final long serialVersionUID = 1L;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "code"
   )
   private String code;
   @Column(
      name = "description"
   )
   private String description;
   @Column(
      name = "path"
   )
   private String path;
   @Column(
      name = "icon"
   )
   private String icon;
   @Column(
      name = "view_index"
   )
   private Integer viewIndex;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "parent_id"
   )
   @NotFound(
      action = NotFoundAction.EXCEPTION
   )
   private MenuItem parent;
   @OneToMany(
      mappedBy = "parent",
      cascade = {CascadeType.ALL},
      fetch = FetchType.LAZY,
      orphanRemoval = true
   )
   @OrderBy("code")
   private Set<MenuItem> children;
   @OneToMany(
      mappedBy = "menuItem",
      cascade = {CascadeType.ALL},
      fetch = FetchType.EAGER,
      orphanRemoval = true
   )
   private Set<MenuItemRole> roles;
   @ManyToOne
   @JoinColumn(
      name = "org_id"
   )
   private Organization org;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getViewIndex() {
      return this.viewIndex;
   }

   public void setViewIndex(Integer viewIndex) {
      this.viewIndex = viewIndex;
   }

   public String getIcon() {
      return this.icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public MenuItem getParent() {
      return this.parent;
   }

   public void setParent(MenuItem parent) {
      this.parent = parent;
   }

   public Organization getOrg() {
      return this.org;
   }

   public void setOrg(Organization org) {
      this.org = org;
   }

   public Set<MenuItem> getChildren() {
      return this.children;
   }

   public void setChildren(Set<MenuItem> children) {
      this.children = children;
   }

   public Set<MenuItemRole> getRoles() {
      return this.roles;
   }

   public void setRoles(Set<MenuItemRole> roles) {
      this.roles = roles;
   }
}
