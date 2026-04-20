package com.expense.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.expense.db.DBConnection;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.html");
			return;
		}

		int userId = (int) session.getAttribute("userId");

		String totalExpense = "0";
		int expenseCount = 0;

		ArrayList<String> categories = new ArrayList<>();
		ArrayList<String> amounts = new ArrayList<>();

		ArrayList<String> months = new ArrayList<>();
		ArrayList<String> monthAmounts = new ArrayList<>();

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT SUM(amount) FROM expenses WHERE user_id=?");

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				totalExpense = rs.getString(1);
				if (totalExpense == null)
					totalExpense = "0";
			}

			PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(*) FROM expenses WHERE user_id=?");

			ps1.setInt(1, userId);

			ResultSet rs1 = ps1.executeQuery();

			if (rs1.next()) {
				expenseCount = rs1.getInt(1);
			}

			PreparedStatement ps2 = con
					.prepareStatement("SELECT category,SUM(amount) FROM expenses WHERE user_id=? GROUP BY category");

			ps2.setInt(1, userId);

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				categories.add("'" + rs2.getString(1) + "'");
				amounts.add(rs2.getString(2));
			}

			PreparedStatement ps3 = con.prepareStatement(
					"SELECT MONTHNAME(expense_date),SUM(amount) FROM expenses WHERE user_id=? GROUP BY MONTH(expense_date)");

			ps3.setInt(1, userId);

			ResultSet rs3 = ps3.executeQuery();

			while (rs3.next()) {
				months.add("'" + rs3.getString(1) + "'");
				monthAmounts.add(rs3.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<head>");

		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");

		out.println("<title>Expense Dashboard</title>");

		out.println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");

		out.println("<style>");

		out.println("body{background:#f5f6fa;overflow-x:hidden;}");

		out.println(
				".sidebar{height:100vh;background:linear-gradient(180deg,#4e73df,#224abe);color:white;position:relative;}");

		out.println(
				".sidebar a{color:white;text-decoration:none;padding:12px;display:block;border-radius:6px;margin-bottom:5px;}");

		out.println(".sidebar a:hover{background:rgba(255,255,255,0.2);}");

		out.println(".logout{position:absolute;bottom:20px;width:85%;}");

		out.println(
				".header-box{background:white;border-radius:10px;padding:15px;border:1px solid #e2e2e2;margin-bottom:20px;}");

		out.println(".card{border:none;border-radius:12px;}");

		out.println(".chart-container{height:300px;position:relative;}");

		out.println("</style>");

		out.println("</head>");

		out.println("<body>");

		out.println("<div class='container-fluid'>");

		out.println("<div class='row'>");

		/* SIDEBAR */

		out.println("<div class='col-md-2 sidebar p-3'>");

		out.println("<h4 class='text-center mb-4'>Expense Tracker</h4>");

		out.println("<a href='dashboard'>Dashboard</a>");
		out.println("<a href='addExpense.html'>Add Expense</a>");
		out.println("<a href='viewExpenses'>View Expenses</a>");

		out.println("<a href='logout' class='logout btn btn-danger'>Logout</a>");

		out.println("</div>");

		/* MAIN */

		out.println("<div class='col-md-10 p-4'>");

		out.println("<div class='header-box'>");
		out.println("<h4 class='mb-0'>Expense Analytics</h4>");
		out.println("</div>");

		/* TOP CARDS */

		out.println("<div class='row g-3'>");

		out.println("<div class='col-md-6'>");
		out.println("<div class='card shadow h-100'>");
		out.println("<div class='card-body text-center'>");
		out.println("<h6>Total Expense</h6>");
		out.println("<h3 class='text-primary'>₹ " + totalExpense + "</h3>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='col-md-6'>");
		out.println("<div class='card shadow h-100'>");
		out.println("<div class='card-body text-center'>");
		out.println("<h6>Total Entries</h6>");
		out.println("<h3 class='text-success'>" + expenseCount + "</h3>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</div>");

		/* CHARTS */

		out.println("<div class='row mt-4 g-3'>");

		out.println("<div class='col-md-6'>");
		out.println("<div class='card shadow h-100'>");
		out.println("<div class='card-body'>");
		out.println("<h5 class='mb-3'>Category Distribution</h5>");
		out.println("<div class='chart-container'>");
		out.println("<canvas id='pieChart'></canvas>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("<div class='col-md-6'>");
		out.println("<div class='card shadow h-100'>");
		out.println("<div class='card-body'>");
		out.println("<h5 class='mb-3'>Monthly Spending</h5>");
		out.println("<div class='chart-container'>");
		out.println("<canvas id='barChart'></canvas>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");

		out.println("</div>");

		/* CHART JS */

		out.println("<script>");

		out.println("new Chart(document.getElementById('pieChart'),{");

		out.println("type:'pie',");

		out.println("data:{");

		out.println("labels:[" + String.join(",", categories) + "],");

		out.println("datasets:[{");

		out.println("data:[" + String.join(",", amounts) + "],");

		out.println("backgroundColor:['#4e73df','#1cc88a','#36b9cc','#f6c23e','#e74a3b']");

		out.println("}]");

		out.println("},");

		out.println("options:{responsive:true,maintainAspectRatio:false}");

		out.println("});");

		out.println("new Chart(document.getElementById('barChart'),{");

		out.println("type:'bar',");

		out.println("data:{");

		out.println("labels:[" + String.join(",", months) + "],");

		out.println("datasets:[{");

		out.println("label:'Expenses',");

		out.println("data:[" + String.join(",", monthAmounts) + "],");

		out.println("backgroundColor:'#4e73df'");

		out.println("}]");

		out.println("},");

		out.println("options:{responsive:true,maintainAspectRatio:false}");

		out.println("});");

		out.println("</script>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");

		out.println("</body>");

		out.println("</html>");
	}
}