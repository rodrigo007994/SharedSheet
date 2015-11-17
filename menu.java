
import javax.swing.JFrame;
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
import javax.swing.JOptionPane;

public class menu{
	
	public static void menubar(JFrame frame1){
		JMenuBar outMenuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Sheets");
		JMenu menu2 = new JMenu("Files");
		
		JMenuItem newitem = new JMenuItem("New Sheet");
		newitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				Object result = JOptionPane.showInputDialog(new JFrame(), "Name of the new Sheet: ");
				System.out.println(result.toString());
				Object result2 = JOptionPane.showInputDialog(new JFrame(), "Number of rows: ");
				System.out.println(result2.toString());
				queryFunctions.newTable(result.toString(),Integer.parseInt(result2.toString()));
            }
        });
		
		menu2.add(newitem);
		
		menu1=addItems(menu1);
		outMenuBar.add(menu2);
		outMenuBar.add(menu1);
		frame1.setJMenuBar(outMenuBar);
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
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/sheetsdb","tom", "myPassword");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE' AND table_name!='records';");
			JMenuItem item = null;
			while(rs.next()){
				item = new JMenuItem(rs.getString("table_name"));
				item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				String command=event.paramString();
				command=command.substring(command.indexOf("cmd=")+4);
				command=command.substring(0,command.indexOf(","));
				jSheet.setSheet(command);
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
        if(stmt!=null){
            try{
                stmt.close();
            }catch(SQLException e2){
                System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e2){
                System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
            }
        }
        return menu1;
	}
	

}
