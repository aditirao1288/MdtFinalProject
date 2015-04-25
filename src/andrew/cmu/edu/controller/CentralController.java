package andrew.cmu.edu.controller;


import java.util.HashMap;

import andrew.cmu.edu.model.Cart;

public class CentralController {

    HashMap<String, String> users = new HashMap<>();
    HashMap<String, Cart> sessions = new HashMap<>();

    private static CentralController instance = null;

    private CentralController() {
    }

    // Lazy Initialization (If required then only)
    public static CentralController getInstance() {
        if (instance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (CentralController.class) {
                if (instance == null) {
                    instance = new CentralController();
                }
            }
        }
        return instance;
    }

    /**
     * *********************************************************
     * Mobile client methods
     * ********************************************************
     */
    public String mobGetStatus(String userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            return "-1";
        }
    }

    public String mobGetCart(String session) {
        if (sessions.containsKey(session)) {
            return sessions.get(session).toXml();
        } else {
            return "<?xml version=\"1.0\"?>\n<cart></cart>";
        }
    }

    public String mobCheckout(String session) {
        if (sessions.containsKey(session)) {
            return sessions.get(session).checkout();
        } else {
            return "ERROR";
        }
    }

    public void mobSetPaymentSuccess(String session) {
        if (sessions.containsKey(session)) {
            sessions.get(session).setSuccessPayment();
        } 
    }

    /**
     * ********************************************************
     * Cart client methods
     * *********************************************************
     */
    public void cartAddUser(String userID, String cartId) {
        String session = userID + "_" + cartId;
        if (!sessions.containsKey(session)) {
            if (users.containsKey(userID)) {
                users.remove(userID);
            }
            users.put(userID, session);
            sessions.put(session, new Cart());
        }
    }

    public void cartUpdate(String itemID, String weight, String sessionID) {
        if (sessions.containsKey(sessionID)) {
            sessions.get(sessionID).updateCart(itemID, weight);
        }
    }

    public String cartGetCart(String sessionID) {
        if (sessions.containsKey(sessionID)) {
            return sessions.get(sessionID).toXml();
        } else {
            return "<?xml version=\"1.0\"?>\n<cart></cart>";
        }
    }

    public String cartGetColorStatus(String sessionID) {
        if (sessions.containsKey(sessionID)) {
            return sessions.get(sessionID).getColorStatus();
        } else {
            return "RED";
        }
    }
    
    public void cartDeleteItem(String itemID, String sessionID) {
        if (sessions.containsKey(sessionID)) {
            sessions.get(sessionID).deleteItemFromCart(itemID);
        }
    }
}
