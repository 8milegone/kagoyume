/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kagoyume;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;

/**
 *
 * @author 8mile_000
 */
public class LoginCheck {
    
    //ログインチェック
    //ログインチェック
    public static void loginCheck(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, ServletException, IOException, SQLException{
        HttpSession hs = request.getSession();
        try{            
            if(hs.getAttribute("login") == null){
                //urlを指定しないとき、ログイン直前のページに飛ぶ
                if(hs.getAttribute("url") == null){
                    //loginページが最初のページのとき、Refereがないのでその場合はトップページに遷移する
                    if(request.getHeader("Referer") != null){
                        hs.setAttribute("url", request.getHeader("Referer"));
                    }else{
                        hs.setAttribute("url", "top.jsp");
                    }
                }else{
                    hs.setAttribute("url", hs.getAttribute("url").toString());                
                } 
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
            
        }catch(ServletException ex){
            throw new ServletException(ex);
        }catch(IOException ex){
            throw new IOException(ex);
        }
        
    }
        
}