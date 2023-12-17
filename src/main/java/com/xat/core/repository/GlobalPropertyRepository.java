package com.xat.core.repository;

import com.globits.core.domain.GlobalProperty;
import com.globits.core.dto.GlobalPropertyDto;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalPropertyRepository extends JpaRepository<GlobalProperty, UUID> {
   @Query("select u from GlobalProperty u where u.property = ?1")
   GlobalProperty findByProperty(String property);

   @Query("select new com.globits.core.dto.GlobalPropertyDto(u,true) from GlobalProperty u where u.property = ?1")
   GlobalPropertyDto findDtoByProperty(String property);
}
