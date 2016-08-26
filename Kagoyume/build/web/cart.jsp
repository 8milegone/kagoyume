<%-- 
    希望商品カート内の確認ページ
    「カートに追加」でクッキーやセッションに保存された登録情報が登録古い順に表示される
    商品の写真と名前(リンクつき)、金額を表示。
    画面下部に全額の合計金額を表示する
    「購入する」ボタンあり
    各商品には「削除」のリンクあり。このリンクをクリックすることで、カートから商品を削除する
    カートの中身はユーザー毎に切り替えられる
    ログインしていない状態ならばログインページに遷移、そこでログインに成功した場合、非ログイン状態で「カートに追加」操作をしていた商品はそのユーザー用のカートに移る


    Document   : cart
    Created on : 2016/08/15, 16:30:15
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.UserData"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Kagoyume.CartData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<CartData> cart = (ArrayList<CartData>)hs.getAttribute("cart");
    int total = Integer.parseInt(hs.getAttribute("total").toString());
    
//ログインのチェック
    boolean loginChk = false;
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
        <title>カートページ</title>
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
        <h3>カート</h3>
        <form action="Cart" method="post">
        <table border="1">
            <tr>
                <th>サムネイル</th>
                <th>商品名</th>
                <th>価格</th>
            <%for(CartData cd : cart){%>
            <tr>
                <a style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                <th><img alt="<%=cd.getItemcode()%>" src="<%=cd.getImgurl()%>"></th>
                </a>
                
                <a class="pure-u-4-5" style="vertical-align: middle;display: inline-block;font-weight: bold;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                <th><%=cd.getItemname()%></th>
                </a>
                
                <th><%=cd.getPrice()%>円</th>
            </tr>
            <h3>小計:<%=cd.getQuantity() * cd.getPrice()%>円</h3>
            <div>
                個数:<input type="number" name="qty" value="<%=cd.getQuantity()%>" style="width: 3em">個
                </div>
                <input type="hidden" name="changeqty" value="<%=cd.getItemcode()%>">
                <button type="submit" name="change">変更</button>
                <input type="hidden" name="deleteitem" value="<%=cd.getItemcode()%>">
                <button type="submit" name="delete">削除</button>
            <% } %>
        </table>
        </form>
        <h3>合計金額：<%= total %>円</h3>
        <form action="BuyConfirm" method="POST">
             <button type="submit">購入する</button>
        </form>
        <form>
            <button type="submit" name="alldelete">カートを空にする</button>
        </form>
        <%=jh.home()%>   
    </body>
</html>

