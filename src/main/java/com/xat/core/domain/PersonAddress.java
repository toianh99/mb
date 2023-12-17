package com.xat.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "tbl_person_address"
)
public class PersonAddress extends BaseObject {
   private static final long serialVersionUID = -726825325771876837L;
   @Column(
      name = "address"
   )
   private String address;
   @Column(
      name = "address1",
      nullable = true
   )
   private String address1;
   @Column(
      name = "city",
      nullable = true
   )
   private String city;
   @Column(
      name = "province",
      nullable = true
   )
   private String province;
   @Column(
      name = "country",
      nullable = true
   )
   private String country;
   @Column(
      name = "postal_code",
      nullable = true
   )
   private String postalCode;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "person_id"
   )
   @JsonIgnore
   private Person person;
   @ManyToOne(
      fetch = FetchType.EAGER
   )
   @JoinColumn(
      name = "admin_unit_id"
   )
   private AdministrativeUnit adminUnit;
   @Column(
      name = "is_current"
   )
   private Boolean isCurrent;
   @Column(
      name = "from_date"
   )
   private Date fromDate;
   @Column(
      name = "to_date"
   )
   private Date toDate;
   private String latitude;
   private String longitude;
   private Integer type;

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getAddress1() {
      return this.address1;
   }

   public void setAddress1(String address1) {
      this.address1 = address1;
   }

   public String getCity() {
      return this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getProvince() {
      return this.province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getCountry() {
      return this.country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public String getLatitude() {
      return this.latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public String getLongitude() {
      return this.longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public Person getPerson() {
      return this.person;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public Integer getType() {
      return this.type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

   public AdministrativeUnit getAdminUnit() {
      return this.adminUnit;
   }

   public void setAdminUnit(AdministrativeUnit adminUnit) {
      this.adminUnit = adminUnit;
   }

   public Boolean getIsCurrent() {
      return this.isCurrent;
   }

   public Date getFromDate() {
      return this.fromDate;
   }

   public void setFromDate(Date fromDate) {
      this.fromDate = fromDate;
   }

   public Date getToDate() {
      return this.toDate;
   }

   public void setToDate(Date toDate) {
      this.toDate = toDate;
   }

   public void setIsCurrent(Boolean isCurrent) {
      this.isCurrent = isCurrent;
   }

   public PersonAddress() {
   }

   public PersonAddress(PersonAddress personAddress) {
      super(personAddress);
      this.address = personAddress.getAddress();
      this.address1 = personAddress.getAddress1();
      this.city = personAddress.getCity();
      this.country = personAddress.getCountry();
      this.latitude = personAddress.getLatitude();
      this.longitude = personAddress.getLongitude();
      this.postalCode = personAddress.getPostalCode();
      this.province = personAddress.getProvince();
      this.type = personAddress.getType();
      if (personAddress.getPerson() != null) {
         this.person = new Person(personAddress.getPerson().getId(), personAddress.getPerson().getLastName(), personAddress.getPerson().getFirstName(), personAddress.getPerson().getDisplayName(), personAddress.getPerson().getBirthDate(), personAddress.getPerson().getPhoneNumber());
      }

   }
}
