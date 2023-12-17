package com.xat.core.repository;

import com.xat.core.domain.Organization;
import com.xat.core.dto.OrganizationDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
   @Query("select u from Organization u where u.id = ?1")
   Optional<Organization> findById(UUID id);

   @Query("select ou.organization from OrganizationUser ou where ou.user.id = ?1")
   List<Organization> findOrganizationByUserId(Long id);

   @Query("select d from Organization d where d.parent=null")
   List<Organization> getListOrganizationByTree();

   @Query("select new com.xat.core.dto.OrganizationDto(r,true) from Organization r")
   Page<OrganizationDto> getBasicInfoByPage(Pageable pageable);

   @Query("select new com.xat.core.dto.OrganizationDto(r,false) from Organization r where r.parent is null")
   Page<OrganizationDto> getFullInfoByPage(Pageable pageable);

   @Query("select new com.xat.core.dto.OrganizationDto(r,false) from Organization r where r.parent is null and (r.name like ?1)")
   Page<OrganizationDto> searchFullInfoByPage(String keyword, Pageable pageable);
}
