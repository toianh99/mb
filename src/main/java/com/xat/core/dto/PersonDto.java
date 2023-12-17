package com.xat.core.dto;

import com.xat.core.domain.Person;
import com.xat.core.domain.PersonAddress;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class PersonDto extends BaseObjectDto {
   protected UUID id;
   protected String firstName;
   protected String lastName;
   protected String displayName;
   protected String shortName;
   protected Date birthDate;
   protected String birthPlace;
   protected String gender;
   protected Date startDate;
   protected Date endDate;
   protected String phoneNumber;
   protected String idNumber;
   protected String idCitizen;
   protected String idNumberIssueBy;
   protected Date idNumberIssueDate;
   protected String email;
   protected CountryDto nationality;
   protected AdministrativeUnitDto nativeVillage;
   protected EthnicsDto ethnics;
   protected ReligionDto religion;
   protected byte[] photo;
   private Boolean photoCropped;
   private String imagePath;
   protected Set<PersonAddressDto> addresses;
   protected Long userId;
   protected Date communistYouthUnionJoinDate;
   protected Date communistPartyJoinDate;
   protected String carrer;
   private Integer maritalStatus;

   public Person toEntity() {
      Person person = new Person();
      if (this.id != null) {
         person.setId(this.id);
      }

      if (this.id != null) {
         person.setId(this.id);
      }

      person.setFirstName(this.firstName);
      person.setLastName(this.lastName);
      person.setDisplayName(this.displayName);
      person.setBirthDate(this.birthDate);
      person.setBirthPlace(this.birthPlace);
      person.setGender(this.gender);
      person.setStartDate(this.startDate);
      person.setEndDate(this.endDate);
      person.setPhoneNumber(this.phoneNumber);
      person.setIdNumber(this.idNumber);
      person.setIdCitizen(this.idCitizen);
      person.setIdNumberIssueBy(this.idNumberIssueBy);
      person.setIdNumberIssueDate(this.idNumberIssueDate);
      person.setEmail(this.email);
      person.setCarrer(this.carrer);
      person.setShortName(this.shortName);
      if (this.nationality != null) {
         person.setNationality(this.nationality.toEntity());
      }

      if (this.nativeVillage != null) {
         person.setNativeVillage(this.nativeVillage.toEntity());
      }

      if (this.ethnics != null) {
         person.setEthnics(this.ethnics.toEntity());
      }

      if (this.religion != null) {
         person.setReligion(this.religion.toEntity());
      }

      if (this.addresses != null) {
         Set<PersonAddress> addrs = new HashSet();
         Iterator var3 = this.addresses.iterator();

         while(var3.hasNext()) {
            PersonAddressDto dto = (PersonAddressDto)var3.next();
            addrs.add(dto.toEntity());
         }

         person.setAddress(addrs);
      }

      person.setPhoto(this.photo);
      return person;
   }

   public UUID getId() {
      return this.id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public String getShortName() {
      return this.shortName;
   }

   public void setShortName(String shortName) {
      this.shortName = shortName;
   }

   public Date getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public String getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(String birthPlace) {
      this.birthPlace = birthPlace;
   }

   public String getGender() {
      return this.gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
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

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getIdNumber() {
      return this.idNumber;
   }

   public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
   }

   public String getIdCitizen() {
      return this.idCitizen;
   }

   public void setIdCitizen(String idCitizen) {
      this.idCitizen = idCitizen;
   }

   public String getIdNumberIssueBy() {
      return this.idNumberIssueBy;
   }

   public void setIdNumberIssueBy(String idNumberIssueBy) {
      this.idNumberIssueBy = idNumberIssueBy;
   }

   public Date getIdNumberIssueDate() {
      return this.idNumberIssueDate;
   }

   public void setIdNumberIssueDate(Date idNumberIssueDate) {
      this.idNumberIssueDate = idNumberIssueDate;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public CountryDto getNationality() {
      return this.nationality;
   }

   public void setNationality(CountryDto nationality) {
      this.nationality = nationality;
   }

   public AdministrativeUnitDto getNativeVillage() {
      return this.nativeVillage;
   }

   public void setNativeVillage(AdministrativeUnitDto nativeVillage) {
      this.nativeVillage = nativeVillage;
   }

   public EthnicsDto getEthnics() {
      return this.ethnics;
   }

   public void setEthnics(EthnicsDto ethnics) {
      this.ethnics = ethnics;
   }

   public ReligionDto getReligion() {
      return this.religion;
   }

   public void setReligion(ReligionDto religion) {
      this.religion = religion;
   }

   public Set<PersonAddressDto> getAddress() {
      return this.addresses;
   }

   public void setAddress(Set<PersonAddressDto> address) {
      this.addresses = address;
   }

   public byte[] getPhoto() {
      return this.photo;
   }

   public void setPhoto(byte[] photo) {
      this.photo = photo;
   }

   public Boolean getPhotoCropped() {
      return this.photoCropped;
   }

   public void setPhotoCropped(Boolean photoCropped) {
      this.photoCropped = photoCropped;
   }

   public Set<PersonAddressDto> getAddresses() {
      return this.addresses;
   }

   public void setAddresses(Set<PersonAddressDto> addresses) {
      this.addresses = addresses;
   }

   public Long getUserId() {
      return this.userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Date getCommunistYouthUnionJoinDate() {
      return this.communistYouthUnionJoinDate;
   }

   public void setCommunistYouthUnionJoinDate(Date communistYouthUnionJoinDate) {
      this.communistYouthUnionJoinDate = communistYouthUnionJoinDate;
   }

   public Date getCommunistPartyJoinDate() {
      return this.communistPartyJoinDate;
   }

   public void setCommunistPartyJoinDate(Date communistPartyJoinDate) {
      this.communistPartyJoinDate = communistPartyJoinDate;
   }

   public String getCarrer() {
      return this.carrer;
   }

   public void setCarrer(String carrer) {
      this.carrer = carrer;
   }

   public String getImagePath() {
      return this.imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public Integer getMaritalStatus() {
      return this.maritalStatus;
   }

   public void setMaritalStatus(Integer maritalStatus) {
      this.maritalStatus = maritalStatus;
   }

   public PersonDto() {
      this.addresses = new HashSet();
   }

   public PersonDto(Person p) {
      if (p != null) {
         this.birthDate = p.getBirthDate();
         this.birthPlace = p.getBirthPlace();
         this.displayName = p.getDisplayName();
         this.email = p.getEmail();
         this.endDate = p.getEndDate();
         this.firstName = p.getFirstName();
         this.lastName = p.getLastName();
         this.gender = p.getGender();
         this.id = p.getId();
         this.idNumber = p.getIdNumber();
         this.idCitizen = p.getIdCitizen();
         this.idNumberIssueBy = p.getIdNumberIssueBy();
         this.idNumberIssueDate = p.getIdNumberIssueDate();
         this.photoCropped = p.getPhotoCropped();
         this.shortName = p.getShortName();
         this.imagePath = p.getImagePath();
         this.phoneNumber = p.getPhoneNumber();
//         if (p.getUser() != null) {
//            this.userId = p.getUser().getId();
//         }

         if (p.getAddress() != null) {
            Set<PersonAddressDto> address = new HashSet();
            Iterator var3 = p.getAddress().iterator();

            while(var3.hasNext()) {
               PersonAddress tt = (PersonAddress)var3.next();
               PersonAddressDto ttDto = new PersonAddressDto();
               ttDto.setId(tt.getId());
               ttDto.setAddress(tt.getAddress());
               ttDto.setCity(tt.getCity());
               ttDto.setCountry(tt.getCountry());
               ttDto.setPersonId(p.getId());
               ttDto.setPostalCode(tt.getPostalCode());
               ttDto.setProvince(tt.getProvince());
               ttDto.setType(tt.getType());
               address.add(ttDto);
            }

            this.addresses = address;
         }

         if (p.getNationality() != null) {
            this.nationality = new CountryDto(p.getNationality());
         }

         if (p.getNativeVillage() != null) {
            this.nativeVillage = new AdministrativeUnitDto(p.getNativeVillage());
         }

         if (p.getCommunistPartyJoinDate() != null) {
            this.communistPartyJoinDate = p.getCommunistPartyJoinDate();
         }

         if (p.getCommunistYouthUnionJoinDate() != null) {
            this.communistYouthUnionJoinDate = p.getCommunistYouthUnionJoinDate();
         }

         this.carrer = p.getCarrer();
         this.setCreateDate(p.getCreateDate());
         this.setCreatedBy(p.getCreatedBy());
         this.setModifyDate(p.getModifyDate());
         this.setModifiedBy(p.getModifiedBy());
      }

   }

   public PersonDto(Person p, boolean simple) {
      if (p != null) {
         this.birthDate = p.getBirthDate();
         this.birthPlace = p.getBirthPlace();
         this.displayName = p.getDisplayName();
         this.email = p.getEmail();
         this.endDate = p.getEndDate();
         this.firstName = p.getFirstName();
         this.lastName = p.getLastName();
         this.gender = p.getGender();
         this.id = p.getId();
         this.idNumber = p.getIdNumber();
         this.idCitizen = p.getIdCitizen();
         this.idNumberIssueBy = p.getIdNumberIssueBy();
         this.idNumberIssueDate = p.getIdNumberIssueDate();
         this.photoCropped = p.getPhotoCropped();
         this.shortName = p.getShortName();
         this.imagePath = p.getImagePath();
         this.phoneNumber = p.getPhoneNumber();
//         if (p.getUser() != null) {
//            this.userId = p.getUser().getId();
//         }

         if (!simple) {
            if (p.getAddress() != null) {
               Set<PersonAddressDto> address = new HashSet();
               Iterator var4 = p.getAddress().iterator();

               while(var4.hasNext()) {
                  PersonAddress tt = (PersonAddress)var4.next();
                  PersonAddressDto ttDto = new PersonAddressDto();
                  ttDto.setId(tt.getId());
                  ttDto.setAddress(tt.getAddress());
                  ttDto.setCity(tt.getCity());
                  ttDto.setCountry(tt.getCountry());
                  ttDto.setPersonId(p.getId());
                  ttDto.setPostalCode(tt.getPostalCode());
                  ttDto.setProvince(tt.getProvince());
                  ttDto.setType(tt.getType());
                  address.add(ttDto);
               }

               this.addresses = address;
            }

            if (p.getNationality() != null) {
               this.nationality = new CountryDto(p.getNationality());
            }

            if (p.getNativeVillage() != null) {
               this.nativeVillage = new AdministrativeUnitDto(p.getNativeVillage());
            }

            if (p.getCommunistPartyJoinDate() != null) {
               this.communistPartyJoinDate = p.getCommunistPartyJoinDate();
            }

            if (p.getCommunistYouthUnionJoinDate() != null) {
               this.communistYouthUnionJoinDate = p.getCommunistYouthUnionJoinDate();
            }

            this.carrer = p.getCarrer();
         }

         this.setCreateDate(p.getCreateDate());
         this.setCreatedBy(p.getCreatedBy());
         this.setModifyDate(p.getModifyDate());
         this.setModifiedBy(p.getModifiedBy());
      }

   }
}
