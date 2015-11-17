import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class queryFunctions{

	public static boolean updateCell(int row,int col,String sheet,String value){
		value=value.replace("'","‘");
		Connection conn=null;
		Statement stmt=null;
		conn=null;
        stmt=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/sheetsdb","tom", "myPassword");
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE "+sheet+" SET col"+String.format("%03d", col)+" = '"+value+"' WHERE id = "+row+";");
			System.out.println("UPDATE "+sheet+" SET col"+String.format("%03d", col)+" = '"+value+"' WHERE id = "+row+";");
			
		 }catch(ClassNotFoundException e1){
            System.out.println( e1.getClass().getName()+": "+ e1.getMessage() );
            return false;
        }catch(SQLException e2){
            System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
            return false;
        }
        
        if(stmt!=null){
            try{
                stmt.close();
            }catch(SQLException e2){
                System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
                return false;
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e2){
                System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
                return false;
            }
        }
        return true;
	}
	public static void newTable(String name, int rows){
		name=name.replace(" ","_");
		Connection conn=null;
		Statement stmt=null;
		conn=null;
        stmt=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/sheetsdb","tom", "myPassword");
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE "+name+" ( id serial NOT NULL, col001 character varying(256), col002 character varying(256), col003 character varying(256), col004 character varying(256), col005 character varying(256), col006 character varying(256), col007 character varying(256), col008 character varying(256), col009 character varying(256), col010 character varying(256), col011 character varying(256), col012 character varying(256), col013 character varying(256), col014 character varying(256), col015 character varying(256), col016 character varying(256), col017 character varying(256), col018 character varying(256), col019 character varying(256), col020 character varying(256), col021 character varying(256), col022 character varying(256), col023 character varying(256), col024 character varying(256), col025 character varying(256), col026 character varying(256), col027 character varying(256), col028 character varying(256), col029 character varying(256), col030 character varying(256), col031 character varying(256), col032 character varying(256), col033 character varying(256), col034 character varying(256), col035 character varying(256), col036 character varying(256), col037 character varying(256), col038 character varying(256), col039 character varying(256), col040 character varying(256), col041 character varying(256), col042 character varying(256), col043 character varying(256), col044 character varying(256), col045 character varying(256), col046 character varying(256), col047 character varying(256), col048 character varying(256), col049 character varying(256), col050 character varying(256), col051 character varying(256), col052 character varying(256), col053 character varying(256), col054 character varying(256), col055 character varying(256), col056 character varying(256), CONSTRAINT "+name+"_pkey PRIMARY KEY (id) );");
			System.out.println("TABLE "+name+" created.");
			for(int c=0;c<rows;c++){
				stmt.executeUpdate("INSERT INTO "+name+"(col001) VALUES ('');");
				}
		 }catch(ClassNotFoundException e1){
            System.out.println( e1.getClass().getName()+": "+ e1.getMessage() );
        }catch(SQLException e2){
            System.out.println( e2.getClass().getName()+": "+ e2.getMessage() );
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
	}
}
