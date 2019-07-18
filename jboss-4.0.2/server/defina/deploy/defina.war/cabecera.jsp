<html>
<head>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<style>
   body { margin: 0% 0% 0% 3%; background-color: #F2EDDF}
</style>
<title>Defina - Iniciar sesi&oacute;n</title>
<%
	// obtención de un objeto de acceso a la base de datos de versiones
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);

	String[] canalesTecnicos = (String []) atad.defina.entorno.Configuracion.getCfg("cfg.canales");
	Object[] entornos = (String []) atad.defina.entorno.Configuracion.getCfg("cfg.entornos");

	Object[][] versiones = dao.getTablaVersiones();

	session.putValue("canales",canalesTecnicos);
	session.putValue("versiones",versiones);
	session.putValue("entornos", entornos);
%>
<script>
	function comenzar() {

		var strCanal = document.getElementById("canal").options[document.getElementById("canal").selectedIndex].text
		var strVersion = document.getElementById("version").options[document.getElementById("version").selectedIndex].text;
		var strEntorno = document.getElementById("entorno").options[document.getElementById("entorno").selectedIndex].text;

		var strMensaje = "Va a conectarse a:\n  Version: " + strVersion + "\n  Canal: " + strCanal + "\n  Entorno: " + strEntorno + "\n¿Desea continuar?";

		if (confirm(strMensaje)){
			top.location.href='logoff.jsp?canal='+document.getElementById("canal").value+'&version='+document.getElementById("version").value+'&entorno='+document.getElementById("entorno").value+'&mostrar=true';
		}
	}

  function mostrarFrame(){
  	var mostrar = <%=session.getValue("mostrar")%>;
  	if (mostrar != null && mostrar==true){
  		document.getElementById('version').value='<%=session.getValue("version")%>';
  		document.getElementById('canal').value='<%=session.getValue("canal")%>';
  		document.getElementById('entorno').value='<%=session.getValue("entorno")%>';
			validar();  		
  	}
  }
  
  function validar(){
  	var ver = document.getElementById('version');
		var can = document.getElementById('canal');
		var ent = document.getElementById('entorno');
		if (ver.value != 0 && can.value != 0 && ent.value != 0) {
  		var fr = document.getElementById('ifr1');
			fr.src = 'menu.jsp?version='+ver.value+"&canal="+can.value+"&entorno="+ent.value;
			document.getElementById('boton').value='Conectar';
			fr.style.visibility="visible";
			pulsado=true;
		} else {
			alert("Seleccione todas las opciones para iniciar la sesión de trabajo en defina");
		}
  }
</script>
</head>

<body onload="mostrarFrame();">

<form name="formulario" action="">
<input type="hidden" name="literal" value="Conectar">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="images/defina.jpg"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><select name="version" class="CampoEntrada" style="width:180px"">
      <option value=0 selected>Seleccione versi&oacute;n</option>
      <%
      	if (versiones != null && versiones.length > 0){
      		for (int i=0;i<versiones.length;i++){
      			out.print("<option value=\"" + versiones[i][0] + "\"" + (versiones[i][0].equals(request.getParameter("version"))?"selected":"") + ">" + versiones[i][1] + "</option>");
      		}
      	};
      %>
    </select></td>
  </tr>
  <tr>
    <td><img src="images/ftv2blank.gif" width="100" height="5"></td>
  </tr>
  <tr>
    <td><select name="canal" class="CampoEntrada" style="width:180px">
      <option value=0 selected>Seleccione canal</option>
      <%
      	if (canalesTecnicos != null && canalesTecnicos.length > 0){
      		for (int i=0;i<canalesTecnicos.length;i++){
      			out.print("<option value=\"" + canalesTecnicos[i] + "\"" + (canalesTecnicos[i].equals(request.getParameter("canal"))?"selected":"") + ">" + canalesTecnicos[i] + "</option>");
      		}
      	}
      %>
    </select></td>
  </tr>
  <tr>
    <td><img src="images/ftv2blank.gif" width="100" height="5"></td>
  </tr>
  <tr>
    <td><select name="entorno" class="CampoEntrada" style="width:180px">
      <option value=0 selected>Seleccione entorno</option>
      <%
      	if (entornos != null && entornos.length > 0){
      		for (int i=0;i<entornos.length;i++){
      			out.print("<option value=\"" + entornos[i].toString().substring(0,1) + "\"" + (entornos[i].toString().substring(0,1).equals(request.getParameter("entorno"))?"selected":"") + ">" + entornos[i] + "</option>");
      		}
      	}
      %>
  </tr>
  <tr>
    <td><img src="images/ftv2blank.gif" width="100" height="5"></td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="boton" value="Conectar" class="Boton" style="align:right" onClick="comenzar();"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<iframe id="ifr1" width=180 height=400 FRAMEBORDER="0" style="visibility:hidden" ></frame>
</form>
</body>
</html>
