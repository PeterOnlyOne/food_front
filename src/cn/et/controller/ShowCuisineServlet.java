package cn.et.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.et.model.MyCuisine;
import cn.et.model.MyFood;
import cn.et.model.PageTools;

/**
 * Servlet implementation class ShowCuisineServlet
 */
public class ShowCuisineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCuisineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyCuisine myCuisine = new MyCuisine();
    MyFood myFood = new MyFood();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//进入页面查询所有菜品
		//点击菜系，传入菜系编号，查询菜系下的菜品
		String typeId = "";
		if (request.getParameter("typeId") != null) {
			typeId = request.getParameter("typeId");
		}
		//将deskId放入session中，方便餐车结账使用
		HttpSession session = request.getSession();
		if (request.getParameter("deskId") != null) {
			session.setAttribute("deskId", request.getParameter("deskId"));
		}
		
		String curPage = request.getParameter("curPage");
		Integer curPageInt = 1;
		if (curPage != null) {
			curPageInt = Integer.parseInt(curPage);
		}
		//菜品
		PageTools foodListPage = myFood.getFoodListPage(curPageInt,typeId);
		request.setAttribute("foodList", foodListPage);
		request.getRequestDispatcher("/detail/caidan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
