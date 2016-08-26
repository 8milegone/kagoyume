<%-- 
    登録したユーザー情報が閲覧できるページ(ユーザーID以外全て)
    購入履歴へのリンクあり
    登録情報を更新する、削除するボタンあり

    Document   : mydata
    Created on : 2016/08/15, 16:29:51
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.JumsHelper"%>
<%@page import="Kagoyume.UserData"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("userdata");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー情報ページ</title>
    </head>
    <body>
        <a href="MyHistory">購入履歴</a>
        <HR>
        <h1>ユーザー情報管理</h1>
        名前: <%= ud.getName()%><br>
        パスワード: <%= ud.getPassword()%><br>
        メールアドレス: <%= ud.getMail()%><br>
        住所: <%= ud.getAddress()%><br>
        登録日時：<%= ud.getNewDate() %><br>
        <br>
        <form action="MyUpdate" method="POST">
            <%--アクセスルートチェック--%>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="login" value="ユーザー情報の更新">
        </form>
        <form action="MyDelete" method="POST">
            <%--アクセスルートチェック--%>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="login" value="ユーザー情報の削除">
        </form> 
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
