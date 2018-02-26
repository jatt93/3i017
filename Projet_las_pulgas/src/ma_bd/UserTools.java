package ma_bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import servicesTools.ServiceRefused;
import servicesTools.ServiceAccepted;

import org.json.JSONObject;

import com.mongodb.DBCollection;

public class UserTools {

	
	/* Permet de faire toutes les opérations sur la bd. */
	

	public static boolean userExists(String login)throws SQLException{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="SELECT login FROM Users WHERE login='"+login+"';";
		ResultSet curseur = lecture.executeQuery(query);		
		boolean retour;		
		if (curseur.next())
			retour=true;
		else
			retour=false;
		curseur.close();
		lecture.close();
		c.close();
		return retour;
	}

	public static JSONObject deleteUser(String login) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="DELETE from Users where login='"+login+"';";
		JSONObject retour = ServiceAccepted.ServiceAccepted();
		try
		{
			lecture.executeUpdate(query);
		}
		catch (SQLException e)
		{
			retour = ServiceRefused.serviceRefused("KO",100);
		}
		lecture.close();
		c.close();
		return retour;
	}

	public static JSONObject addUser(String login, String mdp, String prenom, String nom) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="insert into Users values (NULL,'"+login+"','"+mdp+"','"+prenom+"','"+nom+"');";
		JSONObject retour = ServiceAccepted.ServiceAccepted();
		try
		{
			lecture.executeUpdate(query);
		}
		catch (SQLException e)
		{
			retour = ServiceRefused.serviceRefused("KO",100);
		}
		lecture.close();
		c.close();
		return retour;
	}

	public static boolean checkPassword(String login, String pwd) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="select * from Users where login='"+login+"';";
		ResultSet cursor=lecture.executeQuery(query);
		boolean retour;
		if (!cursor.next())
			retour=false;
		else
			retour=cursor.getString("password").equals(pwd);
		lecture.close();
		c.close();
		return retour;
	}

	public static boolean is_root(String login) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="select * from Sessions where login='"+login+"';";
		ResultSet cursor=lecture.executeQuery(query);
		boolean retour;
		boolean reponse;
		reponse = cursor.getBoolean("is_root");
		if (reponse)
			retour= true;
		else
			retour= false;
		lecture.close();
		c.close();
		return retour;
	}
	
	public static int getUserId(String login) throws SQLException{		
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="select * from Users where login='"+login+"';";
		ResultSet cursor=lecture.executeQuery(query);
		int user_id=0;
		while (cursor.next())
			user_id=cursor.getInt("Id");
		lecture.close();
		c.close();
		return user_id;
	}
	
	public static int getUserId2(String key) throws SQLException{		
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="select * from Sessions s where s.key='"+key+"';";
		ResultSet cursor=lecture.executeQuery(query);
		int user_id=0;
		while (cursor.next())
			user_id=cursor.getInt("id_user");
		lecture.close();
		c.close();
		return user_id;
	}

	public static String generate_key() {
		String key = "";
		char c;
		for(int i=0; i<32; i++) 
		{
			Random r = new Random();
			if(Math.random() < 0.5) 
				c = (char)(r.nextInt(26) + 'A');
			else
				c = (char)(r.nextInt(26) + 'a');
			key += c;			
		}
		return key;		
	}
	
	

	
	public static String insertConnection(String login) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String key = generate_key();
		int id_user = getUserId(login);
		String query="INSERT into Sessions values ("+id_user+",CURRENT_TIMESTAMP,'"+key+"',false,0);";
		lecture.executeUpdate(query);
		lecture.close();
		c.close();
		return key;
	}

	public static String insertConnection(String login, boolean root) throws SQLException {
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String key = generate_key();
		int id_user = getUserId(login);
		String query="INSERT into Sessions values ("+id_user+",CURRENT_TIMESTAMP,'"+key+"','"+root+"',0);";
		lecture.executeUpdate(query);
		lecture.close();
		c.close();
		return key;
	}

	public static JSONObject insertDisconnection(String key) throws SQLException {
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		String query="update Sessions s set expired=true where s.key='"+key+"';";
		lecture.executeUpdate(query);
		lecture.close();
		c.close();
		return ServiceAccepted.ServiceAccepted();
	}
	public static JSONObject AddFriend(String key, int id_friend) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		int user_id=getUserId2(key);
		String query="insert into Friends values ("+user_id+","+id_friend+",CURRENT_TIMESTAMP)";
		JSONObject retour = ServiceAccepted.ServiceAccepted();
		try
		{
			lecture.executeUpdate(query);
		}
		catch (SQLException e)
		{
			retour = ServiceRefused.serviceRefused("KO",100);
		}
		lecture.close();
		c.close();
		return retour;
	}
	public static JSONObject RemoveFriend(String key, int id_friend) throws SQLException
	{
		Connection c=ma_bd.Database.getMySQLConnection();
		Statement lecture = c.createStatement();
		int user_id=getUserId2(key);
		String query="delete from Friends where id_user="+user_id+" and id_friend="+id_friend+";";
		JSONObject retour = ServiceAccepted.ServiceAccepted();
		try
		{
			lecture.executeUpdate(query);
		}
		catch (SQLException e)
		{
			retour = ServiceRefused.serviceRefused("KO",100);
		}
		lecture.close();
		c.close();
		return retour;
	}

	public static String ListMessage(String users) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DBCollection AddMessage(String key, String message) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
