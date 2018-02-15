package mz.ciuem.sipma.service;

import java.util.List;

import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.User;

public interface UserService extends GenericService<User> {

	public User getUser(String login);
	public List<User> getUserByOrgan(Organ organ);
	public String encriptarSenha(String senha);
}
