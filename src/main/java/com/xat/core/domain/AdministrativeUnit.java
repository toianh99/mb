package com.xat.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_administrative_unit"
)
public class AdministrativeUnit extends BaseObject {
   private static final long serialVersionUID = -5349886210112100999L;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "code"
   )
   private String code;
   @Column(
      name = "level"
   )
   private Integer level;
   @Column(
      name = "line_path"
   )
   private String linePath;
   @Column(
      name = "lat"
   )
   private Double lat;
   @Column(
      name = "lng"
   )
   private Double lng;
   @ManyToOne
   @JoinColumn(
      name = "parent_id"
   )
   private AdministrativeUnit parent;
   @JsonIgnore
   @OneToMany(
      mappedBy = "parent",
      fetch = FetchType.LAZY
   )
   private Set<AdministrativeUnit> subAdministrativeUnits;

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

   public AdministrativeUnit getParent() {
      return this.parent;
   }

   public void setParent(AdministrativeUnit parent) {
      this.parent = parent;
   }

   public Integer getLevel() {
      return this.level;
   }

   public void setLevel(Integer level) {
      this.level = level;
   }

   public String getLinePath() {
      return this.linePath;
   }

   public void setLinePath(String linePath) {
      this.linePath = linePath;
   }

   public Double getLat() {
      return this.lat;
   }

   public void setLat(Double lat) {
      this.lat = lat;
   }

   public Double getLng() {
      return this.lng;
   }

   public void setLng(Double lng) {
      this.lng = lng;
   }

   public Set<AdministrativeUnit> getSubAdministrativeUnits() {
      return this.subAdministrativeUnits;
   }

   public void setSubAdministrativeUnits(Set<AdministrativeUnit> subAdministrativeUnits) {
      this.subAdministrativeUnits = subAdministrativeUnits;
   }

   public AdministrativeUnit() {
   }

   public AdministrativeUnit(UUID id, String code, String name) {
      this.setId(id);
      this.setCode(code);
      this.setName(name);
   }

   public AdministrativeUnit(AdministrativeUnit administrativeUnit, boolean isSetParent) {
      this.setId(administrativeUnit.getId());
      this.code = administrativeUnit.getCode();
      this.name = administrativeUnit.getName();
      this.level = administrativeUnit.getLevel();
      if (isSetParent) {
         this.parent = administrativeUnit.getParent();
      }

   }

   public String toString() {
      StringBuilder sbf = new StringBuilder();
      sbf.append("{");
      sbf.append('"');
      sbf.append("id");
      sbf.append('"');
      sbf.append(':');
      sbf.append(this.getId());
      sbf.append(",");
      sbf.append('"');
      sbf.append("name");
      sbf.append('"');
      sbf.append(':');
      sbf.append(this.getName());
      sbf.append(",");
      sbf.append('"');
      sbf.append("code");
      sbf.append('"');
      sbf.append(':');
      sbf.append(this.getCode());
      sbf.append(",");
      sbf.append('"');
      sbf.append("linePath");
      sbf.append('"');
      sbf.append(':');
      sbf.append(this.getLinePath());
      sbf.append("}");
      return sbf.toString();
   }
}
