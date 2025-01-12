package com.example.servlet;  
  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.util.ArrayList;  
import java.util.List;  
  
import com.example.config.DatabaseConfig;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


public class CustomerServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    private DatabaseConfig dbConfig;  
  
    public void init() throws ServletException {  
        dbConfig = DatabaseConfig.getInstance();  
    }
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        List<String> customers = new ArrayList<>();  
  
        try {  
            // JDBC ドライバのロード  
            Class.forName(dbConfig.getJdbcDriver());  
  
            Connection conn;  
            // SQLite の場合、ユーザー名とパスワードは不要  
            if (dbConfig.getDbUser() == null || dbConfig.getDbUser().isEmpty()) {  
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl());  
            } else {  
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getDbUser(), dbConfig.getDbPass());  
            }  
  
            Statement stmt = conn.createStatement();  
            String sql = "SELECT name FROM customers";  
            ResultSet rs = stmt.executeQuery(sql);  
  
            while (rs.next()) {  
                customers.add(rs.getString("name"));  
            }  
  
            rs.close();  
            stmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        request.setAttribute("customers", customers);  
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewCustomers.jsp");  
        dispatcher.forward(request, response);  
    }  
}
