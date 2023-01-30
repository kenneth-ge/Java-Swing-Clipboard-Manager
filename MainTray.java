import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;

public class MainTray {

	public static void main(String[] args) throws Exception {
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("icons8-full-tool-storage-box-90.png"), "Clipboard Manager");
        final SystemTray tray = SystemTray.getSystemTray();
       
		BufferedReader br = new BufferedReader(new FileReader("copy.txt"));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
        String line;
		while((line = br.readLine()) != null) {
			String[] stuff = line.split("\t");
			
	        MenuItem aboutItem = new MenuItem(stuff[0]);
	        
	        aboutItem.addActionListener(e -> {
				StringSelection selection = new StringSelection(stuff[1]);
				clipboard.setContents(selection, selection);
	        });
	        
	        popup.add(aboutItem);
		}
		br.close();
       
		MenuItem exit = new MenuItem("Exit");
        
		exit.addActionListener(e -> {
			System.exit(0);
        });
        
        popup.add(exit);
		
        trayIcon.setPopupMenu(popup);
        trayIcon.addActionListener(e -> {
        	try {
				Main.main(null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        
        trayIcon.setImageAutoSize(true);
        tray.add(trayIcon);
	}
	
}
