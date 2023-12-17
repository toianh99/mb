package com.xat.core.service;

import com.globits.core.domain.MenuItem;
import com.globits.core.dto.CommonSearchObjectDto;
import com.globits.core.dto.MenuItemDto;
import com.globits.core.dto.ResultMessageDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface MenuItemService extends GenericService<MenuItem, UUID> {
   MenuItemDto saveMenuItem(MenuItemDto dto);

   List<MenuItemDto> getAllMenuItem();

   List<MenuItemDto> getAllRootItem();

   Page<MenuItemDto> searchByPage(CommonSearchObjectDto searchObject);

   List<MenuItemDto> getAllMenuItemByRoleList();

   List<MenuItemDto> getFlatRootChild();

   ResultMessageDto deleteMenuItem(UUID id);
}
