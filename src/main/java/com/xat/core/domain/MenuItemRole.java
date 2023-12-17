package com.xat.core.domain;

import com.globits.security.domain.Role;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(
   name = "tbl_menu_item_role"
)
@XmlRootElement
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
