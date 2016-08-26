<%-- 
    ユーザー削除確認ページ
    対象のレコードの全データを表示したのちに、「このユーザーをマジで削除しますか？」という質問があり、「はい」と「いいえ」が直リンクとして設置してある。「はい」ならmydeleteresultへ、そうでないならトップページへ遷移する

    Document   : mydelete
    Created on : 2016/08/15, 16:45:09
    Author     : 8mile_000
--%>

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
        <title>ユーザー削除ページ</title>
    </head>
    <body>
        <h1>削除確認</h1>
    ID: <%= ud.getUserID()%><br>
    ユーザー名: <%= ud.getName()%><br>
    パスワード: <%= ud.getPassword()%><br>
    メールアドレス: <%= ud.getMail()%><br>
    住所: <%= ud.getAddress()%><br>
    登録日時: <%= ud.getNewDate()%><br>
    削除フラグ: <%= ud.getDeleteFlg()%><br>
    <br>
    このユーザーを削除しますか？<br>
    <br>
    <a href="MyDeleteResult?id=<%= ud.getUserID()%>&ac=<%=hs.getAttribute("ac")%>">はい</a>　
    <a href="top.jsp">いいえ</a>
    </body>
</html>
