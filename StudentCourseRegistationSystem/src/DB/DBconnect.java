
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class DBconnect {
    
    public static Connection connect(){
    
    Connection conn = null;
    
       try{
//          Class.forName("com.mysql.jdbc.Driver");
//          conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/studentcourseregistationsystem", "root", "");

          Class.forName("org.sqlite.JDBC");
          conn = (Connection) DriverManager.getConnection("jdbc:sqlite:studentcourseregistationsystem.sqlite");

       } catch(Exception e){
       JOptionPane.showMessageDialog(null, e);}
    
    return conn;
    }
    
}
