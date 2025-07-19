
package dogBeautySaloon;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import dogBeautySaloon.gui.*;
import dogBeautySaloon.gui.DataUploader.Data;
import dogBeautySaloon.model.Owner;
import dogBeautySaloon.model.Pet;
import dogBeautySaloon.dao.*;



import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


public class Controller implements ActionListener {
	
	//	7	Zoe	crossed	Mustard	Nicole&Luca	222 222 2222	No	Yes	is a can

	public static Controller brain;
	public static HomePage homePage;
	public static DataUploader dataUploader;
	public static DataVisualizer dataVisualizer;	

	private Controller() 
	{
		branchToHomePage();
	}
	
	public static void main(String[] args) {
		Controller.brain = new Controller(); // Initialize the brain
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {

        // Discriminate the source or command
		Commands cmd = Commands.commandsMap.get(event.getActionCommand());
		
		switch (cmd.getClassName()) {
			case "/DogBeautySaloon/src/dogBeautySaloon/gui/HomePage.java":
				handleInHomePage((HomePage.Commands)cmd);
				break;
			case "/DogBeautySaloon/src/dogBeautySaloon/gui/DataUploader.java":
				handleInDataUploader((DataUploader.Commands)cmd);
				break;
			case "/DogBeautySaloon/src/dogBeautySaloon/gui/DataVisualizer.java":
				handleInDataVisualizer((DataVisualizer.Commands)cmd);
				break;
		}

	}

	private void handleInHomePage(HomePage.Commands cmd) {
		
		switch (cmd) {
			case UPLOAD_DATA:
	        	branchToDataUploader();
				break;
			case DISPLAY_DATA:
				branchToDataVisualizer();
				break;
			case EXIT:
				break;
			default:
        }

	}

	private void handleInDataUploader(DataUploader.Commands cmd) {
		
		switch (cmd) {
		case SAVE:
			uploadData(dataUploader.new Data());
			break;
		case EDIT:
			updateData(new PetDAO().read(dataUploader.id), dataUploader.new Data());
			break;
		case CLEAR:
			break;
		case EXIT:
			break;
		default:
		}

	}
	
	private void updateData(Pet oldPet, Data data) 
	{
		
		oldPet.setName(data.name);
		oldPet.setBreed(data.breed);
		oldPet.setColor(data.color);
		oldPet.setObservations(data.observations);
		oldPet.setAllergeens(data.allergeens);
		oldPet.setSpecialTreatment(data.specialTreatment);
		Owner oldOwner = oldPet.getOwner();
		oldOwner.setName(data.owner);
		oldOwner.setCellphone(data.phoneNumber);
		
		
		new PetDAO().update(oldPet);
		new OwnerDAO().update(oldOwner);
	}

	private void handleInDataVisualizer(DataVisualizer.Commands cmd) {
		
		switch (cmd) {
		case OPENED:
			dataVisualizer.loadTable();
			break;
		case DELETE:
			deleteSelectedRows(dataVisualizer.getTable());
			break;
		case EDIT:
			editSelectedRow(dataVisualizer.getTable());
			break;
		case EXIT:
			break;
		default:
		}

	}

	private static void deleteSelectedRows(JTable table)
	{
	
		DialogDisplayer displayer;
		if (table.getSelectedRowCount() == 0)
		{
			displayer = ()-> JOptionPane.WARNING_MESSAGE;
			displayer.displayMessage("No rows selected!", "Warning");
			return;
		}
		
		var daoPet = new PetDAO();
		for (var i : table.getSelectedRows())
			daoPet.delete((Integer)table.getValueAt(i, 0));

		displayer = ()-> JOptionPane.INFORMATION_MESSAGE;
		displayer.displayMessage("Deleted successfully!", "Success");
		dataVisualizer.loadTable();
	}
	
	private static void editSelectedRow(JTable table)
	{
		
		DialogDisplayer displayer;
		if (table.getSelectedRowCount() > 1 || table.getSelectedRowCount() == 0)
		{
			displayer = ()-> JOptionPane.WARNING_MESSAGE;
			displayer.displayMessage("Please select only one row to edit!", "Warning");
			return;
		}		
		branchToDataUploader(dataVisualizer);

	}

	private void branchToHomePage()
	{
		HomePage.main(null); // Display the home page
	}
	
	
	private void uploadData(DataUploader.Data data)
	{
		System.out.println(data.name+ " " + data.breed + " " + data.color + " " + data.owner + " " + data.phoneNumber + " " + data.observations + " " + data.allergeens + " " + data.specialTreatment);
		String DB_User = null;
		String DB_Password = null;
		String DB = null;
		BufferedReader buffer = null;
        try
        {
			buffer = new BufferedReader(new FileReader(new File("resources/files/.DB_Credentials")));
			DB_User=buffer.readLine();
			DB_Password=buffer.readLine();
			DB=buffer.readLine();			
        }
        catch (Exception e) {
        	
        }
        finally
        {
        	if (buffer != null)
        	{
				try {
					buffer.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
        	}
        }
        System.out.println("DB_User: " + DB_User + " DB_Password: " + DB_Password + " DB: " + DB);
//       	try {
	        OwnerDAO daoOwner = new OwnerDAO();
	       	PetDAO daoPet = new PetDAO();
	       	
	       	Owner owner = new Owner();
	       	owner.setName(data.owner);
	       	owner.setCellphone(data.phoneNumber);
	       	
	       	Pet pet = new Pet();
	       	pet.setName(data.name);
	       	pet.setBreed(data.breed);
	       	pet.setColor(data.color);
	       	pet.setObservations(data.observations);
	       	pet.setAllergeens(data.allergeens);
	       	pet.setSpecialTreatment(data.specialTreatment);
	       	pet.setOwner(owner);
	       	
	       	daoOwner.create(owner);
	       	daoPet.create(pet);  
	       	
	       	DialogDisplayer displayer = ()-> JOptionPane.INFORMATION_MESSAGE;
	       	displayer.displayMessage("Saved successfully!", "Success");
	       	
//       	} catch (Exception e) {
       		
//	       	JOptionPane optionPane = new JOptionPane("Error in Saving!", JOptionPane.INFORMATION_MESSAGE);
//	       	JDialog dialog = optionPane.createDialog("Error");
//	       	dialog.setAlwaysOnTop(true);
//	       	dialog.setVisible(true);
       		
//       	}
	}
	
	
	public static void branchToDataUploader(DataVisualizer dataVisualizer) 
	{
        int row = dataVisualizer.getTable().getSelectedRow();
        if (row < 0) 
        	 return;
        dataUploader = (DataUploader) dataVisualizer.editingFrames.get(row);
        if (dataUploader == null) {
	    	dataUploader = new DataUploader(dataVisualizer.getTable());
	    	dataUploader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	dataUploader.setTitle("Editor row: " + (row+1));
	        dataUploader.addWindowListener(
	        	new WindowAdapter() {
	                @Override
	                public void windowClosed(WindowEvent e) {
	                    dataVisualizer.editingFrames.remove(row);
	                    dataVisualizer.getTable().repaint();
	            		DialogDisplayer displayer = ()-> JOptionPane.INFORMATION_MESSAGE;
	            		displayer.displayMessage("Edited successfully!", "Success");
	            		dataVisualizer.loadTable();
	                }
	        	}
	        );
	        dataVisualizer.editingFrames.put(row, dataUploader);
			var lastFrame = lastFrameOnFocus();
			
			branchToDataUploader();
			SwingUtilities.invokeLater(() -> {
			    lastFrame.setVisible(true);
			});
			SwingUtilities.invokeLater(() -> {
			    lastFrame.setLocationRelativeTo(null);
			});
        }
        SwingUtilities.invokeLater(() -> {
        	dataUploader.toFront();
        	dataUploader.requestFocus();
		});
	}

	
	public static void branchToDataUploader() 
	{
		DataUploader.main(null);
	}
	
	
	private void branchToDataVisualizer()
	{
		DataVisualizer.main(null);	
	}

	static public interface Commands {

		HashMap<String, Commands> commandsMap = new HashMap<>();
		
		default void subscribeToButton(JButton btn) {
			btn.setActionCommand(this.getName());
			btn.addActionListener(brain);
		}
		
		default void unsubscribeFromButton(JButton btn) {
			btn.removeActionListener(brain);
			btn.setActionCommand(null);
		}
		
		String getName();
		String getClassName();
	}

	public List<Pet> downloadPetData() 
	{
		var res = new PetDAO().readAll();
		System.out.println("Downloaded " + res.size() + " pets from the database.");
		return res;
	}
	
	public static JFrame lastFrameOnFocus() {
		return (JFrame) KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
	}

	@FunctionalInterface
	public static interface DialogDisplayer {
		public default void displayMessage(String message, String title) 
		{
			JOptionPane optionPane = new JOptionPane(message, option());
			JDialog dialog = optionPane.createDialog(title);
			dialog.setAlwaysOnTop(true);
			var lastFrame = Controller.lastFrameOnFocus();
			if (lastFrame != null)
				dialog.setLocationRelativeTo(lastFrame);
			dialog.setVisible(true);
		}
		public abstract int option();
	}
	

}
