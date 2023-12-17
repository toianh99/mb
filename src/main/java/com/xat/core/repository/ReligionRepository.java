package com.xat.core.repository;

import com.globits.core.domain.Religion;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, UUID> {
   @Query("select e from Religion e   where  e.code=?1")
   Religion findByCode(String code);

   @Query("select e from Religion e   where  e.code=?1")
   List<Religion> findListByCode(String code);
}
