package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.management.MBeanServer;
import org.jboss.mx.util.MBeanServerLocator;
import org.jboss.mx.util.InstanceOfQueryExp;
import java.util.Set;
import java.util.Iterator;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.util.HashSet;
import java.util.ArrayList;

public final class listMonitors_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write(' ');
      out.write('\n');

try
{

      out.write("\n");
      out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>JBoss Management Console - Manage Monitor</title>\n");
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
      out.write("\t\t\t<h3>Monitors and Monitor Status</h3>\n");
      out.write("\t\t\t\t\t<p>&nbsp;</p>\n");
      out.write("<table cellspacing=\"2\" cellpadding=\"2\" border=\"1\">\n");
      out.write("<tr>\n");
      out.write("    <td><b>Status</b></td>\n");
      out.write("    <td><b>Monitor Name</b></td>\n");
      out.write("    <td><b>Observed MBean</b></td>\n");
      out.write("    <td><b>Observed Attribute</b></td>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("</tr>\n");

   MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
   InstanceOfQueryExp queryExp = null;
   queryExp = new InstanceOfQueryExp("org.jboss.monitor.JBossMonitorMBean");
   Set monitors = mbeanServer.queryNames(null, queryExp);
   Iterator mbeans = monitors.iterator();
   boolean someAlertSet = false;
   while (mbeans.hasNext())
   {
      ObjectName moname = (ObjectName)mbeans.next();
      String monitorName = (String)mbeanServer.getAttribute(moname, "MonitorName");
      ObjectName observedObject = (ObjectName)mbeanServer.getAttribute(moname, "ObservedObject");
      String attribute = (String)mbeanServer.getAttribute(moname, "ObservedAttribute");
      boolean enabled = ((Boolean)mbeanServer.getAttribute(moname, "Enabled")).booleanValue();

      Object[] args = {"monitors", monitorName, "-service.xml"};
      String[] signature = {"java.lang.String", "java.lang.String", "java.lang.String"};
      Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args, signature);
      boolean persisted = ((Boolean)rtn).booleanValue();

      Object[] nullArgs = {};
      String[] nullSig = {};
      boolean alerted = ((Boolean)mbeanServer.invoke(moname, "alerted", nullArgs, nullSig)).booleanValue();
      if (alerted) someAlertSet = true;
      String color = "black";
      if (!enabled) color = "grey";
      String status = "";
      if (alerted)
      {
         status = "<font color=\"red\">ALERT</font>";
      }
      else if (!enabled)
      {
         status = "<font color=\"grey\"><i>disabled</i></font>";
      }
      else
      {
         status = "<font color=\"green\">OK</font>";
      }
      String link = null;
      if (persisted)
      {
         link = "manageThresholdMonitor.jsp?monitorObjectName=" + java.net.URLEncoder.encode(moname.toString());
      }
      else
      {
         link = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + java.net.URLEncoder.encode(moname.toString());
      }
      String observedMbeanLink = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + java.net.URLEncoder.encode(observedObject.toString());

      out.write("\n");
      out.write("<tr>\n");
      out.write("    <td>");
      out.print(status);
      out.write("</td>\n");
      out.write("    <td><font color=\"");
      out.print(color);
      out.write('"');
      out.write('>');
      out.print(monitorName);
      out.write("</font></td>\n");
      out.write("    <td><font color=\"");
      out.print(color);
      out.write("\"><a href=\"");
      out.print(observedMbeanLink);
      out.write('"');
      out.write('>');
      out.print(observedObject.toString());
      out.write("</a></font></td>\n");
      out.write("    <td><font color=\"");
      out.print(color);
      out.write('"');
      out.write('>');
      out.print(attribute);
      out.write("</font></td>\n");
      out.write("    <td><a href=\"");
      out.print(link);
      out.write("\">manage</a></td>\n");
      out.write("</tr>\n");

   }
   
      out.write("\n");
      out.write("</table>\n");

   if (someAlertSet)
   {

      out.write("\n");
      out.write("<form action=\"ClearMonitorAlerts\" method=\"post\">\n");
      out.write("<input type=\"submit\" name=\"action\" value=\"Clear All Alerts\">\n");
      out.write("</form>\n");

   }
}
catch (Exception ex)
{
   
      out.write(" ERROR in parsing ");

   ex.printStackTrace();
}

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
