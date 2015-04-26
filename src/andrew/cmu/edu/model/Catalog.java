
package andrew.cmu.edu.model;

import java.util.*;

public class Catalog {

	  private static Map<String,Item> items;

	  static {
		   items = new HashMap<String,Item>(); 
		   items.put("1",new Item("1","Hat","Stylish bowler hat",19.99,1.0));
		   items.put("2",new Item("2","Dress","A cocktail gown to wear for a dinner",599,2.0));
		   items.put("3",new Item("3","Laptop","A gadget for all your electronic needs",699,3.0));
		   items.put("4",new Item("4","Chair","Swivelling office chair", 60.99,4.0));
		   items.put("5",new Item("5","Swing","A swing", 1.98,5.0));
		   items.put("6",new Item("6","Car","Stylish toy car",29.99,6.0));
		   items.put("7",new Item("7","Scooter","Harley miniature",299,7.0));
		   items.put("8",new Item("8","Bag","A very spacious bag",100,8.0));
		   items.put("9",new Item("9","Cookies","Mouth watering cookies", 40.99,9.0));
		   items.put("10",new Item("10","Shirt","GAP white shirt", 26.98,10.0));
		  }
	  public Collection<Item> getAllItems() {
	    return items.values();
	  }

	  public boolean containsItem(String itemCode) {
	    return items.containsKey(itemCode);
	  }

	  public Item getItem(String itemCode) {
	    return items.get(itemCode);
	  }
}
