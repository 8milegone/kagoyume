package Kagoyume;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 8mile_000
 */
public class BuyComplete extends HttpServlet {

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
        
        //リクエストパラメータの文字コードをUTF-8に変更
        request.setCharacterEncoding("UTF-8");
        try {
            //セッションスタート
            HttpSession hs = request.getSession();
            //購入するから遷移しなかったときトップページへリダイレクト
//            String ac = request.getParameter("ac");
//            if(ac == null || hs.getAttribute("ac") == null || !ac.equals((hs.getAttribute("ac")).toString())){
//                response.sendRedirect("top.jsp");
//            }else{
//                //更新を防ぐ
//                hs.removeAttribute("ac");
                UserData udb = (UserData)hs.getAttribute("userdata");
                int total = (Integer)hs.getAttribute("total");
                udb.setTotal(total);
                System.out.println("total..."+(Integer)hs.getAttribute("total"));

                int type = Integer.parseInt(request.getParameter("type"));
                
                ArrayList<CartData> cart = (ArrayList<CartData>)hs.getAttribute("cart");

                //商品情報を配列に入れる
                for(int i = 0; i< cart.size(); i++){
                    //購入履歴を挿入
                    BuyDataDAO.getInstance().insert(udb.getUserID(), cart.get(i).getItemcode(), type);
                    
                //}
                UserDataDTO udd = new UserDataDTO();
                udb.UDB2DTOMapping(udd);
                
                UserDataDTO addtotal = UserDataDAO.getInstance().UserSearch(udd);
                UserData ud = new UserData();
                ud.DTOUD2Mapping(addtotal);
                int resulttotal=ud.getTotal()+total;
                ud.setTotal(resulttotal);
                UserDataDTO finaltotal = new UserDataDTO();
                ud.UDB2DTOMapping(finaltotal);
               
                //userの総額を更新
                UserDataDAO.getInstance().UserTotal(finaltotal);
                
                hs.setAttribute("ud", udb);

                //セッションからカートの情報を破棄            
                hs.removeAttribute("cart");            
                hs.removeAttribute("total");
                
                //ログを記録
                Log.getInstance().logtext("buycompleteへ遷移しました。");
                request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
            }
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
