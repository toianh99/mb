package com.xat.core.repository;

import com.globits.core.domain.Location;
import com.globits.core.dto.LocationDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
   @Query("select l from Location l where l.code = ?1")
   List<Location> findByCode(String code);

   @Query("select new com.globits.core.dto.LocationDto(e,true) from Location e")
   Page<LocationDto> getByPage(Pageable pageable);
}
