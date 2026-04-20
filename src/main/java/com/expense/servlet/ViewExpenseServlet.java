package com.expense.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.expense.db.DBConnection;

@WebServlet("/viewExpenses")
public class ViewExpenseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.html");
			return;
		}

		int userId = (int) session.getAttribute("userId");

		out.println("<html>");
		out.println("<head>");

		out.println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

		out.println("</head>");

		out.println("<body class='bg-light'>");

		out.println("<div class='container mt-5'>");

		out.println("<h2>Expense List</h2>");

		out.println("<a href='dashboard' class='btn btn-secondary btn-sm'>Dashboard</a> ");
		out.println("<a href='addExpense.html' class='btn btn-success btn-sm'>Add Expense</a>");
		out.println("<br><br>");

		out.println("<table class='table table-bordered table-striped'>");

		out.println("<tr>");
		out.println("<th>Title</th>");
		out.println("<th>Amount</th>");
		out.println("<th>Category</th>");
		out.println("<th>Date</th>");
		out.println("<th>Action</th>");
		out.println("</tr>");

		try {

			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM expenses WHERE user_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				out.println("<tr>");

				out.println("<td>" + rs.getString("title") + "</td>");
				out.println("<td>" + rs.getInt("amount") + "</td>");
				out.println("<td>" + rs.getString("category") + "</td>");
				out.println("<td>" + rs.getString("expense_date") + "</td>");

				out.println("<td>");

				out.println(
						"<a class='btn btn-warning btn-sm' href='editExpense?id=" + rs.getInt("id") + "'>Edit</a> ");

				out.println(
						"<a class='btn btn-danger btn-sm' href='deleteExpense?id=" + rs.getInt("id") + "'>Delete</a>");

				out.println("</td>");

				out.println("</tr>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}
}