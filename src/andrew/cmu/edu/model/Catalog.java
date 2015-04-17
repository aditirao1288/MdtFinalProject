
package andrew.cmu.edu.model;

import java.util.*;

public class Catalog {

  private static Map<String,Item> items;

  static {
   items = new HashMap<String,Item>(); 
   items.put("hat001",new Item("hat001","Hat","Stylish bowler hat (SALE!)",19.99,5.0));
   items.put("dress001",new Item("dress001","Dress","A cocktail gown to wear for a dinner",599,4.5));
   items.put("laptop001",new Item("laptop001","Laptop","A gadget for all your electronic needs",699,10));
   items.put("cha001",new Item("cha001","Chair","Swivelling office chair", 60.99,15));
   items.put("str001",new Item("str001","String","Metric tonne of bailing twine", 1.98, 0.5));
   
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
