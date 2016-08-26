package Kagoyume;

/*
   IDなどを受け取り、DBを更新結果を確認ページ
    「以上の内容で更新しました。」と、フォームで入力された値を表示
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yoshikawatoshio
 */
public class MyUpdateResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //セッションスタート
        HttpSession hs = request.getSession();
        
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            UserData id = (UserData)hs.getAttribute("userdata");
            
            //フォームからの変更を取得して、JavaBeansに格納
            UserData ud = new UserData();
            ud.setUserID(String.valueOf(id.getUserID()));
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("pass"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));
                        
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO updateData = new UserDataDTO();
            ud.UDB2DTOMapping(updateData);
            
            //DBへデータの挿入
            UserDataDAO .getInstance().UserUpdate(updateData);
            
            //セッションに更新データをセット
            hs.setAttribute("userdata", ud);
            System.out.println("Session update!!");
            
            //ログを記録
            Log.getInstance().logtext("myupdateresultへ遷移");
            
         request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

