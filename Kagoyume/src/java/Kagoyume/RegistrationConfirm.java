package Kagoyume;

/* 
   会員登録確認画面
    フォームで入力された文字や選択を表示し、「上記の内容で登録いたします。よろしいですか？」と表示。「はい」でregistrationcomplete「いいえ」でregistrationに値を保持したまま(戻った時にフォーム入力済みになっている)遷移
    もし不足していた場合はどの項目のデータが不足しているのかを表示。registrationに値を保持したまま遷移するリンクを表示
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
 * @author 8mile_000
 */
public class RegistrationConfirm extends HttpServlet {

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
        try{
            //リクエストパラメータも文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです。");
            }
            
            /*String destination ="";
            //追加要件:同じユーザー名とパスワードが重複していれば登録不可
            if(!RegistrationLogic.getInstance().checkUserDataInDB(udb)){
                String warning = "既に同じ情報で登録されています。別のユーザー名またはパスワードを入力して下さい。";
                request.setAttribute("warning",warning);
                destination ="/registrationconfirm.jsp";
                return;
            }*/
            



            //registration.jspのフォームから情報を取得して,JavaBeansに格納
            UserData ud = new UserData();
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("password"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));
            
            //ユーザー情報をセッションに格納
            hs.setAttribute("ud",ud);
            System.out.println("Session update完了");
            
            //仕様要件①ログを記録
            Log.getInstance().logtext("registrationconfirmへ遷移しました。");
            
            request.getRequestDispatcher("/registrationconfirm.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
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
