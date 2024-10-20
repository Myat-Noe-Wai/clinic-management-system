package com.clinic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class GetDataServlet
 */
public class GetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String data = request.getParameter("data");
		 System.out.println("*************");
		 try {
			// Now, you can use the value in your JSP scriptlets
		        request.setAttribute("data", data);
//				  request.getRequestDispatcher("/patient_history.jsp").forward(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
