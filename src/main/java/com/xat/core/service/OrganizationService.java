package com.xat.core.service;

import com.xat.core.domain.Organization;
import com.xat.core.dto.OrganizationDto;
import com.xat.core.dto.OrganizationUserDto;
import com.xat.core.dto.ResultMessageDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface OrganizationService extends GenericService<Organization, UUID> {
   OrganizationDto saveOrganization(OrganizationDto organization);

   OrganizationDto getOrganization(UUID id);

   OrganizationDto updateOrganization(Organization organization, UUID organizationId);

   Page<OrganizationDto> getListPageList(int pageIndex, int pageSize);

   Page<Organization> getListOrganizationByTree();

   OrganizationUserDto saveOrgnizationUser(OrganizationUserDto orgUserDto);

   ResultMessageDto deleteOrganizationUser(UUID id);

   List<Organization> findOrganizationByUserId(Long userId);

   Page<OrganizationDto> toDtoPage(Page<Organization> page);

   Page<OrganizationDto> getListRootTreeDataByPage(int pageIndex, int pageSize);

   ResultMessageDto deleteOrganization(UUID id);

   Page<OrganizationDto> getListRootTreeDataByPage(String keyword, int pageIndex, int pageSize);
}
