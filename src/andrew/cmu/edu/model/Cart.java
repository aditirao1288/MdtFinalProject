package andrew.cmu.edu.model;

import java.math.BigDecimal;
import java.util.*;

/**
 * A very simple shopping Cart
 */
public class Cart {

    double actualCartWeight = 0.0;
    double theoreticalCartWeight = 0.0;
    CartColorState colorState = CartColorState.GREEN;
    CartState cartState = CartState.READY;

    private HashMap<Item,Integer> contents;

    public HashMap<Item, Integer> getContents() {
        return contents;
    }

    public void setContents(HashMap<Item, Integer> contents) {
        this.contents = contents;
    }

    /**
     * Creates a new Cart instance
     */
    public Cart() {
        contents = new HashMap<Item, Integer>();

    }

    /**
     * Adds a named item to the cart
     *
     * @param itemName The name of the item to add to the cart
     */
    public void addItem(String itemCode) {
        if(colorState == CartColorState.GREEN) {
            Catalog catalog = new Catalog();

        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = 1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
            }
            contents.put(item, new Integer(newQuantity));
        }
        }
    }

    public void removeItems(String itemCode) {
        Catalog catalog = new Catalog();
        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);
            int newQuantity = 1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity = currentQuantity.intValue() - 1;
                if (newQuantity == 0) {
                    contents.remove(new Catalog().getItem(itemCode));
                } else {
                    contents.remove(new Catalog().getItem(itemCode));
                    contents.put(item, new Integer(newQuantity));
                }
            }
        }
    }
    /**
     * @return XML representation of cart contents
     */
    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<cart generated=\"" + System.currentTimeMillis() + "\" total=\"" + getCartTotal() + "\">\n");

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();
            double totalPrice = item.getPrice() * itemQuantity;
            
            xml.append("<item code=\"" + item.getCode() + "\">\n");
            xml.append("<name>");
            xml.append(item.getName());
            xml.append("</name>\n");
            xml.append("<quantity>");
            xml.append(itemQuantity);
            xml.append("</quantity>\n");
            xml.append("<totalprice>\n");
            xml.append(totalPrice);
            xml.append("</totalprice>\n");
            xml.append("</item>\n");
        }
        xml.append("</cart>\n");
        return xml.toString();
    }

    private String getCartTotal() {
        int total = 0;

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();

            total += (item.getPrice() * itemQuantity);
        }

        return "$" + new BigDecimal(total).movePointLeft(2);
    }

    public String checkout() {
        if(colorState == CartColorState.GREEN) {
            cartState = CartState.PAYMENT_FREEZE;
            return getCartTotal();
        } else {
            return "ERROR";
        }
    }

    public void setSuccessPayment() {
        if(cartState == CartState.PAYMENT_FREEZE) {
            colorState = CartColorState.BLUE;
            cartState = CartState.PAYMENT_SUCCESS;
        }
    }

   public void updateCart(String itemID, String weight) {
        if(!itemID.equals("")) {
            addItem(itemID);
            computeAndSetWeight();
        }
        if(!weight.equals("")) {
            actualCartWeight = Double.valueOf(weight);
        }
        
        checkAndSetState();
    }

    public String getColorStatus() {
        return colorState.toString();
    }

    private void checkAndSetState() {
        if(theoreticalCartWeight != actualCartWeight) {
            cartState = CartState.WEIGHT_ERROR;
            colorState = CartColorState.RED;
        } else {
            cartState = CartState.READY;
            colorState = CartColorState.GREEN;
        }
    }

    public void deleteItemFromCart(String itemID) {
        removeItems(itemID);
        computeAndSetWeight();
        checkAndSetState();
    }

    public enum CartColorState {

        RED, GREEN, BLUE
    }

    public enum CartState {

        READY, WEIGHT_ERROR, GEN_ERROR, PAYMENT_FREEZE, PAYMENT_SUCCESS, PAYMENT_ERROR
    }

    private void computeAndSetWeight() {
        double weight = 0.0;
        for (Map.Entry<Item, Integer> entry : contents.entrySet()) {
            weight += (entry.getKey().getWeight() * entry.getValue());
        }
        theoreticalCartWeight = weight;
    }
}
