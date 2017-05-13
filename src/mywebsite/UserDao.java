package mywebsite;

import net.sf.json.JSONArray;

public interface UserDao {

	void add_message(User user);
	
	JSONArray get_all();	
	
}
