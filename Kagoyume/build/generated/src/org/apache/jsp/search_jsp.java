package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Kagoyume.ItemDataclass;
import java.util.List;
import Kagoyume.UserData;
import java.util.ArrayList;
import Kagoyume.ShoppingData;
import Kagoyume.JumsHelper;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
 HttpSession hs = request.getSession(); 
   JumsHelper jh = JumsHelper.getInstance();
   ShoppingData sd = (ShoppingData)request.getAttribute("sd");
   List<ShoppingData> sdList = (ArrayList) request.getAttribute("searchResult");
   ArrayList<ItemDataclass> array = (ArrayList<ItemDataclass>)request.getAttribute("searchresults"); 
    //ログインのチェック
    boolean loginChk = false;   
    UserData ud = (UserData)hs.getAttribute("userdata");
    if(ud != null) {
        loginChk =true;
    }

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>検索結果ページ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <h4>商品検索結果</h4>\n");
      out.write("        ");
      out.write("\n");
      out.write("        検索件数; ");
      out.print(request.getAttribute("totalresults"));
      out.write("件<br>\n");
      out.write("        検索キーワード;");
      out.print(request.getParameter("query"));
      out.write("<br>\n");
      out.write("        <br><br>\n");
      out.write("        <table border=\"1\" class=\"list\"></table>     ");
      out.write("\n");
      out.write("        <tr>\n");
      out.write("        <th>サムネイル</th>\n");
      out.write("        <th>商品名</th>\n");
      out.write("        <th>価格</th>\n");
      out.write("        </tr>\n");
      out.write("        ");
 for (int i = 0; i < 20; i++) { 
      out.write(' ');
      out.write(' ');
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <th><img src=");
      out.print( sdList.get(i).getImage() );
      out.write("/></th>\n");
      out.write("                <th><a href=\"Item?code=");
      out.print( sdList.get(i).getCode() );
      out.write('"');
      out.write('>');
      out.print(sdList.get(i).getItemName() );
      out.write("</a></th>\n");
      out.write("                <th>");
      out.print( sdList.get(i).getPrice() );
      out.write(" 円</th>\n");
      out.write("            </tr>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </table>\n");
      out.write("        ");
      out.print(jh.home());
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
