package com.xat.core.domain;

import com.globits.security.domain.User;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(
   name = "tbl_person"
)
@Inheritance(
   strategy = InheritanceType.JOINED
)
@XmlRootElement
public class Person extends BaseObject {
   @Transient
   private static final long serialVersionUID = 1216825583672377485L;
   @Column(
      name = "first_name",
      nullable = true
   )
   protected String firstName;
   @Column(
      name = "last_name",
      nullable = true
   )
   protected String lastName;
   @Column(
      name = "display_name",
      nullable = true
   )
   protected String displayName;
   @Column(
      name = "short_name",
      nullable = true
   )
   protected String shortName;
   @Column(
      name = "birth_date",
      nullable = true
   )
   protected Date birthDate;
   @Column(
      name = "birth_place",
      nullable = true
   )
   protected String birthPlace;
   @Column(
      name = "gender",
      nullable = true
   )
   protected String gender;
   @Column(
      name = "start_date",
      nullable = true
   )
   protected Date startDate;
   @Column(
      name = "end_date",
      nullable = true
   )
   protected Date endDate;
   @Column(
      name = "phone_number",
      nullable = true
   )
   protected String phoneNumber;
   @Column(
      name = "id_number",
      nullable = true
   )
   protected String idNumber;
   @Column(
      name = "id_citizen",
      nullable = true
   )
   private String idCitizen;
   @Column(
      name = "id_number_issue_by",
      nullable = true
   )
   protected String idNumberIssueBy;
   @Column(
      name = "id_number_issue_date",
      nullable = true
   )
   protected Date idNumberIssueDate;
   @Column(
      name = "email",
      nullable = true
   )
   protected String Email;
   @Column(
      name = "communist_youth_union_join_date",
      nullable = true
   )
   protected Date communistYouthUnionJoinDate;
   @Column(
      name = "communist_party_join_date",
      nullable = true
   )
   protected Date communistPartyJoinDate;
   @Column(
      name = "communist_party_member",
      nullable = true
   )
   private Boolean isCommunistPartyMember;
   @ManyToOne(
      fetch = FetchType.LAZY
   )
   @JoinColumn(
      name = "country_id",
      nullable = true
   )
   protected Country nationality;
   @ManyToOne
   @JoinColumn(
      name = "native_village",
      nullable = true
   )
   protected AdministrativeUnit nativeVillage;
   @ManyToOne(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.REFRESH}
   )
   @JoinColumn(
      name = "ethnics_id",
      nullable = true
   )
   protected Ethnics ethnics;
   @ManyToOne(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.REFRESH}
   )
   @JoinColumn(
      name = "religion_id",
      nullable = true
   )
   protected Religion religion;
   @OneToMany(
      mappedBy = "person",
      fetch = FetchType.EAGER,
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   protected Set<PersonAddress> address;
   @Column(
      name = "carrer",
      nullable = true
   )
   protected String carrer;
   @ManyToOne(
      optional = true,
      fetch = FetchType.LAZY
   )
   @JoinColumn(
      name = "user_id",
      unique = false
   )
   protected User user;
   @Basic(
      fetch = FetchType.LAZY
   )
   @Transient
   protected byte[] photo;
   @Column(
      name = "photo_cropped",
      nullable = true
   )
   private Boolean photoCropped;
   @Column(
      name = "marital_status",
      nullable = true
   )
   private Integer maritalStatus;
   @Column(
      name = "is_dead",
      nullable = true
   )
   private Boolean isDead;
   @Column(
      name = "image_path",
      nullable = true
   )
   private String imagePath;
   @Column(
      name = "tax_code"
   )
   private String taxCode;

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

   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
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

   public Set<PersonAddress> getAddress() {
      return this.address;
   }

   public void setAddress(Set<PersonAddress> address) {
      this.address = address;
   }

   public Integer getMaritalStatus() {
      return this.maritalStatus;
   }

   public void setMaritalStatus(Integer maritalStatus) {
      this.maritalStatus = maritalStatus;
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

   public Ethnics getEthnics() {
      return this.ethnics;
   }

   public void setEthnics(Ethnics ethnics) {
      this.ethnics = ethnics;
   }

   public Religion getReligion() {
      return this.religion;
   }

   public void setReligion(Religion religion) {
      this.religion = religion;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public Country getNationality() {
      return this.nationality;
   }

   public void setNationality(Country nationality) {
      this.nationality = nationality;
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
      return this.Email;
   }

   public void setEmail(String email) {
      this.Email = email;
   }

   public AdministrativeUnit getNativeVillage() {
      return this.nativeVillage;
   }

   public void setNativeVillage(AdministrativeUnit nativeVillage) {
      this.nativeVillage = nativeVillage;
   }

   public String getCarrer() {
      return this.carrer;
   }

   public void setCarrer(String carrer) {
      this.carrer = carrer;
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

   public Boolean getIsCommunistPartyMember() {
      return this.isCommunistPartyMember;
   }

   public void setIsCommunistPartyMember(Boolean isCommunistPartyMember) {
      this.isCommunistPartyMember = isCommunistPartyMember;
   }

   public Boolean getIsDead() {
      return this.isDead;
   }

   public void setIsDead(Boolean isDead) {
      this.isDead = isDead;
   }

   public String getImagePath() {
      return this.imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public String getTaxCode() {
      return this.taxCode;
   }

   public void setTaxCode(String taxCode) {
      this.taxCode = taxCode;
   }

   public Person() {
   }

   public Person(UUID id, String firstName, String lastName, String displayName, Date birtDate, String phoneNumber) {
      this.setId(id);
      this.setFirstName(firstName);
      this.setLastName(lastName);
      this.setDisplayName(displayName);
      this.setBirthDate(birtDate);
   }

   public Person(Person person) {
      super(person);
      this.firstName = person.getFirstName();
      this.lastName = person.getLastName();
      this.displayName = person.getDisplayName();
      if (person.getAddress() != null) {
         Set<PersonAddress> address = new HashSet();
         Iterator var3 = person.getAddress().iterator();

         while(var3.hasNext()) {
            PersonAddress tt = (PersonAddress)var3.next();
            new PersonAddress();
            address.add(tt);
         }

         this.address = address;
      }

      this.birthDate = person.getBirthDate();
      this.birthPlace = person.getBirthPlace();
      this.Email = person.getEmail();
      this.photo = person.getPhoto();
      this.photoCropped = person.getPhotoCropped();
      this.nationality = person.getNationality();
      this.nativeVillage = person.getNativeVillage();
      this.ethnics = person.getEthnics();
      this.gender = person.getGender();
      this.idNumber = person.getIdNumber();
      this.idNumberIssueDate = person.getIdNumberIssueDate();
      this.phoneNumber = person.getPhoneNumber();
      this.idNumberIssueBy = person.getIdNumberIssueBy();
      this.religion = person.getReligion();
      if (person.getUser() != null) {
         this.user = new User(person.getUser(), false);
      }

      this.carrer = person.getCarrer();
      if (person.getMaritalStatus() != null) {
         this.maritalStatus = person.getMaritalStatus();
      }

   }
}
