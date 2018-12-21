package security.basic.auth.user.service;

import java.util.Optional;

import security.basic.auth.user.domain.User;

public interface UserService {

	public User userSave(User user);

	public Optional<User> getUser(User user);

	
}
