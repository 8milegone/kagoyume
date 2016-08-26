<%-- 
    購入確認ページ
    カートに追加順で商品の名前(リンクなし)、金額が表示される
    合計金額が表示され、その下に発送方法を選択するラジオボタンがある。
    「上記の内容で購入する」ボタンと「カートに戻る」ボタンがある。

    Document   : buyconfirm
    Created on : 2016/08/15, 16:30:26
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.CartData"%>
<%@page import="Kagoyume.UserData"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Kagoyume.JumsHelper"%>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<CartData> cart = (ArrayList<CartData>)hs.getAttribute("cart");
    int total = Integer.parseInt(hs.getAttribute("total").toString());
    //合計金額の初期値
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認ページ</title>
    </head>
    <body>
        <div class="main">
        <h1>購入確認</h1>
        
            <%for(CartData cd : cart){%>
            
                <div class="pure-u-4-5" style="font-weight: bold">
                    <a style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                        <img alt="<%=cd.getItemcode()%>" src="<%=cd.getImgurl()%>"> 
                    </a>
                    <a class="pure-u-3-5" style="vertical-align: middle;display: inline-block;" href="http://localhost:8080/Kagoyume/Item?itemcode=<%=cd.getItemcode()%>">
                        <p><%=cd.getItemname()%></p>
                    </a>
                </div>
                <div align="right" class="pure-u-1-5" style="margin-left:-1%;margin-top: 5%;">
                    <div style="font-weight: bold;">
                        価格:<span style="color:red;">&yen;<%=cd.getPrice()%></span>
                    </div>
                </div>
                <div align="right" style="font-weight:bold;margin-top:-1em">
                    個数:<%=cd.getQuantity()%>個  小計:&yen;<%= cd.getPrice() * cd.getQuantity() %>
                </div>
                <hr>
           
            <%}%>
        
            <div align="right" style="font-weight: bold;font-size: large">総額:&yen;<%=total%></div><br>
            <div align="right">
                <form action="BuyComplete">
                    <h3>発送方法をお選びください</h3>
                    <%for(int j = 1; j <= 4; j++){%>
                    <input type="radio" value="<%=j%>" name="type" <%if(j == 1){out.print("checked");}%>><%=Kagoyume.BuyData.typeToString(j)%><br>
                    <%}%>
                    <input type="hidden" name="ac" value="<%=hs.getAttribute("ac")%>">
                    <button type="submit" name="buycomplete">上記の内容で購入する</button>
                </form>
            </div>
            <div style="margin-top: -1.7em">
                <form action="Cart">
                    <button type="submit">カートに戻る</button>
                </form>
            </div>
        </div>
        <%=jh.home()%>  
    </body>
</html>