import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
public class gui{
	public static void main(String[] args) {
		JFrame frame1=new JFrame();
		build(frame1);
	}
	public static void build(JFrame frame1){
		frame1.setTitle("SharedSheet V 1.0");
		frame1.setSize(800, 600);
		frame1.setLocationRelativeTo(null);
		menu.menubar(frame1);
		frame1.setVisible(true);
		}
}
