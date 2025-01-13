<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ page import="java.util.List"%>  
<%@ page import="com.example.model.Customer"%>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>ヒアリング内容追加</title>  
    <link rel="stylesheet" type="text/css" href="css/style.css">  
</head>  
<body>  
    <div class="header">  
        <h1>ヒアリング内容追加</h1>  
    </div>  
    <div class="content">  
        <form action="HearingServlet" method="post">  
            <p>  
                顧客名：  
                <select name="customerId" required>  
                    <option value="">選択してください</option>  
                    <% List<Customer> customers = (List<Customer>) request.getAttribute("customers"); if(customers != null){ for(Customer customer : customers){ %>  
                    <option value="<%= customer.getId() %>"><%= customer.getName() %></option>  
                    <% }} %>  
                </select>  
            </p>  
            <p>  
                ヒアリング内容：<br />  
                <textarea name="hearing" rows="5" cols="30" required></textarea>  
            </p>  
            <p>  
                <input type="submit" value="送信" />  
            </p>  
        </form>  
        <a href="index.jsp">戻る</a>  
    </div>  
</body>  
</html>
