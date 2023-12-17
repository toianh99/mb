package com.xat.core.repository;

import com.globits.core.domain.Ethnics;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicsRepository extends JpaRepository<Ethnics, UUID> {
   @Query("select e from Ethnics e   where  e.code=?1")
   Ethnics findByCode(String code);

   @Query("select e from Ethnics e   where  e.code=?1")
   List<Ethnics> findListByCode(String code);
}
