<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ page import="java.util.List" %>  
<% List<String> customers = (List<String>) request.getAttribute("customers"); %>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>顧客一覧</title>  
    <link rel="stylesheet" type="text/css" href="css/styles.css">  
</head>  
<body>  
    <div class="header">  
        <h1>顧客一覧</h1>  
    </div>  
    <div class="content">  
        <ul>  
            <% for (String customer : customers) { %>  
            <li><%= customer %></li>  
            <% } %>  
        </ul>  
        <a href="index.jsp">戻る</a>  
    </div>  
</body>  
</html>
