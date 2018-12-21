package security.basic.auth.user.service.impl;

import java.util.Optional;

import javax.xml.ws.WebServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import security.basic.auth.user.dao.UserDao;
import security.basic.auth.user.domain.User;
import security.basic.auth.user.service.UserService;

@Service
public class UserSeviceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public User userSave(User user) {
		return userDao.save(user);
		
	}

	@Override
	public Optional<User> getUser(User user) {
	 return userDao.findById(user.getId());
	}

}
