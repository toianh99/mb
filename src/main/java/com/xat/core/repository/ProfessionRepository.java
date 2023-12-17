package com.xat.core.repository;

import com.globits.core.domain.Profession;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, UUID> {
   @Query("select e from Profession e   where  e.code=?1")
   Profession findByCode(String code);

   @Query("select e from Profession e   where  e.code=?1")
   List<Profession> findListByCode(String code);
}
