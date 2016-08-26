<%-- 
    これまで購入した商品の履歴が見れるページ
    Document   : myhistory
    Created on : 2016/08/15, 16:44:07
    Author     : 8mile_000
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page import="Kagoyume.BuyData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Kagoyume.*"%>
<%
    HttpSession hs = request.getSession();
    ArrayList<ItemDataclass> idList = (ArrayList<ItemDataclass>)request.getAttribute("idList");
    int alltotal = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴ページ</title>
    </head>
    <body>
        <h3>購入商品履歴</h3>
        <%if(idList.size() == 0){%>
        <h3>購入履歴はありません</h3>
            <%}else{%>
                <table border="1">
            <tr>
                <th>商品名</th>
                <th>サムネイル</th>
                <th>購入金額</th>
                
            </tr>
            <%for(int i=0;i<idList.size();i++){%>
            <tr>
                <th><%=idList.get(i).getName()%></th>
                <th><img src="<%=idList.get(i).getImageurl() %>"></th>
                <th><%=idList.get(i).getPrice()%>円</th>
                
            </tr>
                <% } %>
                </table>
            <% } %>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
