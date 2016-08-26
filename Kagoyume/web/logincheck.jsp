<%-- 
    Document   : logincheck
    Created on : 2016/07/28, 0:21:59
    Author     : yoshikawatoshio
--%>

<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
   // UserDataBeans udb = (UserDataBeans)hs.getAttribute("userdata");
     UserData ud = (UserData)hs.getAttribute("userdata");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログインチェック画面</title>
    </head>
    <body>
        <h1>ログイン完了</h1>
        ようこそ <%= ud.getName()%> さん。<br>
    <br><br>
    <form action="SearchResult" method="GET">
        キーワードから探す：<input type="text" name="name">
        <br><br>
        <%--アクセスルートチェック--%>
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="商品検索">
    </form><br>
        <%--トップページに戻る--%>
        <%=jh.home()%>
    </body>
</html>
