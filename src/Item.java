
public class Item {

	// instance variables
	private String name;
	private String usageText;
	private String useText;

	// constructor
	public Item(String name) {
		this.name = name;
	}
	//method that shows the text when item is being used
	public String getUseText() {
		return useText;
	}
	
	public void setUseText(String useText) {
		this.useText = useText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//method that shows information on why to use this item
	public String getUsageText() {
		return usageText;
	}

	public void setUsageText(String usageText) {
		this.usageText = usageText;
	}

}
