package Kagoyume;

/*カート追加ボタンかからのデータ保存などメソッドから処理伝達を書き込んでいる
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 8mile_000
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
 * @author yoshikawatoshio
 */
public class Add extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        try{
            HttpSession hs = request.getSession();
            ItemDataclass item = (ItemDataclass)hs.getAttribute("itemdata");            
            
            //直接商品をカートに入れられないように防止している(アクセスすると商品ページへと遷移する。)
            String ac = request.getParameter("ac");
            if(ac == null || hs.getAttribute("ac") == null ||  !ac.equals(hs.getAttribute("ac").toString())){
                response.sendRedirect("Item?itemcode=" + item.getItemcode());
                }else{
            //このページの更新を防ぐ
                    hs.removeAttribute("ac");
            //セッションから商品詳細を取り出し、カートクラスにマッピング
                    int qty = Integer.parseInt(request.getParameter("qty"));
                    CartData cd = new CartData(item,qty);
                    ArrayList<CartData> cart = new ArrayList<CartData>();

            //ログインしていないならばゲストカートへカートを登録
                    if(hs.getAttribute("login") == null){
            //セッションにほかのカート情報があれば取得しなおす
                        if(hs.getAttribute("guestcart") != null){
                            cart = (ArrayList<CartData>)hs.getAttribute("guestcart");
                            for(CartData acd : cart){
            //カートに同じ商品が入っていた場合、個数だけを変更する
                                if((acd.getItemcode().equals(cd.getItemcode()))){
                                    qty += acd.getQuantity();
                                    cart.remove(acd);
                                    cd.setQuantity(qty);
                            break;
                                }                    
                            }                
                        }                
            //ゲストカートにアイテム追加
                    cart.add(cd);
                    hs.setAttribute("guestcart", cart);                
                            }else{
                                int userID = Integer.parseInt(hs.getAttribute("userID").toString());
                                if(hs.getAttribute("cart") != null){
                                    cart = (ArrayList<CartData>)hs.getAttribute("cart");
                                    for(CartData acd : cart){
            //カートに同じ商品が入っていた場合、個数だけを変更する
                                        if((acd.getItemcode().equals(cd.getItemcode()))){
                                            qty += acd.getQuantity();
                                            cart.remove(acd);
                                            cd.setQuantity(qty);
                                            CartDataDAO.getInstance().delete(userID, new CartDataDTO(cd));
                                    break;
                                        }                    
                                    }                
                                }
            //新しい商品のとき、カートに追加しDBに新しく挿入する
                                cart.add(cd);
                                CartDataDAO.getInstance().insert(userID, new CartDataDTO(cd));
                                hs.setAttribute("cart", cart);
                            }
            //ログを記録
            Log.getInstance().logtext("addへ遷移しました。");   
                
            hs.setAttribute("query", hs.getAttribute("query"));
            hs.setAttribute("page",hs.getAttribute("page"));
            hs.setAttribute("itemdata", item);
            hs.setAttribute("qty",request.getParameter("qty"));
            request.getRequestDispatcher("add.jsp").forward(request, response);
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
