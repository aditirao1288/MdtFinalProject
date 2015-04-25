
package andrew.cmu.edu.model;

import java.util.*;

public class Catalog {

	  private static Map<String,Item> items;

	  static {
	   items = new HashMap<String,Item>(); 
	   items.put("1",new Item("hat001","Hat","Stylish bowler hat (SALE!)",19.99,1.0));
	   items.put("2",new Item("dress001","Dress","A cocktail gown to wear for a dinner",599,2.0));
	   items.put("3",new Item("laptop001","Laptop","A gadget for all your electronic needs",699,3.0));
	   items.put("4",new Item("cha001","Chair","Swivelling office chair", 60.99,4.0));
	   items.put("5",new Item("str001","String","Metric tonne of bailing twine", 1.98,5.0));
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
