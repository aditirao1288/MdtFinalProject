package andrew.cmu.edu.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shrek
 */
import java.io.IOException;

import javax.servlet.http.*;

public class CartControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        CentralController cc = CentralController.getInstance();

        String method = req.getParameter("method");
        String sessionID = null;
        String userID = null;
        String itemID = null;
        String cartID = null;

        switch (method) {
            case "mobGetStatus":
                userID = req.getParameter("userID");
                sessionID = cc.mobGetStatus(userID);
                resp.setContentType("text/plain");
                resp.getWriter().write(sessionID);
                break;

            case "mobGetCart":
                sessionID = req.getParameter("sessionID");
                String xmlString = cc.mobGetCart(sessionID);
                resp.setContentType("application/xml");
                resp.getWriter().write(xmlString);
                break;

            case "mobCheckout":
                sessionID = req.getParameter("sessionID");
                String totPrice = cc.mobCheckout(sessionID);
                resp.setContentType("text/plain");
                resp.getWriter().write(totPrice);
                break;

            case "mobSetPaymentSuccess":
                String success = req.getParameter("sessionID");
                cc.mobSetPaymentSuccess(success);
                break;

            //Cart cases  
            case "cartAddUser":
                userID = req.getParameter("userID");
                cartID = req.getParameter("cartID");
                cc.cartAddUser(userID, cartID);
                break;

            case "cartUpdate":
                itemID = req.getParameter("itemID");
                String weight = req.getParameter("weight");
                sessionID = req.getParameter("sessionID");
                cc.cartUpdate(itemID, weight, sessionID);
                break;

            case "cartDeleteItem":
                itemID = req.getParameter("itemID");
                sessionID = req.getParameter("sessionID");
                cc.cartDeleteItem(itemID, sessionID);
                break;

            case "cartGetColorStatus":
                sessionID = req.getParameter("sessionID");
                String color = cc.cartGetColorStatus(sessionID);
                resp.setContentType("text/plain");
                resp.getWriter().write(color);
                break;

            case "cartGetCart":
                sessionID = req.getParameter("sessionID");
                String cartXML = cc.cartGetCart(sessionID);
                resp.setContentType("application/xml");
                resp.getWriter().write(cartXML);
                break;
                
            case "cartResetCart":
                sessionID = req.getParameter("sessionID");
                String response = cc.resetSession(sessionID);
                resp.setContentType("text/plain");
                resp.getWriter().write(response);
                break;
                
                
            case "cartGetStatus":
                cartID = req.getParameter("cartID");
                sessionID = cc.cartGetStatus(cartID);
                resp.setContentType("text/plain");
                resp.getWriter().write(sessionID);
                break;
                
            default:               
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}
