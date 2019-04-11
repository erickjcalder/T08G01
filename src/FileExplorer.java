import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileExplorer {
	String filename;
	String title;

	public FileExplorer(String title) {
		this.title = title;
	}

	public void open() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogTitle(title);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		int rVal = fileChooser.showOpenDialog(fileChooser);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename = fileChooser.getCurrentDirectory().toString();
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				filename += "\\" + fileChooser.getSelectedFile().getName();
			} else {
				filename += "/" + fileChooser.getSelectedFile().getName();
			}
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			filename = null;
		}
	}

	public void save() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogTitle(title);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		int rVal = fileChooser.showSaveDialog(fileChooser);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename = fileChooser.getCurrentDirectory().toString();
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				filename += "\\" + fileChooser.getSelectedFile().getName();
			} else {
				filename += "/" + fileChooser.getSelectedFile().getName();
			}
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			filename = null;
		}
	}

	public String getSelectedFile() {
		return filename;
	}

}
