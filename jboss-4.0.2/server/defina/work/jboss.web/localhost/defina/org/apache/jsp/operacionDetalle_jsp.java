package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class operacionDetalle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 atad.defina.datos.AccesoDatosBase dao = null; Object[][] opRel = null; Object refresca=null; String accionRef = null;
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Untitled Document</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<script src=\"js/defina.js\"></script>\r\n");
      out.write("<script language=\"JavaScript\">\r\n");

	Object codPadre = request.getParameter("codigo");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
  if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  	disabled = "disabled=\"true\"";
  } else {
  	disabled ="";
  }

      out.write("\r\n");
      out.write("var filaSel = null;\r\n");
      out.write("\r\n");
      out.write("function seleccionarAutorizacion() {\r\n");
      out.write("\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true");
      out.write("','','dialogWidth:600px;dialogHeight:680px');\r\n");
      out.write("\tif (rc != null) document.getElementById(\"autorizacion\").value = rc[0];\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function seleccionarIcono() {\r\n");
      out.write("\t//\r\n");
      out.write("\t// <ETIQUETA>\r\n");
      out.write("\t// Si es operacion de tipo Etiqueta no lleva asociado ningun icono, por lo q se \"desabilita\" el boton\r\n");
      out.write("    if (\"A\"==document.getElementById('tipoOperacion').value)\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true");
      out.write("','','dialogWidth:500px;dialogHeight:580px');\r\n");
      out.write("\tif (rc != null){\r\n");
      out.write("\t\tdocument.getElementById(\"icono\").value = rc[0];\r\n");
      out.write("\t\tdocument.getElementById(\"imgicono\").src = \"images/\" + rc[0] + \".gif\";\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function seleccion(str){\r\n");
      out.write("\tfilaSel = str.split(\"<sep>\");\r\n");
      out.write("}\r\n");
      out.write("function actualiza(){\r\n");
      out.write("\tdocument.getElementById(\"codigoCanal\").value = document.getElementById(\"codCanal\").value;\r\n");
      out.write("}\r\n");
      out.write("function addOpRel(){\r\n");
      out.write("\tvar continuar=true;\r\n");
      out.write("\tif (document.getElementById(\"codigo\").value==\"\"){\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif (continuar){\r\n");
      out.write("\t\tvar tmp = 'operacionesRelacionadas.jsp?accprev=");
      out.print(request.getParameter("accion"));
      out.write("&accion=INICIAR&tipoOperacion=' + document.getElementById(\"tipoOperacion\").value + '&tipoOperacionFinal=' + document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\t\tlocation.href = tmp + generaCadena();\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\talert(\"Antes de añadir operaciones relacionadas, debe especificar el código de la operación que está dando de alta.\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function eliminarOpRel(){\r\n");
      out.write("\t// Elimina la información de la base de datos\r\n");
      out.write("\tvar rc = confirm('Se va a eliminar la operación relacionada ' + filaSel[0]);\r\n");
      out.write("\tif (rc) {\r\n");
      out.write("\t\t");

		// Sube a sesión la información relativa a las operaciones relacionadas
		Object acc = request.getParameter("accion");
		if (!"RELACIONAR".equals(acc)){
			session.putValue("opRelacionadas",opRel);
		}
		
      out.write("\r\n");
      out.write("\t\tlocation.href=\"operacionDetalle.jsp?accion=RELACIONAR&operacion=");
      out.print(codPadre);
      out.write("&indice=\" + filaSel[3] + generaCadena() + \"&refresca=true\";\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");

	String referrer = request.getParameter("referrer");
	String destinoCancelar = null;
	if (referrer == null) {
		destinoCancelar = "operacion.jsp?accion=BUSCAR";
	} else {
		accionRef = request.getParameter("accionref");
		destinoCancelar = referrer;
		if (accionRef != null){
			destinoCancelar = referrer + "?accion=" + accionRef;
		} else {
			destinoCancelar = referrer + "?accion=BUSCAR";
		}
	}

      out.write("\r\n");
      out.write("function cancelar(){\r\n");
      out.write("\tlocation.href='");
      out.print(destinoCancelar);
      out.write("' + generaCadenaCancelar();\r\n");
      out.write("}\r\n");
      out.write("function obtener(){\r\n");
      out.write("\tvar textos = document.all.texto;\r\n");
      out.write("\tvar valor = \"\";\r\n");
      out.write("\tif (textos !=null){\r\n");
      out.write("\t\tif(textos.length > 1){\r\n");
      out.write("\t\t\tfor (var i=0;i<textos.length;i++){\r\n");
      out.write("\t\t\t\t// Antes de añadir, se comprueba si el valor se contiene en la cadena cuando hay más de una operación relacionada\r\n");
      out.write("\t\t\t\tfor (var j = 0;j<textos.length;j++){\r\n");
      out.write("\t\t\t\t\t// No se comprueba consigo mismo\r\n");
      out.write("\t\t\t\t\tif (j != i){\r\n");
      out.write("\t\t\t\t\t\tif (textos[i].value == textos[j].value){\r\n");
      out.write("\t\t\t\t\t\t\t// Elimina la información que exista en el campo\r\n");
      out.write("\t\t\t\t\t\t\tdocument.getElementById(\"ordenes_oprel\").value=\"\";\r\n");
      out.write("\t\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvalor = valor + (textos[i].value) + \"<sep>\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tdocument.getElementById(\"ordenes_oprel\").value=valor;\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tdocument.getElementById(\"ordenes_oprel\").value=textos.value + \"<sep>\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function cambiarSalida(){\r\n");
      out.write("\tglobalSalida = true;\r\n");
      out.write("\talert(\"globalSalida=\" + globalSalida);\r\n");
      out.write("}\r\n");
      out.write("function aceptar() {\r\n");
      out.write("\t// Establece el control para los campos obligatorios: código, nombre, entorno y tipo\r\n");
      out.write("\tvar strCadenaCodigo = document.getElementById(\"codigo\").value.substring(0,2).toUpperCase();\r\n");
      out.write("\tcontinuar=true;\r\n");
      out.write("\tif (document.getElementById(\"codigo\").value==\"\"){\r\n");
      out.write("\t\talert('El campo código es requerido.');\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t}\r\n");
      out.write("\t//else{\r\n");
      out.write("\t//\tvar bOperacionAccion =");
      out.print("MODIFICAR".equals(request.getParameter("accion")));
      out.write(";\r\n");
      out.write("\t//\tif (!bOperacionAccion){\r\n");
      out.write("\t//\t\tif ((strCadenaCodigo == \"AD\") || (strCadenaCodigo == \"ON\") || (strCadenaCodigo == \"MN\")){\r\n");
      out.write("\t//\t\t\talert(\"El código de la operación no puede empezar por las letras '\" + strCadenaCodigo + \"'\");\r\n");
      out.write("\t//\t\t\tcontinuar=false;\r\n");
      out.write("\t//\t\t}\r\n");
      out.write("\t//\t}\r\n");
      out.write("\t//}\r\n");
      out.write("\tif (document.getElementById(\"descripcion\").value==\"\"){\r\n");
      out.write("\t\talert('El campo nombre es requerido.');\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif (document.getElementById(\"entorno\").value==\"\" || document.getElementById(\"entorno\").value==\" \"){\r\n");
      out.write("\t\talert('El campo entorno es requerido.');\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif (document.getElementById(\"tipoOperacion\").value==\"\" || document.getElementById(\"tipoOperacion\").value==\" \"){\r\n");
      out.write("\t\talert('El campo tipo de operación es requerido.');\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tif (document.getElementById(\"tipoOperacion\").value == \"O\"){\r\n");
      out.write("\t\t\tvar strCodigo = document.getElementById(\"codigo\").value.substring(0,3).toUpperCase();\r\n");
      out.write("\t\t\tif (strCodigo != \"OBJ\"){\r\n");
      out.write("\t\t\t\talert(\"Los objetos de negocio deben empezar por OBJ\");\r\n");
      out.write("\t\t\t\tcontinuar = false;\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t\t if ((continuar == true) &&  (document.getElementById(\"tipoOperacionFinal\").value != \"1\")){\r\n");
      out.write("\t\t\t \talert(\"Un Objeto de Negocio solo puede tener como operacion final\\nuna 'Ejecucion de Flujo NACAR'.\");\r\n");
      out.write("\t\t\t \tcontinuar = false;\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar numParamsReq=0;\r\n");
      out.write("\t// Considera la seleccion en la combo de tipoOperacionFinal si esta habilitado\r\n");
      out.write("\tif (!document.getElementById(\"tipoOperacionFinal\").disabled && continuar == true){\r\n");
      out.write("\t\t// Toma el valor seleccionado\r\n");
      out.write("\t\tvar dato = document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\t\t// Considera el valor recuperado para validar las operaciones\r\n");
      out.write("\t\tswitch(dato){\r\n");
      out.write("\t\t\tcase \"0\" :\r\n");
      out.write("\t\t\tcase \"8\" :{\r\n");
      out.write("\t\t\t\tnumParamsReq=0;\r\n");
      out.write("\t\t\t\tbreak;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tcase \"1\" :\r\n");
      out.write("\t\t\tcase \"3\" :\r\n");
      out.write("\t\t\tcase \"A\" :\r\n");
      out.write("\t\t\tcase \"E\" :\r\n");
      out.write("\t\t\t// Ampliación Filtrado\r\n");
      out.write("\t\t\tcase \"F\" :\r\n");
      out.write("\t\t\tcase \"U\" :{\r\n");
      out.write("\t\t\t\tnumParamsReq=1;\r\n");
      out.write("\t\t\t\tbreak;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tcase \"7\" :\r\n");
      out.write("\t\t\tcase \"C\" :{\r\n");
      out.write("\t\t\t\tnumParamsReq=2;\r\n");
      out.write("\t\t\t\tbreak;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tcase \"6\" :{\r\n");
      out.write("\t\t\t\tnumParamsReq=3;\r\n");
      out.write("\t\t\t\tbreak;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\t\tif (document.getElementById(\"tipoOperacionFinal\").value==\"\") document.getElementById(\"tipoOperacionFinal\").value = 0;\r\n");
      out.write("\t}\r\n");
      out.write("\tif (numParamsReq==1){\r\n");
      out.write("\t\tif (document.getElementById(\"parametro1\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t} else if (numParamsReq==2){\r\n");
      out.write("\t\tif (document.getElementById(\"parametro1\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (document.getElementById(\"parametro2\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 2, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t} else if (numParamsReq==3){\r\n");
      out.write("\t\tif (document.getElementById(\"parametro1\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (document.getElementById(\"parametro2\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 2, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (document.getElementById(\"parametro3\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Es necesario informar el campo paramétro 3, para el tipo de operación seleccionado\");\r\n");
      out.write("\t\t\tcontinuar=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//\r\n");
      out.write("\t// <ETIQUETA>\t\t\r\n");
      out.write("\t// Comprueba cual es la pagina que solicita el detalle de la operacion\r\n");
      out.write("\t// Si la pagina anterior es: operacionArbol o escenariosArbol --> significa q voy a editar una operacion q cuelga de\r\n");
      out.write("\t// un escenario o menu. En este caso, no puedo permitir q se modifique el tipo de esta operacion a Etiqueta\r\n");
      out.write("\tvar referencia = '");
      out.print( request.getParameter("referrer") );
      out.write("';\r\n");
      out.write("\t\r\n");
      out.write("\t// Si quiero modificar una operacion a tipo Etiqueta q esta colgada de un menu --> no lo permito\t\r\n");
      out.write("\tif (referencia.indexOf(\"operacionArbol\")>0 && \"A\"==document.getElementById(\"tipoOperacion\").value){\r\n");
      out.write("\t\talert(\"Accion no permitida: No se puede colgar una operacion de tipo Etiqueta de un menu\");\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t} else if (referencia.indexOf(\"escenariosArbol\")>0 && \"A\"==document.getElementById(\"tipoOperacion\").value) {\r\n");
      out.write("\t\talert (\"Accion no permitida: No se puede colgar una operacion de tipo Etiqueta de un escenario\");\r\n");
      out.write("\t\tcontinuar=false;\r\n");
      out.write("\t} \t\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t// En función de las validaciones anteriores, se decide continuar o no\r\n");
      out.write("\tif (continuar){\r\n");
      out.write("\t\t// Recupera la información de los órdenes de las operaciones relacionadas y las prepara\r\n");
      out.write("\t\tvar r = obtener();\r\n");
      out.write("\t\tif (r == false){\r\n");
      out.write("\t\t\t// Existen datos con el mismo orden\r\n");
      out.write("\t\t\talert(\"Existen operaciones relacionadas con el mismo orden. Por favor, modifique el orden de forma que no coincidan.\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t// Efectúa la petición AJAX para almacenar la información en la base de datos\r\n");
      out.write("\t\t");

		String [][] campos = new String[29][2];
		campos[0][0]="0";
		campos[0][1]="codigo.value";
		campos[1][0]="1";
		campos[1][1]="descripcion.value";
		campos[2][0]="2";
		campos[2][1]="entorno.value";
		campos[3][0]="3";
		campos[3][1]="tooltip.value";
		campos[4][0]="4";
		campos[4][1]="descAd.value";
		campos[5][0]="5";
		campos[5][1]="icono.value";
		campos[6][0]="6";
		campos[6][1]="autorizacion.value";
		campos[7][0]="7";
		campos[7][1]="tipoOperacion.value";
		campos[8][0]="8";
		campos[8][1]="aplicacion.value";
		campos[9][0]="9";
		campos[9][1]="teclaMenu.value";
		campos[10][0]="10";
		campos[10][1]="pseudocodigo1.value";
		campos[11][0]="11";
		campos[11][1]="pseudocodigo2.value";
		campos[12][0]="12";
		campos[12][1]="atributos.value";
		campos[13][0]="13";
		campos[13][1]="codCanal.value";
		campos[14][0]="14";
		campos[14][1]="tipoOperacionFinal.value";
		campos[15][0]="15";
		campos[15][1]="parametro1.value";
		campos[16][0]="16";
		campos[16][1]="parametro2.value";
		campos[17][0]="17";
		campos[17][1]="parametro3.value";
		// La posición 18 queda abierta para el tratamiento del canal dentro del procesador AJAX
		campos[19][0]="19";
		campos[19][1]="ordenes_oprel.value";
		// Parámetros de búsqueda
		campos[20][0]="20";
		campos[20][1]="codigoBusc.value";
		campos[21][0]="21";
		campos[21][1]="fecModificacionBusc.value";
		campos[22][0]="22";
		campos[22][1]="descripcionBusc.value";
		campos[23][0]="23";
		campos[23][1]="entornoBusc.value";
		campos[24][0]="24";
		campos[24][1]="iconoBusc.value";
		campos[25][0]="25";
		campos[25][1]="autorizacionBusc.value";
		campos[26][0]="26";
		campos[26][1]="tipoOperacionBusc.value";
		campos[27][0]="27";
		campos[27][1]="parametro1Busc.value";
		campos[28][0]="28";
		campos[28][1]="tipoOperacionFinalBusc.value";
		boolean modificar = (("MODIFICAR".equals(request.getParameter("accion")))?true:false);
		if (modificar || "RELACIONAR".equals(request.getParameter("accion"))){
			session.putValue("borrarOperacion","true");
		} 
		if ("NUEVA".equals(request.getParameter("accion"))){
			session.putValue("nueva","true");
		} else {
			session.putValue("nueva","false");
		}
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript("OPERACIONES", modificar, false, campos));
		
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function generaCadena(){\r\n");
      out.write("\tvar tmp = '&codigo=' + document.getElementById(\"codigo\").value;\r\n");
      out.write("\ttmp = tmp + '&descripcion=' + document.getElementById(\"descripcion\").value;\r\n");
      out.write("\ttmp = tmp + '&entorno=' + document.getElementById(\"entorno\").value;\r\n");
      out.write("\ttmp = tmp + '&tooltip=' + document.getElementById(\"tooltip\").value;\r\n");
      out.write("\ttmp = tmp + '&descAd=' + document.getElementById(\"descAd\").value;\r\n");
      out.write("\ttmp = tmp + '&icono=' + document.getElementById(\"icono\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacion=' + document.getElementById(\"autorizacion\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacion=' + document.getElementById(\"tipoOperacion\").value;\r\n");
      out.write("\ttmp = tmp + '&aplicacion=' + document.getElementById(\"aplicacion\").value;\r\n");
      out.write("\ttmp = tmp + '&teclaMenu=' + document.getElementById(\"teclaMenu\").value;\r\n");
      out.write("\ttmp = tmp + '&pseudocodigo1=' + document.getElementById(\"pseudocodigo1\").value;\r\n");
      out.write("\ttmp = tmp + '&pseudocodigo2=' + document.getElementById(\"pseudocodigo2\").value;\r\n");
      out.write("\ttmp = tmp + '&atributos=' + document.getElementById(\"atributos\").value;\r\n");
      out.write("\ttmp = tmp + '&codCanal=' + document.getElementById(\"codigoCanal\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionFinal=' + document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1=' + document.getElementById(\"parametro1\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro2=' + document.getElementById(\"parametro2\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro3=' + document.getElementById(\"parametro3\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacion=' + document.getElementById(\"fecModificacion\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacionIn=' + document.getElementById(\"fecModificacionIn\").value;\r\n");
      out.write("\t// Incluye la información de la que query\r\n");
      out.write("\ttmp = tmp + '&query=' + document.getElementById(\"query\").value;\t\r\n");
      out.write("\treturn tmp;\r\n");
      out.write("}\r\n");
      out.write("function generaCadenaCancelar(){\r\n");
      out.write("\tvar tmp = '&codigo=' + document.getElementById(\"codigorel\").value;\r\n");
      out.write("\ttmp = tmp + '&codigoBusc=' + document.getElementById(\"codigoBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&descripcion=' + document.getElementById(\"descripcionrel\").value;\r\n");
      out.write("\ttmp = tmp + '&descripcionBusc=' + document.getElementById(\"descripcionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&entorno=' + document.getElementById(\"entornorel\").value;\r\n");
      out.write("\ttmp = tmp + '&entornoBusc=' + document.getElementById(\"entornoBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&tooltip=' + document.getElementById(\"tooltiprel\").value;\r\n");
      out.write("\ttmp = tmp + '&descAd=' + document.getElementById(\"descAdrel\").value;\r\n");
      out.write("\ttmp = tmp + '&icono=' + document.getElementById(\"iconorel\").value;\r\n");
      out.write("\ttmp = tmp + '&iconoBusc=' + document.getElementById(\"iconoBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacion=' + document.getElementById(\"autorizacionrel\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacionBusc=' + document.getElementById(\"autorizacionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacion=' + document.getElementById(\"tipoOperacionrel\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionBusc=' + document.getElementById(\"tipoOperacionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&aplicacion=' + document.getElementById(\"aplicacionrel\").value;\r\n");
      out.write("\ttmp = tmp + '&teclaMenu=' + document.getElementById(\"teclaMenurel\").value;\r\n");
      out.write("\ttmp = tmp + '&pseudocodigo1=' + document.getElementById(\"pseudocodigo1rel\").value;\r\n");
      out.write("\ttmp = tmp + '&pseudocodigo2=' + document.getElementById(\"pseudocodigo2rel\").value;\r\n");
      out.write("\ttmp = tmp + '&atributos=' + document.getElementById(\"atributosrel\").value;\r\n");
      out.write("\ttmp = tmp + '&codCanal=' + document.getElementById(\"codCanalrel\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionFinal=' + document.getElementById(\"tipoOperacionFinalrel\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1=' + document.getElementById(\"parametro1rel\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1Busc=' + document.getElementById(\"parametro1Busc\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro2=' + document.getElementById(\"parametro2rel\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro3=' + document.getElementById(\"parametro3rel\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacion=' + document.getElementById(\"fecModificacionrel\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacionBusc=' + document.getElementById(\"fecModificacionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacionIn=' + document.getElementById(\"fecModificacionIn\").value;\r\n");
      out.write("\t// Incluye la operación para posicionarse sobre ella\r\n");
      out.write("\ttmp = tmp + '&opPosicion=' + document.getElementById(\"opPosicion\").value;\r\n");
      out.write("\ttmp = tmp + '&codigoEsc=' + document.getElementById(\"cod_escenario\").value;\r\n");
      out.write("\ttmp = tmp + '&nombreEsc=' + document.getElementById(\"nombre_escenario\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacionEsc=' + document.getElementById(\"aut_escenario\").value;\r\n");
      out.write("\ttmp = tmp + '&iconoEsc=' + document.getElementById(\"icono_escenario\").value;\r\n");
      out.write("\ttmp = tmp + '&ordenEsc=' + document.getElementById(\"orden_escenario\").value;\r\n");
      out.write("\t// Incluye la información de la que query\r\n");
      out.write("\ttmp = tmp + '&query=' + document.getElementById(\"query\").value;\r\n");
      out.write("\treturn tmp;\r\n");
      out.write("}\r\n");
      out.write("function validaOp(){\r\n");
      out.write("  // Para el caso de operaciones finales u Objetos de Negocio\r\n");
      out.write("\tif(\"F\"==document.getElementById('tipoOperacion').value || \"O\"==document.getElementById('tipoOperacion').value || \"M\"==document.getElementById('tipoOperacion').value){\r\n");
      out.write("\t\t// Habilita los campos para la operación final\r\n");
      out.write("\t\tdocument.getElementById('tipoOperacionFinal').disabled=false; // combo\r\n");
      out.write("\t\tdocument.getElementById('tipoOperacionFinal').className=\"CampoObligatorio\";\r\n");
      out.write("\t\tdocument.getElementById('parametro1').disabled=false;\r\n");
      out.write("\t\tdocument.getElementById('parametro1').className=\"CampoEntrada\";\r\n");
      out.write("\t\tdocument.getElementById('parametro2').disabled=false;\r\n");
      out.write("\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\tdocument.getElementById('parametro3').disabled=false;\r\n");
      out.write("\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\tif (\"Pesados\" == '");
      out.print(session.getValue("canal"));
      out.write("' && \"\" == '");
      out.print(disabled);
      out.write("' && \"F\" == document.getElementById('tipoOperacion').value){\r\n");
      out.write("\t\t\tdocument.getElementById('Button3').disabled=false;\r\n");
      out.write("\t\t\tdocument.getElementById('Button31').disabled=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//\r\n");
      out.write("\t\t// <ETIQUETA>\r\n");
      out.write("\t\t// Si se cambia el tipo de la operacion de etiqueta a final, hay que habilitar los campos correspondientes q se habian\r\n");
      out.write("\t\t// deshabilitado para etiquetas\t\t\r\n");
      out.write("\t\tdocument.getElementById('tooltip').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('tooltip').className=\"CampoEntrada\";\t\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').className=\"CampoEntrada\";\t\t\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('icono').disabled=false; \r\n");
      out.write("\t\tdocument.getElementById('imagenIcono').src=\"images/BotonPrismatico.gif\";  \t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').className=\"CampoEntrada\";\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').className=\"CampoEntrada\";\t\r\n");
      out.write("\t\tdocument.getElementById('atributos').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('atributos').className=\"CampoEntrada\";\r\n");
      out.write("\t\tdocument.getElementById('imgicono').width=\"17\";\r\n");
      out.write("\t// \r\n");
      out.write("\t// <ETIQUETA>\r\n");
      out.write("\t// Habilita/deshabilita los campos para las operaciones de tipo etiqueta\r\n");
      out.write("\t} else if (\"A\"==document.getElementById('tipoOperacion').value) {\r\n");
      out.write("\t\t// Habilita los campos para la operación final\t\r\n");
      out.write("\t\tdocument.getElementById('tooltip').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('tooltip').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('tooltip').className=\"CampoSalida\";\t\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').className=\"CampoSalida\";\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('icono').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('icono').disabled=true; \r\n");
      out.write("\t\tdocument.getElementById('imagenIcono').src=\"images/BotonPrismaticoD.gif\";\r\n");
      out.write("\t\t// Se quita la previsualizacion del icono\r\n");
      out.write("\t\tdocument.getElementById('imgicono').width=\"0\";\r\n");
      out.write("  \t\tdocument.getElementById('pseudocodigo1').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').className=\"CampoSalida\";\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').className=\"CampoSalida\";\t\r\n");
      out.write("\t\tdocument.getElementById('atributos').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('atributos').value=\"\"; \r\n");
      out.write("\t\tdocument.getElementById('atributos').className=\"CampoSalida\";\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\tdocument.getElementById('tipoOperacionFinal').disabled=true; // combo\r\n");
      out.write("\t\tdocument.getElementById('tipoOperacionFinal').value=\"\"; // combo\r\n");
      out.write("\t\tdocument.getElementById('tipoOperacionFinal').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro1').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro1').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro11').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro1').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro2').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro2').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro21').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro2').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro3').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro3').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro3').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('Button3').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('Button31').disabled=true;\r\n");
      out.write("\t} else {\r\n");
      out.write("\t  // Vacia y elimina el contenido del texto\r\n");
      out.write("\t  document.getElementById('tipoOperacionFinal').disabled=true; // combo\r\n");
      out.write("\t  document.getElementById('tipoOperacionFinal').value=\"\"; // combo\r\n");
      out.write("\t  document.getElementById('tipoOperacionFinal').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro1').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro1').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro11').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro1').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro2').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro2').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro21').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro2').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('parametro3').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('parametro3').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\tdocument.getElementById('parametro3').className=\"CampoSalida\";\r\n");
      out.write("\t\tdocument.getElementById('Button3').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('Button31').disabled=true;\r\n");
      out.write("\t\t//\r\n");
      out.write("\t\t// <ETIQUETA>\r\n");
      out.write("\t\t// Si se cambia el tipo de la operacion de etiqueta a final, hay que habilitar los campos correspondientes q se habian\r\n");
      out.write("\t\t// deshabilitado para etiquetas\t\t\r\n");
      out.write("\t\tdocument.getElementById('tooltip').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('tooltip').className=\"CampoEntrada\";\t\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('teclaMenu').className=\"CampoEntrada\";\t\t\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('icono').disabled=false; \r\n");
      out.write("\t\tdocument.getElementById('imagenIcono').src=\"images/BotonPrismatico.gif\";  \t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo1').className=\"CampoEntrada\";\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('pseudocodigo2').className=\"CampoEntrada\";\t\r\n");
      out.write("\t\tdocument.getElementById('atributos').disabled=false;\t\t\r\n");
      out.write("\t\tdocument.getElementById('atributos').className=\"CampoEntrada\";\r\n");
      out.write("\t\tdocument.getElementById('imgicono').width=\"17\";\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function validaTipoOpFinal(){\r\n");
      out.write("\t// Toma el valor seleccionado\r\n");
      out.write("\tvar dato = document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\t// Considera el valor recuperado para validar las operaciones\r\n");
      out.write("\tswitch(dato){\r\n");
      out.write("\t\tcase \"0\" :\r\n");
      out.write("\t\tcase \"8\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\t\r\n");
      out.write("\t\tcase \"1\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Código de Flujo NACAR\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"True (Defecto) si es monoinstancia o False si no\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"3\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value='Mandato (normalmente un cmd) a ejecutar, indicar path con \"/\" si es preciso';\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"A\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Parámetro 1\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"Parámetro 2\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"Parámetro 3\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t// Ampliación Filtrado\r\n");
      out.write("\t\tcase \"F\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Operación NACAR de filtrado (Debe ser de tipo flujo NACAR)\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"True (defecto) si es monoinstancia o False si no\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"E\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Código de Flujo NACAR\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"True (defecto) si es monoinstancia o False si no\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"U\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Url a lanzar\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"True (defecto) si es monoinstancia o False si no\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"3 para que abra una ventana nueva, URL de desconexión (opcional)\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"6\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Nombre del runtime TFM\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"Código de aplicación\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"Descripción de la aplicación\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"7\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Nombre Clase\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"Nombre Método\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"Parámetros separados por comas\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcase \"C\" :{\r\n");
      out.write("\t\t\tdocument.getElementById('parametro11').value=\"Código de Flujo NACAR\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro1').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro21').value=\"True (Defecto) si es monoinstancia o False si no\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro2').className=\"CampoObligatorio\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro31').value=\"Código de operación relacionada o en blanco si es la primaria\";\r\n");
      out.write("\t\t\tdocument.getElementById('parametro3').className=\"CampoEntrada\";\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");

	Object [][] data = null;
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	Object accion = request.getParameter("accion");
	Object accprev = request.getParameter("accprev");
  atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Accion: " + accion);
	if (accion != null){
		if ("NUEVA".equals(accion)){
			// Se va a dar de alta una nueva operación.
		} else if ("MODIFICAR".equals(accion)){
		  Object codigoOpModif=null;
		  // Recupera el valor del flag "usarSesion", informado a false cuando se llega aquí desde el link de la página de
		  // operaciones relacionadas.
			if (session.getValue("codigoModificar")== null){
				// Se trata de una nueva modificación (la primera vez que se accede a esta página)
				// Se informa en la sesión el código de la operación
				codigoOpModif=request.getParameter("codigo");
				session.putValue("codigoModificar",codigoOpModif);
			} else {
				String usarSesion = request.getParameter("usarSesion");
				if (usarSesion != null && "false".equals(usarSesion)){
					codigoOpModif = request.getParameter("codigo");
				} else {
				  // Se había intentando modificar esta operación sin pulsar "Aceptar" o "Cancelar" previamente (no es la primera vez)
			  	// Se ha navegado sucesivas veces por otras pantallas y se vuelve aquí
			  	codigoOpModif = session.getValue("codigoModificar");
		  	}
			}		  
			// Recupera todos los datos que faltan para la operación
			data = dao.getDatosOperacion(session.getValue("canal"),codigoOpModif);
		} else if ("CANCELADO".equals(accion)){
				data = new Object[1][19];
				data[0][0] = request.getParameter("codigo");
				data[0][1] = request.getParameter("descripcion");
				data[0][2] = request.getParameter("entorno");
				data[0][3] = request.getParameter("autorizacion");
				data[0][4] = request.getParameter("tipoOperacion");
				data[0][5] = request.getParameter("descAd");
				data[0][6] = request.getParameter("aplicacion");
				data[0][7] = request.getParameter("icono");
				data[0][8] = request.getParameter("teclaMenu");
				data[0][9] = request.getParameter("tooltip");
				data[0][10] = request.getParameter("atributos");
				data[0][11] = request.getParameter("tipoOperacionFinal");
				data[0][12] = request.getParameter("parametro1");
				data[0][13] = request.getParameter("parametro2");
				data[0][14] = request.getParameter("parametro3");
				data[0][15] = request.getParameter("parametro1");
				data[0][16] = request.getParameter("parametro2");
				data[0][17] = request.getParameter("pseudocodigo1");
				data[0][18] = request.getParameter("pseudocodigo2");
		} else if ("GRABAR".equals(accion)){
			// Almacena todos los datos de la operación en la base de datos
			Object[] params = new Object[]{
				request.getParameter("codigo"),request.getParameter("descripcion"),request.getParameter("entorno"),
				request.getParameter("tooltip"),request.getParameter("descAd"),request.getParameter("icono"),
				request.getParameter("autorizacion"),request.getParameter("tipoOperacion"),
				(request.getParameter("aplicacion").toString().length()==0?" ":request.getParameter("aplicacion")),
				request.getParameter("teclaMenu"),request.getParameter("pseudocodigo1"),request.getParameter("pseudocodigo2"),
				request.getParameter("atributos"),request.getParameter("codCanal"),request.getParameter("tipoOperacionFinal"),
				(request.getParameter("parametro1").toString().length()==0?" ":request.getParameter("parametro1")),
				(request.getParameter("parametro2").toString().length()==0?" ":request.getParameter("parametro2")),
				(request.getParameter("parametro3").toString().length()==0?" ":request.getParameter("parametro3")),
				session.getValue("canal")};
			// Comprueba el tipo de operación antes de grabar nada
			if (!"F".equals(request.getParameter("tipoOperacion")) && !"O".equals(request.getParameter("tipoOperacion"))){
				// Pone a nulos los datos de operaciones relacionadas
				session.putValue("opRelacionadas",null);
			}
			int grabado = dao.grabarOperacion(request.getParameter("entorno"),params,(Object[])session.getValue("opRelacionadas"));
			if (grabado==0){
				// Después de grabar, fuerza a que se pongan a nulos los datos
				session.putValue("opRelacionadas",null);
				application.getRequestDispatcher("/operacion.jsp?accion=INICIAR").forward(request,response);
				return;
			}	
		} else if ("RELACIONAR".equals(accion)){
			// Recupera el parámetro sel de la request, ya que contiene la información sobre las operaciones relacionadas
			Object s = request.getParameter("sel");
			refresca = request.getParameter("refresca");
			String sel = null;
			if (s != null){
				sel = s.toString();
			}
			if (sel != null && sel.length() > 0){
				String bloque = null;
				String trozo = null;
				java.util.ArrayList objTotal = new java.util.ArrayList(7);
				java.util.ArrayList obj = null;
				java.util.StringTokenizer tk2 = null;
				while (sel.indexOf("<obj>") != -1){
					bloque = sel.substring(0,sel.indexOf("<obj>"));
					sel = sel.substring(sel.indexOf("<obj>")+5,sel.length());
					obj = new java.util.ArrayList(11);
					while(bloque.indexOf("<sep>") != -1){
						trozo = bloque.substring(0,bloque.indexOf("<sep>"));
						bloque = bloque.substring(bloque.indexOf("<sep>")+5,bloque.length());
						obj.add(trozo);
					}
					// añade el último
					obj.add(bloque);
					objTotal.add(obj.toArray());
					obj = null;
				}
				// Actualiza el último bloque
				obj = new java.util.ArrayList(11);
				bloque = sel;
				while(bloque.indexOf("<sep>") != -1){
					trozo = bloque.substring(0,bloque.indexOf("<sep>"));
					bloque = bloque.substring(bloque.indexOf("<sep>")+5,bloque.length());
					obj.add(trozo);
				}
				// añade el último
				obj.add(bloque);
				objTotal.add(obj.toArray());
				obj = null;
				// En este punto se habrán obtenido las operaciones en forma de array.
				opRel = new Object[objTotal.size()][8];
				for (int i=0;i<objTotal.size();i++){
					opRel[i] = (Object[])objTotal.get(i);
				}
				
				// Valida si había algo en la sesión previamente
				Object[][] opRelPrev = (Object[][])session.getValue("opRelacionadas");
				if ("RELACIONAR".equals(accion)){
					Object[][] opDef = null;
					if ("RELACIONAR".equals(accprev) || "CANCELADO".equals(accprev)){
						if (opRelPrev != null && opRelPrev.length !=0){
							opDef = new Object[opRel.length+opRelPrev.length][opRel[0].length];
							System.arraycopy(opRelPrev,0,opDef,0,opRelPrev.length);
							System.arraycopy(opRel,0,opDef,opRelPrev.length,opRel.length);
						} else {
							opDef = new Object[opRel.length][opRel[0].length];
							System.arraycopy(opRel,0,opDef,0,opRel.length);
						}
					} else if ("MODIFICAR".equals(accprev)){
						Object[][] f = dao.recuperaOpCtx(request.getParameter("codigo"),session.getValue("entorno"));
						if (f != null && f.length > 0){
							// Existen datos en BBDD --> Se toma la base de datos, y el resultado
							opDef = new Object[opRel.length+f.length][opRel[0].length];
							System.arraycopy(f,0,opDef,0,f.length);
							System.arraycopy(opRel,0,opDef,f.length,opRel.length);
						} else {
							// No existen --> Solo se toma lo que se ha recibido
							opDef = new Object[opRel.length][opRel[0].length];
							System.arraycopy(opRel,0,opDef,0,opRel.length);
						}
					}
					// Establece el valor en opRel
					opRel= opDef;
					session.putValue("opRelacionadas",opRel);
				}
			}
			if (refresca != null && "true".equals(refresca.toString())){
				// Obtiene el índice que se va a eliminar del array
				Object ind = request.getParameter("indice");
				// Baja de la sesión las operaciones relacionadas
				Object[][] res  = (Object[][])session.getValue("opRelacionadas");
				int in = 0;
				if (res != null && res.length>0){
					opRel = new Object[res.length - 1][res[0].length];
					for (int i=0;i<res.length;i++){
						if (Integer.parseInt(ind.toString()) != i){
							for (int j=0;j<res[0].length;j++){
								opRel[in][j] = res[i][j];
							}
							in++;
						} else {
							continue;
						}
					}
				} else if (opRel != null){
					Object[][] opR2 = new Object[opRel.length - 1][opRel[0].length];
					for (int i=0;i<opRel.length;i++){
						if (Integer.parseInt(ind.toString()) != i){
							for (int j=0;j<opRel[0].length;j++){
								opR2[in][j] = opRel[i][j];
							}
							in++;
						} else {
							continue;
						}
					}
					opRel = opR2;
				}
				// Sube la información a la sesión
				session.putValue("opRelacionadas",opRel);
			}
			if((sel != null && sel.length() > 0) || (refresca != null && "true".equals(refresca.toString())) || (sel != null && sel.trim().length() == 0)){
				data = new Object[1][19];
				data[0][0] = request.getParameter("codigo");
				data[0][1] = request.getParameter("descripcion");
				data[0][2] = request.getParameter("entorno");
				data[0][3] = request.getParameter("autorizacion");
				data[0][4] = request.getParameter("tipoOperacion");
				data[0][5] = request.getParameter("descAd");
				data[0][6] = request.getParameter("aplicacion");
				data[0][7] = request.getParameter("icono");
				data[0][8] = request.getParameter("teclaMenu");
				data[0][9] = request.getParameter("tooltip");
				data[0][10] = request.getParameter("atributos");
				data[0][11] = request.getParameter("tipoOperacionFinal");
				data[0][12] = request.getParameter("parametro1");
				data[0][13] = request.getParameter("parametro2");
				data[0][14] = request.getParameter("parametro3");
				data[0][15] = request.getParameter("parametro1");
				data[0][16] = request.getParameter("parametro2");
				data[0][17] = request.getParameter("pseudocodigo1");
				data[0][18] = request.getParameter("pseudocodigo2");
			}
		}
		
	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Fin bloque 1");
	}

      out.write("\r\n");
      out.write("<body onLoad=\"validaOp();validaTipoOpFinal();\">\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" name=\"codigorel\" value=\"");
      out.print((request.getParameter("codigoprev")==null?"":request.getParameter("codigoprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"codigoBusc\" value=\"");
      out.print((request.getParameter("codigoBusc")==null?"":request.getParameter("codigoBusc")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"fecModificacionrel\" value=\"");
      out.print((request.getParameter("fecModificacionprev")==null?"":request.getParameter("fecModificacionprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"fecModificacionBusc\" value=\"");
      out.print((request.getParameter("fecModificacionBusc")==null?"":request.getParameter("fecModificacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"descripcionrel\" value=\"");
      out.print((request.getParameter("descripcionprev")==null?"":request.getParameter("descripcionprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"descripcionBusc\" value=\"");
      out.print((request.getParameter("descripcionBusc")==null?"":request.getParameter("descripcionBusc")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"entornorel\" value=\"");
      out.print((request.getParameter("entornoprev")==null?"":request.getParameter("entornoprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"entornoBusc\" value=\"");
      out.print((request.getParameter("entornoBusc")==null?"":request.getParameter("entornoBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"tooltiprel\" value=\"");
      out.print((request.getParameter("tooltipprev")==null?"":request.getParameter("tooltipprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"descAdrel\" value=\"");
      out.print((request.getParameter("descAdprev")==null?"":request.getParameter("descAdprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"iconorel\" value=\"");
      out.print((request.getParameter("iconoprev")==null?"":request.getParameter("iconoprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"iconoBusc\" value=\"");
      out.print((request.getParameter("iconoBusc")==null?"":request.getParameter("iconoBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"autorizacionrel\" value=\"");
      out.print((request.getParameter("autorizacionprev")==null?"":request.getParameter("autorizacionprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"autorizacionBusc\" value=\"");
      out.print((request.getParameter("autorizacionBusc")==null?"":request.getParameter("autorizacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionrel\" value=\"");
      out.print((request.getParameter("tipoOperacionprev")==null?"":request.getParameter("tipoOperacionprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionBusc\" value=\"");
      out.print((request.getParameter("tipoOperacionBusc")==null?"":request.getParameter("tipoOperacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"aplicacionrel\" value=\"");
      out.print((request.getParameter("aplicacionprev")==null?"":request.getParameter("aplicacionprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"teclaMenurel\" value=\"");
      out.print((request.getParameter("teclaMenuprev")==null?"":request.getParameter("teclaMenuprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"pseudocodigo1rel\" value=\"");
      out.print((request.getParameter("pseudocodigo1prev")==null?"":request.getParameter("pseudocodigo1prev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"pseudocodigo2rel\" value=\"");
      out.print((request.getParameter("pseudocodigo2prev")==null?"":request.getParameter("pseudocodigo2prev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"atributosrel\" value=\"");
      out.print((request.getParameter("atributosprev")==null?"":request.getParameter("atributosprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"codCanalrel\" value=\"");
      out.print((request.getParameter("codCanalprev")==null?"":request.getParameter("codCanalprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionFinalrel\" value=\"");
      out.print((request.getParameter("tipoOperacionFinalprev")==null?"":request.getParameter("tipoOperacionFinalprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionFinalBusc\" value=\"");
      out.print((request.getParameter("tipoOperacionFinalBusc")==null?"":request.getParameter("tipoOperacionFinalBusc")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"parametro1rel\" value=\"");
      out.print((request.getParameter("parametro1prev")==null?"":request.getParameter("parametro1prev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"parametro1Busc\" value=\"");
      out.print((request.getParameter("parametro1Busc")==null?"":request.getParameter("parametro1Busc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"parametro2rel\" value=\"");
      out.print((request.getParameter("parametro2prev")==null?"":request.getParameter("parametro2prev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"parametro3rel\" value=\"");
      out.print((request.getParameter("parametro3prev")==null?"":request.getParameter("parametro3prev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"accprev\" value=\"");
      out.print((request.getParameter("accprevprev")==null?"":request.getParameter("accprevprev")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"opPosicion\" value=\"");
      out.print((request.getParameter("codigo")==null?"":request.getParameter("codigo")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"cod_escenario\" value=\"");
      out.print((request.getParameter("cod_escenario")==null?"":request.getParameter("cod_escenario")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"nombre_escenario\" value=\"");
      out.print((request.getParameter("nombre_escenario")==null?"":request.getParameter("nombre_escenario")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"aut_escenario\" value=\"");
      out.print((request.getParameter("aut_escenario")==null?"":request.getParameter("aut_escenario")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"icono_escenario\" value=\"");
      out.print((request.getParameter("icono_escenario")==null?"":request.getParameter("icono_escenario")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"orden_escenario\" value=\"");
      out.print((request.getParameter("orden_escenario")==null?"":request.getParameter("orden_escenario")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"ordenes_oprel\" value=\"\">\r\n");
      out.write("<input type=\"hidden\" name=\"datovalidar\" value=\"\">\r\n");
      out.write("<input type=\"hidden\" name=\"query\" value=\"");
      out.print((request.getParameter("query")==null)?"":request.getParameter("query"));
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("  \t");

  		String titulo=null;
  		if ("NUEVA".equals(accion)){
  			titulo = "Añadir";
  		} else if ("MODIFICAR".equals(accion)){
  			titulo = "Modificar";
  		} else if ("CANCELADO".equals(accion) && accprev != null && accprev.toString().length() > 0){
  			if ("NUEVA".equals(accprev)){
  				titulo = "Añadir";
  			} else if ("MODIFICAR".equals(accprev)){
  				titulo = "Modificar";
  			} else {
  				// Valor por defecto
  				titulo = "Modificar";
  			}
  		} else {
  				// Valor por defecto
  				titulo = "Añadir";
  		}
  	
      out.write("\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;OPERACIONES :: ");
      out.print(titulo);
      out.write(" </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <input type=\"hidden\" name=\"fecModificacion\" value =\"");
      out.print(request.getParameter("fecModificacion"));
      out.write("\">\r\n");
      out.write("  <input type=\"hidden\" name=\"fecModificacionIn\" value =\"");
      out.print(request.getParameter("fecModificacionIn")!=null?request.getParameter("fecModificacionIn"):request.getParameter("fecModificacion"));
      out.write("\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><fieldset>\r\n");
      out.write("    <legend>Operaci&oacute;n</legend>\r\n");
      out.write("        <table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>C&oacute;digo&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"codigo\" type=\"text\" maxlength=\"8\" class=\"CampoObligatorio\" size=\"9\" value=\"");
      out.print((data != null && data.length > 0 && data[0][0] != null && data[0][0].toString().length() > 0?data[0][0]:""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"descripcion\" type=\"text\" maxlength=\"40\" class=\"CampoObligatorio\" size=\"35\" value=\"");
      out.print((data != null && data.length > 0 && data[0][1] != null && data[0][1].toString().length() > 0?data[0][1]:""));
      out.write("\">\r\n");
      out.write("                    &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>Entorno&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                            <select size=\"1\" name=\"entorno\" class=\"CampoObligatorio\">\r\n");
      out.write("          <option value=\" \">Sin c&oacute;digo</option>\r\n");
      out.write("          ");

                	// Recupera los entornos de la sesión
                	Object[] entornos = (Object[])session.getValue("entornos");
                	Object dato = null;
                	if (!"NUEVA".equals(accion)){
                		dato = data[0][2];
                	}
                	if (entornos != null && entornos.length > 0){
                		String cadena = null;
                		for (int i=0;i<entornos.length;i++){
											cadena = entornos[i].toString().substring(0, 1);
                			if (dato == null){
                				out.print("<option value=\"" + cadena + "\"" + ((dato==cadena)?"selected":"") + ">" + entornos[i] + "</option>");
                			} else {
                				out.print("<option value=\"" + cadena + "\"" + ((dato.toString().equals(cadena))?"selected":"") + ">" + entornos[i] + "</option>");
                			}
                		}
                	}
                
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("                            </select>&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>Texto ayuda emergente&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"tooltip\" type=\"text\" maxlength=\"80\" class=\"CampoEntrada\" size=\"20\" value=\"");
      out.print((data != null && data.length > 0 && data[0][9] != null && data[0][9].toString().length() > 0?data[0][9]:""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("      breve<br>\r\n");
      out.write("      <input name=\"descAd\" type=\"text\" maxlength=\"25\" class=\"CampoEntrada\" size=\"15\" value=\"");
      out.print((data != null && data.length > 0 && data[0][5] != null && data[0][5].toString().length() > 0?data[0][5]:""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label></label></td>\r\n");
      out.write("                <td width=\"94%\" nowrap><label></label>\r\n");
      out.write("&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>                  Icono&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                \t<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                \t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<!--\t\t\t-->\r\n");
      out.write("\t\t\t\t\t\t<!-- <ETIQUETA> -->\r\n");
      out.write("                      <td><input name=\"icono\" type=\"text\" maxlength=\"30\" class=\"CampoSalida\" size=\"15\" value=\"");
      out.print((data != null && data.length > 0 && data[0][7] != null && data[0][7].toString().length() > 0?data[0][7]:""));
      out.write("\">&nbsp;<img name=\"imgicono\" src=\"images/");
      out.print((data != null && data.length > 0 && data[0][7] != null && data[0][7].toString().length() > 0?data[0][7]:"defecto"));
      out.write(".gif\" width=\"17\" height=\"17\"></td>\r\n");
      out.write("                      <td><img name=\"imagenIcono\" src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarIcono();\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                \t</table></td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label></label>\r\n");
      out.write("                  <label>                  </label>\r\n");
      out.write("                  C&oacute;digo autorizaci&oacute;n <br>\r\n");
      out.write("                  <table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                    <tr>\r\n");
      out.write("                      <td><input name=\"autorizacion\" type=\"text\" maxlength=\"8\" class=\"CampoSalida\" size=\"15\" value=\"");
      out.print((data != null && data.length > 0 && data[0][3] != null && data[0][3].toString().length() > 0?data[0][3]:""));
      out.write("\">&nbsp;</td>\r\n");
      out.write("                      <td><img src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarAutorizacion();\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                    </tr>\r\n");
      out.write("                  </table></td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>                  Tipo&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                            <select size=\"1\" name=\"tipoOperacion\" class=\"CampoObligatorio\" onChange=\"validaOp()\">\r\n");
      out.write("          <option value=\" \">Sin tipo</option>\r\n");
      out.write("          ");

                  	// Recupera los tipos de operación
                  	Object[][] datos = dao.getTiposOperacion();
                  	Object dat = null;
                  	String tipoOp = null;
                  	if("MODIFICAR".equals(accion)){
                  		if(data != null && data.length > 0 && data[0][4] != null){
                  			tipoOp=data[0][4].toString();
                  		} else {
                  			if (request.getParameter("ret") != null && "opRel".equals(request.getParameter("ret").toString())){
                  				tipoOp="F"; // Se vuelve de operaciones relacionadas, por lo que se trata de operaciones finales
                  			} else {
                  				tipoOp = "";
                  			}
                  		}
                  	} else {
                  		tipoOp=request.getParameter("tipoOperacion");
                  	}
                  	if (datos != null && datos.length > 0){
                  		for (int i=0;i<datos.length;i++){
                  			dat = datos[i][0];                			
							// 
							// <ETIQUETA>
							// Se comprueba si el canal es pesados para NO mostrar el tipo de operacion Etiqueta
							// Si es un dialogo tampoco hay q mostrar en la combo el tipo etiqueta xq se trata de la operacon "Colgar"
							if  ("Ligeros".equals(session.getValue("canal")) && "A".equals(datos[i][0]))
								continue;
                  			if (dat == null){
                  				out.print("<option value=\"" + datos[i][0] + "\"" + ((tipoOp==datos[i][0])?"selected":"") + ">" + datos[i][1] + "</option>");
                  			} else {
                  				out.print("<option value=\"" + datos[i][0] + "\"" + ((tipoOp.equals(datos[i][0]))?"selected":"") + ">" + datos[i][1] + "</option>");
                  			}
                  		}
                  	}
                  
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("        </select>\r\n");
      out.write("              &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"98%\" nowrap><label>Aplicaci&oacute;n/Instancia en la \r\n");
      out.write("                  que se ejecutar&aacute; &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"aplicacion\" ");
      out.print((session.getValue("canal").equals("Ligeros")?"class='CampoEntrada'":"disabled class='CampoSalida'"));
      out.write(" type=\"text\" maxlength=\"25\" class=\"CampoEntrada\" size=\"25\" value=\"");
      out.print((data != null && data.length > 0 && data[0][6] != null && data[0][6].toString().length() > 0 && !"null".equals(data[0][6])?data[0][6]:""));
      out.write("\">\r\n");
      out.write("              &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("              <tr>\r\n");
      out.write("                <td width=\"1%\" nowrap><label>Tecla de<br> \r\n");
      out.write("                acceso directo &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"teclaMenu\" type=\"text\" maxlength=\"1\" class=\"CampoEntrada\" size=\"1\" value=\"");
      out.print((data != null && data.length > 0 && data[0][8] != null && data[0][8].toString().length() > 0?data[0][8]:""));
      out.write("\">\r\n");
      out.write("              &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label><br>\r\n");
      out.write("                  Pseudo c&oacute;digos &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                    <input name=\"pseudocodigo1\" ");
      out.print((session.getValue("canal").equals("Pesados")?"enabled":"disabled"));
      out.write(" type=\"text\" maxlength=\"8\" class=\"CampoEntrada\" size=\"6\" value=\"");
      out.print((data != null && data.length > 0 && data[0][17] != null && data[0][17].toString().length() > 0?data[0][17]:""));
      out.write("\">&nbsp;<input name=\"pseudocodigo2\" type=\"text\" maxlength=\"8\" ");
      out.print((session.getValue("canal").equals("Pesados")?"enabled":"disabled"));
      out.write(" class=\"CampoEntrada\" size=\"6\" value=\"");
      out.print((data != null && data.length > 0 && data[0][18] != null && data[0][18].toString().length() > 0?data[0][18]:""));
      out.write("\">              &nbsp;</td>\r\n");
      out.write("                <td width=\"1%\" nowrap><label><br>\r\n");
      out.write("                Atributos&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                    <br>\r\n");
      out.write("                  <input name=\"atributos\" type=\"text\" maxlength=\"8\" class=\"CampoEntrada\" size=\"15\" value=\"");
      out.print((data != null && data.length > 0 && data[0][10] != null && data[0][10].toString().length() > 0?data[0][10]:""));
      out.write("\">\r\n");
      out.write("              &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td width=\"97%\" nowrap><label><br> \r\n");
      out.write("                  Canal de distribuci&oacute;n\r\n");
      out.write("&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                  <br>\r\n");
      out.write("                          <select size=\"1\" name=\"codCanal\" class=\"CampoEntrada\" onChange=\"actualiza();\">\r\n");
      out.write("          <option SELECTED value=1>Atención Presencial</option>\r\n");
      out.write("\t\t<option value=2>Autoservicio</option>\r\n");
      out.write("\t\t<option value=3>Call Center</option>\r\n");
      out.write("\t\t<option value=4>Banca a Distancia</option>\r\n");
      out.write("\t\t\r\n");
      out.write("        </select>&nbsp;<input name=\"codigoCanal\" disabled type=\"text\" class=\"CampoSalida\" value=\"1\" size=\"1\">\r\n");
      out.write("&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("              </tr>\r\n");
      out.write("            </table></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td><fieldset><legend>Operación final</legend><table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><label>Operaci&oacute;n final &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("      <br>\r\n");
      out.write("              <select size=\"1\" name=\"tipoOperacionFinal\" class=\"CampoEntrada\" disabled onChange=\"validaTipoOpFinal()\">\r\n");
      out.write("          ");

                	// Recupera los tipos de operaciones finales
                	dato = null;
                	String tipoOpFinal=null;
                	Object[][] tipos = dao.getTipoOperacionFinal();
                	if (tipos != null && tipos.length > 0){
                		if ("MODIFICAR".equals(accion)){
                			tipoOpFinal = data[0][11].toString();
                		} else {
                			tipoOpFinal = request.getParameter("tipoOperacionFinal");
                		}
                		for (int i=0;i<tipos.length;i++){
                			dato = tipos[i][0];
                			if (dato == null){
                				out.print("<option value=\"" + tipos[i][0] + "\"" + ((tipoOpFinal==tipos[i][0])?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + " obligatorios)]</option>");
                			} else {
                				out.print("<option value=\"" + tipos[i][0] + "\"" + ((tipoOpFinal.equals((tipos[i][0].toString())))?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + " obligatorios)]</option>");
                			}
                		}
                	}	
                
      out.write("\t\r\n");
      out.write("        </select></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><label>Par&aacute;metro 1 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("      <br>\r\n");
      out.write("      <input name=\"parametro1\" type=\"text\" class=\"CampoEntrada\" maxlength=\"200\" size=\"50\" disabled value=\"");
      out.print((data != null && data.length > 0 && data[0][12] != null && data[0][12].toString().length() > 0?data[0][12]:""));
      out.write("\">\r\n");
      out.write("      <input name=\"parametro11\" type=\"text\" class=\"CampoSalida\" size=\"75\" disabled>\r\n");
      out.write("      </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><label>Par&aacute;metro 2 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("      <br>\r\n");
      out.write("      <input name=\"parametro2\" type=\"text\" class=\"CampoEntrada\" maxlength=\"254\" size=\"50\" disabled value=\"");
      out.print((data != null && data.length > 0 && data[0][13] != null && data[0][13].toString().length() > 0?data[0][13]:""));
      out.write("\">\r\n");
      out.write("      <input name=\"parametro21\" type=\"text\" class=\"CampoSalida\" size=\"75\" disabled>\r\n");
      out.write("      </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><label>Par&aacute;metro 3 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("      <br>\r\n");
      out.write("      <input name=\"parametro3\" type=\"text\" class=\"CampoEntrada\" maxlength=\"254\" size=\"50\" disabled value=\"");
      out.print((data != null && data.length > 0 && data[0][14] != null && data[0][14].toString().length() > 0?data[0][14]:""));
      out.write("\">\r\n");
      out.write("      <input name=\"parametro31\" type=\"text\" class=\"CampoSalida\" size=\"75\" disabled>\r\n");
      out.write("      </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>Operaciones relacionadas </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">\r\n");
      out.write("      <tr class=\"CabeceraTabla\">\r\n");
      out.write("      \t<td width=\"1%\" valign=\"top\" class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("      \t<td width=\"5%\" class=\"CabeceraTabla\">Orden </td>\r\n");
      out.write("        <td width=\"7%\" class=\"CabeceraTabla\">C&oacute;digo </td>\r\n");
      out.write("        <td width=\"28%\" class=\"CabeceraTabla\">Nombre de Operaci&oacute;n </td>\r\n");
      out.write("        <td width=\"5%\" nowrap class=\"CabeceraTabla\">Entorno </td>\r\n");
      out.write("        <td width=\"5%\" nowrap class=\"CabeceraTabla\">Icono </td>\r\n");
      out.write("        <td width=\"5%\" nowrap class=\"CabeceraTabla\">Tipo Elem. </td>\r\n");
      out.write("        <td width=\"27%\" class=\"CabeceraTabla\">Par&aacute;metro 1 </td>\r\n");
      out.write("        <td width=\"7%\" nowrap class=\"CabeceraTabla\">Autorizaci&oacute;n</td>\r\n");
      out.write("        <td width=\"10%\" class=\"CabeceraTabla\">Fecha Modificaci&oacute;n</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      ");

    	// Recupera operaciones relacionadas
    	if (!"RELACIONAR".equals(accion)){
    		opRel = dao.recuperaOpCtx(request.getParameter("codigo"),session.getValue("entorno"));
    		if ("CANCELADO".equals(accion)){
    			if(opRel == null || opRel.length ==0){
    				opRel = (Object[][])session.getValue("opRelacionadas");
    			} else {
						opRel = (Object[][])session.getValue("opRelacionadas");
    			}
    		}
    	} else {
    		if (refresca != null){
	    		// Baja la información de la sesión
	    		opRel = (Object[][])session.getValue("opRelacionadas");
    		}
    	}
    	if(opRel != null && opRel.length > 0){
    		Object indice = request.getParameter("indice");
    		Object[][] opRelac = new Object[opRel.length][opRel[0].length];
    			
				for (int i=0;i<opRel.length;i++){
    			for (int j=0;j<opRel[i].length;j++){
    				opRelac[i][j] = opRel[i][j];
    			}
    		}
    		if (opRelac != null){
    			opRel = opRelac;
    		}
    		session.putValue("opRelacionadas",opRel);
    		int index = 2;
    		int orden = -1;
    		for (int i=0;i<opRel.length;i++){
    			if (index == 2){
    				index = 1;
    			} else if (index ==1){
    				index =2;
    			}
    			if (opRel[i].length > 8 && opRel[i][8]!=null){
    				//orden = Integer.parseInt(opRel[i][8].toString()) - 50000;
    				orden = Integer.parseInt(opRel[i][8].toString()) - 50000;
    			} else {
				if (i > 0 && opRel != null && opRel.length >= i && opRel[i-1].length > 8 && opRel[i-1][8] != null){
					orden = (Integer.parseInt(opRel[i-1][8] .toString()) - 50000) + 1;
					// Establece el valor en el array
					opRel[i][8] = "" + (orden + 50000);
				} else {
	    				orden = i+1;
				}
    			}
    			out.print("<tr class=\"Pijama" + index + "\">");
    			out.print("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type= \"radio\" onClick=\"seleccion('" + opRel[i][0] + "<sep>" + request.getParameter("codigo") + "<sep>" + opRel[i][7] + "<sep>" + i + "')\"></td>");
    			out.print("<td width=\"5%\"><input length=\"1\" name=\"texto\" type=\"text\" value=\"" + orden + "\"></td>");
       		out.print("<td class=\"TextoTablaN\">" + opRel[i][0] + "</td>");
       		out.print("<td width=\"10%\" class=\"TextoTablaN\">" + (opRel[i][1]==null?"":opRel[i][1]) + "</td>");
       		out.print("<td nowrap class=\"TextoTablaN\">" + opRel[i][2] + " </td>");
       		out.print("<td nowrap class=\"TextoTablaN\"><img src=\"images/" + opRel[i][3] + ".gif\" width=\"17\" height=\"17\"></td>");
       		out.print("<td nowrap class=\"TextoTablaN\">" + opRel[i][4] + " </td>");
       		out.print("<td class=\"TextoTablaN\">" + (opRel[i][5]==null||opRel[i][5].equals("null")?"":opRel[i][5]) + "</td>");
       		out.print("<td nowrap class=\"TextoTablaN\">" + (opRel[i][6]==null||opRel[i][6].equals("null")?"":opRel[i][6]) + "</td>");
       		out.print("<td nowrap class=\"TextoTablaN\">" + (opRel[i][7]==null||opRel[i][7].equals("null")?"":opRel[i][7].toString().substring(0,opRel[i][7].toString().indexOf(" "))) + " </td>");
    			out.print("</tr>");
    		}
    	} else {
    		out.print("<tr class=\"Pijama1\">");
    		out.print("<td colspan=\"8\" class=\"TextoTablaCenN\">NO SE HAN RELACIONADO OPERACIONES</td>");
    		out.print("</tr>");
    	}
      
      out.write("\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("  \t<td align=\"left\"><label>Fecha de modificaci&oacute;n &nbsp;&nbsp;&nbsp;</label><label>");
out.print((request.getParameter("fecModificacionIn")!=null && !"null".equals(request.getParameter("fecModificacionIn").toString()))?request.getParameter("fecModificacionIn"):((data != null && data.length > 19)?data[0][19]:""));
      out.write("</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button3\" value=\"Añadir\" class=\"Boton\" onclick=\"addOpRel();\" disabled>&nbsp;&nbsp;<input type=\"button\" name=\"Button31\" value=\"Suprimir\" class=\"Boton\" onclick=\"eliminarOpRel();\" disabled>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</fieldset></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </fieldset></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button\" value=\"Aceptar\" class=\"Boton\" onclick=\"aceptar();\" ");
      out.print(((accionRef!=null && "INICIAR".equals(accionRef) && destinoCancelar.indexOf("operacionesRelacionadas") != -1)?"disabled":"enabled"));
      out.write(' ');
      out.print(disabled);
      out.write(">\r\n");
      out.write("    <input type=\"button\" name=\"Button2\" value=\"Cancelar\" class=\"Boton\" onclick=\"cancelar();\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
