package com.xat.core.domain;

import com.globits.security.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(
   name = "tbl_organization_user"
)
@XmlRootElement
public class OrganizationUser extends BaseObject {
   private static final long serialVersionUID = 2174258240790039379L;
   @ManyToOne
   @JoinColumn(
      name = "organization_id"
   )
   private Organization organization;
   @ManyToOne
   @JoinColumn(
      name = "user_id"
   )
   @NotFound(
      action = NotFoundAction.IGNORE
   )
   private User user;
   @Column(
      name = "is_admin_user"
   )
   private Boolean isAdminUser;

   public Organization getOrganization() {
      return this.organization;
   }

   public void setOrganization(Organization organization) {
      this.organization = organization;
   }

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Boolean getIsAdminUser() {
      return this.isAdminUser;
   }

   public void setIsAdminUser(Boolean isAdminUser) {
      this.isAdminUser = isAdminUser;
   }
}
