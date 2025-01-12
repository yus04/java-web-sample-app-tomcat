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
  
 
public class HearingServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;    
  
    private DatabaseConfig dbConfig;    
  
    public void init() throws ServletException {  
        dbConfig = DatabaseConfig.getInstance();  
    }  
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
        request.setCharacterEncoding("UTF-8");
        String customerIdStr = request.getParameter("customerId");    
        String hearingContent = request.getParameter("hearing");    
  
        try {    
            int customerId = Integer.parseInt(customerIdStr);  
  
            // JDBC ドライバのロード    
            Class.forName(dbConfig.getJdbcDriver());    
  
            Connection conn;    
            // SQLite の場合、ユーザー名とパスワードは不要    
            if (dbConfig.getDbUser() == null || dbConfig.getDbUser().isEmpty()) {    
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl());    
            } else {    
                conn = DriverManager.getConnection(dbConfig.getJdbcUrl(), dbConfig.getDbUser(), dbConfig.getDbPass());    
            }    
  
            String sql = "INSERT INTO hearings (customer_id, content, date) VALUES (?, ?, datetime('now'))";    
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setInt(1, customerId);    
            pstmt.setString(2, hearingContent);    
            pstmt.executeUpdate();    
  
            pstmt.close();    
            conn.close();    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
  
        response.sendRedirect("index.jsp");    
    }    
}
