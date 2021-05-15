import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PetDatabase {

	public static ArrayList<Pet> petDatabase = new ArrayList<Pet>();
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		petDatabase.add(new Pet("Fido", 2));
		petDatabase.add(new Pet("Mittens", 5));
		petDatabase.add(new Pet("Spike", 4));
		
		int menuSelection = 0;
		boolean validInput = false;
		
		System.out.print("Pet Database Program\n");
		printMenu();
		
		while (menuSelection!=7) {
			validInput = false;
			
			while (!validInput) {
				
				try {
					printMenu();
					menuSelection = input.nextInt();
					if (menuSelection < 1 || menuSelection > 7) {
						
						System.out.print("\nThat is not a valid menu selection, please enter a number matching a menu option.\n");
						input.next();
						printMenu();
						
					} else {
						
						validInput = true;
						
					}
					
					
					
				} catch (InputMismatchException e) {
					
					//Catch exception and display error if non number
					System.out.print("\nThat is not a valid menu selection, please enter a number matching a menu option.\n");
				
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
					break;
				case 4:
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
	
	public static void addPet() {
		
		String line = "";
		String name = "";
		int age = 0;
		int numPetsAdded = 0;
		
		do {
			
			System.out.print("\nadd pet (name, age): ");
			line = input.nextLine();
			String[] part = line.split("(?<=\\D)(?=\\d)");
			name = part[0].toLowerCase();
			if (part.length > 1) {
				age = Integer.parseInt(part[1]);
				petDatabase.add(new Pet(name,age));
				numPetsAdded++;
			}
		} while (!name.equals("done"));
		
		System.out.print("\n" + numPetsAdded + " pets added.\n");
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
	
	public static void printMenu() {
		System.out.print("\nWhat would you like to do?\n"
				+ "1) View all pets\n"
				+ "2) Add more pets\n"
				+ "3) Update an existing pet\n"
				+ "4) Remove an existing pet\n"
				+ "5) Search pets by name\n"
				+ "6) Search pets by age\n"
				+ "7) Exit program\n"
				+ "Your choice: "			
				);
	}
}
