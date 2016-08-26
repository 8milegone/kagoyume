<%-- 
    ユーザー削除結果ページ
    ここにアクセスした段階で、IDによる削除が実行される(外部キー制約により直接DELETEは出来ないので、削除フラグを0から1に変更する)
    「削除しました｝という一文が表示される

    Document   : mydeleteresult
    Created on : 2016/08/15, 16:45:29
    Author     : 8mile_000
--%>

<%@page import="Kagoyume.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー削除完了ページ</title>
    </head>
    <body>
        <h1>削除確認</h1>
        削除しました
    </body>
    <%=jh.home()%>
</html>
