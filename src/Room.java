import java.util.ArrayList;
import java.util.HashMap;

public class Room {

	// instance variables
	private ArrayList<Item> items;
	private HashMap<String, Room> doors;
	private String description;
	private Game game;

	// constructor
	public Room(Game game) {
		this.game = game;
		items = new ArrayList<>();
		doors = new HashMap<>();
	}

	// method that returns all the doors from a room
	public HashMap<String, Room> getDoors() {
		return doors;
	}

	// method that adds an item to the room
	public void addRoomItem(Item item) {
		items.add(item);
	}

	// method that removes room item
	public void removeRoomItem(Item item) {
		items.remove(item);
	}

	// returns all the items that are present in room
	public ArrayList<Item> getItems() {
		return items;
	}

	// method that shows more specific information about the room and item that it
	// contains within the room
	public void look() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == null) {
				System.out.println("This room has no  items.");
				System.out.println(description);
			}
			if (items.get(i) != null) {
				System.out.println(
						items.get(i).getName() + ", " + items.get(i).getUsageText() + " is currently in this room");
			}
		}
	}

	// method that shows a small description about the room itself, with the
	// possible exits
	public void showRoomInfo() {
		System.out.println("current room: " + description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
