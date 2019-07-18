package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class SysProperties_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>JBoss Management Console - System Properties</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("<link rel=\"StyleSheet\" href=\"css/jboss.css\" type=\"text/css\"/>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- header begin -->\n");
      out.write("\t<div id=\"footer\">\n");
      out.write("\t\t<div id=\"footer_bar\">&nbsp;</div>\n");
      out.write("\t</div>\n");
      out.write("<!-- header end -->\n");
      out.write("<hr class=\"hide\"/>\n");
      out.write("\t<center>\n");
      out.write("\t<div id=\"content\">\n");
      out.write("\t\t<div class=\"content_block\" style=\"width: 100%;\">\n");
      out.write("\t\t\t<h3>JBoss&trade; System Properties</h3>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t<table border=\"1\" cellpadding=\"1\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"85%\" align=\"center\" id=\"AutoNumber1\">\n");

	java.util.Properties props = System.getProperties ();
	java.util.Iterator iter = props.keySet().iterator();
	
	while (iter.hasNext())
	{
		String key = (String)iter.next();	
		String value = props.getProperty (key);

      out.write("\n");
      out.write("                  <tr>\n");
      out.write("                    <td align=\"left\" valign=\"top\"><font size=\"1\">");
      out.print(key);
      out.write("</font>&nbsp;</td>\n");
      out.write("                    <td align=\"left\" valign=\"top\"><font size=\"1\">");
      out.print(value);
      out.write("</font>&nbsp;</td>\n");
      out.write("                  </tr>\n");

	}

      out.write("\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("            <br/>    <br/>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"spacer\"><hr/></div>\n");
      out.write("\t</div>\n");
      out.write("\t</center>\n");
      out.write("<!-- content end -->\n");
      out.write("\n");
      out.write("<hr class=\"hide\"/>\n");
      out.write("<!-- footer begin -->\n");
      out.write("\t<div id=\"footer\">\n");
      out.write("\t\t<div id=\"footer_bar\">&nbsp;</div>\n");
      out.write("\t</div>\n");
      out.write("<!-- footer end -->\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
