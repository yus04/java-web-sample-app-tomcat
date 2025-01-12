package com.example.servlet;  
  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.util.ArrayList;  
import java.util.List;  
  
import com.example.config.DatabaseConfig;  
import com.example.model.Hearing;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


public class ViewHearingsServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    private DatabaseConfig dbConfig;  
  
    public void init() throws ServletException {  
        dbConfig = DatabaseConfig.getInstance();  
    }  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        List<Hearing> hearings = new ArrayList<>();  
  
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
  
            String sql = "SELECT h.content, h.date, c.name AS customer_name "  
                       + "FROM hearings h JOIN customers c ON h.customer_id = c.id";  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            ResultSet rs = pstmt.executeQuery();  
  
            while (rs.next()) {  
                Hearing hearing = new Hearing();  
                hearing.setCustomerName(rs.getString("customer_name"));  
                hearing.setContent(rs.getString("content"));  
                hearing.setDate(rs.getString("date"));  
                hearings.add(hearing);  
            }  
  
            rs.close();  
            pstmt.close();  
            conn.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        request.setAttribute("hearings", hearings);  
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewHearings.jsp");  
        dispatcher.forward(request, response);  
    }  
}
