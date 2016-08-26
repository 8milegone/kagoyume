/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;
import java.util.ArrayList;
/**
 *画面系の処理や表示を簡略化するためのヘルパークラス。
 * @author yoshikawatoshio
 */
public class JumsHelper {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static JumsHelper getInstance(){
        return new JumsHelper();
    }

    //リンクを定数として設定
    private final String homeURL = "top.jsp";
    private final String loginURL = "login.jsp";    
    private final String logoutURL = "Login";
    private final String cartURL = "Cart";
    private final String registerURL = "registration.jsp";   
    
    //トップページへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }  
    
    //ログインへのリンクを返却
    public String login() {
        return "<a href=\""+loginURL+"\">ログイン</a>";
    }
    //ログアウトへのリンクを返却
    public String logout() {
        return "<a href=\""+logoutURL+"\">ログアウト</a>";
    }
    //カートへのリンクを返却
    public String cart() {
        return "<a href=\""+cartURL+"\">カート</a>";
    }
    //ユーザー新規登録へのリンクを返却
    public String register() {
        return "<a href=\""+registerURL+"\">新規登録</a>";
    }
    
    /**
     * 入力されたデータのうち未入力項目がある場合、
     * チェックリストに従いどの項目が未入力なのかhtml文を返却する
     * @param chkList UserDataBeansで生成されるリスト。未入力要素の名前が格納されている
     * @return 未入力の項目に対応する文字列
     */ 
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("mail")){
                    output += "メールアドレス";
                }
                if(val.equals("password")){
                    output +="パスワード";
                }
                if(val.equals("name")){
                    output +="氏名";
                }
                if(val.equals("address")) {
                    output +="住所";
                }
                output +="が未記入です。<br>";
            }
        return output;
    }
    
    /**
     * 購入確認画面での発送方法を選択するラジオボタンの数字を日本語に変換
     * @param i
     * @return 発送方法（日本語）
     */
    public String exTypenum(int i) {
        switch(i) {
            case 1:
                return "宅配便";
            case 2:
                return "メール便";
            case 3:
                return "その他";
        }
        return "";
    }
}