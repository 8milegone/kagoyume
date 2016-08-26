<%-- 
    会員登録結果画面
    プロフィール用のDBに値を挿入。この際、現在時(年日時分)を組み込み関数で取得し、追加。
    「以上の内容で登録しました。」とregistrationconfirmのようにフォームで入力された値を表示
    「トップページへ戻る」のリンクが、目立つ場所に設置されている
    
    Document   : registrationcomplete
    Created on : 2016/08/15, 16:29:39
    Author     : 8mile_000
--%>
<%@page import="Kagoyume.JumsHelper"%>
<%@page import="Kagoyume.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserData ud = (UserData)request.getAttribute("ud");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録完了</title>
    </head>
    <body>
        <h3>会員登録完了しました。</h3>
        名前:<%= ud.getName()%><br>        
        パスワード: <%= ud.getPassword()%><br>
        メールアドレス: <%= ud.getMail()%><br>
        住所: <%= ud.getAddress()%><br><br>
        以上の内容で登録しました。
        <a href="top.jsp">トップページへ戻る</a>
        <HR>
    </body>
</html>
