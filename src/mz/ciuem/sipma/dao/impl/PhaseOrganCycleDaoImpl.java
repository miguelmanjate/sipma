package mz.ciuem.sipma.dao.impl;

import java.util.List;

import javax.persistence.Query;

import mz.ciuem.sipma.dao.PhaseOrganCycleDao;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.PhaseOrganCycle;

import org.springframework.stereotype.Repository;

@Repository
public class PhaseOrganCycleDaoImpl extends GenericDaoImpl<PhaseOrganCycle>
		implements PhaseOrganCycleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PhaseOrganCycle> filterByOrgan(Organ organ) {

		Query query = em
				.createQuery("select poc from PhaseOrganCycle poc where poc.organ = ?");
		query.setParameter(1, organ);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhaseOrganCycle> filterByState(String state) {

		Query query = em
				.createQuery("select poc from PhaseOrganCycle poc where poc.acepted = ?");
		query.setParameter(1, state);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhaseOrganCycle> allNotAcepted() {

		Query query = em
				.createQuery("select poc from PhaseOrganCycle poc where poc.acepted = ? or poc.acepted = ?");
		query.setParameter(1, "success");
		query.setParameter(2, "embarrassment");

		return query.getResultList();
	}

}
