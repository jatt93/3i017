package services;

import java.awt.List;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONException;

import com.mongodb.DBCollection;

public class Messages {

		public static DBCollection AddMessage(String key,String message)throws JSONException, UnknownHostException, SQLException
		{
			return ma_bd.UserTools.AddMessage(key,message);
		}
		
		//public static DBCollection RemoveMessage(String key,int id_message)throws JSONException, UnknownHostException
		//{
		//	return ma_bd.UserTools.RemoveMessage(key,id_message);
		//}
		
		public static String ListMessage(String users)throws JSONException, UnknownHostException
		{
			return ma_bd.UserTools.ListMessage(users);
		}
	}
