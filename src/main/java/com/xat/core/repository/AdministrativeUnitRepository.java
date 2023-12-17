package com.xat.core.repository;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.AdministrativeUnitDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, UUID> {
   @Query("select au from AdministrativeUnit au where au.parent=null")
   Page<AdministrativeUnit> getPageRootAdministrativeUnit(Pageable pageable);

   @Query("select new com.globits.core.dto.AdministrativeUnitDto(au,true) from AdministrativeUnit au where au.parent=null")
   List<AdministrativeUnitDto> getListRootAdministrativeUnitDto();

   @Query("select au from AdministrativeUnit au where  au.parent.id=?1")
   Page<AdministrativeUnit> getPageByParentId(UUID parentId, Pageable pageable);

   @Query("select new com.globits.core.dto.AdministrativeUnitDto(au,true) from AdministrativeUnit au where  au.parent.id=?1")
   List<AdministrativeUnitDto> getListDtoByParentId(UUID parentId);

   @Query("select e from AdministrativeUnit e   where  e.code=?1")
   AdministrativeUnit findByCode(String code);

   @Query("select e from AdministrativeUnit e   where  e.code=?1")
   List<AdministrativeUnit> findListByCode(String code);

   @Query("select COUNT(*) from AdministrativeUnit cs where cs.parent is null")
   Long countDadAdministrativeUnit();

   @Query("select cs from AdministrativeUnit cs left join fetch cs.subAdministrativeUnits where cs.parent is null  order by cs.code ASC")
   List<AdministrativeUnit> findTreeAdministrativeUnit(Pageable pageable);

   @Query("select new com.globits.core.dto.AdministrativeUnitDto(s,true) from AdministrativeUnit s")
   Page<AdministrativeUnitDto> findByPageBasicInfo(Pageable pageable);

   @Query("select au from AdministrativeUnit au where  au.parent.id=?1")
   List<AdministrativeUnit> getListdministrativeUnitbyParent(UUID parentId);
}
