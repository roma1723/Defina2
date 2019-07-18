package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.management.ObjectName;
import javax.management.j2ee.statistics.ServletStats;
import javax.management.j2ee.statistics.TimeStatistic;

public final class Servlet_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(1);
    _jspx_dependants.add("/WEB-INF/tlds/webconsole.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_jb_mbean_intf_id_nobody;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_jb_mbean_intf_id_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_jb_mbean_intf_id_nobody.release();
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

      out.write('\n');
      out.write('\n');
      //  jb:mbean
      org.jboss.console.plugins.helpers.servlet.MBeanTag _jspx_th_jb_mbean_0 = (org.jboss.console.plugins.helpers.servlet.MBeanTag) _jspx_tagPool_jb_mbean_intf_id_nobody.get(org.jboss.console.plugins.helpers.servlet.MBeanTag.class);
      _jspx_th_jb_mbean_0.setPageContext(_jspx_page_context);
      _jspx_th_jb_mbean_0.setParent(null);
      _jspx_th_jb_mbean_0.setId("servlet");
      _jspx_th_jb_mbean_0.setIntf("org.jboss.management.j2ee.ServletMBean");
      int _jspx_eval_jb_mbean_0 = _jspx_th_jb_mbean_0.doStartTag();
      if (_jspx_th_jb_mbean_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_jb_mbean_intf_id_nobody.reuse(_jspx_th_jb_mbean_0);
      org.jboss.management.j2ee.ServletMBean servlet = null;
      servlet = (org.jboss.management.j2ee.ServletMBean) _jspx_page_context.findAttribute("servlet");
      out.write('\n');

   String doReset = request.getParameter("doReset");
   if (doReset != null && doReset.equals("true"))
   {
       servlet.resetStats();
   }

   String resetUrl = response.encodeURL(request.getRequestURI()) + "?doReset=true&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));
   String myUrl = response.encodeURL(request.getRequestURI()) + "?" + "&ObjectName=" + java.net.URLEncoder.encode(request.getParameter("ObjectName"));

      out.write("\n");
      out.write("\n");
      out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>JBoss Management Console - Servlet</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\n");
      out.write("<link rel=\"StyleSheet\" href=\"css/jboss.css\" type=\"text/css\"/>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!-- header begin -->\n");
      out.write("\t<img src=\"images/logo.gif\" alt=\"JBoss\" id=\"logo\" width=\"226\" height=\"105\" />\n");
      out.write("\t<div id=\"header\">\n");
      out.write("\t\t&nbsp;</div>\n");
      out.write("\t<div id=\"navigation_bar\">\n");
      out.write("\t</div>\n");
      out.write("<!-- header end -->\n");
      out.write("<hr class=\"hide\"/>\n");
      out.write("\t<center>\n");
      out.write("\t<div id=\"content\">\n");
      out.write("\t\t<div class=\"content_block\" style=\"width: 831; height: 247\">\n");
      out.write("\t\t\t<h3>Servlet</h3>\n");
      out.write("\t\t\t\t<h4>Name</h4>\n");
      out.write("\t\t\t\t\t<p>");
      out.print(new ObjectName(servlet.getobjectName()).getKeyProperty("name"));
      out.write(" </p>\n");
      out.write("\t\t\t\t<h4>Servlet Statistics:</h4>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\n");
      out.write("\t\t\t\t<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"95%\" align=\"center\" id=\"AutoNumber1\">\n");
      out.write("                  <tr>\n");
      out.write("                    <td align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\">Min (ms)</h4>\n");
      out.write("                    </td>\n");
      out.write("                    <td align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\">Max (ms)</h4>\n");
      out.write("                    </td>\n");
      out.write("                    <td align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\">Average (ms)</h4>\n");
      out.write("                    </td>\n");
      out.write("                    <td align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\">Total (ms)</h4>\n");
      out.write("                    </td>\n");
      out.write("                    <td align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\"># Invocations</h4>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("\t\t\t\t");

                    ServletStats stats = (ServletStats)servlet.getstats();
                    TimeStatistic stat = stats.getServiceTime();
				
      out.write("\n");
      out.write("\n");
      out.write("                  <tr>\n");
      out.write("                    <td ><font size=\"1\">\n");
      out.write("                    <h4 style=\"text-align: center\">");
      out.print(stat.getMinTime());
      out.write("</h4></font>\n");
      out.write("                    </td>\n");
      out.write("                    <td ><font size=\"1\">\n");
      out.write("                    <h4 style=\"text-align: center\">");
      out.print(stat.getMaxTime());
      out.write("</h4></font>\n");
      out.write("                    </td>\n");
      out.write("                    <td ><font size=\"1\">\n");
      out.write("                    <h4 style=\"text-align: center\">");
      out.print((float)stat.getTotalTime()/(float)stat.getCount());
      out.write("</h4></font>\n");
      out.write("                    </td>\n");
      out.write("                    <td ><font size=\"1\">\n");
      out.write("                    <h4 style=\"text-align: center\">");
      out.print(stat.getTotalTime());
      out.write("</h4></font>\n");
      out.write("                    </td>\n");
      out.write("                    <td ><font size=\"1\">\n");
      out.write("                    <h4 style=\"text-align: center\">");
      out.print(stat.getCount());
      out.write("</h4></font>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("            </table>\n");
      out.write("            <p align=\"center\"><a href=\"");
      out.print(resetUrl);
      out.write("\">Reset Stats</a> / <a href=\"");
      out.print(myUrl);
      out.write("\">Refresh Stats</a></p>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\t\t\t<p>&nbsp;</p>\n");
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"spacer\"><hr/></div>\n");
      out.write("\t</div>\n");
      out.write("\t</center>\n");
      out.write("<!-- content end -->\n");
      out.write("\n");
      out.write("<hr class=\"hide\"/>\n");
      out.write("<!-- footer begin -->\n");
      out.write("\t<div id=\"footer\">\n");
      out.write("\t\t<div id=\"credits\">JBoss Management Console</div>\n");
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
