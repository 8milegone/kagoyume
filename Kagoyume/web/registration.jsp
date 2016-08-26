<%-- 
    新規会員登録ページ
    loginからのみ遷移
    フォームがあり、入力するのは以下の要素
        ユーザー名
        パスワード
        メールアドレス
        住所
    registrationconfirmから戻ってきた場合は、値を保持して記入済みにできる

    Document   : registration
    Created on : 2016/08/15, 16:29:07
    Author     : 8mile_000
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>

<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = null;
    boolean reinput = false;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        ud = (UserData)hs.getAttribute("ud");
    } 
    
%>
<%--List<String> caution = (ArrayList<String>)request.getAttribute("caution");--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規会員登録ページ</title>
    </head>
    <body>
       <h3>新規会員登録</h3>
       以下の情報を漏れなく入力して下さい。
       <form action="RegistrationConfirm" method="POST">
        ユーザー名:
        <input type="text" name="name" placeholder="アルファベット" value="<% if(reinput){out.print(ud.getName());} %>">
        
        <br><br>
        
        パスワード:
        <input type="text" name="password" placeholder="半角英数字" value="<% if(reinput){out.print(ud.getPassword());} %>">
        <br><br>
        
        メールアドレス:
        <input type="text" name="mail" value="<% if(reinput){out.print(ud.getMail());} %>">
        <br><br>
        
        住所:
        <input type="text" name="address" placeholder="現住所" value="<% if(reinput){out.print(ud.getAddress());} %>">
        <br><br>
        <%--アクセスルートチェック--%>
        <input type="hidden" name="ac" value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面">
       </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
