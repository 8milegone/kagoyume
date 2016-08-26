/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author 8mile_000
 */
public class YahooURL {
    // YahooショッピングAPIのID・ベースURL・リクエストURL   保守性を保つ為にfinalStringにする
    //アプリケーションID
    private static final String APP_ID = "dj0zaiZpPW1vUU1ESlAzZFo5TSZzPWNvbnN1bWVyc2VjcmV0Jng9OTc-";
    //商品検索結果のURL
    private static final String BASE_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
    //商品詳細のURL
    private static final String CODE_URL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";
  
//    public static String getResult(String query) {
//        
//        try{
//            //URLの作成
//            URL url = new URL(BASE_URL+"?appid="+APP_ID + "&query=" + query);
//            System.out.println("url..."+url);
//            //接続用HttpURLConnectionオブジェクト作成
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            //リクエストメソッドの設定
//            con.setRequestMethod("GET");
//            // リダイレクトを自動で許可しない設定
//            con.setInstanceFollowRedirects(false);
//            //接続
//            con.connect();
//            //検索に入力されたキーワードをまとめて読み込む
//            BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder responseBuilder = new StringBuilder();
//            while (true){
//                String line = bf.readLine();
//                if ( line == null ){ break; }
//                responseBuilder.append(line);                 
//            }
//        bf.close();
//        //切断
//        con.disconnect();
//        result =responseBuilder.toString();
//        
//        }catch(Exception e) {
//        }
//        return result;
//    }
    /**
     * @param query 検索語句
     * @param offset 仕様要件①検索結果の表示件数の上限を定義するメソッド
     * @return 検索結果のJsonファイルURL
     * @throws  UnsupportedEncodingException "UTF-8"というエンコード形式がない時の例外
     */
    public static String itemSearch(String query,int offset) throws UnsupportedEncodingException{
        query = java.net.URLEncoder.encode(query,"UTF-8");
        return BASE_URL+"?appid="+APP_ID + "&query=" + query + "&offset=" + offset;    
    }
    /**
     * @param itemcode 
     * @return 商品の詳細情報が記載されたJsonファイルURL
     * @throws  UnsupportedEncodingException "UTF-8"というエンコード形式がない時の例外
     */
    public static String itemDetail(String itemcode) throws UnsupportedEncodingException{
        itemcode = java.net.URLEncoder.encode(itemcode, "UTF-8");
        return CODE_URL+"?appid="+APP_ID + "&itemcode="+ itemcode +"&responsegroup=large" + "&image_size=300";
    }    
}
