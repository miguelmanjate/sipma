package mz.ciuem.sipma.dao;

import mz.ciuem.sipma.entity.User;

public interface UserDao extends GenericDao<User> {

	public User getUser(String login);
}
