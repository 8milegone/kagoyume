<%-- 
    ログイン管理ページ
    どのページからも遷移できる。ログインしているかいないかで処理が分岐する
    ログインしていない状態(各ページの「ログイン」というリンクから)で遷移してきた場合は、ユーザー名とパスワードを入力するフォームが表示される。また、「新規会員登録」というリンクも表示される。
    ログインに成功すると、その情報をログイン状態を管理できるセッションに書き込み、そのまま直前まで閲覧していたページに遷移する
    ログインしている状態で(各ページの「ログアウト」というリンクから)遷移してきた場合は、ログアウト処理を行う(セッションの破棄、クッキーに保存されたセッションIDを破棄)その後topへ
    ユーザーデータの削除フラグが1の場合は削除されたユーザーとして処理すること

    Document   : login
    Created on : 2016/08/15, 16:28:49
    Author     : 8mile_000
--%>
<%@page import="Kagoyume.JumsHelper"%>

<%
    JumsHelper jh = JumsHelper.getInstance();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>ログイン情報入力ページ</title>
    </head>
    <body>
        <h3>ログイン</h3>
        <form action="Login" method="POST">
            <table border=1>
                <tr>
                    <th>ユーザー名</th>
                    <td><input type="text" name="name" style="width: 180px;" placeholder="ユーザー名(アルファベット)"></td>
                </tr>
                <tr>
                    <th>パスワード</th>
                    <td><input type="text" name="pass" placeholder="半角英数字" style="width: 180px;"></td>
                </tr>
            </table>
            <input type="submit" name="login" value="ログイン">
        </form>
        <p>会員登録していない方は<a href="Registration">こちら</a>から新規登録して下さい。</p>
        <br>
        <%=jh.home()%>
    </body>
</html>
