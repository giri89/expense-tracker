package com.expense.test;

import java.sql.Connection;

import com.expense.db.DBConnection;

public class TestDB {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if(con != null){
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Failed");
        }

    }
}