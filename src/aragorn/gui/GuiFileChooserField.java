package aragorn.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * The {@code GuiFileChooserField} is the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiFileChooserField extends GuiPanel {

	public static void main(String[] args) {
		GuiFrame frame = new GuiFrame(new Dimension(800, 450), false) {
			@Override
			protected void editContentPane() {
				terminal_panel = new GuiTerminalPanel();

				getContentPane().addComponent(new GuiFileChooserField.Save(this), 0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
				getContentPane().addComponent(terminal_panel, 0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
			}
		};
		frame.setVisible(true);
	}

	/**
	 * {@code GuiFileChooserField.Open} is the implement for the input of the file I/O chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Open extends GuiFileChooserField {

		/** Create {@code GuiFileChooserField.Open}. */
		public Open(GuiFrame parent) {
			super();
			chooser = new GuiFileChooser.Open(parent) {
				@Override
				public void actionPerformed(ActionEvent arg) {
					super.actionPerformed(arg);
					if (this.getSelectedFile() != null) {
						field.setText(this.getSelectedFile().getAbsolutePath());
					}
				}
			};
			button.setText(chooser.getActionString());
			button.addActionListener(chooser);
		}

		@Override
		protected File getSelectedFile() {
			return chooser.getSelectedFile();
		}
	}

	/**
	 * {@code GuiFileChooserField.Save} is the implement for the output of the file chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Save extends GuiFileChooserField {

		/** Create {@code GuiFileChooserField.Save}. */
		public Save(GuiFrame parent) {
			super();
			chooser = new GuiFileChooser.Save(parent) {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					super.actionPerformed(arg0);
					if (this.getSelectedFile() != null) {
						field.setText(this.getSelectedFile().getAbsolutePath());
					}
				}
			};
			button.setText(chooser.getActionString());
			button.addActionListener(chooser);
		}

		@Override
		protected File getSelectedFile() {
			return chooser.getSelectedFile();
		}
	}

	/** The text field to print the directory of the chosen file. */
	protected JTextField field = new JTextField();

	protected JButton button = new JButton();

	protected GuiFileChooser chooser;

	/** Create the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser. */
	protected GuiFileChooserField() {
		super();

		field.setEditable(false);
		field.setBackground(Color.WHITE);

		setDefaultMargin(0);
		addComponent(field, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
		addComponent(button, 1, 0);
	}

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return the selected file
	 */
	protected abstract File getSelectedFile();
}