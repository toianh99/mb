package com.xat.core.repository;

import com.xat.core.domain.MenuItemRole;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRoleRepository extends JpaRepository<MenuItemRole, UUID> {
   @Query("from MenuItemRole r where r.role.id=?1 and r.menuItem.id=?2")
   MenuItemRole findByRoleMenuId(Long roleId, UUID menuId);
}
