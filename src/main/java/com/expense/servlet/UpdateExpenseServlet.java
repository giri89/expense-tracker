package com.expense.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.expense.db.DBConnection;

@WebServlet("/updateExpense")
public class UpdateExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String category = request.getParameter("category");

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE expenses SET title=?, amount=?, category=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setInt(2, amount);
            ps.setString(3, category);
            ps.setInt(4, id);

            ps.executeUpdate();

            response.sendRedirect("viewExpenses");

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}