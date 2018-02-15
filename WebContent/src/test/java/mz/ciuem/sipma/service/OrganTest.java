package mz.ciuem.sipma.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import mz.ciuem.sipma.GenericTestUnit;
import mz.ciuem.sipma.entity.Cycle;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.OrganType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganTest extends GenericTestUnit {

	@Autowired
	private OrganService organService;

	@Autowired
	private OrganTypeService organTypeService;

	@Autowired
	private CycleService cycleService;

	public OrganType newOrganType() {

		Date now = Calendar.getInstance().getTime();

		OrganType organType = new OrganType();
		organType.setInitials("OrganType" + now);
		organType.setTitle("SIPMA" + now);
		organType.setUpdated(now);
		return organTypeService.create(organType);

	}

	public Organ newOrgan(int num) {

		Date now = Calendar.getInstance().getTime();

		Organ organ = new Organ();
		organ.setDescription("Description" + now);
		organ.setOrganType(newOrganType());
		organ.setDesignation("Organ" + num);
		organ.setUpdated(now);
		return organService.create(organ);

	}

	public Cycle newCycle(int num) {

		Date now = Calendar.getInstance().getTime();

		Cycle c = new Cycle();
		c.setCode(Calendar.getInstance().get(Calendar.YEAR) + "");
		c.setDescription("Description" + now);
		c.setEndDate(now);
		c.setStartDate(new Date());
		c.setState("Started");
		c.setSubject("Cycle" + num);
		c.setType("Type");
		c.setUpdated(new Date());

		return cycleService.create(c);
	}

	@Test
	public void shouldReturnOrgansWithoutCycles() {
		Organ organ1 = newOrgan(1);
		Organ organ2 = newOrgan(2);
		Organ organ3 = newOrgan(3);
		Organ organ4 = newOrgan(4);

		Cycle cycle1 = newCycle(1);
		cycle1.getOrgans().add(organ1);
		
		Cycle cycle2 = newCycle(2);
		cycle2.getOrgans().add(organ3);
		cycle2.getOrgans().add(organ4);

		assertEquals(1, organService.findAllWithoutCycles().size());
		assertEquals(organ2, organService.findAllWithoutCycles().get(0));

	}
	
	@Test
	public void shouldReturnOrgansNotInSelectedCycle() {
		
		Organ organ1 = newOrgan(1);
		Organ organ2 = newOrgan(2);
		
		Cycle cycle1 = newCycle(1);
		cycle1.getOrgans().add(organ2);
		
		Cycle cycle2 = newCycle(2);
		cycle2.getOrgans().add(organ1);
		cycle2.getOrgans().add(organ2);
		
		assertEquals(1, organService.findAllNotInCycle(cycle1).size());
		assertEquals(0, organService.findAllNotInCycle(cycle2).size());
	}
}
