<%-- 
    トップページ。
    このシステムの簡単な説明が記載されている。テキストは自由
    キーワード検索フォームが設置されている。検索の遷移先はsearchで、GETメソッド。未入力ならエラーを表示

    Document   : top
    Created on : 2016/08/15, 16:27:06
    Author     : 8mile_000
--%>
<%@page import="Kagoyume.UserData"%>
<%@page import="Kagoyume.JumsHelper"%>
<%--仕様要件③検索欄が未入力ならエラーとする--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)hs.getAttribute("userdata");
    
    //検索キーワードがnullかを比較
    boolean keyword = false;
    if(request.getAttribute("empty") != null) {
        keyword = true;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめトップページ</title>
    </head>
    <body>
        <h4>かごゆめショッピングサイト</h4>
        <%--ユーザー情報を表示--%>
        <%--ログインを実装予定--%>
        <%--ログアウトを実装予定--%>
        <%--アカウントを作成していない場合は新規登録を表示--%>
        <%--カートの中身確認ボタンを表示--%>
        <%--アカウントを作成していない場合は新規登録を表示--%>
        
        <%if(hs.getAttribute("userdata") == null) {%>
        <%--ログイン情報が未記入なら--%>
        
        <%--ログインページ--%>
         
        <%=jh.login()%><br><%--JumsHelperから引用--%>
        
        <%--ログイン情報がnullじゃなければ--%>
        <% }else { %>
        <%--ユーザー情報・ログアウト・カートを表示させる--%>
        <p>ようこそ <a href="MyData"><%=ud.getName()%></a> さん</p>
        <%=jh.logout()%><br>
        <%=jh.cart()%>
        <% } %>
        <%--情報を見やすくさせる為、水平の罫線を作成--%>
        <HR>
        
        <form action="Search" method="GET">     <%--仕様要件:①遷移先はsearch②GETメソッド--%>
        商品キーワード検索<br>
        <font color="#ff0000" >※何も記入せずに検索ボタンを押すとエラーが表示されます。</font><br>
        <input type="text" name="query" placeholder="キーワードを入力" style="width:100px">
        <input type="submit" name="btnSubmit" value="検索" style="width:40px">
        </form> 
        <p>
        ショッピングサイトを使っている時、こんな経験ありませんか？<br>
        <br>
        「あれいいな」「これいいな」「あっ、関連商品のこれもいい」「20%オフセールだって！？　買わなきゃ！」・・・<br>
        そしていざ『買い物かご』を開いたとき、その合計金額に愕然とします。「こんなに買ってたのか・・・しょうがない。いくつか減らそう・・・」<br>
        <br>
        仕方がありません。無駄遣いは厳禁です。でも、一度買うと決めたものを諦めるなんて、ストレスじゃあありませんか？<br>
        できればお金の事なんか考えずに好きなだけ買い物がしたい・・・。<br>
        <br>
        このサービスは、そんなフラストレーションを解消するために生まれた<br>
        『金銭取引が絶対に発生しない』『いくらでも、どんなものでも購入できる(気分になれる)』『ECサイト』です
        </p>        
    </body>
</html>
