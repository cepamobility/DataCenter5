/**
 * Created on 23-ene-2006
 * CEPA
 * DataCenter 5
 */
package business.security;

import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.DatabaseServerLoginModule;

import communication.util.MD5;

/**
 * @author jrodriguez
 */
public class AuthentificationModule extends DatabaseServerLoginModule {

    protected Group[] getRoleSets() throws LoginException {
    	
       String username = getUsername();
        Connection conn = null;
        HashMap setsMap = new HashMap();
        PreparedStatement ps = null;
  
        try {
           
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dsJndiName);
            conn = ds.getConnection();

//            ps = conn.prepareStatement("select corporationid from users where loginname=? and corporationid = 4");
            ps = conn.prepareStatement("select corporationid from users where loginname=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ps = conn.prepareStatement("select permission from userpermissions where userid=? and corporationid=?");
                ps.setString(1, username);
                ps.setInt(2, rs.getInt(1));
                
                ResultSet roles = ps.executeQuery();
                if(roles.next()) {
                    do {
                        String name = roles.getString(1);
                        String groupName = "Roles";
                        Group group = (Group) setsMap.get(groupName);
                        if(group == null) {
                            group = new SimpleGroup(groupName);
                            setsMap.put(groupName, group);
                        }
                        group.addMember(new SimplePrincipal(name));
                    } while(roles.next());
                }else {    
                    if(getUnauthenticatedIdentity() == null) {
                        throw new FailedLoginException("No matching username found in Roles");
                    }
                    Group[] roleSets = { new SimpleGroup("Roles") };
                    return roleSets;
                }
                roles.close();
                rs.close();
            }
        }catch(NamingException ex) {
            ex.printStackTrace();
           throw new LoginException(ex.toString(true));
        }catch(SQLException ex) {
            ex.printStackTrace();
           super.log.error("SQL failure", ex);
           throw new LoginException(ex.toString());
        }finally {
           if(ps != null) {
              try{
                 ps.close();
              }catch(SQLException e){}
           }
           if(conn != null) {
              try {
                 conn.close();
              }catch (Exception ex)
              {}
           }
        }
        Group[] roleSets = new Group[setsMap.size()];
        setsMap.values().toArray(roleSets);
        return roleSets;
    }
    
   

    protected String[] getUsernameAndPassword() throws LoginException {
        Connection conn = null;
        PreparedStatement ps = null;
        String[] info = super.getUsernameAndPassword();
        
        if(info[0] != null) { 
	        info[0] = info[0].trim();
	        info[1] = info[1].trim();
	        boolean encriptar = true;
	        if(info[1].length() > 13) {
	            try {
	                InitialContext ctx = new InitialContext();
	                DataSource ds = (DataSource) ctx.lookup(dsJndiName);
	                conn = ds.getConnection();
	                String password = info[1].substring(0,info[1].length()-13);
	                String date = info[1].substring(info[1].length()-13);
	                ps = conn.prepareStatement("select * from userslogins where logindate=?");
	                ps.setString(1, date);
	                ResultSet rs = ps.executeQuery();
	                if(!rs.next()) {
	                    ps = conn.prepareStatement("select * from users where loginname=? and password=?");
	                    ps.setString(1, info[0]);
	                    ps.setString(2, password);
	                    rs = ps.executeQuery();
	                    if(rs.next()) {
	                        info[1] = password;                        
	                        encriptar = false;
	                    }
	                }
	            }catch(Exception ex) {
	                ex.printStackTrace();
	            }finally {
	                if(ps != null) {
	                   try{
	                      ps.close();
	                   }catch(SQLException e){}
	                }
	                if(conn != null) {
	                   try {
	                      conn.close();
	                   }catch (Exception ex)
	                   {}
	                }
	            }
	        }
	        if(encriptar) {
	        	
	            if(info[1] != null) {
	                info[1] = new MD5().encriptar(info[1]);
	            }
	        }
        }
        return info;
    }
    
}
