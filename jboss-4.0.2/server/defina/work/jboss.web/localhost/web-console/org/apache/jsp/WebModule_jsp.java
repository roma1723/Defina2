package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.management.ObjectName;

public final class WebModule_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write(' ');
      out.write('\n');
      out.write('\n');
      //  jb:mbean
      org.jboss.console.plugins.helpers.servlet.MBeanTag _jspx_th_jb_mbean_0 = (org.jboss.console.plugins.helpers.servlet.MBeanTag) _jspx_tagPool_jb_mbean_intf_id_nobody.get(org.jboss.console.plugins.helpers.servlet.MBeanTag.class);
      _jspx_th_jb_mbean_0.setPageContext(_jspx_page_context);
      _jspx_th_jb_mbean_0.setParent(null);
      _jspx_th_jb_mbean_0.setId("webModule");
      _jspx_th_jb_mbean_0.setIntf("org.jboss.management.j2ee.WebModuleMBean");
      int _jspx_eval_jb_mbean_0 = _jspx_th_jb_mbean_0.doStartTag();
      if (_jspx_th_jb_mbean_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_jb_mbean_intf_id_nobody.reuse(_jspx_th_jb_mbean_0);
      org.jboss.management.j2ee.WebModuleMBean webModule = null;
      webModule = (org.jboss.management.j2ee.WebModuleMBean) _jspx_page_context.findAttribute("webModule");
      out.write("\n");
      out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>JBoss Management Console - Web Module</title>\n");
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
      out.write("\t\t<div class=\"content_block\" style=\"width: 100%; height: 247\">\n");
      out.write("\t\t\t<h3>Web Module: '");
      out.print(new ObjectName(webModule.getobjectName()).getKeyProperty("name"));
      out.write("'</h3>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\t\t\t\t<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"95%\" align=\"center\" id=\"AutoNumber1\">\n");
      out.write("                  <tr>\n");
      out.write("                    <td width=\"50%\" align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\"><font size=\"3\">Servlets</font></h4>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                  <tr>\n");
      out.write("                    <td width=\"50%\" align=\"center\" valign=\"top\">\n");
      out.write("                    <h4>This Web Module is composed of the following Servlets:</h4>\n");
      out.write("                    ");

                        String[] namesStr = webModule.getservlets();
                        ObjectName[] names = null;
                        if (namesStr != null) {
                            names = new ObjectName[namesStr.length];
                            for (int i = 0; i < namesStr.length; i++) {
                                names[i] = new ObjectName(namesStr[i]);
                            }
                        }
                        if (names != null)
                    
      out.write("\n");
      out.write("\t\t\t\t\t<ul>\n");
      out.write("                    ");

                            for (int i = 0; i < names.length; i++)
                            {
                    
      out.write("\n");
      out.write("\t\t\t\t\t\t<li>\n");
      out.write("\t\t\t\t\t\t<p align=\"left\"><font size=\"1\"><b>");
      out.print(names[i].getKeyProperty("name"));
      out.write("</b></font></p>\n");
      out.write("\t\t\t\t\t\t</li>\n");
      out.write("                        ");

                            }
                        
      out.write("\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("                    <p align=\"left\"><font size=\"1\"><b>Number of Servlets: </b>");
      out.print((names!=null?names.length:0));
      out.write("</font></p>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("            <p>&nbsp;</p>\n");
      out.write("\t\t\t\t<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"95%\" align=\"center\" id=\"AutoNumber1\">\n");
      out.write("                  <tr>\n");
      out.write("                    <td width=\"50%\" align=\"center\">\n");
      out.write("                    <h4 style=\"text-align: center\"><font size=\"3\">Deployment\n");
      out.write("\t\t\t\t\tDescriptor</font></h4>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("                  <tr>\n");
      out.write("                    <td width=\"50%\" align=\"left\" valign=\"top\"><pre>\n");
      out.write("                    ");
      out.print(org.jboss.console.plugins.helpers.servlet.ServletHelper.filter(webModule.getdeploymentDescriptor()));
      out.write("\n");
      out.write("                    </pre>\n");
      out.write("                    </td>\n");
      out.write("                  </tr>\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"spacer\"><hr/></div>\n");
      out.write("\t</div>\n");
      out.write("\t</center>\n");
      out.write("<!-- content end -->\n");
      out.write("\n");
      out.write("<hr class=\"hide\"/>\n");
      out.write("<!-- footer begin -->\n");
      out.write("\t<div id=\"footer\">\n");
      out.write("\t\t<div id=\"credits\">JBoss&trade; Management Console</div>\n");
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
