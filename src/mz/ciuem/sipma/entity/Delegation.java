package mz.ciuem.sipma.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "delegations")
@Access(AccessType.FIELD)
public class Delegation extends IdEntity {

	
	private static final long serialVersionUID = -393731508484780801L;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Permission> permissions = new HashSet<Permission>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id")
	private Employee responsible;

	private String comment;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(employee);
		hcb.append(permissions);
		hcb.append(comment);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Delegation)) {
			return false;
		}
		Delegation that = (Delegation) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(permissions, that.permissions);
		eb.append(employee, that.employee);
		eb.append(comment, that.comment);
		return eb.isEquals();
	}

	@Override
	public String toString() {

		String perms = "";
		for (Permission permission : permissions) {
			perms += permission.getPermissionname() + ", ";
		}

		return perms.substring(0, perms.length() - 2);
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}
}
