package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "criterias")
@Access(AccessType.FIELD)
public class Criteria extends IdEntity {

	private static final long serialVersionUID = -4546282628184789335L;

	private String name;

	private int weight;

	private String description;

	private String type;

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(name);
		hcb.append(weight);
		hcb.append(type);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Criteria)) {
			return false;
		}
		Criteria that = (Criteria) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(name, that.name);
		eb.append(weight, that.weight);
		eb.append(type, that.type);
		return eb.isEquals();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
