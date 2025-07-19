package dogBeautySaloon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dogBeautySaloon.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(
			new Runnable() {	// Anonymous Runnable with a run override
				@Override
				public void run() 
				{
					try {
						Controller.homePage = new HomePage(); // Initialize the home page
						Controller.homePage.setVisible(true); // Set it visible
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		);
	}

	/**
	 * Create the frame.
	 */
	public HomePage() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null); // Center the frame on the screen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDogBeautySaloon = new JLabel("DogBeautySaloon");
		lblDogBeautySaloon.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblDogBeautySaloon.setBounds(31, 10, 374, 70);
		panel.add(lblDogBeautySaloon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 90, 164, 140);
		panel.add(panel_1);
		
		JButton uploadDataButton = new JButton("Upload data");
		Commands.UPLOAD_DATA.subscribeToButton(uploadDataButton);
		
		JButton displayDataButton = new JButton("Display data");
		Commands.DISPLAY_DATA.subscribeToButton(displayDataButton);
		
		JButton exitButton = new JButton("Exit");
		Commands.EXIT.subscribeToButton(exitButton);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(uploadDataButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(exitButton, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
						.addComponent(displayDataButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(uploadDataButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayDataButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel dogBathIcon = new JLabel("");
		dogBathIcon.setIcon(new ImageIcon(HomePage.class.getResource("/images/dogBath.jpeg")));
		dogBathIcon.setBounds(203, 90, 195, 140);
		panel.add(dogBathIcon);
	}
	
	private static final String className = "/DogBeautySaloon/src/dogBeautySaloon/gui/HomePage.java";
	public enum Commands implements Controller.Commands  {
		
		
		UPLOAD_DATA(className + ": UploadDataButton"),
		DISPLAY_DATA(className + ": DisplayDataButton"),
		EXIT(className + ": ExitButton");
		
		static {
			for (Commands cmd : Commands.values()) {
				commandsMap.put(cmd.commandName, cmd);
			}
		}
		
		
		public final String commandName;
		
		Commands(String string) {
			this.commandName = string;
		}
		
		@Override
		public String getName() {
			return commandName;
		}
		
		@Override
		public String getClassName() {
			return className;
		}
		
	}

}
