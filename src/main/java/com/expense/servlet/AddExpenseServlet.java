package com.expense.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.expense.db.DBConnection;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.html");
			return;
		}

		int userId = (int) session.getAttribute("userId");

		String title = request.getParameter("title");
		String amount = request.getParameter("amount");
		String category = request.getParameter("category");

		try {

			Connection con = DBConnection.getConnection();

			String sql = "INSERT INTO expenses(user_id,title,amount,category,expense_date) VALUES(?,?,?,?,NOW())";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, userId);
			ps.setString(2, title);
			ps.setString(3, amount);
			ps.setString(4, category);

			ps.executeUpdate();

			/* SUCCESS MESSAGE FOR DASHBOARD */

			session.setAttribute("msg", "Expense Added Successfully");

			/* REDIRECT TO DASHBOARD */

			response.sendRedirect("dashboard");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}