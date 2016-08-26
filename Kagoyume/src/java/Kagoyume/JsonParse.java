/* Yahoo!Shoppingの商品検索APIを用いて商品情報取得に必要なメソッドを書き込んでいる
   主な記述内容を下記にまとめている。
 * ②String型でのデータをJsonオブジェクト(Javascriptへ情報を変換できるように)へ変換
     同時に取得する内容によってメソッドも違う。
　 ②-① 検索結果合計数
   ②-② 検索結果表示の内容、条件
   ②-③ 選択商品の詳細の内容を取得
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import net.arnx.jsonic.JSON;

/**
 * Json文字列を解析するクラス
 * @author
 */
public class JsonParse {
    Map<String, Map<String, Object>> root;  
    
    //String型でのデータをJsonオブジェクト(Javascriptへ情報を変換できるように)へ変換
    //同時に取得する内容によってメソッドも違う
    public JsonParse(String json){
        root = JSON.decode(json);
        System.out.println(root);
    }
    
    //②-① 検索結果合計数
    public int getTotalResults(){
        return Integer.valueOf(root.get("ResultSet").get("totalResultsAvailable").toString());                    
    }
    
    //②-② 検索結果表示の内容、条件
    public ArrayList getItemSearchResult()
        throws Exception{
        //返却用のItemData配列
        ArrayList<ItemDataclass> array = new ArrayList<ItemDataclass>();
        
        //見つからなかった場合の例外
        if(Integer.valueOf(root.get("ResultSet").get("firstResultPosition").toString()) == 0){
            System.out.println("result..."+((BigDecimal) root.get("ResultSet").get("totalResultsReturned")).intValue());
            throw new Exception("検索結果がありませんでした");
        }
        
        //検索結果数が0件でなければ処理をする
        if(getTotalResults() != 0 ){
            //totalResultsReturned = Jsonファイルにある検索結果件数に到達するまでループ。
            for(int i = 0; i < ((BigDecimal) root.get("ResultSet").get("totalResultsReturned")).intValue(); i++){
                ItemDataclass item = new ItemDataclass();
                //コンパイラによる警告を無視
                @SuppressWarnings("unchecked")
                //i番目の商品データを入手
                Map<String, Object> result = ((Map<String, Object>)(
                                                (Map<String, Map<String, Object>>)root.get("ResultSet").get("0")
                                                    ).get("Result").get(Integer.toString(i)));

                //値を取得
                @SuppressWarnings("unchecked")
                String name = result.get("Name").toString();
                String imageurl = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
                int price = Integer.parseInt(((Map<String, Object>)result.get("Price")).get("_value").toString());                
                String itemcode = result.get("Code").toString();
                double favorite = Double.parseDouble(((Map<String,Object>)result.get("Review")).get("Rate").toString());
                int reviewer = Integer.parseInt(((Map<String,Object>)result.get("Review")).get("Count").toString());
                
                item.setName(name);
                item.setImageurl(imageurl);
                item.setPrice(price);
                item.setItemcode(itemcode);
                item.setFavorite(favorite);
                item.setReviewer(reviewer);
                array.add(item);      
            }
        
        }
        
        return array;
    }
    
    //②-③ 選択商品の詳細の内容を取得
    public ItemDataclass getItemDetail()
        throws Exception{
        //返却用のItemData
        ItemDataclass item = new ItemDataclass();
        
        //見つからなかった場合の例外
        if(Integer.valueOf(root.get("ResultSet").get("firstResultPosition").toString()) == 0){
            throw new Exception("検索結果がありませんでした");
        }

        //コンパイラによる警告を無視
        @SuppressWarnings("unchecked")
        //i番目の商品データを入手
        Map<String, Object> result = ((Map<String, Object>)(
                                        (Map<String, Map<String, Object>>)root.get("ResultSet").get("0")
                                            ).get("Result").get("0"));

        //値の取得
        @SuppressWarnings("unchecked")
        String name = result.get("Name").toString();
        String headline = result.get("Headline").toString();
        String imageurl = ((Map<String, Object>)result.get("Image")).get("Medium").toString();
        String bigimageurl = ((Map<String, Object>)result.get("ExImage")).get("Url").toString();        
        int price = Integer.parseInt(((Map<String, Object>)result.get("Price")).get("_value").toString());                                
        String itemcode = result.get("Code").toString();
        String storename = ((Map<String,Object>)result.get("Store")).get("Name").toString();
        double favorite = Double.parseDouble(((Map<String,Object>)result.get("Review")).get("Rate").toString());
        int reviewer = Integer.parseInt(((Map<String,Object>)result.get("Review")).get("Count").toString());
        String caption = result.get("Caption").toString();
        String add1 = result.get("Additional1").toString();
        String add2 = result.get("Additional2").toString();
        String add3 = result.get("Additional3").toString();
        
        item.setName(name);
        item.setPrice(price);
        item.setImageurl(imageurl);
        item.setBigimageurl(bigimageurl);
        item.setItemcode(itemcode);
        item.setStorename(storename);
        item.setFavorite(favorite);
        item.setReviewer(reviewer);                      
        item.setCaption(caption);
        item.setAdd1(add1);
        item.setAdd2(add2);
        item.setAdd3(add3);
                
        return item;
    }
        
}

