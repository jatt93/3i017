package services;
//import servicesTools;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import ma_bd.UserTools;
 

public class User {
	

	public static JSONObject createUser(String login, String pwd, String nom, String prenom) throws JSONException, SQLException{
			
		JSONObject retour = new JSONObject();
			try{
				if (login == null || pwd == null || nom == null || prenom == null)
					return servicesTools.ServiceRefused.serviceRefused("Missing args",100);
				if(ma_bd.UserTools.userExists(login)){
					
					retour.put("Status","KO");
					retour.put("Error","User exists");

				}else{
					ma_bd.UserTools.addUser(login, pwd, nom, prenom);
					retour.put("Status","OK");
				}
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return retour;
	}
	
	
	public static JSONObject DeleteUser(String login) throws JSONException, SQLException {
		if (login == null)
			return servicesTools.ServiceRefused.serviceRefused("Missing arg",100);
		if (!ma_bd.UserTools.userExists(login))
			return servicesTools.ServiceRefused.serviceRefused("User doesn't exist", 100);
		
        return ma_bd.UserTools.deleteUser(login);
	}
	
	
	public static JSONObject Login(String login, String pwd) throws JSONException, SQLException{
		JSONObject retour = new JSONObject();
		
		try{
			if(login == null || pwd == null){
				return servicesTools.ServiceRefused.serviceRefused("Wrong Argument", -1);
			}
			
			if(!ma_bd.UserTools.userExists(login)){
				retour.put("Status", "KO");
				retour.put("Error", "User doesn't exist ! Join us :) ");
				return retour;
			
			}
			if(!ma_bd.UserTools.checkPassword(login,pwd)){
				retour.put("Status", "KO");
				retour.put("Error", "Wrong Password ! Please try again");
				return retour;
			}
			
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		boolean root=ma_bd.UserTools.is_root(login);
		if (root){
			String key = ma_bd.UserTools.insertConnection(login,root);
			retour.put("Status","OK");
			retour.put("Key",key);
			return retour;
		} else {
		String key_2 = ma_bd.UserTools.insertConnection(login);
		retour.put("Status","OK");
		retour.put("Key",key_2);
		return retour;
		}
	}
	
		 

		public static JSONObject Logout(String key) throws JSONException,SQLException{
			if (key == null)
				return servicesTools.ServiceRefused.serviceRefused("key manquant",100);
			return ma_bd.UserTools.insertDisconnection(key);
		}

}

