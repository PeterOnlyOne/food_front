package cn.et.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.et.model.MyOrder;

/**
 * Servlet implementation class FoodCartServlet
 */
public class FoodCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyOrder order = new MyOrder();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		if ("delete".equals(flag)) {
			String foodId = request.getParameter("foodId");
			session.removeAttribute(foodId);
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
		}else if ("order".equals(flag)) {
			String deskId = session.getAttribute("deskId").toString();
			int state = 0;
			try {
				Integer orderId = order.saveOrder(deskId, state);
				Enumeration<String> keys = session.getAttributeNames();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					if (key.startsWith("cart_")) {
						String foodId = key.split("cart_")[1];
						Map map = (Map) session.getAttribute(key);
						Integer count = (Integer) map.get("count");
						order.saveOrderDetail(orderId, Integer.parseInt(foodId), count);
					}
				}
				session.invalidate();
				
				pw.println("<script>alert('下单成功')</script>");
				response.setHeader("refresh", "1,url="+request.getContextPath()+"/showDesk");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			String foodId = request.getParameter("foodId");
			String foodName = request.getParameter("foodName");
			String price = request.getParameter("price");
			
			Object fid = session.getAttribute("cart_"+foodId);
			if (fid == null) {
				Map map = new HashMap<>();
				map.put("foodName", foodName);
				map.put("price", price);
				map.put("count", 1);
				session.setAttribute("cart_"+foodId, map);
			}else {
				Map map = (Map) fid;
				Integer n = (Integer) map.get("count");
				map.put("count", n+1);
				session.setAttribute("cart_"+foodId, map);
			}
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
