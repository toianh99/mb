package com.xat.core.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(
   name = "tbl_fiscal_year"
)
@XmlRootElement
public class FiscalYear extends BaseObject {
   private static final long serialVersionUID = 1L;
   @Column(
      name = "year"
   )
   private Integer year;
   @Column(
      name = "start_date"
   )
   private Date startDate;
   @Column(
      name = "end_date"
   )
   private Date endDate;
   @Column(
      name = "name"
   )
   private String name;
   @Column(
      name = "code"
   )
   private String code;
   @Column(
      name = "description"
   )
   private String description;

   public Integer getYear() {
      return this.year;
   }

   public void setYear(Integer year) {
      this.year = year;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
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
}
