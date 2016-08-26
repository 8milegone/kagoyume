<%-- 
    購入完了ページ
    総購入金額を更新
    購入データを保存
    「購入が完了しました」と表示

    Document   : buycomplete
    Created on : 2016/08/15, 16:30:38
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.*"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.util.ArrayList"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("ud");
%>

<!DOCTYPE html><html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入完了ページ</title>
    </head>
    <body>
        <h3>ありがとうございました!購入が完了しました。</h3>

            <p>購入総額:;<%= ud.getTotal() %>円</p>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>