package mz.ciuem.sipma.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "individuals")
@Access(AccessType.FIELD)
public abstract class Individual extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "name")
	protected String name;

	@Column(name = "last_name")
	protected String lastName;

	@Column(name = "middle_name")
	protected String middleName;

	@Column(name = "birthday")
	protected Date birthday;

	@Column(name = "id_number")
	protected String idNumber;

	@Column(name = "gender")
	protected String gender;

	@Column(name = "nationality")
	protected String nationality;

	@Column(name = "academic_level")
	protected String academicLevel;

	@Column(name = "marital_status")
	protected String maritalStatus;

	@Column(name = "number_children")
	protected int numberChildren;

	@OneToOne
	@JoinColumn(name = "type_id")
	protected IdentityDocumentType type;

	@Column(name = "address")
	protected String address;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<ContactPoint> contactPoints = new HashSet<ContactPoint>();

	public Set<ContactPoint> getContactPoints() {
		return contactPoints;
	}

	public void setContactPoints(Set<ContactPoint> contactPoints) {
		this.contactPoints = contactPoints;
	}

	public IdentityDocumentType getType() {
		return type;
	}

	public void setType(IdentityDocumentType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getNumberChildren() {
		return numberChildren;
	}

	public void setNumberChildren(int numberChildren) {
		this.numberChildren = numberChildren;
	}

	public String fullName() {

		if (middleName == null) {
			return name + " " + lastName;
		}

		return name + " " + middleName + " " + lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
