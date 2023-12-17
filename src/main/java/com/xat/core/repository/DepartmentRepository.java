package com.xat.core.repository;

import com.xat.core.domain.Department;
import com.xat.core.dto.DepartmentDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
   @Query("select d from Department d order by d.code asc")
   List<Department> findAllDepartment();

   @Query("select d from Department d order by d.displayOrder asc")
   List<Department> findAllDepartmentOrderByDisplayOrder();

   @Query("select u from Department u left join fetch u.subDepartments where u.id = ?1")
   Department findFullDepartmentById(UUID id);

   @Query("select d from Department d where d.parent=null")
   List<Department> getListDepartmentByTree();

   @Query("select d from Department d where d.departmentType=?1")
   List<Department> getListDepartmentByDepartmentType(int departmentType);

   @Query("select d from Department d left join fetch d.subDepartments where d.parent is null")
   List<Department> findTreeDepartment(Pageable pageable);

   @Query("select COUNT(*) from Department d where d.parent is null")
   Long countRootDepartment();

   @Query("select u from Department u where u.code = ?1")
   List<Department> findByCode(String code);

   @Query("select new com.xat.core.dto.DepartmentDto(d) from Department d where d.id = ?1")
   DepartmentDto getOneDtoBy(UUID departmentId);

   @Query("select d from Department d where d.departmentType = 2 and d.level = 1")
   List<Department> findFaculties();

   @Query("select new com.xat.core.dto.DepartmentDto(d) from Department d where d.parent.id = ?1")
   List<DepartmentDto> getListChild(UUID parentId);
}
