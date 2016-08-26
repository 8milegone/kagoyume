package Kagoyume;

/*　
   ログイン管理ページ
    どのページからも遷移できる。ログインしているかいないかで処理が分岐する
    ログインしていない状態(各ページの「ログイン」というリンクから)で遷移してきた場合は、ユーザー名とパスワードを入力するフォームが表示される。また、「新規会員登録」というリンクも表示される。
    ログインに成功すると、その情報をログイン状態を管理できるセッションに書き込み、そのまま直前まで閲覧していたページに遷移する
    ログインしている状態で(各ページの「ログアウト」というリンクから)遷移してきた場合は、ログアウト処理を行う(セッションの破棄、クッキーに保存されたセッションIDを破棄)その後topへ
    ユーザーデータの削除フラグが1の場合は削除されたユーザーとして処理すること
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
public class Login extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        //セッションスタート
        HttpSession hs = request.getSession();
        try {
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            
            UserData ud = new UserData();
            
            if(request.getParameter("login") != null) {
                //フォームからの入力を取得し、UserDataBeansに格納
                ud.setName(request.getParameter("name"));
                ud.setPassword(request.getParameter("pass"));
                
                //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                UserDataDTO udd = new UserDataDTO();
                ud.UDB2DTOMapping(udd);
            
                //DBにデータを挿入
                UserDataDTO login = UserDataDAO.getInstance().UserSearch(udd);
            
                //View用のパラメータにマッピング(DTO→UserData)
                UserData userdata = new UserData();
                userdata.DTOUD2Mapping(login);
            
                //セッションに値をセット
                hs.setAttribute("userdata", userdata);
                System.out.println("Session update!!");
                
                //deleteFlgが1の場合（削除処理を行った場合）
                if(userdata.getDeleteFlg() == 1) {
                    hs.invalidate();
                    throw new Exception("お探しのユーザーはありません");
                }
                
                //ログを記録
                Log.getInstance().logtext("loginへ遷移しました。");
                                
            //logoutの場合    
            }else {
                //セッションリセット
                hs.invalidate();                
            }
                
            request.getRequestDispatcher("/top.jsp").forward(request, response);
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
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
