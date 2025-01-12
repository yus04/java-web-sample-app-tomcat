package com.example.servlet;  
  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
  
import com.example.config.DatabaseConfig;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
 
public class RegisterCustomerServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    private DatabaseConfig dbConfig;  
  
    public void init() throws ServletException {  
        dbConfig = DatabaseConfig.getInstance();  
    }  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");  
        String email = request.getParameter("email");  
        String phone = request.getParameter("phone");  
        String address = request.getParameter("address");  
  
        try {  
            // JDBC ドライバのロード  
            Class.forName(dbConfig.getJdbcDriver());  
  
            Connection conn;  
            // SQLite の場合、ユーザー名とパスワードは不要  
            if (dbConfig.getDbUser() == null || dbConfig.getDbUser().isEmpty()) {  
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl());  
            } else {  
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getDbUser(),  
                        dbConfig.getDbPass());  
            }  
  
            String sql = "INSERT INTO customers (name, email, phone, address) VALUES (?, ?, ?, ?)";  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);  
            pstmt.setString(2, email);  
            pstmt.setString(3, phone);  
            pstmt.setString(4, address);  
            pstmt.executeUpdate();  
  
            pstmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        response.sendRedirect("CustomerServlet");  
    }  
}
