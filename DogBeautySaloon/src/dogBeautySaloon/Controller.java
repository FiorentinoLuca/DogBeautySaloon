
package dogBeautySaloon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import dogBeautySaloon.gui.*;
import dogBeautySaloon.model.Owner;
import dogBeautySaloon.model.Pet;
import dogBeautySaloon.dao.*;



import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Controller implements ActionListener {

	public static Controller controller;
	public static HomePage homePage;
	public static DataUploader dataUploader;
	public static OwnerDAO daoOwner;
	public static PetDAO daoPet;
	
	

	private Controller() 
	{
		branchToHomePage();
	}
	
	public static void main(String[] args) {
		Controller.controller = new Controller(); // Initialize the controller
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {

        // Discriminate the source or command
		Commands cmd = Commands.commandsMap.get(event.getActionCommand());
		
		if (cmd instanceof HomePage.Commands) {
			handleInHomePage((HomePage.Commands)cmd);
		}
		else if (cmd instanceof DataUploader.Commands) {
			handleInDataUploader((DataUploader.Commands)cmd);			
		}

	}

	private void handleInHomePage(HomePage.Commands cmd) {
		
		switch (cmd) {
			case UPLOAD_DATA:
	        	branchToDataUploader();
	        	
				break;
			case DISPLAY_DATA:
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
		case CLEAR:
			break;
		case EXIT:
			break;
		default:

		}

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
	       	
	       	JOptionPane optionPane = new JOptionPane("Saved successfully!", JOptionPane.INFORMATION_MESSAGE);
	       	JDialog dialog = optionPane.createDialog("Success");
	       	dialog.setAlwaysOnTop(true);
	       	dialog.setVisible(true);
	       	
//       	} catch (Exception e) {
       		
//	       	JOptionPane optionPane = new JOptionPane("Error in Saving!", JOptionPane.INFORMATION_MESSAGE);
//	       	JDialog dialog = optionPane.createDialog("Error");
//	       	dialog.setAlwaysOnTop(true);
//	       	dialog.setVisible(true);
       		
//       	}
       	
	}
	
	
	public static void branchToDataUploader() 
	{
		DataUploader.main(null); // Display the data uploader
	}

	public interface Commands {

		HashMap<String, Commands> commandsMap = new HashMap<>();
		
		default void SubscribeToButton(JButton component) {
			component.setActionCommand(this.getName());
			component.addActionListener(Controller.controller);
		}
		
		String getName();

	}

}
