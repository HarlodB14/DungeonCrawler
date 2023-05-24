import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	// instance variables
	private String name;
	private ArrayList<Item> backPack;
	private Scanner scanner = new Scanner(System.in);

	// constructor
	public Player() {
		backPack = new ArrayList<>();
	}

	// method that adds item to backpack of player
	public void addItem(Item item) {
		backPack.add(item);
		return;
	}

	// method that removes items from the backpack
	public void removeItem(Item item) {
		backPack.remove(item);
		return;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// method that returns all the items from the player
	public ArrayList<Item> getBackPack() {
		return backPack;
	}

	// method that shows contents of player inventory
	public void pack() {
		if (backPack.isEmpty() == true) {
			System.out.println("*There are no items in your backpack*");
		} else {
			System.out.println("<Inventory>:");
			for (int i = 0; i < backPack.size(); i++) {
				System.out.print("Item: ");
				System.out.println(i + 1 + " -" + " " + backPack.get(i).getName());
			}
		}
	}

	// method that shows usefull commands to the player
	public void help() {
		System.out.println("Here are helpfull commands you can use:");
		System.out.println("- go (use this to traverse around the rooms)\r\n"
				+ "- get  (use this to retrieve items in the room)\r\n"
				+ "- drop (use this to drop all items from your backpack)\r\n"
				+ "- pack (show all your items that are currently in your backpack)\r\n"
				+ "- help (shows a list of all the commands you can use) \r\n"
				+ "- look (shows a list of all the items and exits within a room)\r\n" + "- quit (exit the game)\r\n"
				+ "- use  (use something within from within your backpack or current room)");
	}

	// method that asks input for the playername
	public String getPlayerName() {
		String input = "?";
		boolean repeat = true;
		while (repeat) {
			System.out.println("What is your name?");
			input = scanner.nextLine();
			if (!(input.matches("[a-zA-Z-0-9]+"))) {
				System.out.println("Please fill in a correct name with only letters and numbers");
			} else {
				repeat = false;
			}

		}
		return input;
	}

}
