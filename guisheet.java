import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class guisheet extends JFrame {

    public guisheet() {

        initUI();
    }

    private void initUI() {
        setJMenuBar(menu.menubar());
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                guisheet ex = new guisheet();
                
                ex.setVisible(true);
            }
        });
    }
}
