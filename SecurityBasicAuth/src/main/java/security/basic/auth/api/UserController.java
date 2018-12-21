package security.basic.auth.api;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import security.basic.auth.user.domain.User;
import security.basic.auth.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public User userSave(User user) {		
		return userService.userSave(user);
	}
	
	@GetMapping("/getUser")
	public Optional<User> getUser(User user){
		return userService.getUser(user);
	}
	
	@PostMapping("/login")
	public Optional<User> login(HttpServletResponse res, User user){
		Optional<User> userInfo= userService.getUser(user);
		boolean pass = user.getUserPw().equals(userInfo.get().getUserPw());
		if( pass ) {
			return userInfo;
			
		}
		return null;
	}
}
