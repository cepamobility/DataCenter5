package communication.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Created on 21-dic-2006
 * CEPA
 * DataCenter 5
 */

public class UpdateUserPermissions implements Serializable {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String insert = "";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/respaldo","postgres","postgres");
        
            File f = new File("/home/jrodriguez/Desktop/userpermissions.sql");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write("DELETE FROM userpermissions;\n".getBytes());

            Statement st1 = conn1.createStatement();
            Statement st2 = conn1.createStatement();

            Hashtable rolePermissions = new Hashtable();
            ResultSet set1 = st1.executeQuery("SELECT resourcekey FROM roles");
            while(set1.next()) {
                String role = set1.getString(1);
                Collection permissions = new LinkedList();
                ResultSet set2 = st2.executeQuery("SELECT permission FROM rolepermissions WHERE resourcekey = '"+role+"'");
                while(set2.next()) {
                    permissions.add(set2.getString(1));
                }
                rolePermissions.put(role,permissions);
            }
            
            set1 = st1.executeQuery("SELECT loginname,id,role FROM usercorporations");
            while(set1.next()) {
                String user = set1.getString(1);
                String corporation = set1.getString(2);
                Iterator it = ((Collection)rolePermissions.get(set1.getString(3))).iterator();
                while(it.hasNext()) {
                    String permission = (String)it.next();
                    if(permission.equals("viewreport")) {
                        if(!corporation.equals("4")) {
                            insert = "INSERT INTO userpermissions VALUES ("+corporation+",'"+user+"','"+permission+"');\n";
                            fos.write(insert.getBytes());
                        }
                    }else if(permission.equals("viewreportjohnson")) {
                        if(corporation.equals("4")) {
                            insert = "INSERT INTO userpermissions VALUES ("+corporation+",'"+user+"','"+permission+"');\n";
                            fos.write(insert.getBytes());
                        }
                    }else {
                        insert = "INSERT INTO userpermissions VALUES ("+corporation+",'"+user+"','"+permission+"');\n";
                        fos.write(insert.getBytes());
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
