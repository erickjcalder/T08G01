import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class FileExplorer {
	File selectedFile;
	String title;

	public FileExplorer(String title) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogTitle(title);

		try {
			fileChooser.showOpenDialog(null);
		} catch (HeadlessException ex) {
			System.out.println("Keyboard and Mouse Required");
			ex.printStackTrace();
			System.exit(-1);
		}

		selectedFile = fileChooser.getSelectedFile();
		if (selectedFile != null) {
			if (!selectedFile.getName().endsWith(".txt")) {
				selectedFile = null;
			}
		}
	}

	public File getSelectedFile() {
		return selectedFile;
	}

}
