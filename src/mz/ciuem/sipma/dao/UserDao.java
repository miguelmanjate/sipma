package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.User;

public interface UserDao extends GenericDao<User> {

	public User getUser(String login);
	public List<User> getUserByOrgan(Organ organ);
}
