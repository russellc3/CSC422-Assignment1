import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PetDatabase {

	public static ArrayList<Pet> petDatabase = new ArrayList<Pet>();
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		petDatabase = load();
		
		int menuSelection = 0;
		boolean validInput = false;
		
		System.out.print("Pet Database Program\n");
		
		while (menuSelection!=4) {
			validInput = false;
			
			while (!validInput) {
				
				try {
					printMenu();
					menuSelection = input.nextInt();
					if (menuSelection < 1 || menuSelection > 4) {
						
						System.out.print("\nThat is not a valid menu selection, please enter a number matching a menu option.\n");
						input.next();
						printMenu();
						
					} else {
						
						validInput = true;
						
					}
					
					
					
				} catch (InputMismatchException e) {
					
					//Catch exception and display error if non number
					System.out.print("\nThat is not a valid menu selection, please enter a number matching a menu option.\n");
					input.nextLine();
				}
			}
			
			switch(menuSelection) {
				case 1:
					printPets();
					break;
				case 2:
					addPet();
					break;
				case 3:
					removePet();
					break;
				case 4:
					save();
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
			}
			
		}
	}
	
	public static void printPets() {
		
		System.out.print("\n+----------------------+");
		System.out.print("\n| ID | NAME      | AGE |");
		System.out.print("\n+----------------------+");
		for (int i = 0; i < petDatabase.size(); i++) {
			System.out.print("\n");
			System.out.print("|");
			System.out.printf("%3s", i);
			System.out.print(" | ");
			System.out.printf("%-9s", petDatabase.get(i).getName());
			System.out.print(" |");
			System.out.printf("%4s", petDatabase.get(i).getAge());
			System.out.print(" |");
		}
		System.out.print("\n+----------------------+\n");
		System.out.print(petDatabase.size() + " rows in set.\n");
	}
	
	public static void addPet () {
		
		String line = "";
		String name = "";
		int age = 0;
		int numPetsAdded = 0;
		input.nextLine();
		
		do {
			
			if(petDatabase.size() >= 5) {
				System.out.print("\nPet Database is already at max size: 5. \nYou will need to remove a pet from the database first.");
				return;
			}
			
			System.out.print("\nadd pet (name, age): ");
			line = input.nextLine();
			String[] part = line.split("(?<=\\D)(?=\\d)");
			name = part[0].toLowerCase();
			if (part.length > 1) {
				
				try{
					age = Integer.parseInt(part[1]);
				
					if (age >= 1 && age <= 20) {
						petDatabase.add(new Pet(name,age));
						numPetsAdded++;
					} else {
						System.out.print("\nPet's age needs to be between 1 and 20");
					}
				} catch(InputMismatchException ime) {
					System.out.print("\nThe second value needs to be an integer.");
				}
			} else if (!name.equals("done")) {
				System.out.print("\nPet not added, you need to enter an age for the pet.");
			}
		} while (!name.equals("done"));
		
		System.out.print("\n" + numPetsAdded + " pets added.\n");
	}
	
	public static void updatePet () {
		
		int petToUpdate = -1;
		String newName = "";
		int newAge = 0;
		String line = "";
		
		printPets();
		System.out.print("Enter the pet ID you want to update: ");
		petToUpdate = input.nextInt();
		input.nextLine();
		System.out.println("Enter new name and new age: ");
		line = input.nextLine();
		String[] part = line.split("(?<=\\D)(?=\\d)");
		newName = part[0].toLowerCase();
		newAge = Integer.parseInt(part[1]);
		System.out.print(petDatabase.get(petToUpdate).getName() + " " + petDatabase.get(petToUpdate).getAge() + " changed to " + newName + " " + newAge);
		petDatabase.get(petToUpdate).setName(newName);
		petDatabase.get(petToUpdate).setAge(newAge);
	}
	
	public static void removePet () {

		int petToRemove = -1;
		
		printPets();
		System.out.print("Enter the pet ID you want to remove: ");
		try {
			petToRemove = input.nextInt();
		} catch (InputMismatchException ime) {
			System.out.print("\nInvalid input. Input must be a number matching the ID of the pet you want to remove.\n");
			input.nextLine();
			return;
		}
		if (petToRemove < 0 || petToRemove > petDatabase.size() - 1) {
			System.out.print("\nInvalid input. Input must be a number matching the ID of the pet you want to remove.\n");
			input.nextLine();
			return;
		}
		input.nextLine();
		System.out.print(petDatabase.get(petToRemove).getName() + " " + petDatabase.get(petToRemove).getAge() + " have been removed.");
		petDatabase.remove(petToRemove);
	}
	
	public static void nameSearch () {
		
		String nameToSearch = "";
		int numMatches = 0;
		
		System.out.print("\nEnter a name to search: ");
		
		nameToSearch = input.next().toLowerCase();
		
		System.out.print("\n+----------------------+");
		System.out.print("\n| ID | NAME      | AGE |");
		System.out.print("\n+----------------------+");
		
		for (int i = 0; i < petDatabase.size(); i++) {
			if (nameToSearch.equals(petDatabase.get(i).getName())) {
				System.out.print("\n");
				System.out.print("|");
				System.out.printf("%3s", i);
				System.out.print(" | ");
				System.out.printf("%-9s", petDatabase.get(i).getName());
				System.out.print(" |");
				System.out.printf("%4s", petDatabase.get(i).getAge());
				System.out.print(" |");
				numMatches++;
			}
		}
		System.out.print("\n+----------------------+\n");
		System.out.print(numMatches + " rows in set.\n");
		
	}
	
	public static void ageSearch () {
		
		int ageToSearch = 0;
		int numMatches = 0;
		
		System.out.print("\nEnter age to search: ");
		
		ageToSearch = input.nextInt();
		

		System.out.print("\n+----------------------+");
		System.out.print("\n| ID | NAME      | AGE |");
		System.out.print("\n+----------------------+");
		
		for (int i = 0; i < petDatabase.size(); i++) {
			if (ageToSearch == petDatabase.get(i).getAge()) {
				System.out.print("\n");
				System.out.print("|");
				System.out.printf("%3s", i);
				System.out.print(" | ");
				System.out.printf("%-9s", petDatabase.get(i).getName());
				System.out.print(" |");
				System.out.printf("%4s", petDatabase.get(i).getAge());
				System.out.print(" |");
				numMatches++;
			}
		}
		System.out.print("\n+----------------------+\n");
		System.out.print(numMatches + " rows in set.\n");
	}
	
	public static void printMenu () {
		System.out.print("\nWhat would you like to do?\n"
				+ "1) View all pets\n"
				+ "2) Add new pets\n"
				+ "3) Remove an existing pet\n"
				+ "4) Exit program\n"
				+ "Your choice: "			
				);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Pet> load() throws Exception {
        
		//Try-catch block for file errors
        try {
            
        	//InputStream objects for reading the PetDatabase into the ArrayList
        	FileInputStream read = new FileInputStream("PetDatabase");
            ObjectInputStream objectRead = new ObjectInputStream(read);
            
            petDatabase = (ArrayList<Pet>) objectRead.readObject();
 
            objectRead.close();
            read.close();
            
            return petDatabase;
            
        } catch (IOException ioe) {
            
        	//If PetDatabase isn't found, print a message that there wasn't an
        	//existing PetDatabase and that a new one will be created.
        	System.out.print("\nNo existing pet database was found so a new one has been created!\n");
        	
        	petDatabase.add(new Pet("fido", 2));
    		petDatabase.add(new Pet("mittens", 5));
    		petDatabase.add(new Pet("spike", 4));

        	return petDatabase;
        }
	}
	
	public static void save() throws Exception {
		
		//Try-Catch block to make sure we are able to write to the file (or create it)
		try {
			
			//OutputStream objects for accessing or creating the file and writing to it
			FileOutputStream write = new FileOutputStream("PetDatabase");
			ObjectOutputStream objectWrite = new ObjectOutputStream(write);
			
			objectWrite.writeObject(petDatabase);
			
			objectWrite.close();
			write.close();
		
		} catch (IOException ioe) {
			System.out.print("\n" + ioe);
			System.out.print("\nCould not save PetDatabase due to file write error.");;
		}
	}
}
