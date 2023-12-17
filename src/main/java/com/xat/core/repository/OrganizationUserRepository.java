package com.xat.core.repository;

import com.globits.core.domain.Organization;
import com.globits.core.domain.OrganizationUser;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationUserRepository extends JpaRepository<OrganizationUser, UUID> {
   @Query("select u from OrganizationUser u where u.id = ?1")
   Optional<OrganizationUser> findById(UUID id);

   @Query("select u from OrganizationUser u where u.user.id = ?1")
   List<OrganizationUser> findByUser(Long userId);

   @Query("select u.organization from OrganizationUser u where u.user.id = ?1")
   List<Organization> getOrganizationByUserId(Long userId);
}
