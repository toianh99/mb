package com.xat.core.rest;

import com.xat.core.domain.Department;
import com.xat.core.dto.DepartmentDto;
import com.xat.core.service.DepartmentService;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/department"})
public class RestDepartmentController {
   @PersistenceContext
   private EntityManager manager;
   @Autowired
   private DepartmentService departmentService;

   @RequestMapping(
      value = {"/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Department> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Department> page = this.departmentService.getListDepartmentByTree();
      return page;
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/{DepartmentId}"},
      method = {RequestMethod.GET}
   )
   public Department getDepartment(@PathVariable("DepartmentId") String DepartmentId) {
      Department depart = (Department)this.departmentService.findById(UUID.fromString(DepartmentId));
      return depart;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.POST}
   )
   public Department saveDepartment(@RequestBody Department Department) {
      return this.departmentService.saveDepartment(Department);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{DepartmentId}"},
      method = {RequestMethod.PUT}
   )
   public Department updateDepartment(@RequestBody Department Department, @PathVariable("DepartmentId") UUID departmentId) {
      return this.departmentService.updateDepartment(Department, departmentId);
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/{DepartmentId}"},
      method = {RequestMethod.DELETE}
   )
   public Department removeDepartment(@PathVariable("DepartmentId") String DepartmentId) {
      Department Department = (Department)this.departmentService.delete(UUID.fromString(DepartmentId));
      return Department;
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      method = {RequestMethod.DELETE}
   )
   public int removeDepartment(@RequestBody List<DepartmentDto> departments) {
      return this.departmentService.deleteDepartments(departments);
   }

   @RequestMapping(
      value = {"/{type}/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public Page<Department> getListByType(@PathVariable int type, @PathVariable int pageIndex, @PathVariable int pageSize) {
      Page<Department> page = this.departmentService.getListDepartmentByDepartmentType(type);
      return page;
   }

   @RequestMapping(
      value = {"/all"},
      method = {RequestMethod.GET}
   )
   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
      List<DepartmentDto> list = this.departmentService.getAll();
      return new ResponseEntity(list, HttpStatus.OK);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/tree/{pageIndex}/{pageSize}"},
      method = {RequestMethod.GET}
   )
   public ResponseEntity<Page<DepartmentDto>> getTreeData(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
      Page<DepartmentDto> ret = this.departmentService.findTreeDepartment(pageIndex, pageSize);
      return new ResponseEntity(ret, HttpStatus.OK);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/tree_none_child"},
      method = {RequestMethod.GET}
   )
   List<DepartmentDto> findTreeDepartmentNoneChild() {
      return this.departmentService.findTreeDepartmentNoneChild();
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/tree_none_child/faculty"},
      method = {RequestMethod.GET}
   )
   List<DepartmentDto> findTreeFacultiesNoneChild() {
      return this.departmentService.findTreeFacultiesNoneChild();
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/parent/faculty"},
      method = {RequestMethod.GET}
   )
   List<DepartmentDto> findTreeParentFaculties() {
      return this.departmentService.findTreeFacultiesNoneChild();
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/checkCode/{code}/"},
      method = {RequestMethod.GET}
   )
   public DepartmentDto checkDuplicateCode(@PathVariable String code) {
      return this.departmentService.checkDuplicateCode(code);
   }

   @Secured({"ROLE_ADMIN", "ROLE_USER"})
   @RequestMapping(
      value = {"/dto/{departmentId}"},
      method = {RequestMethod.GET}
   )
   public DepartmentDto getDepartmentDto(@PathVariable("departmentId") String departmentId) {
      return this.departmentService.getDepartmentById(UUID.fromString(departmentId));
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/line_path"},
      method = {RequestMethod.PUT}
   )
   public boolean updateLinePath() {
      return this.departmentService.updateLinePath();
   }

   @Secured({"ROLE_ADMIN"})
   @RequestMapping(
      value = {"/dto/{DepartmentId}"},
      method = {RequestMethod.PUT}
   )
   public DepartmentDto updateDepartmentDto(@RequestBody DepartmentDto Department, @PathVariable("DepartmentId") UUID departmentId) {
      return this.departmentService.updateDepartmentDto(Department, departmentId);
   }
}
