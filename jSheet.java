import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class jSheet{

public static void setSheet(String sheetName){
	String[][] sheetData = sqltoArray(sheetName);
	System.out.println(sheetName);
	System.out.println(sheetData[0][1]);
	guisheet ex = new guisheet();
	
	
	JPanel mainpanel=new JPanel();
	JPanel panel1=new JPanel(new SpringLayout());
	

	JScrollPane scrollFrame = new JScrollPane(panel1);
		panel1.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800,600));
	
	JTextField txtfield;
	int rows=0;
	for(int c=0;c<sheetData.length&&sheetData[c][0]!=null;c++){
		for(int r=0;r<257;r++){
			txtfield=new JTextField("");
			////////////////EVENT ENTER
			txtfield.addKeyListener(new KeyAdapter() {
            
            public void keyReleased(KeyEvent event) {
				System.out.println(event.getKeyCode());
				if(event.getKeyCode()==10){
					System.out.println("Save the field");
					}
            }
        });
			/////////////// END EVENT ENTER
			
			if(sheetData[c][r]!=null){	
				txtfield.setText(sheetData[c][r]);
			}else{
				txtfield.setText("");
			}
            panel1.add(txtfield);
		}
		rows=c;
	}
	rows++;
		if(rows<26){
			scrollFrame.setPreferredSize(new Dimension( 800,(rows*22)+20));
			}else{
		scrollFrame.setPreferredSize(new Dimension( 800,570));
				}
		panel1.setPreferredSize(new Dimension( 20000,(rows*22)));
		
		SpringUtilities.makeCompactGrid(panel1,
                                rows, 257, //rows, cols
                                0, 0,        //initX, initY
                                0, 0);       //xPad, yPad
		
		mainpanel.add(scrollFrame);
		ex.add(mainpanel);
		ex.setVisible(false);
		ex.setVisible(true);
	}


public static String[][] sqltoArray(String sheetName){
	String[][] out = new String[16384][257];
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
			rs = stmt.executeQuery("SELECT * FROM "+sheetName+";");
			for(int c=0;rs.next();c++){ ///
				for(int r=0;r<257;r++){
					out[c][r]=rs.getString(r+1);
				}
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
		return out;
}

}
