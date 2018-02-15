package mz.ciuem.sipma.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "allocation_sectors")
@Access(AccessType.FIELD)
public class AllocationSector extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "task")
	private String task;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sector_id")
	private Sector sector;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsible_id")
	private Employee responsible;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "allocationOrgan_id")
	private AllocationOrgan allocationOrgan;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "allocationSector")
	private Set<SectorExecution> sectorExecutions = new HashSet<SectorExecution>();

	public int rate() {

		int rate = 0;
		if(sectorExecutions!=null && !sectorExecutions.isEmpty()){
			for (SectorExecution sectorExecution : sectorExecutions) {
				if (sectorExecution.getRate() <= 100) {

					rate += sectorExecution.getRate();
				} else {
					JOptionPane.showMessageDialog(null,
							"A Percentagem Nao Deve Passar de 100");
				}
			}
		}
	
		return rate;
	}

	private boolean state;

	public AllocationSector() {

		this.setState(false);
	}

	public Employee getResponsible() {
		return responsible;
	}

	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(startDate);
		hcb.append(endDate);
		hcb.append(sector);
		hcb.append(allocationOrgan);
		hcb.append(state);

		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllocationSector)) {
			return false;
		}
		AllocationSector that = (AllocationSector) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(startDate, that.startDate);
		eb.append(endDate, that.endDate);
		eb.append(sector, that.sector);
		eb.append(allocationOrgan, that.allocationOrgan);
		return eb.isEquals();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public AllocationOrgan getAllocationOrgan() {
		return allocationOrgan;
	}

	public void setAllocationOrgan(AllocationOrgan allocationOrgan) {
		this.allocationOrgan = allocationOrgan;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
