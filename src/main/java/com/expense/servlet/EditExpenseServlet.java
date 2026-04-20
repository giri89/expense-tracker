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

@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

			Connection con = DBConnection.getConnection();

			String sql = "SELECT * FROM expenses WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				out.println("<html>");
				out.println("<head>");

				out.println(
						"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

				out.println("</head>");

				out.println("<body class='bg-light'>");

				out.println("<div class='container mt-5'>");

				out.println("<div class='row justify-content-center'>");

				out.println("<div class='col-md-5'>");

				out.println("<div class='card shadow'>");

				out.println("<div class='card-header text-center bg-warning'>");
				out.println("<h4>Edit Expense</h4>");
				out.println("</div>");

				out.println("<div class='card-body'>");

				out.println("<form action='updateExpense' method='post'>");

				out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");

				out.println("<div class='mb-3'>");
				out.println("<label>Title</label>");
				out.println(
						"<input type='text' name='title' class='form-control' value='" + rs.getString("title") + "'>");
				out.println("</div>");

				out.println("<div class='mb-3'>");
				out.println("<label>Amount</label>");
				out.println(
						"<input type='number' name='amount' class='form-control' value='" + rs.getInt("amount") + "'>");
				out.println("</div>");

				out.println("<div class='mb-3'>");
				out.println("<label>Category</label>");
				out.println("<input type='text' name='category' class='form-control' value='" + rs.getString("category")
						+ "'>");
				out.println("</div>");

				out.println("<button class='btn btn-success w-100'>Update Expense</button>");

				out.println("</form>");

				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");

				out.println("</body>");
				out.println("</html>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}