package Kagoyume;

/*
   これまで購入した商品の履歴が見れるページ
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author yoshikawatoshio
 */
public class MyHistory extends HttpServlet {

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
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            
                //購入履歴を取得
                UserData ud  = (UserData)hs.getAttribute("userdata");
                System.out.println(ud.getUserID());
                ArrayList<BuyDataDTO> historyList = BuyDataDAO.getInstance().search(ud.getUserID());
                BuyData bd = new BuyData();
                ArrayList<BuyData> bdList = bd.DTOBD2Mapping(historyList);
                System.out.println(historyList.get(0).getItemcode());
                
                //Http接続して結果を得る
                ArrayList<ItemDataclass>idList=new ArrayList<ItemDataclass>();
                for(int i=0; i <bdList.size();i++){
                    String result = YahooAPI.getResult(YahooURL.itemDetail(bdList.get(i).getItemcode()));
                    JsonParse jp = new JsonParse(result);
                    ItemDataclass item = jp.getItemDetail();
                    
                    idList.add(item);
                    
                }
                request.setAttribute("idList",idList);
                //ログを記録
                Log.getInstance().logtext("myhistoryへ遷移");
                request.getRequestDispatcher("/myhistory.jsp").forward(request, response);
            
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
