package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Kagoyume.JumsHelper;
import java.util.ArrayList;
import Kagoyume.CartData;

public final class buycomplete_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    ArrayList<CartData> cart = (ArrayList<CartData>)hs.getAttribute("cart");
    int total = Integer.parseInt(hs.getAttribute("total").toString());
    //合計金額の初期値

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>購入完了ページ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h3>ありがとうございました!購入が完了しました。</h3>\n");
      out.write("        ");
for(CartData cd : cart){
      out.write("\n");
      out.write("            <img alt=\"");
      out.print(cd.getItemcode());
      out.write("\" src=\"");
      out.print(cd.getImgurl());
      out.write("\">\n");
      out.write("            <p>");
      out.print(cd.getItemname());
      out.write("</p>\n");
      out.write("            <div align=\"right\" class=\"pure-u-1-5\" style=\"margin-left:-1%;margin-top: 5%;\">\n");
      out.write("                    <div style=\"font-weight: bold;\">\n");
      out.write("                        価格:<span style=\"color:red;\">&yen;");
      out.print(cd.getPrice());
      out.write("</span>\n");
      out.write("                    </div>\n");
      out.write("            </div>\n");
      out.write("            <div align=\"right\" style=\"font-weight:bold;margin-top:-1em\">\n");
      out.write("                    個数:");
      out.print(cd.getQuantity());
      out.write("個  小計:&yen;");
      out.print( cd.getPrice() * cd.getQuantity() );
      out.write("\n");
      out.write("            </div>\n");
      out.write("                <hr>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        <div align=\"right\">\n");
      out.write("            配送方法:");
      out.print(Kagoyume.BuyData.typeToString(Integer.parseInt(hs.getAttribute("type").toString())));
      out.write("\n");
      out.write("            <div style=\"font-weight: bold;font-size: large\">総額:&yen;");
      out.print(total);
      out.write("</div><br>\n");
      out.write("        </div>\n");
      out.write("        <div align=\"center\"></div>\n");
      out.write("        ");
      out.print(jh.home());
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
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
