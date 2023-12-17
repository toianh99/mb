package com.xat.core.repository;

import com.xat.core.domain.FileDescription;
import com.xat.core.dto.FileDescriptionDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDescriptionRepository extends JpaRepository<FileDescription, UUID> {
   @Query("select new com.xat.core.dto.FileDescriptionDto(f,true) from FileDescription f where (?1 is null or f.name like ?1)")
   Page<FileDescriptionDto> searchByPage(String keyword, Pageable pageable);
}
