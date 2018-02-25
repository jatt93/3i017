package servlets.friend;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.friend.AddFriend;

public class ServletAddFriend extends HttpServlet {
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	

			String key = request.getParameter("key");
			String content = request.getParameter("id_friend");	
			JSONObject retour= new JSONObject();
			
			try{
				retour = services.AddFriend(key, id_friend);
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("/text/plain");
			PrintWriter out = response.getWriter ();
			out.println(retour.toString() );
		 }
		 

}
