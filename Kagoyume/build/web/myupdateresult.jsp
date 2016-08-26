<%-- 
    IDなどを受け取り、DBを更新結果を確認ページ
    「以上の内容で更新しました。」と、フォームで入力された値を表示

    Document   : myupdateresult
    Created on : 2016/08/15, 16:44:46
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.JumsHelper"%>
<%@page import="Kagoyume.UserData"%>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)hs.getAttribute("userdata");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kagoyumeユーザー更新完了ページ</title>
    </head>
    <body>
        <h1>更新結果</h1>
        名前:<%= ud.getName()%><br>
        パスワード: <%= ud.getPassword()%><br>
        メールアドレス: <%= ud.getMail()%><br>
        住所: <%= ud.getAddress()%><br><br>
        以上の内容で変更しました。<br>
        <HR>
        <%--トップページ--%>
        <%= jh.home()%>
    </body>
</html>
