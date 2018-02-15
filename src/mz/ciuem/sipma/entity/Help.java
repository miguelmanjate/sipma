package mz.ciuem.sipma.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "helps")
@Access(AccessType.FIELD)
public class Help extends IdEntity {

	private static final long serialVersionUID = -1226738822876735625L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_id", nullable = false)
	private User who;

	private String what;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_id", nullable = false)
	private User support;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attach_id", nullable = true)
	private File attach;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "help_comments", joinColumns = { @JoinColumn(name = "help_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "comment_id", nullable = false, updatable = false) })
	private Set<Comment> comments = new HashSet<Comment>();

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(who);
		hcb.append(what);
		hcb.append(support);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Help)) {
			return false;
		}
		Help that = (Help) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(who, that.who);
		eb.append(what, that.what);
		eb.append(support, that.support);
		return eb.isEquals();
	}

	public User getWho() {
		return who;
	}

	public void setWho(User who) {
		this.who = who;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public User getSupport() {
		return support;
	}

	public void setSupport(User support) {
		this.support = support;
	}

	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
}
