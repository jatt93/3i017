package test;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import com.mongodb.DBCollection;

public class TestMessage {
public static void main(String[] args) throws SQLException, JSONException, ClassNotFoundException, UnknownHostException {
	DBCollection c=ma_bd.Database.getCollection("message");
	System.out.println("Add Message: "+services.Messages.AddMessage("IDiWqmhkhSbsdSGmmdWrcBFSLBYMJJCr","test4").toString());
	String id_object="gtfHVnDcynICOnbtomrIirHsVKbSZJCs";
	System.out.println("delete Message: "+services.Messages.DeleteMessage("IDiWqmhkhSbsdSGmmdWrcBFSLBYMJJCr",id_object).toString());
	}
}