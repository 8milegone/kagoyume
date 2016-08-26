/* Yahoo!Shoppingの商品検索APIを用いて商品情報取得に必要なメソッドを書き込んでいる
   主な記述内容を下記にまとめている。
   ①検索キーワードから得た情報をString型で受け取る
   ②String型でのデータをJsonオブジェクト(Javascriptへ情報を変換できるように)へ変換
     同時に取得する内容によってメソッドも違う。
　 ②-① 検索結果合計数
   ②-② 検索結果表示の内容、条件
   ②-③ 選択商品の詳細の内容を取得
  仕様要件①:検索結果表示上限を定義

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 
 * @author 8milegone
 */
public class YahooAPI {
    static String result ="";
    
    /**
     * Httpリクエストで検索ワードの結果を得るメソッド
     * @param query
     * @return 文字列での検索結果
     */
    public static String getResult(String query) {
        
        try{
            //URLの作成
            URL url = new URL(query);
            System.out.println("url..."+url);
            //接続用HttpURLConnectionオブジェクト作成
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //リクエストメソッドの設定
            con.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            //接続
            con.connect();
            //検索に入力されたキーワードをまとめて読み込む
            BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            while (true){
                String line = bf.readLine();
                if ( line == null ){ break; }
                responseBuilder.append(line);                 
            }
        bf.close();
        //切断
        con.disconnect();
        result =responseBuilder.toString();
        
        }catch(Exception e) {
        }
        return result;
    }
}
