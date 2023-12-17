package com.xat.core.domain;

import com.xat.core.security.domain.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_menu_item_role"
)
public class MenuItemRole extends BaseObject {
   private static final long serialVersionUID = 1L;
   @ManyToOne
   @JoinColumn(
      name = "item_id"
   )
   private MenuItem menuItem;
   @ManyToOne
   @JoinColumn(
      name = "role_id"
   )
   private Role role;

   public MenuItem getMenuItem() {
      return this.menuItem;
   }

   public void setMenuItem(MenuItem menuItem) {
      this.menuItem = menuItem;
   }

   public Role getRole() {
      return this.role;
   }

   public void setRole(Role role) {
      this.role = role;
   }
}
