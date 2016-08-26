package org.apache.jsp.TopPage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Kagoyume.UserData;
import Kagoyume.JumsHelper;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write('\n');
      out.write('\n');

  HttpSession hs = request.getSession();
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)hs.getAttribute("userdata");
    
    //検索キーワードがnullかを比較
    boolean keyword = false;
    if(request.getAttribute("empty") != null) {
        keyword = true;
    }

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>かごゆめトップページ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h4>かごゆめショッピングサイト</h4>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        \n");
      out.write("        ");
if(hs.getAttribute("userdata") == null) {
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("         \n");
      out.write("        ");
      out.print(jh.login());
      out.write("<br>　");
      out.write("\n");
      out.write("        \n");
      out.write("        ");
      out.print(jh.register());
      out.write('　');
      out.write(' ');
      out.write("\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
 }else { 
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        <p>ようこそ <a href=\"MyData\">");
      out.print(ud.getName());
      out.write("</a> さん</p>\n");
      out.write("        ");
      out.print(jh.logout());
      out.write("<br>\n");
      out.write("        ");
      out.print(jh.cart());
      out.write("\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        <HR>\n");
      out.write("        \n");
      out.write("        <form action=\"Search\" method=\"GET\">     ");
      out.write("\n");
      out.write("        商品キーワード検索<br>\n");
      out.write("        <font color=\"#ff0000\" >※何も記入せずに検索ボタンを押すとエラーが表示されます。</font><br>\n");
      out.write("        <input type=\"text\" name=\"query\" placeholder=\"キーワードを入力\" style=\"width:100px\">\n");
      out.write("        <input type=\"submit\" name=\"btnSubmit\" value=\"検索\" style=\"width:40px\">\n");
      out.write("        </form> \n");
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
