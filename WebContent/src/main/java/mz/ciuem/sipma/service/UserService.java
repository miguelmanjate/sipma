package mz.ciuem.sipma.service;

import mz.ciuem.sipma.entity.User;

public interface UserService extends GenericService<User> {

	public User getUser(String login);
}
