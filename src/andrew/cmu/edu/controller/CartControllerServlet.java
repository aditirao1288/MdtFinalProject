package andrew.cmu.edu.controller;
import java.io.IOException;

import javax.servlet.http.*;

import andrew.cmu.edu.model.Cart;


public class CartControllerServlet extends HttpServlet {
	private static final String MOBILE = "Mobile";
	private static final String CART = "Cart";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String search = req.getParameter("client");
		if (MOBILE.equalsIgnoreCase(search)) {
			// return list of items in cart to android
			Cart c = getCartFromSession(req);
			String cartXml = c.toXml();
			resp.setContentType("text/xml");
			resp.getWriter().write(cartXml);

		} else if (CART.equalsIgnoreCase(search)) {
			//get color status
			//return list of items
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String search = req.getParameter("client");
		if (MOBILE.equalsIgnoreCase(search)) {
			//success payment - success message
			//
			
		} else if (CART.equalsIgnoreCase(search)) {
			//color codes
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}

	private Cart getCartFromSession(HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		return cart;
	}
}
