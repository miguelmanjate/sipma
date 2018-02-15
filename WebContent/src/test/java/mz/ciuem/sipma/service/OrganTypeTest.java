package mz.ciuem.sipma.service;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

import mz.ciuem.sipma.GenericTestUnit;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.OrganType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganTypeTest extends GenericTestUnit {

	private List<Organ> organs;

	@Autowired
	private OrganService organService;

	@Autowired
	private OrganTypeService organTypeService;

	public OrganType createNewOrganType() {

		organs = organService.getAll();
		OrganType organType = new OrganType();
		organType.setInitials("CIUEM");
		organType.setTitle("Centro de Informatica");
		organType.setUpdated(Calendar.getInstance().getTime());
		organType.setOrgans(organs);
		return organType;

	}

	@Test
	public void createTest() {
		OrganType type = createNewOrganType();
		OrganType typeSave = organTypeService.create(type);

		assertNotNull(typeSave);
		assertNotNull(typeSave.getId());

	}

	@Test
	public void findByIdAllOrganType() {
		OrganType organType = createNewOrganType();
		OrganType type = organTypeService.create(organType);
		assertNotNull(type.getId() != null);
	}



}
