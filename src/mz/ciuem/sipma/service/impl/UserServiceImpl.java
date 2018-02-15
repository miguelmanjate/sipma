package mz.ciuem.sipma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mz.ciuem.sipma.dao.UserDao;
import mz.ciuem.sipma.entity.Employee;
import mz.ciuem.sipma.entity.Organ;
import mz.ciuem.sipma.entity.User;
import mz.ciuem.sipma.service.EmployeeService;
import mz.ciuem.sipma.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public User getUser(String login) {
		return userDao.getUser(login);
	}

	@Override
	public User create(User u) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(hashedPassword);
		
		User user = userDao.create(u);
		
		Employee emp = employeeService.find(u.getResponsible().getId());
		emp.setUserLogin(user);
		employeeService.update(emp);
		

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		return getUser(username);
	}

	@Override
	public List<User> getUserByOrgan(Organ organ) {
		return userDao.getUserByOrgan(organ);
	}

	@Override
	public String encriptarSenha(String senha){

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(senha);
		
		return hashedPassword;
	}

}
