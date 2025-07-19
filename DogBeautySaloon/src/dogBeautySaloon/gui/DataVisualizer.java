package dogBeautySaloon.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dogBeautySaloon.Controller;
import dogBeautySaloon.model.Pet;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataVisualizer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public final Map<Integer, JFrame> editingFrames = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					if (Controller.dataVisualizer == null) 
						Controller.dataVisualizer = new DataVisualizer();
					
					var lastFrame = Controller.lastFrameOnFocus();
					if (lastFrame != null) 
						Controller.dataVisualizer.setLocationRelativeTo(lastFrame);
					Controller.dataVisualizer.setVisible(true);
					if (lastFrame != null) 
						lastFrame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DataVisualizer() {
		addWindowListener(
			new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) 
				{
					Controller.brain.actionPerformed(
						new ActionEvent(
							this, 
							ActionEvent.ACTION_PERFORMED, 
							Commands.OPENED.getName()
						)
					);
				}
			}
		);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditData = new JLabel("Edit Data");
		lblEditData.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblEditData.setBounds(354, 10, 86, 26);
		contentPane.add(lblEditData);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(4, 46, 790, 217);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		table = new JTable();
		table.setBounds(0, 11, 269, 195);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 24, 708, 186);
		table.setFillsViewportHeight(true);
		panel.add(scrollPane);
		
		JButton btnDelete = new JButton("");
		Commands.DELETE.subscribeToButton(btnDelete);
		btnDelete.setIcon(new ImageIcon(DataVisualizer.class.getResource("/images/delete.jpg")));
		btnDelete.setBounds(727, 10, 63, 39);
		panel.add(btnDelete);
		
		JButton btnEdit = new JButton("");
		Commands.EDIT.subscribeToButton(btnEdit);
		btnEdit.setIcon(new ImageIcon(DataVisualizer.class.getResource("/images/edit.jpg")));
		btnEdit.setBounds(727, 59, 63, 39);
		panel.add(btnEdit);
		
		JLabel lblRegisteredPets = new JLabel("Registered Pets:");
		lblRegisteredPets.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRegisteredPets.setBounds(4, 1, 109, 19);
		panel.add(lblRegisteredPets);

	}
	
	public void loadTable() 
	{
		table.setModel( new DefaultTableModel() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
	
				/**
				 * 
				 *  @return false
				 */
				@Override
				public boolean isCellEditable (int row, int column) {
					return false;
				}
			} 
		);

		((DefaultTableModel) table.getModel()).setColumnIdentifiers(
			Arrays.asList("ID", "Name", "Breed", "Color", "Owner", "Cellphone", "Allergeens", "Special Service", "Observations")
			.toArray()
		);
		List<Pet> resultSet;
		
		resultSet = Controller.brain.downloadPetData();
		for (Pet pet : resultSet) {
			((DefaultTableModel) table.getModel()).addRow(
				Arrays.asList(
					pet.getId(), 
					pet.getName(), 
					pet.getBreed(), 
					pet.getColor(),
					pet.getOwner().getName(),
					pet.getOwner().getCellphone(),
					pet.getAllergeens(), 
					pet.getSpecialTreatment(), 
					pet.getObservations()
				).toArray()
			);
		}
		table.setDefaultRenderer(Object.class, 
			new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;
				@Override
				public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
						boolean isSelected, boolean hasFocus, int row, int column) 
				{
					java.awt.Component c = super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
					if (editingFrames.containsKey(row)) {
						c.setBackground(java.awt.Color.YELLOW);
					} else {
						c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
					}
					return c;
				}
			}
		);
		
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	private static final String className = "/DogBeautySaloon/src/dogBeautySaloon/gui/DataVisualizer.java";
	public enum Commands implements Controller.Commands  {
		
		
		OPENED(className + ": Opened"),
		DELETE(className + ": DeleteButton"),
		EDIT(className + ": EditButton"),
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
