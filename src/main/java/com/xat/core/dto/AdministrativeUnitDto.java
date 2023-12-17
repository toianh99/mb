package com.xat.core.dto;

import com.xat.core.domain.AdministrativeUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class AdministrativeUnitDto {
   private UUID id;
   private String name;
   private String code;
   private Integer level;
   private AdministrativeUnitDto parent;
   private Set<AdministrativeUnitDto> subAdministrativeUnits;
   private List<AdministrativeUnitDto> children;

   public AdministrativeUnit toEntity() {
      AdministrativeUnit entity = new AdministrativeUnit();
      entity.setId(this.id);
      entity.setName(this.name);
      entity.setCode(this.code);
      entity.setLevel(this.level);
      if (this.parent != null) {
         AdministrativeUnit parent = new AdministrativeUnit();
         parent.setId(parent.getId());
         parent.setName(parent.getName());
         parent.setCode(parent.getCode());
         parent.setLevel(this.level);
         entity.setParent(parent);
      }

      if (this.subAdministrativeUnits != null) {
         Set<AdministrativeUnit> subs = new HashSet();
         Iterator var3 = this.subAdministrativeUnits.iterator();

         while(var3.hasNext()) {
            AdministrativeUnitDto dto = (AdministrativeUnitDto)var3.next();
            AdministrativeUnit sub = new AdministrativeUnit();
            sub.setId(dto.getId());
            sub.setName(dto.getName());
            sub.setCode(dto.getCode());
            sub.setLevel(dto.getLevel());
            subs.add(sub);
         }

         entity.getSubAdministrativeUnits().addAll(subs);
      }

      return entity;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public AdministrativeUnitDto getParent() {
      return this.parent;
   }

   public void setParent(AdministrativeUnitDto parent) {
      this.parent = parent;
   }

   public Set<AdministrativeUnitDto> getSubAdministrativeUnits() {
      return this.subAdministrativeUnits;
   }

   public void setSubAdministrativeUnits(Set<AdministrativeUnitDto> subAdministrativeUnits) {
      this.subAdministrativeUnits = subAdministrativeUnits;
   }

   public List<AdministrativeUnitDto> getChildren() {
      return this.children;
   }

   public void setChildren(List<AdministrativeUnitDto> children) {
      this.children = children;
   }

   public AdministrativeUnitDto() {
   }

   public AdministrativeUnitDto(AdministrativeUnit unit) {
      this.code = unit.getCode();
      this.id = unit.getId();
      this.level = unit.getLevel();
      this.name = unit.getName();
      if (unit.getParent() != null) {
         this.parent = new AdministrativeUnitDto();
         this.parent.setCode(unit.getParent().getCode());
         this.parent.setId(unit.getParent().getId());
         this.parent.setName(unit.getParent().getName());
         this.parent.setLevel(unit.getParent().getLevel());
      }

      if (unit.getSubAdministrativeUnits() != null && unit.getSubAdministrativeUnits().size() > 0) {
         this.subAdministrativeUnits = new HashSet();
         Iterator var2 = unit.getSubAdministrativeUnits().iterator();

         while(var2.hasNext()) {
            AdministrativeUnit c = (AdministrativeUnit)var2.next();
            AdministrativeUnitDto cDto = new AdministrativeUnitDto();
            cDto.setId(c.getId());
            cDto.setCode(c.getCode());
            cDto.setName(c.getName());
            cDto.setLevel(c.getLevel());
            this.subAdministrativeUnits.add(cDto);
         }
      }

      this.setChildren(this.getListChildren(unit));
   }

   public AdministrativeUnitDto(AdministrativeUnit unit, boolean simple) {
      this.code = unit.getCode();
      this.id = unit.getId();
      this.level = unit.getLevel();
      this.name = unit.getName();
      if (unit.getParent() != null) {
         this.parent = new AdministrativeUnitDto();
         this.parent.setCode(unit.getParent().getCode());
         this.parent.setId(unit.getParent().getId());
         this.parent.setName(unit.getParent().getName());
         this.parent.setLevel(unit.getParent().getLevel());
      }

      if (!simple) {
         if (unit.getSubAdministrativeUnits() != null && unit.getSubAdministrativeUnits().size() > 0) {
            this.subAdministrativeUnits = new HashSet();
            Iterator var3 = unit.getSubAdministrativeUnits().iterator();

            while(var3.hasNext()) {
               AdministrativeUnit c = (AdministrativeUnit)var3.next();
               AdministrativeUnitDto cDto = new AdministrativeUnitDto();
               cDto.setId(c.getId());
               cDto.setCode(c.getCode());
               cDto.setName(c.getName());
               cDto.setLevel(c.getLevel());
               this.subAdministrativeUnits.add(cDto);
            }
         }

         this.setChildren(this.getListChildren(unit));
      }

   }

   public AdministrativeUnitDto(AdministrativeUnit unit, int datalevel) {
      this.code = unit.getCode();
      this.id = unit.getId();
      this.level = unit.getLevel();
      this.name = unit.getName();
      if (datalevel > 0 && unit.getParent() != null) {
         this.parent = new AdministrativeUnitDto();
         this.parent.setCode(unit.getParent().getCode());
         this.parent.setId(unit.getParent().getId());
         this.parent.setName(unit.getParent().getName());
         this.parent.setLevel(unit.getParent().getLevel());
      }

      if (datalevel > 1) {
         if (unit.getSubAdministrativeUnits() != null && unit.getSubAdministrativeUnits().size() > 0) {
            this.subAdministrativeUnits = new HashSet();
            Iterator var3 = unit.getSubAdministrativeUnits().iterator();

            while(var3.hasNext()) {
               AdministrativeUnit c = (AdministrativeUnit)var3.next();
               AdministrativeUnitDto cDto = new AdministrativeUnitDto();
               cDto.setId(c.getId());
               cDto.setCode(c.getCode());
               cDto.setName(c.getName());
               cDto.setLevel(c.getLevel());
               this.subAdministrativeUnits.add(cDto);
            }
         }

         this.setChildren(this.getListChildren(unit));
      }

   }

   private List<AdministrativeUnitDto> getListChildren(AdministrativeUnit unit) {
      List<AdministrativeUnitDto> ret = new ArrayList();
      if (unit.getSubAdministrativeUnits() != null && unit.getSubAdministrativeUnits().size() > 0) {
         Iterator var3 = unit.getSubAdministrativeUnits().iterator();

         while(var3.hasNext()) {
            AdministrativeUnit s = (AdministrativeUnit)var3.next();
            AdministrativeUnitDto sDto = new AdministrativeUnitDto();
            sDto.setId(s.getId());
            sDto.setCode(s.getCode());
            sDto.setName(s.getName());
            sDto.setLevel(s.getLevel());
            sDto.setChildren(this.getListChildren(s));
            ret.add(sDto);
         }
      }

      return ret;
   }
}
