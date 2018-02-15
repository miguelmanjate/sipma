package mz.ciuem.sipma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "notification_msgs")
public class NotificationMsg extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "type", unique = true)
	private String type;

	@Column(name = "msg")
	private String msg;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(type);
		hcb.append(msg);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof NotificationMsg)) {
			return false;
		}
		NotificationMsg that = (NotificationMsg) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(type, that.type);
		eb.append(msg, that.msg);
		return eb.isEquals();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
