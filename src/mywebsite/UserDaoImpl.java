package mywebsite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class UserDaoImpl implements UserDao{

	@Override
	public void add_message(User user) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			//获取一个数据库连接
			conn = JdbcUtils.getConnection();
			String sql = "insert into message(name,time,message) values(?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getName());
			st.setString(2, user.getTime());
			st.setString(3, user.getMessage());
			int num = st.executeUpdate();
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
		
	}

	@Override
	public JSONArray get_all() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		JSONArray js = new JSONArray();
		String name, time, message;
		
		List<User> userlist = new ArrayList<User>();
		
		try{
			conn = JdbcUtils.getConnection();
			String sql = "select * from message order by time desc;";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()){
				User userdata = new User();
				name = rs.getString("name");
				time = rs.getString("time");
				message = rs.getString("message");
				userdata.setName(name);
				userdata.setTime(time);
				userdata.setMessage(message);
				userlist.add(userdata);
				
			}
			
			js = JSONArray.fromObject(userlist);
			return js;
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.release(conn, st, rs);
		}
		
	}

}
