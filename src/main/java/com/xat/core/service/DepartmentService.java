package com.xat.core.service;

import com.xat.core.domain.Department;
import com.xat.core.dto.DepartmentDto;
import com.xat.core.dto.DepartmentSearchDto;
import com.xat.core.dto.DepartmentTreeDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface DepartmentService extends GenericService<Department, UUID> {
   Department saveDepartment(Department department);

   Department updateDepartment(Department department, UUID departmentId);

   Page<Department> getListDepartmentByTree();

   Page<Department> getListDepartmentByDepartmentType(int departmentType);

   List<DepartmentDto> getAll();

   List<DepartmentTreeDto> getTreeData();

   Page<DepartmentDto> findTreeDepartment(int pageIndex, int pageSize);

   int deleteDepartments(List<DepartmentDto> deparments);

   List<DepartmentDto> findTreeDepartmentNoneChild();

   DepartmentDto checkDuplicateCode(String code);

   DepartmentDto getDepartmentById(UUID departmentId);

   Page<DepartmentDto> searchDepartment(DepartmentSearchDto dto, int pageSize, int pageIndex);

   boolean updateLinePath();

   DepartmentDto updateDepartmentDto(DepartmentDto department, UUID departmentId);

   List<DepartmentDto> findTreeFacultiesNoneChild();
}
