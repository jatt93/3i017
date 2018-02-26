package servlets.friend;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend.ListFriend;

public class ListFriend extends HttpServlet {
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	
		String key= request.getParameter("key"); 
		String id_user = request.getParameter("id_user"); 
		String index_debut = request.getParameter("index_debut");
		String nb_demandes = request.getParameter("nb_demandes");	
		JSONObject retour= new JSONObject();
			
		try{
			retour = services.Friend.ListFriend(key, id_user, index_debut, nb_demandes);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			response.setContentType("/text/plain");
			PrintWriter out = response.getWriter ();
			out.println(retour.toString() );
	}
}
