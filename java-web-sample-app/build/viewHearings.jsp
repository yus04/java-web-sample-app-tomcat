<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ page import="java.util.List"%>  
<%@ page import="com.example.model.Hearing"%>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>ヒアリング内容一覧</title>  
</head>  
<body>  
    <h1>ヒアリング内容一覧</h1>  
    <table border="1">  
        <tr>  
            <th>顧客名</th>  
            <th>ヒアリング内容</th>  
            <th>日付</th>  
        </tr>  
        <%  
            List<Hearing> hearings = (List<Hearing>) request.getAttribute("hearings");  
            if(hearings != null){  
                for(Hearing hearing : hearings){  
        %>  
        <tr>  
            <td><%= hearing.getCustomerName() %></td>  
            <td><%= hearing.getContent() %></td>  
            <td><%= hearing.getDate() %></td>  
        </tr>  
        <%  
                }  
            }  
        %>  
    </table>  
    <a href="index.jsp">戻る</a>  
</body>  
</html>
