package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

	@Test
	public void addAndget() throws SQLException, ClassNotFoundException {
		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao",UserDao.class);
	
		dao.deleteAll();
		User user = new User();
		user.setId("user2");
		user.setName("김석현");
		user.setPassword("1234");
		
		dao.add(user);
			
		User user2 = dao.get(user.getId());
//1.	System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		
//		System.out.println(user2.getId()+" 조회 성공");

//2.	if(!user.getName().equals(user2.getName())) {
//			System.out.println("테스트 실패 (name)");
//		}
//		else if(!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("테스트 실패 (password)");
//		}
//		else {
//			System.out.println("조회 테스트 성공");
//		}
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
	}
	
}
