package Kagoyume;

/* 画面遷移、ユーザー登録、購入を時間とともに記録。引数を、ログに残すテキストとするような
   クラスを作成しておき、画面遷移などの処理のタイミングで「searchに遷移」といったテキストで
   追記保存。log.javaという個別のクラスを用意すべき
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

 class Log {
     public static Log getInstance(){
         return new Log();
     }
     
     public void logtext(String log){
         File filepath = null;
         FileWriter fw =null;
         Date date = new Date();
         try{
             //ファイルをセット
             filepath = new File("/Users/8mile_000/C/soeda_origin/開発/開発- JAVA/Kagoyume/web/WEB-INF/log/log.txt");
             //ログファイルに追加
             fw = new FileWriter(filepath, true);
             //日時を書き込む
             fw.write(log+"(" + date+ ")--");
             //書き込み終了
             fw.flush();
         }catch(IOException e){
             System.out.printf(e.getMessage());
            }
        }
 }

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    


