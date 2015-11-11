import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class menu{
	
	public static JMenuBar menubar(){
		JMenuBar outMenuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Sheets");
		JMenuItem menuItem1 = new JMenuItem("First Shared Sheet");
		menu1.add(menuItem1);
		outMenuBar.add(menu1);
		return outMenuBar;
		}
	

}
