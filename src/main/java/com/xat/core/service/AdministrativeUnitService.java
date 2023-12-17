package com.xat.core.service;

import com.xat.core.domain.AdministrativeUnit;
import com.xat.core.dto.AdministrativeUnitDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface AdministrativeUnitService extends GenericService<AdministrativeUnit, UUID> {
   AdministrativeUnitDto saveAdministrativeUnit(AdministrativeUnitDto administrativeUnit);

   AdministrativeUnit updateAdministrativeUnit(AdministrativeUnit department, UUID departmentId);

   Page<AdministrativeUnit> getListAdministrativeUnitByTree(int pageIndex, int pageSize);

   Page<AdministrativeUnit> getPageRootAdministrativeUnit(int pageIndex, int pageSize);

   Page<AdministrativeUnit> getPageByParentId(UUID parentId, int pageIndex, int pageSize);

   int deleteAdministrativeUnits(List<AdministrativeUnitDto> dtos);

   Page<AdministrativeUnitDto> getListTree(Integer pageIndex, Integer pageSize);

   Page<AdministrativeUnitDto> findByPageBasicInfo(int pageIndex, int pageSize);

   AdministrativeUnitDto deleteAdministrativeUnit(UUID id);

   AdministrativeUnit findByCode(String code);

   List<AdministrativeUnitDto> getListRootAdministrativeUnitDto();

   List<AdministrativeUnitDto> getListDtoByParentId(UUID parentId);
}
