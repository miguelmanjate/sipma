package mz.ciuem.sipma.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Objects;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User extends IdEntity implements UserDetails {

	private static final long serialVersionUID = 6311364761937265306L;

	@NotNull(message = "Utilizador não pode ser vazio")
	@NotEmpty(message = "Utilizador não pode conter apenas espaços em branco")
	@Size(max = 50, message = "Utilizador não deve ter mais de 50 caracteres")
	@Column(name = "username", length = 50, unique=true)
	@org.hibernate.validator.constraints.Email(message = "Email inváildo")
	private String username;

	@NotNull(message = "Senha não pode ser vazia")
	@NotEmpty(message = "Senha não pode conter apenas espaços em branco")
	@Column(columnDefinition = "LONGTEXT")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
	private Set<UserRole> roles = new HashSet<UserRole>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id", nullable=true)
	private Employee responsible;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organ", nullable=true)
	private Organ organ;

	@Transient
	private String confirmPassword;

	public User(String username2, String password2, boolean accountIsEnabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Set<Permission> perms) {
		// TODO Auto-generated constructor stub
	}

	public User() {
		this.enabled = true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)",
				this.getClass().getSimpleName(), this.getId(),
				this.getUsername(), this.getPassword(), this.getEnabled());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof User) {
			final User other = (User) o;
			return Objects.equal(getId(), other.getId())
					&& Objects.equal(getUsername(), other.getUsername())
					&& Objects.equal(getPassword(), other.getPassword())
					&& Objects.equal(getEnabled(), other.getEnabled());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getUsername(), getPassword(),
				getEnabled());
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();
		for (UserRole role : roles) {
			perms.addAll(role.getPermissions());
		}
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRoles());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Organ getOrgan() {
		return organ;
	}

	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	
	

}