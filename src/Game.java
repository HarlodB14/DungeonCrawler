import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	// instance variables
	private Player myPlayer;
	private Room[] rooms;
	private Scanner scanner = new Scanner(System.in);
	private String playerName;
	private Room currentRoom;

	// constructor
	public Game() {
		setPlayer();
		showIntro();
		createRooms();
		setStartingRoom();
		run();
	}

	// method that sets up the player with a name
	private void setPlayer() {
		myPlayer = new Player();
		playerName = myPlayer.getPlayerName();
		myPlayer.setName(playerName);
	}

	// gameloop method
	private void run() {
		boolean keepPlaying = true;
		while (keepPlaying) {
			while (getPlayerInput() != "quit") {
				getPlayerInput();
				if (checkEndGame() == true) {
					showEndingScreen();
					keepPlaying = false;
					break;
				}
			}
		}
	}

	// method that checks if the player found the magical weapon
	private boolean checkEndGame() {
		boolean result = false;
		ArrayList<Item> roomItems = currentRoom.getItems();
		if (currentRoom == rooms[10]) {
			for (Item item : roomItems) {
				if (item.getName().equals("skywardsword")) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	// method that shows ending credits
	private void showEndingScreen() {
		System.out.println("Oh my goodness you have returned with the Magical Skyward sword!! " + playerName
				+ " :O, you are now granted as the chosen One!");
		System.out.println("You are now more prepared for other quests in the future!  ^^");
		System.out.println(
				"[===========================================================================================================]");
		System.out.println();
	}

	// method that sets the startingroom where the player will begin
	private void setStartingRoom() {
		currentRoom = rooms[0];
	}

	// introductiontext method
	private void showIntro() {
		System.out.println("========================================================================");
		System.out.println(
				"Hi " + playerName + "!, Welcome to Room EXplorer! Where you will crawl within adventurous rooms!");
		System.out.println(
				"The goal for you is to find a very magical item that sages have left in this scary dungeon. Find this item and retrieve it back to the entrance where you started");
		System.out.println(
				"You will begin your journey in te starting room, where you will begin you adventure, goodluck!");
		System.out.println("Tip: use the help commands to get familiar with the controls");
		System.out.println();
	}

	// method that keeps waiting for input from player
	private String getPlayerInput() {
		String input = "?";
		boolean repeat = true;
		while (repeat) {
			if (checkEndGame() == true) {
				break;
			}
			input = scanner.nextLine();
			handleCommand(input);
			currentRoom.showRoomInfo();
			System.out.println(
					"[==========================================================================================================]");

		}

		return input;
	}

	// method that handles the commands of the userInput
	private void handleCommand(String input) {
		String[] command = new String[2];
		command = input.toLowerCase().split(" ");

		if (command.length == 1) {
			switch (command[0]) {
			case "help":
				myPlayer.help();
				break;
			case "quit":
				System.exit(0);
				break;
			case "look":
				currentRoom.look();
				break;
			case "pack":
				myPlayer.pack();
				break;
			default:
				System.out.println("wrong input, try again");
			}
		} else {
			switch (command[0]) {
			case "go":
				checkRoomTravel(command[1]);
				break;
			case "get":
				handleGetCommand(command[1]);
				break;
			case "use":
				handleUseCommand(command[1]);
				break;
			case "drop":
				handleDropCommand(command[1]);
				break;
			default:
				System.out.println("wrong input, try again");
			}
		}

	}

	// method that checks if input matches with the doors of a specific room
	private void checkRoomTravel(String command) {
		if (!(currentRoom.getDoors().containsKey(command))) {
			System.out.println("false, No door here, try specifying a location correctly");
		} else {
			currentRoom = currentRoom.getDoors().get(command);
			System.out.println("True");
			System.out.println("Travelling now to destination room");
		}
	}

	// method that returns all the items present within the whole game
	private ArrayList<Item> getallItems() {
		ArrayList<Item> backPack = myPlayer.getBackPack();
		ArrayList<Item> items = currentRoom.getItems();
		ArrayList<Item> allItems = new ArrayList<>();
		allItems.addAll(backPack);
		allItems.addAll(items);
		return allItems;
	}

	// method that picks up items that will be stored in the players backpack
	private void handleGetCommand(String itemName) {
		ArrayList<Item> allItems = getallItems();
		ArrayList<Item> backPack = myPlayer.getBackPack();

		for (Item item : allItems) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				if (!(backPack.contains(item))) {
					System.out.println(item.getName() + " picked!");
					backPack.add(item);
					currentRoom.removeRoomItem(item);
					return;
				}
			}
		}
		System.out.println(
				"You are trying to get something that does not exist or is not present at the room anymore...");
	}

	// method that drops a specific item the user wants to drop in the room
	private void handleDropCommand(String itemName) {
		ArrayList<Item> backPack = myPlayer.getBackPack();

		for (Item item : backPack) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				System.out.println(item.getName() + " dropped!*");
				backPack.remove(item);
				currentRoom.addRoomItem(item);
				return;
			}
		}
		System.out.println("You are trying to drop something that is not present in your backpack...");

	}

	// method that makes the player interact with the items in the game
	private void handleUseCommand(String itemName) {
		ArrayList<Item> backpack = myPlayer.getBackPack();
		ArrayList<Item> allItems = getallItems();

		for (Item item : allItems) {
			if (item.getName().equalsIgnoreCase(itemName)) {
				System.out.println(item.getUseText());
				if (backpack.contains(item)) {
					backpack.remove(item);
				} else {
					currentRoom.removeRoomItem(item);
				}
				allItems.remove(item);
				return;
			}
		}
		System.out.println("You are trying to use something that does not exist...");

	}

	// method that creates rooms and fills it with items.
	private void createRooms() {
		rooms = new Room[11];
		// room1
		rooms[0] = new Room(this);

		Item jugglerStone = new Item("jugglerStone");
		jugglerStone.setUseText("*JUG JUG JUG....Stamina is being replenished*");
		jugglerStone.setUsageText("a StaminaFill-item");
		rooms[0].addRoomItem(jugglerStone);
		// room2
		rooms[1] = new Room(this);

		Item majorasMask = new Item("Majoramask");
		majorasMask.setUseText("*Changing form in mask*");
		majorasMask.setUsageText("Form changing mask");
		rooms[1].addRoomItem(majorasMask);
		// room3
		rooms[2] = new Room(this);

		Item stick = new Item("Woodenstick");
		stick.setUseText("*Throwing stick*");
		stick.setUsageText(
				"The stick of truth, this stick is able to spew facts at people who do not respect authority");
		rooms[2].addRoomItem(stick);
		// room4
		rooms[3] = new Room(this);

		Item juggernaught = new Item("Juggernaught");
		juggernaught.setUsageText("A potion that makes you stronger");
		juggernaught.setUseText("*DRINKING NOW, FEELING STRONGERRRRR*");
		rooms[3].addRoomItem(juggernaught);
		// room5
		rooms[4] = new Room(this);

		Item health = new Item("HealthPotion(50+)");
		health.setUseText("*Health regenerated by +50*");
		health.setUsageText("A potion that gives you +50 health");
		rooms[4].addRoomItem(health);
		// room6
		rooms[5] = new Room(this);

		Item wood = new Item("WoodenLog");
		wood.setUsageText("A wooden log that you can use to build tools");
		wood.setUseText("*building something with this log*");
		rooms[5].addRoomItem(wood);
		// room7
		rooms[6] = new Room(this);

		Item health2 = new Item("Healthpotion(25+)");
		health2.setUseText("*Health replenished by +25*");
		health2.setUsageText("A potion that gives you 25+ health");
		rooms[6].addRoomItem(health2);
		// room8
		rooms[7] = new Room(this);

		Item sword = new Item("skywardsword");
		sword.setUseText("*SLAASHHHHHHHHH*");
		sword.setUsageText(
				"A almighty sword, which is really usefull in stressfull situations caused by known/unknown threats");
		// room9
		rooms[7].addRoomItem(sword);
		rooms[8] = new Room(this);

		Item ocarina = new Item("Ocarina");
		ocarina.setUseText("*now Playing  song of storm's*");
		ocarina.setUsageText("A powerfull instrument, which can be used to time travel :O");
		rooms[8].addRoomItem(ocarina);
		// room10
		rooms[9] = new Room(this);

		Item cloth = new Item("PaperCloth");
		cloth.setUseText("*Wiping ASS*");
		cloth.setUsageText("A useless cloth");
		rooms[9].addRoomItem(cloth);

		// outside
		rooms[10] = new Room(this);

		setDescriptions();
		setDoors();
	}

	// method that sets all the descriptions of the rooms
	private void setDescriptions() {
		rooms[0].setDescription(
				"[Starting room]. this room  may contain 1/4 doors, that you can maybe enter from going east, south to get outside the dungeon, west or north.\r\n\""
						+ "This room may contain the following items:\r\n" + "- the jugglerStone");
		rooms[1].setDescription(
				"[Majora's room]. this room may contain 1/2 doors, that you can maybe enter from going east or north.\r\n\""
						+ "This room may contain the following items: \r\n" + " - Majora's Mask");
		rooms[2].setDescription(
				"[TreeTrunkRoom]. this room may contain 1/2 doors, that you can maybe enter from going east or south.\r\n\""
						+ "This room may contain the following items: \r\n" + " - a Wooden stick");
		rooms[3].setDescription("jugglerRoom. this room does not have any exits, it contains 1 door.\r\n\""
				+ "This room may contain the following items: \r\n" + " - a juggernaught-Potion");
		rooms[4].setDescription(
				"[KingdomHeartsRoom]. this room has 1 door which you can only exit from the east-side. This room contains a specific potion");
		rooms[5].setDescription(
				"[KikoriRoom]. this room has 1/3 doors which you can enter from north, west or east. This room contains an item for tools");
		rooms[6].setDescription(
				"[PastaRoom]. this room has 1/2 doors which can be entered from the south or west side. This room contains a specific potion");
		rooms[7].setDescription(
				"[Zelda's chamber Room]. this room has 1 door, which is located somewhere north. This room contains a very magical item. An item which can be used to slay All kinds of threats");
		rooms[8].setDescription(
				"[OcarinaRoom]. this room has 2 doors, which are located east and south. This room contains powerfull instrument");
		rooms[9].setDescription(
				"[SoSoRoom]. this room has 2 doors, located south and west. This room has something very useless");
		rooms[10].setDescription("[*Outdoors*]. " + playerName
				+ " you are now located outside. staring at the entrance of startingRoom. Your quest will be completed here if you return with the magical item...");
	}

	// method that connects the doors with the reference of the specific roomObjects
	private void setDoors() {
		// starting room doors
		rooms[0].getDoors().put("north", rooms[4]);
		rooms[0].getDoors().put("east", rooms[3]);
		rooms[0].getDoors().put("west", rooms[1]);
		rooms[0].getDoors().put("south", rooms[10]);

		// room2
		rooms[1].getDoors().put("north", rooms[2]);
		rooms[1].getDoors().put("east", rooms[0]);

		// room3
		rooms[2].getDoors().put("south", rooms[1]);
		rooms[2].getDoors().put("east", rooms[4]);

		// room4
		rooms[3].getDoors().put("west", rooms[0]);

		// room5
		rooms[4].getDoors().put("north", rooms[9]);
		rooms[4].getDoors().put("east", rooms[5]);
		rooms[4].getDoors().put("south", rooms[0]);
		rooms[4].getDoors().put("west", rooms[2]);

		// room6
		rooms[5].getDoors().put("north", rooms[8]);
		rooms[5].getDoors().put("east", rooms[6]);
		rooms[5].getDoors().put("west", rooms[4]);

		// room7
		rooms[6].getDoors().put("west", rooms[5]);
		rooms[6].getDoors().put("south", rooms[7]);

		// room8
		rooms[7].getDoors().put("north", rooms[6]);

		// room9
		rooms[8].getDoors().put("west", rooms[9]);
		rooms[8].getDoors().put("south", rooms[5]);

		// room10
		rooms[9].getDoors().put("east", rooms[8]);
		rooms[9].getDoors().put("south", rooms[4]);

		// "outside"
		rooms[10].getDoors().put("north", rooms[0]);
	}

}
