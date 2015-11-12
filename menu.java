import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu{
	
	public static JMenuBar menubar(){
		JMenuBar outMenuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Sheets");
		menu1=addItems(menu1);
		outMenuBar.add(menu1);
		return outMenuBar;
		}
		
	public static JMenu addItems(JMenu menu1){
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		conn=null;
        stmt=null;
        rs=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/ingsuroo","inguser", "baruna");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id_areas, nombre_areas, division_areas FROM areas;");
			JMenuItem item = null;
			while(rs.next()){ ///
				item = new JMenuItem(rs.getString("nombre_areas"));
				item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				String command=event.paramString();
				command=command.substring(command.indexOf("cmd=")+4);
				command=command.substring(0,command.indexOf(","));
                System.out.println(command);
            }
        });
				menu1.add(item);
				item=null;
			}
		
		 }catch(ClassNotFoundException e1){
            System.out.println( e1.getClass().getName()+": "+ e1.getMessage() );
            return null;
        }catch(SQLException e2){
            System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
            return null;
        }
        return menu1;
	}
	

}
