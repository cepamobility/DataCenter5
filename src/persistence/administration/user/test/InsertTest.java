/**
 * Created on 01-mar-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.user.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jrodriguez
 */
public class InsertTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Prueba","postgres","postgres");
            Statement statement = conn.createStatement();
            
            for(int i = 1000; i < 5000; i++) {
                String query = "insert into users values('user"+i+"','user"+i+"','user"+i+"','es','user"+i+"@cepasafedrive.com','457391c9c82bfdcbb4947278c0401e41',2)";
                statement.execute(query);
                query = "insert into usercorporations values(2,'user"+i+"','administrator')";
                statement.execute(query);
            }
            statement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
