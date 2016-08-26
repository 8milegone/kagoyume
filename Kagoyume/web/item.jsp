<%-- 
    商品詳細ページ
    serchまたはcartから遷移できる。商品IDをGETメソッドにより受け渡す
    YahooショッピングAPIから取得したデータで、より詳細な情報(概要や評価値)、が表示される
    「カートに追加する」ボタンがあり、クリックするとaddに遷移する。

    Document   : item
    Created on : 2016/08/15, 16:27:48
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.ItemDataclass"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    //ログインのチェック
    boolean loginChk = false;
    HttpSession hs = request.getSession();
    ItemDataclass item = (ItemDataclass)hs.getAttribute("itemdata"); 
    UserData ud = (UserData)hs.getAttribute("userdata");
    if(ud != null) {
        loginChk =true;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細ページ</title>
    </head>
    <body>
        <%if(loginChk) {%>
        <%--ユーザー情報・ログアウト・カート--%>
        <p>ようこそ <a href="MyData"><%=ud.getName()%></a> さん</p>
        <%=jh.logout()%><br>
        <%=jh.cart()%>
    <% }else { %>
        <%--ログインページ・新規登録--%>
        <%=jh.login()%><br>
        <%=jh.register()%>
    <% } %>
        <HR>
        <h1>商品詳細</h1>
            <form action="Add" method="post">            
                個数:<input type="number" name="qty" value="1" style="width: 3em">個
                <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                <button type="submit" name="cartin">カートに入れる</button>
            </form>
            <br>
            <h3 style="margin:1px"><%=item.getName()%></h3><br>
            <div style="font-size:small;margin-top:-1.5% "><%=item.getStorename()%></div>
            <div>☆:<%=item.getFavorite() %>/<%=item.getReviewer()%>人</div>
            <img src="<%=item.getBigimageurl() %>"/><br>
             
            <div style="a{pointer-events: none}">
                <p><%=item.getCaption()%></p>
                <p><%=item.getAdd1()%></p>
                <p><%=item.getAdd2()%></p>
                <p><%=item.getAdd3()%></p>
            </div>
            <br><br>
            
            <br>
            <a href="Search?query=<%=hs.getAttribute("query")%>&page=<%=hs.getAttribute("page")%>">検索結果に戻る</a>
            <br><br>
            <a href="top.jsp">トップページへ戻る</a>
    </body>
</html>