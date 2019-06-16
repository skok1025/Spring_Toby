package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import springbook.user.domain.User;

public class UserDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws ClassNotFoundException,SQLException{
		deleteAll();
		Connection conn = dataSource.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"insert into users(id,name,password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();	
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection conn  = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users where id =?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
	
		rs.close();
		ps.close();
		conn.close();
		
		return user;
	}
	
	public void deleteAll() throws SQLException,ClassNotFoundException{
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete from users");
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
	}
	
	
	// test 
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("skok1025");
		user.setName("±è¼®Çö");
		user.setPassword("1234");
		
		dao.add(user);
		
		System.out.println("¼º°ø");
	}

}
