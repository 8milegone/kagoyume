<%-- 
    カートに追加ページ
    商品の情報を受け取り、クッキーやセッションに追加する
    画面には「カートに追加しました」という文言が出てくる。
    Document   : add
    Created on : 2016/08/15, 16:27:59
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.ItemDataclass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    ItemDataclass item = (ItemDataclass)session.getAttribute("itemdata");
     //ログインのチェック
    boolean loginChk = false;
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("userdata");
    if(ud != null) {
        loginChk =true;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート追加ページ</title>
    </head>
    <body>
        <%if(loginChk) {%>
        <%--ユーザー情報・ログアウト・カート--%>
        <p>ようこそ <a href="MyData"><%=ud.getName()%></a> さん</p>
        <%=jh.logout()%><br>
        <%=jh.cart()%><br>
    <% }else { %>
        <%--ログインページ・新規登録--%>
        <%=jh.login()%><br>
        <%=jh.register()%><br>
    <% } %>
    <HR>
        <h1>カート追加</h1>
        <h3>カートに追加しました</h3> <br>
        <img alt="<%=item.name%>" src="<%=item.imageurl%>" style="float: left;margin-right: 2%"><br>
        <h3 style="margin:1px"><%=item.name%></h3><br>
        <div style="font-size:small;margin-top:-1.5% "><%=item.storename%></div>
                <div style="font-weight: bold;">
                    <span style="color: red">&yen;<%=item.price%></span>
                    個数:<%=hs.getAttribute("qty")%>個</div>
                <br>
        
                <a href="Search?query=<%=hs.getAttribute("query")%>&page=<%=hs.getAttribute("page")%>">検索結果に戻る</a>
        <br>
        <a href="top.jsp">トップページへ戻る</a>
    </body>
</html>
