<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ page import="java.util.List" %>  
<%@ page import="com.example.model.Customer" %>  
<%  
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");  
%>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>顧客一覧</title>  
    <link rel="stylesheet" type="text/css" href="css/style.css">  
</head>  
<body>  
    <div class="header">  
        <h1>顧客一覧</h1>  
    </div>  
    <div class="content">  
        <table border="1">  
            <tr>  
                <th>会社名</th>  
                <th>メールアドレス</th>  
                <th>電話番号</th>  
                <th>住所</th>  
            </tr>  
            <% if (customers != null) {  
                for (Customer customer : customers) { %>  
                    <tr>  
                        <td><%= customer.getName() %></td>  
                        <td><%= customer.getEmail() %></td>  
                        <td><%= customer.getPhoneNumber() %></td>  
                        <td><%= customer.getAddress() %></td>  
                    </tr>  
            <% }} %>  
        </table>  
        <a href="index.jsp">戻る</a>  
    </div>  
</body>  
</html>
