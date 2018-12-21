package security.basic.auth.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import security.basic.auth.user.domain.User;

public interface UserDao extends JpaRepository<User, Long>{

}
