package mz.ciuem.sipma.entity;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import mz.ciuem.sipma.util.Carrier;
import mz.ciuem.sipma.util.Category;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "employees")
@Access(AccessType.FIELD)
public class Employee extends Individual {

	private static final long serialVersionUID = 1L;

	@Column(name = "employee_code", unique = true)
	private String employeeCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector")
	private Sector sector;

	@Column(name = "nuit_number")
	private String nuitNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_sector")
	private SubSector subSector;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subScriber", nullable = true)
	private SubScriber subScriber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ")
	private Organ organ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profession")
	private Profession profession;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userLogin;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private Image imageProfile;

	private String cellPhone;

	@Column(name = "process_number")
	private String processNumber;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Enumerated(EnumType.STRING)
	private Carrier carrier;
	
	@Column(name = "corpo")
	private String  corpo;

	

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(name);
		hcb.append(lastName);
		hcb.append(userLogin);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee that = (Employee) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(name, that.name);
		eb.append(lastName, that.lastName);
		eb.append(userLogin, that.userLogin);
		return eb.isEquals();
	}

	public String since() {

		Locale ptBr = new Locale("pt", "BR");

		SimpleDateFormat sdf = new SimpleDateFormat("MMM, yyyy", ptBr);

		return sdf.format(getCreated());
	}

	@Override
	public String toString() {

		if (middleName == null) {
			return name + " " + lastName;
		}

		return name + " " + middleName + " " + lastName;
	}

	@PrePersist
	public void generateEmployeeCode() {
		this.setEmployeeCode(UUID.randomUUID().toString());
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public User getUserLogin() {
		return userLogin;
	}

	public String getNuitNumber() {
		return nuitNumber;
	}

	public void setNuitNumber(String nuitNumber) {
		this.nuitNumber = nuitNumber;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	public Image getImageProfile() {
		return imageProfile;
	}

	public void setImageProfile(Image imageProfile) {
		this.imageProfile = imageProfile;
	}

	public SubSector getSubSector() {
		return subSector;
	}

	public void setSubSector(SubSector subSector) {
		this.subSector = subSector;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}

	public SubScriber getSubScriber() {
		return subScriber;
	}

	public void setSubScriber(SubScriber subScriber) {
		this.subScriber = subScriber;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}


}
