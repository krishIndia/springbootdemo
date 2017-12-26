/*package com.mfg.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mfg.demo.DemoApplication;
import com.mfg.demo.model.User;
import com.mfg.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={DemoApplication.class})
@ActiveProfiles("test")
public class UserRepositoryTest {

	private UserRepository userRepository;

	
	   
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/krishna");
	        dataSource.setUsername("krishna");
	        dataSource.setPassword("Elisa12345");
	 
	        return dataSource;
	    }
	
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Test 
	public void addUser() {
		User user = new User();
		user.setId(100l);
		user.setUsername("krishna");
		user.setAddress("dasda");
		user.setEmail("a@gmail.com");
		user.setMobile(999999999);
		user.setPassword("Elisa12345");
		user.setPasswordConfirm("Elisa12345");
		userRepository.save(user);

	}

	@Test
	public void updateUser() {
		User user = new User();
		user.setId(100l);
		user.setAddress("aaaa");
		user.setEmail("bb@gmail.com");
		user.setMobile(888888888);
		user.setPassword("fffaasf");
		userRepository.save(user);

	}

	@Test
	public void deleteUser() {
		User user = new User();
		user.setId(100l);
		assertNull(user.getId());
		userRepository.delete(user.getId());
	}

}
*/