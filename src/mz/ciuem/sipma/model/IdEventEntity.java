package mz.ciuem.sipma.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mz.ciuem.sipma.interfaces.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.zkoss.calendar.impl.SimpleCalendarEvent;

@MappedSuperclass
public class IdEventEntity extends SimpleCalendarEvent implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1456273248816418744L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String content;
	
	private String headerColor;

	private String contentColor;
	
	private boolean locked = true;
	
	public IdEventEntity() {
		
		setHeaderColor("#FFFFFF");
		setContentColor("#000000");
	}

	@PrePersist
	public void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Long getId() {
		return id;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d)", this.getClass().getSimpleName(),
				this.getId());
	}

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(id);
        hcb.append(beginDate);
        hcb.append(endDate);
        hcb.append(content);
        hcb.append(headerColor);
        hcb.append(contentColor);
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IdEventEntity)) {
            return false;
        }
        IdEventEntity that = (IdEventEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(id, that.id);
        eb.append(beginDate, that.beginDate);
        eb.append(endDate, that.endDate);
        eb.append(content, that.content);
        eb.append(headerColor, that.headerColor);
        eb.append(contentColor, that.contentColor);
        return eb.isEquals();
    }
    
	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(String headerColor) {
		this.headerColor = headerColor;
	}

	public String getContentColor() {
		return contentColor;
	}

	public void setContentColor(String contentColor) {
		this.contentColor = contentColor;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
