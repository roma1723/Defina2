<%@page import="java.util.*,java.text.*"%>
<html>
<head>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<style>
  body { margin: 0% 0% 0% 3%; background-color: #F2EDDF}
  a {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}
	a:link {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}
	a:visited {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}

</style>
<script>
top.frames['contenido'].location.href="contenido.html";
</script>
</head>
<%
	// Obligamos a recargar la página jsp para que no la coja de caché.
	Date ahora = new Date();
	String miFecha = "?dialog=false&newTime=" + ahora.getTime();
		
	// Guarda en la sesión tanto la base de datos a que se ha conectado, como el canal seleccionado
	String ver = request.getParameter("version");
	String can = request.getParameter("canal");
	String ent = request.getParameter("entorno");

	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Conexion a version: " + ver);
	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Canal activo: " + can);
	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Entorno activo: " + ent);

	session.putValue("version", ver);
	session.putValue("canal", can);
	session.putValue("entorno", ent);

	// Inicialización de la conexión a la base de datos para esta sesión
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	//out.println(atad.defina.entorno.Configuracion.getCfg("cfg.prefijo") + "ANAP" + ver + ".mdb");
	dao.setNombreOrigenDatos(atad.defina.entorno.Configuracion.getCfg("cfg.prefijo") + "ANAP" + ver + ".mdb");
	
	String codUsuario=session.getValue("codUsuario").toString();
	if (!"defecto".equals(codUsuario) && codUsuario != null && codUsuario.length() > 0){
		// Una vez establecido el acceso a los datos, se valida el permiso del usuario contra la base de datos seleccionada
		Object[][] codPerfil = dao.recuperaPerfil(session.getValue("codUsuario").toString());
		if (codPerfil != null && codPerfil.length > 0){
			// Sube a sesión el código de perfil del usuario
			String permiso=null;
			if ("adm".equals(codPerfil[0][0].toString())){
				permiso="0"; // Permisos de acceso totales ==> Perfil de administrador
			} else {
				permiso="1"; // Permisos de acceso sólo de consulta ==> Perfil de usuario
			}
			session.putValue("tipoAcceso",permiso);
		}
	} else {
		session.putValue("tipoAcceso","0");
	}
	
	// Evolución "Mis Accesos" NACAR 2.0
	// Comprueba si, en la combinación de Versión / Canal, existe la operación ficticia "MISACC" en la base de datos
	// Si no existe, la genera y deja todo preparado para poder trabajar con este tipo de agrupaciones. Si existe, no hace 
	// nada más con ella.
	if (!dao.isOpMisAccesos()){
		// La op. no existe, así que se da de alta
		dao.insertarOpMisAccesos(can);
	}
	// Evolución "Operaciones Fuera de Menú" NACAR 2.0
	// Comprueba si, en la combinación de Versión / Canal, existe la operación ficticia "OPFMENU" en la base de datos
	// Si no existe, la genera y deja todo preparado para poder trabajar con este tipo de agrupaciones. Si existe, no hace 
	// nada más con ella.
	if (!dao.isOpFueraMenu()){
		// La op. no existe, así que se da de alta
		dao.insertarOpFueraMenu(can);
	}
%>
<body>
<form name="formulario" action="">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.link")%>" target="contenido">Escenarios</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.operaciones.link") + miFecha%>" target="contenido">Operaciones</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.misAccesosArbol.link")%>" target="contenido">Mis Accesos</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.opFueraMenuArbol.link")%>" target="contenido">Op. No Menú SE</a></td>
  </tr>     
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")%>" target="contenido">Autorizaciones</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.jerarquia.link")%>" target="contenido">Jerarquia</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.simulacion.link")%>" target="contenido">Simulaci&oacuten escritorio</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.versiones.link")%>" target="contenido">Versiones</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.perfiles.link")%>" target="contenido">Perfiles</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.validar.link")%>" target="contenido">Validar</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.generar.link")%>" target="contenido">Generar</a></td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.promocion.link")%>" target="contenido">Promoci&oacuten</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td valign="middle"><img src="images/IIRGTreeItem_s.gif"><a href="<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.documentacion.link")%>" target="contenido">Descarga Documentaci&oacuten</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
