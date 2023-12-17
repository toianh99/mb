package com.xat.core.repository;

import com.xat.core.domain.Country;
import com.xat.core.dto.CountryDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
   @Query("select e from Country e   where  e.code=?1")
   Country findByCode(String code);

   @Query("select e from Country e   where  e.code=?1")
   List<Country> findListByCode(String code);

   @Query("select new com.xat.core.dto.CountryDto(e,true) from Country e")
   Page<CountryDto> getByPage(Pageable pageable);
}
