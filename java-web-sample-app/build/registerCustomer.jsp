<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>顧客登録</title>  
</head>  
<body>  
    <h1>顧客登録</h1>  
    <form action="RegisterCustomerServlet" method="post">  
        <p>  
            名前：<input type="text" name="name" required />  
        </p>  
        <p>  
            メールアドレス：<input type="email" name="email" />  
        </p>  
        <p>  
            電話番号：<input type="text" name="phone" />  
        </p>  
        <p>  
            住所：<input type="text" name="address" />  
        </p>  
        <p>  
            <input type="submit" value="登録" />  
        </p>  
    </form>  
    <a href="index.jsp">戻る</a>  
</body>  
</html>
