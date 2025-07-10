package dogBeautySaloon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dogBeautySaloon.Controller;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class DataUploader extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textBreed;
	private JTextField textColor;
	private JTextField textOwner;
	private JTextField textPhoneNumber;
	private JTextArea txtrObsNRelevant;
	private JComboBox<?> cmbSpecialTreatment;
	private JComboBox<?> cmbAlergeens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(
			new Runnable() {
				public void run() {
					try {
						
						if (Controller.dataUploader == null) 
							Controller.dataUploader = new DataUploader();
						
						Controller.dataUploader.setVisible(true);
						
						if (Controller.homePage != null) {
							Controller.dataUploader.setLocationRelativeTo(Controller.homePage);
							Controller.homePage.setVisible(false);
						}
						
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataUploader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataUpload = new JLabel("Data Upload");
		lblDataUpload.setBounds(96, 10, 262, 58);
		lblDataUpload.setFont(new Font("Tahoma", Font.PLAIN, 48));
		contentPane.add(lblDataUpload);
		
		JPanel panel = new JPanel();
		panel.setBounds(61, 78, 351, 380);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel();
		lblName.setText("Name:");
		lblName.setBackground(UIManager.getColor("Button.background"));
		lblName.setBounds(0, 10, 116, 22);
		panel.add(lblName);
		
		JLabel lblBreed = new JLabel();
		lblBreed.setText("Breed:");
		lblBreed.setBackground(UIManager.getColor("Button.background"));
		lblBreed.setBounds(0, 42, 116, 22);
		panel.add(lblBreed);
		
		JLabel lblOwner = new JLabel();
		lblOwner.setText("Owner:");
		lblOwner.setBackground(UIManager.getColor("Button.background"));
		lblOwner.setBounds(0, 106, 116, 22);
		panel.add(lblOwner);
		
		JLabel lblColor = new JLabel();
		lblColor.setText("Color:");
		lblColor.setBackground(UIManager.getColor("Button.background"));
		lblColor.setBounds(0, 74, 116, 22);
		panel.add(lblColor);
		
		JLabel lblObservations = new JLabel();
		lblObservations.setText("Observations & relevant info:");
		lblObservations.setBackground(UIManager.getColor("Button.background"));
		lblObservations.setBounds(0, 241, 375, 22);
		panel.add(lblObservations);
		
		JLabel lblPhoneNumber = new JLabel();
		lblPhoneNumber.setText("Phone number:");
		lblPhoneNumber.setBackground(UIManager.getColor("Button.background"));
		lblPhoneNumber.setBounds(0, 135, 116, 22);
		panel.add(lblPhoneNumber);
		
		JLabel lblAlergeens = new JLabel();
		lblAlergeens.setText("Alergeens:");
		lblAlergeens.setBackground(UIManager.getColor("Button.background"));
		lblAlergeens.setBounds(0, 167, 98, 22);
		panel.add(lblAlergeens);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(62, 10, 279, 22);
		panel.add(textName);
		
		textBreed = new JTextField();
		textBreed.setColumns(10);
		textBreed.setBounds(62, 42, 279, 22);
		panel.add(textBreed);
		
		textColor = new JTextField();
		textColor.setColumns(10);
		textColor.setBounds(62, 75, 279, 22);
		panel.add(textColor);
		
		textOwner = new JTextField();
		textOwner.setColumns(10);
		textOwner.setBounds(62, 106, 279, 22);
		panel.add(textOwner);
		
		textPhoneNumber = new JTextField();
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(108, 135, 233, 22);
		panel.add(textPhoneNumber);
		
		txtrObsNRelevant = new JTextArea();
		txtrObsNRelevant.setBounds(10, 265, 331, 82);
		panel.add(txtrObsNRelevant);
		
		JLabel lblSpecialTreatment = new JLabel();
		lblSpecialTreatment.setText("SpecialTreatment:");
		lblSpecialTreatment.setBackground(UIManager.getColor("Button.background"));
		lblSpecialTreatment.setBounds(0, 199, 116, 22);
		panel.add(lblSpecialTreatment);
		
		cmbAlergeens = new JComboBox();
		cmbAlergeens.setModel(new DefaultComboBoxModel(new String[] {"-", "Yes", "No"}));
		cmbAlergeens.setBounds(77, 168, 264, 21);
		panel.add(cmbAlergeens);
		
		cmbSpecialTreatment = new JComboBox<>();
		cmbSpecialTreatment.setModel(new DefaultComboBoxModel(new String[] {"-", "Yes", "No"}));
		cmbSpecialTreatment.setBounds(123, 197, 218, 21);
		panel.add(cmbSpecialTreatment);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 357, 85, 21);
		panel.add(btnSave);
		Commands.SAVE.SubscribeToButton(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JTextField field : new JTextField[] {textName, textBreed, textColor, textOwner, textPhoneNumber}) {
					field.setText("");
				}
				txtrObsNRelevant.setText("");
				cmbAlergeens.setSelectedIndex(0);
				cmbSpecialTreatment.setSelectedIndex(0);
			}
		});
		btnClear.setIcon(new ImageIcon(DataUploader.class.getResource("/images/brush.jpg")));
		btnClear.setIconTextGap(20);
		btnClear.setHorizontalTextPosition(SwingConstants.LEFT);
		btnClear.setBounds(232, 357, 109, 21);
		panel.add(btnClear);
	}
	
	public class Data {
		
		public final String name;
		public final String breed;
		public final String color;
		public final String owner;
		public final String phoneNumber;
		public final String observations;
		public final String allergeens;
		public final String specialTreatment;
		
		public Data() {
			this.name = DataUploader.this.textName.getText();
			this.breed = textBreed.getText();
			this.color = textColor.getText();
			this.owner = textOwner.getText();
			this.phoneNumber = textPhoneNumber.getText();
			this.observations = txtrObsNRelevant.getText();
			this.allergeens = (String) cmbAlergeens.getSelectedItem();
			this.specialTreatment = (String) cmbSpecialTreatment.getSelectedItem();
//			this.specialTreatment = ((String)cmbSpecialTreatment.getSelectedItem()).equals("Yes")?true:(((String)cmbSpecialTreatment.getSelectedItem()).equals("No")?false:null);
		}
		
	}
	
	private static final String className = "/DogBeautySaloon/src/dogBeautySaloon/gui/DataUploader.java";	
	public enum Commands implements Controller.Commands {
		
		SAVE(className + ": SaveButton"),
		CLEAR(className + ": ClearButton"),
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
