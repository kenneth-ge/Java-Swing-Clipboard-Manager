import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Clipboard");
		
		BufferedReader br = new BufferedReader(new FileReader("copy.txt"));
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3,3,3,3);
		
		String line;
		while((line = br.readLine()) != null) {
			String[] stuff = line.split("\t");
			
			JButton btn = new JButton(stuff[0]);
			btn.addActionListener(e -> {
				StringSelection selection = new StringSelection(stuff[1]);
				clipboard.setContents(selection, selection);
			});
			
			frame.add(btn, gbc);
		}
		
		br.close();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
