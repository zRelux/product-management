import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/***
 * Product managment system
 * @author Leonardo Drici 1905444
 * @version 23/03/2019
 */
public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Window();
	}

}
