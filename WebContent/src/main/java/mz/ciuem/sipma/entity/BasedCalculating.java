package mz.ciuem.sipma.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "based_calculating")
@Access(AccessType.FIELD)
public class BasedCalculating extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2120847407157024485L;

	@Column(name = "designation")
	private String designation;

	@Column(name = "cost")
	private Float cost;
	
	@Column(name = "quantity")
	private Float quantity;
	
	@Column(name = "unit_cost")
	private Float unitCost;
	
	@Column(name = "bar_code", nullable=true)
	private String barCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specified_action_id")
	private SpecifiedAction specifiedAction;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "font_id")
	private Font font;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_rubric_id", nullable=true)
	private SubRubric subRubric;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rubric_id")
	private Rubric rubric;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "super_rubric")
	private SuperRubric superRubric;
	
	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public SuperRubric getSuperRubric() {
		return superRubric;
	}

	public void setSuperRubric(SuperRubric superRubric) {
		this.superRubric = superRubric;
	}

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(designation);
        hcb.append(cost);
        hcb.append(font);
        hcb.append(subRubric);
        hcb.append(superRubric);
        hcb.append(rubric);
        
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BasedCalculating)) {
            return false;
        }
        BasedCalculating that = (BasedCalculating) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(designation, that.designation);
        eb.append(cost, that.cost);
        eb.append(font, that.font);
        eb.append(subRubric, that.subRubric);
        eb.append(rubric, that.rubric);
        eb.append(superRubric, that.superRubric);
        return eb.isEquals();
    }

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public SpecifiedAction getSpecifiedAction() {
		return specifiedAction;
	}

	public void setSpecifiedAction(SpecifiedAction specifiedAction) {
		this.specifiedAction = specifiedAction;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public SubRubric getSubRubric() {
		return subRubric;
	}

	public void setSubRubric(SubRubric subRubric) {
		this.subRubric = subRubric;
	}
	
	

}
