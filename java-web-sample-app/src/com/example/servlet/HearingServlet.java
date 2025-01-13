package com.example.servlet;  
  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.time.LocalDateTime;  
import java.time.ZoneId;  
import java.time.temporal.ChronoUnit;  
import java.time.format.DateTimeFormatter;  
  
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

            String sql = "INSERT INTO hearings (customer_id, content, date) VALUES (?, ?, ?)";  
  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setInt(1, customerId);  
            pstmt.setString(2, hearingContent);  
            
            // 日本時間で現在の日時を取得し、秒とナノ秒を切り捨てる  
            LocalDateTime nowJST = LocalDateTime.now(ZoneId.of("Asia/Tokyo")).truncatedTo(ChronoUnit.MINUTES);  
            
            // 日時をフォーマットして文字列に変換  
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");  
            String formattedDateTime = nowJST.format(formatter);  
            
            // フォーマットした日時文字列をセット  
            pstmt.setString(3, formattedDateTime);  
            
            pstmt.executeUpdate();  
            pstmt.close();  
            conn.close();  
 
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
  
        response.sendRedirect("index.jsp");    
    }    
}
