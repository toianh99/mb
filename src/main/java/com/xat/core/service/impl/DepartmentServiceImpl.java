package com.xat.core.service.impl;

import com.xat.core.domain.Department;
import com.xat.core.dto.DepartmentDto;
import com.xat.core.dto.DepartmentSearchDto;
import com.xat.core.dto.DepartmentTreeDto;
import com.xat.core.repository.DepartmentRepository;
import com.xat.core.service.DepartmentService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DepartmentServiceImpl extends GenericServiceImpl<Department, UUID> implements DepartmentService {
   @Autowired
   DepartmentRepository departmentRepository;

   private List<Department> getListSub(Department dep, int level) {
      ArrayList<Department> retList = new ArrayList();
      dep.setLevel(level);
      retList.add(dep);
      if (dep.getSubDepartments() != null && dep.getSubDepartments().size() > 0) {
         Iterator iter = dep.getSubDepartments().iterator();

         while(iter.hasNext()) {
            Department d = (Department)iter.next();
            retList.addAll(this.getListSub(d, level + 1));
         }
      }

      return retList;
   }

   public Page<Department> getListDepartmentByTree() {
      List<Department> list = this.departmentRepository.getListDepartmentByTree();
      ArrayList<Department> retList = new ArrayList();

      int pageSize;
      for(pageSize = 0; pageSize < list.size(); ++pageSize) {
         if (list.get(pageSize) != null) {
            List<Department> childs = this.getListSub((Department)list.get(pageSize), 0);
            if (childs != null && childs.size() > 0) {
               retList.addAll(childs);
            }
         }
      }

      pageSize = 1;
      if (retList.size() > 0) {
         pageSize = retList.size();
      }

      Pageable pageable = PageRequest.of(0, pageSize);
      Page<Department> page = new PageImpl(retList, pageable, (long)pageSize);
      return page;
   }

   public Page<DepartmentDto> findTreeDepartment(int pageIndex, int pageSize) {
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      List<Department> list = this.departmentRepository.findTreeDepartment(pageable);
      Long numberElement = this.departmentRepository.countRootDepartment();
      List<DepartmentDto> retList = new ArrayList();

      for(int i = 0; i < list.size(); ++i) {
         DepartmentDto dto = new DepartmentDto((Department)list.get(i));
         retList.add(dto);
      }

      Page<DepartmentDto> page = new PageImpl(retList, pageable, numberElement);
      return page;
   }

   public List<DepartmentDto> findTreeFacultiesNoneChild() {
      List<Department> list = this.departmentRepository.findFaculties();
      List<DepartmentDto> retList = new ArrayList();

      for(int i = 0; i < list.size(); ++i) {
         DepartmentDto dto = new DepartmentDto((Department)list.get(i));
         retList.add(dto);
      }

      return retList;
   }

   public List<DepartmentDto> findTreeDepartmentNoneChild() {
      Pageable pageable = PageRequest.of(0, 1000);
      List<Department> list = this.departmentRepository.findTreeDepartment(pageable);
      List<DepartmentDto> retList = new ArrayList();

      for(int i = 0; i < list.size(); ++i) {
         DepartmentDto dto = new DepartmentDto((Department)list.get(i));
         retList.add(dto);
         retList.addAll(this.buildTreeByChild(dto, ""));
      }

      return retList;
   }

   public List<DepartmentDto> buildTreeByChild(DepartmentDto dto, String prefix) {
      List<DepartmentDto> retList = new ArrayList();
      if (dto.getSubDepartments() != null && dto.getSubDepartments().size() > 0) {
         Iterator var4 = dto.getSubDepartments().iterator();

         while(var4.hasNext()) {
            DepartmentDto subDto = (DepartmentDto)var4.next();
            subDto.setName(prefix + "..." + subDto.getName());
            subDto.setCode(prefix + "..." + subDto.getCode());
            retList.add(subDto);
            retList.addAll(this.buildTreeByChild(subDto, prefix + "..."));
         }

         dto.setSubDepartments((List)null);
      }

      return retList;
   }

   public Department updateDepartment(Department department, UUID departmentId) {
      Department updateDepartment = null;
      if (department.getId() != null) {
         updateDepartment = (Department)this.findById(department.getId());
      } else {
         updateDepartment = (Department)this.findById(departmentId);
      }

      updateDepartment.setCode(department.getCode());
      updateDepartment.setName(department.getName());
      updateDepartment.setDepartmentType(department.getDepartmentType());
      updateDepartment.setDisplayOrder(department.getDisplayOrder());
      if (department.getParent() != null && department.getParent().getId() != null) {
         Optional<Department> parentDepartment = this.departmentRepository.findById(department.getParent().getId());
         if (parentDepartment != null && ((Department)parentDepartment.get()).getId() != updateDepartment.getId()) {
            updateDepartment.setParent((Department)parentDepartment.get());
         }
      } else if (updateDepartment.getParent() != null) {
         updateDepartment.setParent((Department)null);
      }

      Department dep = updateDepartment;

      String linePath;
      for(linePath = updateDepartment.getCode(); dep.getParent() != null; dep = dep.getParent()) {
         if (dep.getParent() != null) {
            linePath = dep.getParent().getCode() + "/" + linePath;
         }
      }

      updateDepartment.setLinePath(linePath);
      updateDepartment = (Department)this.save(updateDepartment);
      if (updateDepartment.getParent() != null) {
         updateDepartment.setParent(new Department(updateDepartment.getParent().getId(), updateDepartment.getParent().getName(), updateDepartment.getParent().getCode(), updateDepartment.getParent().getDepartmentType()));
      }

      return updateDepartment;
   }

   public Department saveDepartment(Department department) {
      Department newDepartment = new Department();
      newDepartment.setCode(department.getCode());
      newDepartment.setName(department.getName());
      newDepartment.setDepartmentType(department.getDepartmentType());
      newDepartment.setDisplayOrder(department.getDisplayOrder());
      if (department.getParent() != null && department.getParent().getId() != null) {
         Optional<Department> parentDepartment = this.departmentRepository.findById(department.getParent().getId());
         if (parentDepartment != null && ((Department)parentDepartment.get()).getId() != newDepartment.getId()) {
            newDepartment.setParent((Department)parentDepartment.get());
         }
      }

      Department dep = newDepartment;

      String linePath;
      for(linePath = newDepartment.getCode(); dep.getParent() != null; dep = dep.getParent()) {
         if (dep.getParent() != null) {
            linePath = dep.getParent().getCode() + "/" + linePath;
         }
      }

      newDepartment.setLinePath(linePath);
      return (Department)this.save(newDepartment);
   }

   public Page<Department> getListDepartmentByDepartmentType(int departmentType) {
      List<Department> retList = this.departmentRepository.getListDepartmentByDepartmentType(departmentType);
      int pageSize = 1;
      if (retList.size() > 0) {
         pageSize = retList.size();
      }

      Pageable pageable = PageRequest.of(0, pageSize);
      Page<Department> page = new PageImpl(retList, pageable, (long)pageSize);
      return page;
   }

   @Transactional(
      readOnly = true
   )
   public List<DepartmentDto> getAll() {
      Iterator<Department> itr = this.repository.findAll().iterator();
      ArrayList list = new ArrayList();

      while(itr.hasNext()) {
         list.add(new DepartmentDto((Department)itr.next()));
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List<DepartmentTreeDto> getTreeData() {
      List<Department> list = this.departmentRepository.findAllDepartmentOrderByDisplayOrder();
      List<DepartmentTreeDto> dtos = new ArrayList();
      Iterator var3 = list.iterator();

      while(var3.hasNext()) {
         Department department = (Department)var3.next();
         dtos.add(new DepartmentTreeDto(department));
      }

      return dtos;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteDepartments(List<DepartmentDto> departments) {
      int ret = 0;

      for(Iterator var3 = departments.iterator(); var3.hasNext(); ++ret) {
         DepartmentDto dto = (DepartmentDto)var3.next();
         this.departmentRepository.deleteById(dto.getId());
      }

      return ret;
   }

   public DepartmentDto checkDuplicateCode(String code) {
      DepartmentDto viewCheckDuplicateCodeDto = new DepartmentDto();
      Department department = null;
      List<Department> list = this.departmentRepository.findByCode(code);
      if (list != null && list.size() > 0) {
         department = (Department)list.get(0);
      }

      if (list == null) {
         viewCheckDuplicateCodeDto.setDuplicate(false);
      } else if (list != null && list.size() > 0) {
         viewCheckDuplicateCodeDto.setDuplicate(true);
         viewCheckDuplicateCodeDto.setDupCode(department.getCode());
         viewCheckDuplicateCodeDto.setDupName(department.getName());
      }

      return viewCheckDuplicateCodeDto;
   }

   public DepartmentDto getDepartmentById(UUID departmentId) {
      DepartmentDto departmentDto = this.departmentRepository.getOneDtoBy(departmentId);
      return departmentDto;
   }

   public Page<DepartmentDto> searchDepartment(DepartmentSearchDto dto, int pageSize, int pageIndex) {
      String sql = "select new com.xat.core.dto.DepartmentDto(d) from Department d where (1=1)";
      String sqlCount = "select count(d.id) from Department d where (1=1)";
      if (dto.getCode() != null) {
         sql = sql + " and d.code like :code";
         sqlCount = sqlCount + " and d.code like :code";
      }

      if (dto.getName() != null) {
         sql = sql + " and d.name like :name";
         sqlCount = sqlCount + " and d.name like :name";
      }

      if (dto.getParentId() != null) {
         sql = sql + " and d.parent.id=:parentId";
         sqlCount = sqlCount + " and d.parent.id=:parentId";
      }

      if (dto.getOrderBy() != null && dto.getOrderBy().length() != 0) {
         sql = sql + " order by " + dto.getOrderBy();
      } else {
         sql = sql + " order by d.displayOrder";
      }

      TypedQuery<DepartmentDto> q = this.manager.createQuery(sql, DepartmentDto.class);
      Query qCount = this.manager.createQuery(sqlCount);
      String name;
      if (dto.getCode() != null) {
         name = "%" + dto.getCode() + "%";
         q.setParameter("code", name);
         qCount.setParameter("code", name);
      }

      if (dto.getName() != null) {
         name = "%" + dto.getName() + "%";
         q.setParameter("name", name);
         qCount.setParameter("name", name);
      }

      if (dto.getParentId() != null) {
         q.setParameter("parentId", dto.getParentId());
         qCount.setParameter("parentId", dto.getParentId());
      }

      int startPosition = (pageIndex - 1) * pageSize;
      q.setMaxResults(pageSize);
      q.setFirstResult(startPosition);
      List<DepartmentDto> content = q.getResultList();
      long count = (Long)qCount.getSingleResult();
      Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
      Page<DepartmentDto> result = new PageImpl(content, pageable, count);
      return result;
   }

   private void updateLinePathChildrens(Department parent) {
      if (parent != null && parent.getSubDepartments() != null && parent.getSubDepartments().size() > 0) {
         Iterator var2 = parent.getSubDepartments().iterator();

         while(var2.hasNext()) {
            Department d = (Department)var2.next();
            String linePath = d.getCode();

            for(Department dep = d; dep.getParent() != null; dep = dep.getParent()) {
               if (dep.getParent() != null) {
                  linePath = dep.getParent().getCode() + "/" + linePath;
               }
            }

            d.setLinePath(linePath);
            d = (Department)this.departmentRepository.save(d);
            this.updateLinePathChildrens(d);
         }
      }

   }

   public boolean updateLinePath() {
      List<Department> rootDepartment = this.departmentRepository.getListDepartmentByTree();
      Iterator var2 = rootDepartment.iterator();

      while(var2.hasNext()) {
         Department root = (Department)var2.next();
         root = (Department)this.departmentRepository.save(root);
         this.updateLinePathChildrens(root);
      }

      return true;
   }

   public DepartmentDto updateDepartmentDto(DepartmentDto department, UUID departmentId) {
      Department updateDepartment = null;
      if (department.getId() != null) {
         updateDepartment = (Department)this.findById(department.getId());
      } else {
         updateDepartment = (Department)this.findById(departmentId);
      }

      updateDepartment.setCode(department.getCode());
      updateDepartment.setName(department.getName());
      updateDepartment.setDepartmentType(department.getDepartmentType());
      updateDepartment.setDisplayOrder(department.getDisplayOrder());
      if (department.getParent() != null && department.getParent().getId() != null) {
         Optional<Department> parentDepartment = this.departmentRepository.findById(department.getParent().getId());
         if (parentDepartment != null && ((Department)parentDepartment.get()).getId() != updateDepartment.getId()) {
            updateDepartment.setParent((Department)parentDepartment.get());
         }
      } else if (updateDepartment.getParent() != null) {
         updateDepartment.setParent((Department)null);
      }

      Department dep = updateDepartment;

      String linePath;
      for(linePath = updateDepartment.getCode(); dep.getParent() != null; dep = dep.getParent()) {
         if (dep.getParent() != null) {
            linePath = dep.getParent().getCode() + "/" + linePath;
         }
      }

      updateDepartment.setLinePath(linePath);
      Department parent = null;
      if (department.getParent() != null && department.getParent().getId() != null) {
         parent = (Department)this.departmentRepository.getOne(department.getParent().getId());
      }

      updateDepartment.setParent(parent);
      updateDepartment = (Department)this.departmentRepository.save(updateDepartment);
      if (department.getSubDepartments() != null && department.getSubDepartments().size() > 0) {
         Iterator var7 = department.getSubDepartments().iterator();

         while(var7.hasNext()) {
            DepartmentDto dto = (DepartmentDto)var7.next();
            if (dto != null && dto.getId() != null) {
               Department domain = (Department)this.departmentRepository.getOne(dto.getId());
               if (domain != null) {
                  domain.setDisplayOrder(dto.getDisplayOrder());
                  domain = (Department)this.departmentRepository.save(domain);
               }
            }
         }
      }

      return new DepartmentDto(updateDepartment);
   }
}
