<%-- 
    フォームから入力するデータで既にあるデータを更新できるページ
    画面構成はregistrationと同じ。フォーム内に直接記入された状態である。このフォームの内容を書き換えていくことでデータの更新ができる
    送信ボタン付き

    Document   : myupdate
    Created on : 2016/08/15, 16:44:28
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("userdata");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー更新ページ</title>
    </head>
    <body>
        <h1>ユーザー情報更新</h1>
        <form action="MyUpdateResult" method="POST">
            ユーザー名: 
            <input type="text" name="name" value="<%= ud.getName() %>">
            <br><br>
            パスワード: 
            <input type="password" name="pass" maxlength="4" value="<%= ud.getPassword()%>" placeholder="英数字4文字">
            <br><br>
            氏名: 
            <input type="text" name="mail" value="<%= ud.getMail()%>">
            <br><br>
            住所: 
            <input type="text" name="address" value="<%= ud.getAddress() %>">
            <br><br>
            <%--アクセスルートチェック--%>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form><br>
    </body>
</html>

