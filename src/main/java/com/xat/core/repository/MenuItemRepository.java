package com.xat.core.repository;

import com.xat.core.domain.MenuItem;
import com.xat.core.dto.MenuItemDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
   @Query("select new com.xat.core.dto.MenuItemDto(m,true) from MenuItem m where m.id in (select eRole.menuItem.id from MenuItemRole eRole  where eRole.role.id in ?1) and m.parent=null order by m.code")
   List<MenuItemDto> getAllMenuItemByRoleList(List<Long> roleIds);

   @Query("select new com.xat.core.dto.MenuItemDto(e,true) from MenuItem e  order by code")
   List<MenuItemDto> getAllMenuItem();

   @Query("select new com.xat.core.dto.MenuItemDto(e,true) from MenuItem e where e.parent=null order by code")
   List<MenuItemDto> getAllRootItem();

   @Query("from MenuItem e where e.parent=null order by code")
   List<MenuItem> getAllRootEntity();

   @Query("select new com.xat.core.dto.MenuItemDto(e,true) from MenuItem e where  (?1=null or e.name like ?1) order by code")
   Page<MenuItemDto> searchByPage(String keyword, Pageable pageable);
}
