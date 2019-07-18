<%! atad.defina.datos.AccesoDatosBase dao = null; Object[][] opRel = null; Object refresca=null; String accionRef = null;%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<script src="js/defina.js"></script>
<script language="JavaScript">
<%
	Object codPadre = request.getParameter("codigo");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
  if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  	disabled = "disabled=\"true\"";
  } else {
  	disabled ="";
  }
%>
var filaSel = null;

function seleccionarAutorizacion() {
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true"%>','','dialogWidth:600px;dialogHeight:680px');
	if (rc != null) document.getElementById("autorizacion").value = rc[0];
}

function seleccionarIcono() {
	//
	// <ETIQUETA>
	// Si es operacion de tipo Etiqueta no lleva asociado ningun icono, por lo q se "desabilita" el boton
    if ("A"==document.getElementById('tipoOperacion').value)
		return;
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true"%>','','dialogWidth:500px;dialogHeight:580px');
	if (rc != null){
		document.getElementById("icono").value = rc[0];
		document.getElementById("imgicono").src = "images/" + rc[0] + ".gif";
	}
}

function seleccion(str){
	filaSel = str.split("<sep>");
}
function actualiza(){
	document.getElementById("codigoCanal").value = document.getElementById("codCanal").value;
}
function addOpRel(){
	var continuar=true;
	if (document.getElementById("codigo").value==""){
		continuar=false;
	}
	if (continuar){
		var tmp = 'operacionesRelacionadas.jsp?accprev=<%=request.getParameter("accion")%>&accion=INICIAR&tipoOperacion=' + document.getElementById("tipoOperacion").value + '&tipoOperacionFinal=' + document.getElementById("tipoOperacionFinal").value;
		location.href = tmp + generaCadena();
	} else {
		alert("Antes de añadir operaciones relacionadas, debe especificar el código de la operación que está dando de alta.");
	}
}
function eliminarOpRel(){
	// Elimina la información de la base de datos
	var rc = confirm('Se va a eliminar la operación relacionada ' + filaSel[0]);
	if (rc) {
		<%
		// Sube a sesión la información relativa a las operaciones relacionadas
		Object acc = request.getParameter("accion");
		if (!"RELACIONAR".equals(acc)){
			session.putValue("opRelacionadas",opRel);
		}
		%>
		location.href="operacionDetalle.jsp?accion=RELACIONAR&operacion=<%=codPadre%>&indice=" + filaSel[3] + generaCadena() + "&refresca=true";
	}
}
<%
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
%>
function cancelar(){
	location.href='<%=destinoCancelar%>' + generaCadenaCancelar();
}
function obtener(){
	var textos = document.all.texto;
	var valor = "";
	if (textos !=null){
		if(textos.length > 1){
			for (var i=0;i<textos.length;i++){
				// Antes de añadir, se comprueba si el valor se contiene en la cadena cuando hay más de una operación relacionada
				for (var j = 0;j<textos.length;j++){
					// No se comprueba consigo mismo
					if (j != i){
						if (textos[i].value == textos[j].value){
							// Elimina la información que exista en el campo
							document.getElementById("ordenes_oprel").value="";
							return false;
						}
					}
				}
				valor = valor + (textos[i].value) + "<sep>";
			}
			document.getElementById("ordenes_oprel").value=valor;
		} else {
			document.getElementById("ordenes_oprel").value=textos.value + "<sep>";
		}
	}
}
function cambiarSalida(){
	globalSalida = true;
	alert("globalSalida=" + globalSalida);
}
function aceptar() {
	// Establece el control para los campos obligatorios: código, nombre, entorno y tipo
	var strCadenaCodigo = document.getElementById("codigo").value.substring(0,2).toUpperCase();
	continuar=true;
	if (document.getElementById("codigo").value==""){
		alert('El campo código es requerido.');
		continuar=false;
	}
	//else{
	//	var bOperacionAccion =<%="MODIFICAR".equals(request.getParameter("accion"))%>;
	//	if (!bOperacionAccion){
	//		if ((strCadenaCodigo == "AD") || (strCadenaCodigo == "ON") || (strCadenaCodigo == "MN")){
	//			alert("El código de la operación no puede empezar por las letras '" + strCadenaCodigo + "'");
	//			continuar=false;
	//		}
	//	}
	//}
	if (document.getElementById("descripcion").value==""){
		alert('El campo nombre es requerido.');
		continuar=false;
	}
	if (document.getElementById("entorno").value=="" || document.getElementById("entorno").value==" "){
		alert('El campo entorno es requerido.');
		continuar=false;
	}
	if (document.getElementById("tipoOperacion").value=="" || document.getElementById("tipoOperacion").value==" "){
		alert('El campo tipo de operación es requerido.');
		continuar=false;
	}else{
		if (document.getElementById("tipoOperacion").value == "O"){
			var strCodigo = document.getElementById("codigo").value.substring(0,3).toUpperCase();
			if (strCodigo != "OBJ"){
				alert("Los objetos de negocio deben empezar por OBJ");
				continuar = false;
			 }
			 if ((continuar == true) &&  (document.getElementById("tipoOperacionFinal").value != "1")){
			 	alert("Un Objeto de Negocio solo puede tener como operacion final\nuna 'Ejecucion de Flujo NACAR'.");
			 	continuar = false;
			 }
		}
	}
	
	var numParamsReq=0;
	// Considera la seleccion en la combo de tipoOperacionFinal si esta habilitado
	if (!document.getElementById("tipoOperacionFinal").disabled && continuar == true){
		// Toma el valor seleccionado
		var dato = document.getElementById("tipoOperacionFinal").value;
		// Considera el valor recuperado para validar las operaciones
		switch(dato){
			case "0" :
			case "8" :{
				numParamsReq=0;
				break;
			}
			case "1" :
			case "3" :
			case "A" :
			case "E" :
			// Ampliación Filtrado
			case "F" :
			case "U" :{
				numParamsReq=1;
				break;
			}
			case "7" :
			case "C" :{
				numParamsReq=2;
				break;
			}
			case "6" :{
				numParamsReq=3;
				break;
			}
		}
	}
	else{
		if (document.getElementById("tipoOperacionFinal").value=="") document.getElementById("tipoOperacionFinal").value = 0;
	}
	if (numParamsReq==1){
		if (document.getElementById("parametro1").value==""){
			alert("Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado");
			continuar=false;
		}
	} else if (numParamsReq==2){
		if (document.getElementById("parametro1").value==""){
			alert("Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado");
			continuar=false;
		}
		if (document.getElementById("parametro2").value==""){
			alert("Es necesario informar el campo paramétro 2, para el tipo de operación seleccionado");
			continuar=false;
		}
	} else if (numParamsReq==3){
		if (document.getElementById("parametro1").value==""){
			alert("Es necesario informar el campo paramétro 1, para el tipo de operación seleccionado");
			continuar=false;
		}
		if (document.getElementById("parametro2").value==""){
			alert("Es necesario informar el campo paramétro 2, para el tipo de operación seleccionado");
			continuar=false;
		}
		if (document.getElementById("parametro3").value==""){
			alert("Es necesario informar el campo paramétro 3, para el tipo de operación seleccionado");
			continuar=false;
		}
	}

	//
	// <ETIQUETA>		
	// Comprueba cual es la pagina que solicita el detalle de la operacion
	// Si la pagina anterior es: operacionArbol o escenariosArbol --> significa q voy a editar una operacion q cuelga de
	// un escenario o menu. En este caso, no puedo permitir q se modifique el tipo de esta operacion a Etiqueta
	var referencia = '<%= request.getParameter("referrer") %>';
	
	// Si quiero modificar una operacion a tipo Etiqueta q esta colgada de un menu --> no lo permito	
	if (referencia.indexOf("operacionArbol")>0 && "A"==document.getElementById("tipoOperacion").value){
		alert("Accion no permitida: No se puede colgar una operacion de tipo Etiqueta de un menu");
		continuar=false;
	} else if (referencia.indexOf("escenariosArbol")>0 && "A"==document.getElementById("tipoOperacion").value) {
		alert ("Accion no permitida: No se puede colgar una operacion de tipo Etiqueta de un escenario");
		continuar=false;
	} 	

	
	// En función de las validaciones anteriores, se decide continuar o no
	if (continuar){
		// Recupera la información de los órdenes de las operaciones relacionadas y las prepara
		var r = obtener();
		if (r == false){
			// Existen datos con el mismo orden
			alert("Existen operaciones relacionadas con el mismo orden. Por favor, modifique el orden de forma que no coincidan.");
			return false;
		}
		// Efectúa la petición AJAX para almacenar la información en la base de datos
		<%
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
		%>
	}
}
function generaCadena(){
	var tmp = '&codigo=' + document.getElementById("codigo").value;
	tmp = tmp + '&descripcion=' + document.getElementById("descripcion").value;
	tmp = tmp + '&entorno=' + document.getElementById("entorno").value;
	tmp = tmp + '&tooltip=' + document.getElementById("tooltip").value;
	tmp = tmp + '&descAd=' + document.getElementById("descAd").value;
	tmp = tmp + '&icono=' + document.getElementById("icono").value;
	tmp = tmp + '&autorizacion=' + document.getElementById("autorizacion").value;
	tmp = tmp + '&tipoOperacion=' + document.getElementById("tipoOperacion").value;
	tmp = tmp + '&aplicacion=' + document.getElementById("aplicacion").value;
	tmp = tmp + '&teclaMenu=' + document.getElementById("teclaMenu").value;
	tmp = tmp + '&pseudocodigo1=' + document.getElementById("pseudocodigo1").value;
	tmp = tmp + '&pseudocodigo2=' + document.getElementById("pseudocodigo2").value;
	tmp = tmp + '&atributos=' + document.getElementById("atributos").value;
	tmp = tmp + '&codCanal=' + document.getElementById("codigoCanal").value;
	tmp = tmp + '&tipoOperacionFinal=' + document.getElementById("tipoOperacionFinal").value;
	tmp = tmp + '&parametro1=' + document.getElementById("parametro1").value;
	tmp = tmp + '&parametro2=' + document.getElementById("parametro2").value;
	tmp = tmp + '&parametro3=' + document.getElementById("parametro3").value;
	tmp = tmp + '&fecModificacion=' + document.getElementById("fecModificacion").value;
	tmp = tmp + '&fecModificacionIn=' + document.getElementById("fecModificacionIn").value;
	// Incluye la información de la que query
	tmp = tmp + '&query=' + document.getElementById("query").value;	
	return tmp;
}
function generaCadenaCancelar(){
	var tmp = '&codigo=' + document.getElementById("codigorel").value;
	tmp = tmp + '&codigoBusc=' + document.getElementById("codigoBusc").value;
	tmp = tmp + '&descripcion=' + document.getElementById("descripcionrel").value;
	tmp = tmp + '&descripcionBusc=' + document.getElementById("descripcionBusc").value;
	tmp = tmp + '&entorno=' + document.getElementById("entornorel").value;
	tmp = tmp + '&entornoBusc=' + document.getElementById("entornoBusc").value;
	tmp = tmp + '&tooltip=' + document.getElementById("tooltiprel").value;
	tmp = tmp + '&descAd=' + document.getElementById("descAdrel").value;
	tmp = tmp + '&icono=' + document.getElementById("iconorel").value;
	tmp = tmp + '&iconoBusc=' + document.getElementById("iconoBusc").value;
	tmp = tmp + '&autorizacion=' + document.getElementById("autorizacionrel").value;
	tmp = tmp + '&autorizacionBusc=' + document.getElementById("autorizacionBusc").value;
	tmp = tmp + '&tipoOperacion=' + document.getElementById("tipoOperacionrel").value;
	tmp = tmp + '&tipoOperacionBusc=' + document.getElementById("tipoOperacionBusc").value;
	tmp = tmp + '&aplicacion=' + document.getElementById("aplicacionrel").value;
	tmp = tmp + '&teclaMenu=' + document.getElementById("teclaMenurel").value;
	tmp = tmp + '&pseudocodigo1=' + document.getElementById("pseudocodigo1rel").value;
	tmp = tmp + '&pseudocodigo2=' + document.getElementById("pseudocodigo2rel").value;
	tmp = tmp + '&atributos=' + document.getElementById("atributosrel").value;
	tmp = tmp + '&codCanal=' + document.getElementById("codCanalrel").value;
	tmp = tmp + '&tipoOperacionFinal=' + document.getElementById("tipoOperacionFinalrel").value;
	tmp = tmp + '&parametro1=' + document.getElementById("parametro1rel").value;
	tmp = tmp + '&parametro1Busc=' + document.getElementById("parametro1Busc").value;
	tmp = tmp + '&parametro2=' + document.getElementById("parametro2rel").value;
	tmp = tmp + '&parametro3=' + document.getElementById("parametro3rel").value;
	tmp = tmp + '&fecModificacion=' + document.getElementById("fecModificacionrel").value;
	tmp = tmp + '&fecModificacionBusc=' + document.getElementById("fecModificacionBusc").value;
	tmp = tmp + '&fecModificacionIn=' + document.getElementById("fecModificacionIn").value;
	// Incluye la operación para posicionarse sobre ella
	tmp = tmp + '&opPosicion=' + document.getElementById("opPosicion").value;
	tmp = tmp + '&codigoEsc=' + document.getElementById("cod_escenario").value;
	tmp = tmp + '&nombreEsc=' + document.getElementById("nombre_escenario").value;
	tmp = tmp + '&autorizacionEsc=' + document.getElementById("aut_escenario").value;
	tmp = tmp + '&iconoEsc=' + document.getElementById("icono_escenario").value;
	tmp = tmp + '&ordenEsc=' + document.getElementById("orden_escenario").value;
	// Incluye la información de la que query
	tmp = tmp + '&query=' + document.getElementById("query").value;
	return tmp;
}
function validaOp(){
  // Para el caso de operaciones finales u Objetos de Negocio
	if("F"==document.getElementById('tipoOperacion').value || "O"==document.getElementById('tipoOperacion').value || "M"==document.getElementById('tipoOperacion').value){
		// Habilita los campos para la operación final
		document.getElementById('tipoOperacionFinal').disabled=false; // combo
		document.getElementById('tipoOperacionFinal').className="CampoObligatorio";
		document.getElementById('parametro1').disabled=false;
		document.getElementById('parametro1').className="CampoEntrada";
		document.getElementById('parametro2').disabled=false;
		document.getElementById('parametro2').className="CampoEntrada";
		document.getElementById('parametro3').disabled=false;
		document.getElementById('parametro3').className="CampoEntrada";
		if ("Pesados" == '<%=session.getValue("canal")%>' && "" == '<%=disabled%>' && "F" == document.getElementById('tipoOperacion').value){
			document.getElementById('Button3').disabled=false;
			document.getElementById('Button31').disabled=false;
		}
		//
		// <ETIQUETA>
		// Si se cambia el tipo de la operacion de etiqueta a final, hay que habilitar los campos correspondientes q se habian
		// deshabilitado para etiquetas		
		document.getElementById('tooltip').disabled=false;		
		document.getElementById('tooltip').className="CampoEntrada";	
		document.getElementById('teclaMenu').disabled=false;		
		document.getElementById('teclaMenu').className="CampoEntrada";					
		document.getElementById('icono').disabled=false; 
		document.getElementById('imagenIcono').src="images/BotonPrismatico.gif";  		
		document.getElementById('pseudocodigo1').disabled=false;		
		document.getElementById('pseudocodigo1').className="CampoEntrada";			
		document.getElementById('pseudocodigo2').disabled=false;		
		document.getElementById('pseudocodigo2').className="CampoEntrada";	
		document.getElementById('atributos').disabled=false;		
		document.getElementById('atributos').className="CampoEntrada";
		document.getElementById('imgicono').width="17";
	// 
	// <ETIQUETA>
	// Habilita/deshabilita los campos para las operaciones de tipo etiqueta
	} else if ("A"==document.getElementById('tipoOperacion').value) {
		// Habilita los campos para la operación final	
		document.getElementById('tooltip').disabled=true;
		document.getElementById('tooltip').value=""; 
		document.getElementById('tooltip').className="CampoSalida";	
		document.getElementById('teclaMenu').disabled=true;
		document.getElementById('teclaMenu').value=""; 
		document.getElementById('teclaMenu').className="CampoSalida";			
		document.getElementById('icono').value=""; 
		document.getElementById('icono').disabled=true; 
		document.getElementById('imagenIcono').src="images/BotonPrismaticoD.gif";
		// Se quita la previsualizacion del icono
		document.getElementById('imgicono').width="0";
  		document.getElementById('pseudocodigo1').disabled=true;
		document.getElementById('pseudocodigo1').value=""; 
		document.getElementById('pseudocodigo1').className="CampoSalida";	
		document.getElementById('pseudocodigo2').disabled=true;
		document.getElementById('pseudocodigo2').value=""; 
		document.getElementById('pseudocodigo2').className="CampoSalida";	
		document.getElementById('atributos').disabled=true;
		document.getElementById('atributos').value=""; 
		document.getElementById('atributos').className="CampoSalida";					

		document.getElementById('tipoOperacionFinal').disabled=true; // combo
		document.getElementById('tipoOperacionFinal').value=""; // combo
		document.getElementById('tipoOperacionFinal').className="CampoSalida";
		document.getElementById('parametro1').disabled=true;
		document.getElementById('parametro1').value="";
		document.getElementById('parametro11').value="";
		document.getElementById('parametro1').className="CampoSalida";
		document.getElementById('parametro2').disabled=true;
		document.getElementById('parametro2').value="";
		document.getElementById('parametro21').value="";
		document.getElementById('parametro2').className="CampoSalida";
		document.getElementById('parametro3').disabled=true;
		document.getElementById('parametro3').value="";
		document.getElementById('parametro31').value="";
		document.getElementById('parametro3').className="CampoSalida";
		document.getElementById('Button3').disabled=true;
		document.getElementById('Button31').disabled=true;
	} else {
	  // Vacia y elimina el contenido del texto
	  document.getElementById('tipoOperacionFinal').disabled=true; // combo
	  document.getElementById('tipoOperacionFinal').value=""; // combo
	  document.getElementById('tipoOperacionFinal').className="CampoSalida";
		document.getElementById('parametro1').disabled=true;
		document.getElementById('parametro1').value="";
		document.getElementById('parametro11').value="";
		document.getElementById('parametro1').className="CampoSalida";
		document.getElementById('parametro2').disabled=true;
		document.getElementById('parametro2').value="";
		document.getElementById('parametro21').value="";
		document.getElementById('parametro2').className="CampoSalida";
		document.getElementById('parametro3').disabled=true;
		document.getElementById('parametro3').value="";
		document.getElementById('parametro31').value="";
		document.getElementById('parametro3').className="CampoSalida";
		document.getElementById('Button3').disabled=true;
		document.getElementById('Button31').disabled=true;
		//
		// <ETIQUETA>
		// Si se cambia el tipo de la operacion de etiqueta a final, hay que habilitar los campos correspondientes q se habian
		// deshabilitado para etiquetas		
		document.getElementById('tooltip').disabled=false;		
		document.getElementById('tooltip').className="CampoEntrada";	
		document.getElementById('teclaMenu').disabled=false;		
		document.getElementById('teclaMenu').className="CampoEntrada";					
		document.getElementById('icono').disabled=false; 
		document.getElementById('imagenIcono').src="images/BotonPrismatico.gif";  		
		document.getElementById('pseudocodigo1').disabled=false;		
		document.getElementById('pseudocodigo1').className="CampoEntrada";			
		document.getElementById('pseudocodigo2').disabled=false;		
		document.getElementById('pseudocodigo2').className="CampoEntrada";	
		document.getElementById('atributos').disabled=false;		
		document.getElementById('atributos').className="CampoEntrada";
		document.getElementById('imgicono').width="17";
	}
}
function validaTipoOpFinal(){
	// Toma el valor seleccionado
	var dato = document.getElementById("tipoOperacionFinal").value;
	// Considera el valor recuperado para validar las operaciones
	switch(dato){
		case "0" :
		case "8" :{
			document.getElementById('parametro11').value="";
			document.getElementById('parametro1').className="CampoEntrada";
			document.getElementById('parametro21').value="";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}	
		case "1" :{
			document.getElementById('parametro11').value="Código de Flujo NACAR";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="True (Defecto) si es monoinstancia o False si no";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "3" :{
			document.getElementById('parametro11').value='Mandato (normalmente un cmd) a ejecutar, indicar path con "/" si es preciso';
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "A" :{
			document.getElementById('parametro11').value="Parámetro 1";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="Parámetro 2";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="Parámetro 3";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		// Ampliación Filtrado
		case "F" :{
			document.getElementById('parametro11').value="Operación NACAR de filtrado (Debe ser de tipo flujo NACAR)";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="True (defecto) si es monoinstancia o False si no";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "E" :{
			document.getElementById('parametro11').value="Código de Flujo NACAR";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="True (defecto) si es monoinstancia o False si no";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "U" :{
			document.getElementById('parametro11').value="Url a lanzar";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="True (defecto) si es monoinstancia o False si no";
			document.getElementById('parametro2').className="CampoEntrada";
			document.getElementById('parametro31').value="3 para que abra una ventana nueva, URL de desconexión (opcional)";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "6" :{
			document.getElementById('parametro11').value="Nombre del runtime TFM";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="Código de aplicación";
			document.getElementById('parametro2').className="CampoObligatorio";
			document.getElementById('parametro31').value="Descripción de la aplicación";
			document.getElementById('parametro3').className="CampoObligatorio";
			break;
		}
		case "7" :{
			document.getElementById('parametro11').value="Nombre Clase";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="Nombre Método";
			document.getElementById('parametro2').className="CampoObligatorio";
			document.getElementById('parametro31').value="Parámetros separados por comas";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
		case "C" :{
			document.getElementById('parametro11').value="Código de Flujo NACAR";
			document.getElementById('parametro1').className="CampoObligatorio";
			document.getElementById('parametro21').value="True (Defecto) si es monoinstancia o False si no";
			document.getElementById('parametro2').className="CampoObligatorio";
			document.getElementById('parametro31').value="Código de operación relacionada o en blanco si es la primaria";
			document.getElementById('parametro3').className="CampoEntrada";
			break;
		}
	}
}
</script>
</head>
<%
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
%>
<body onLoad="validaOp();validaTipoOpFinal();">
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>

<input type="hidden" name="codigorel" value="<%=(request.getParameter("codigoprev")==null?"":request.getParameter("codigoprev"))%>">
<input type="hidden" name="codigoBusc" value="<%=(request.getParameter("codigoBusc")==null?"":request.getParameter("codigoBusc"))%>">
<input type="hidden" name="fecModificacionrel" value="<%=(request.getParameter("fecModificacionprev")==null?"":request.getParameter("fecModificacionprev"))%>">
<input type="hidden" name="fecModificacionBusc" value="<%=(request.getParameter("fecModificacionBusc")==null?"":request.getParameter("fecModificacionBusc"))%>">	
<input type="hidden" name="descripcionrel" value="<%=(request.getParameter("descripcionprev")==null?"":request.getParameter("descripcionprev"))%>">
<input type="hidden" name="descripcionBusc" value="<%=(request.getParameter("descripcionBusc")==null?"":request.getParameter("descripcionBusc"))%>">
<input type="hidden" name="entornorel" value="<%=(request.getParameter("entornoprev")==null?"":request.getParameter("entornoprev"))%>">
<input type="hidden" name="entornoBusc" value="<%=(request.getParameter("entornoBusc")==null?"":request.getParameter("entornoBusc"))%>">	
<input type="hidden" name="tooltiprel" value="<%=(request.getParameter("tooltipprev")==null?"":request.getParameter("tooltipprev"))%>">
<input type="hidden" name="descAdrel" value="<%=(request.getParameter("descAdprev")==null?"":request.getParameter("descAdprev"))%>">
<input type="hidden" name="iconorel" value="<%=(request.getParameter("iconoprev")==null?"":request.getParameter("iconoprev"))%>">
<input type="hidden" name="iconoBusc" value="<%=(request.getParameter("iconoBusc")==null?"":request.getParameter("iconoBusc"))%>">	
<input type="hidden" name="autorizacionrel" value="<%=(request.getParameter("autorizacionprev")==null?"":request.getParameter("autorizacionprev"))%>">
<input type="hidden" name="autorizacionBusc" value="<%=(request.getParameter("autorizacionBusc")==null?"":request.getParameter("autorizacionBusc"))%>">	
<input type="hidden" name="tipoOperacionrel" value="<%=(request.getParameter("tipoOperacionprev")==null?"":request.getParameter("tipoOperacionprev"))%>">
<input type="hidden" name="tipoOperacionBusc" value="<%=(request.getParameter("tipoOperacionBusc")==null?"":request.getParameter("tipoOperacionBusc"))%>">	
<input type="hidden" name="aplicacionrel" value="<%=(request.getParameter("aplicacionprev")==null?"":request.getParameter("aplicacionprev"))%>">
<input type="hidden" name="teclaMenurel" value="<%=(request.getParameter("teclaMenuprev")==null?"":request.getParameter("teclaMenuprev"))%>">
<input type="hidden" name="pseudocodigo1rel" value="<%=(request.getParameter("pseudocodigo1prev")==null?"":request.getParameter("pseudocodigo1prev"))%>">
<input type="hidden" name="pseudocodigo2rel" value="<%=(request.getParameter("pseudocodigo2prev")==null?"":request.getParameter("pseudocodigo2prev"))%>">
<input type="hidden" name="atributosrel" value="<%=(request.getParameter("atributosprev")==null?"":request.getParameter("atributosprev"))%>">
<input type="hidden" name="codCanalrel" value="<%=(request.getParameter("codCanalprev")==null?"":request.getParameter("codCanalprev"))%>">
<input type="hidden" name="tipoOperacionFinalrel" value="<%=(request.getParameter("tipoOperacionFinalprev")==null?"":request.getParameter("tipoOperacionFinalprev"))%>">
<input type="hidden" name="tipoOperacionFinalBusc" value="<%=(request.getParameter("tipoOperacionFinalBusc")==null?"":request.getParameter("tipoOperacionFinalBusc"))%>">
<input type="hidden" name="parametro1rel" value="<%=(request.getParameter("parametro1prev")==null?"":request.getParameter("parametro1prev"))%>">
<input type="hidden" name="parametro1Busc" value="<%=(request.getParameter("parametro1Busc")==null?"":request.getParameter("parametro1Busc"))%>">	
<input type="hidden" name="parametro2rel" value="<%=(request.getParameter("parametro2prev")==null?"":request.getParameter("parametro2prev"))%>">
<input type="hidden" name="parametro3rel" value="<%=(request.getParameter("parametro3prev")==null?"":request.getParameter("parametro3prev"))%>">
<input type="hidden" name="accprev" value="<%=(request.getParameter("accprevprev")==null?"":request.getParameter("accprevprev"))%>">
<input type="hidden" name="opPosicion" value="<%=(request.getParameter("codigo")==null?"":request.getParameter("codigo"))%>">
<input type="hidden" name="cod_escenario" value="<%=(request.getParameter("cod_escenario")==null?"":request.getParameter("cod_escenario"))%>">
<input type="hidden" name="nombre_escenario" value="<%=(request.getParameter("nombre_escenario")==null?"":request.getParameter("nombre_escenario"))%>">
<input type="hidden" name="aut_escenario" value="<%=(request.getParameter("aut_escenario")==null?"":request.getParameter("aut_escenario"))%>">
<input type="hidden" name="icono_escenario" value="<%=(request.getParameter("icono_escenario")==null?"":request.getParameter("icono_escenario"))%>">
<input type="hidden" name="orden_escenario" value="<%=(request.getParameter("orden_escenario")==null?"":request.getParameter("orden_escenario"))%>">
<input type="hidden" name="ordenes_oprel" value="">
<input type="hidden" name="datovalidar" value="">
<input type="hidden" name="query" value="<%=(request.getParameter("query")==null)?"":request.getParameter("query")%>">

<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
  	<%
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
  	%>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;OPERACIONES :: <%=titulo%> </td>
  </tr>
  <input type="hidden" name="fecModificacion" value ="<%=request.getParameter("fecModificacion")%>">
  <input type="hidden" name="fecModificacionIn" value ="<%=request.getParameter("fecModificacionIn")!=null?request.getParameter("fecModificacionIn"):request.getParameter("fecModificacion")%>">
  <tr>
    <td><fieldset>
    <legend>Operaci&oacute;n</legend>
        <table width="100%"  border="0" cellspacing="8" cellpadding="0">
          <tr>
            <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="1%" nowrap><label>C&oacute;digo&nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="codigo" type="text" maxlength="8" class="CampoObligatorio" size="9" value="<%=(data != null && data.length > 0 && data[0][0] != null && data[0][0].toString().length() > 0?data[0][0]:"")%>">&nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="descripcion" type="text" maxlength="40" class="CampoObligatorio" size="35" value="<%=(data != null && data.length > 0 && data[0][1] != null && data[0][1].toString().length() > 0?data[0][1]:"")%>">
                    &nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label>Entorno&nbsp;&nbsp;&nbsp;</label>
                    <br>
                            <select size="1" name="entorno" class="CampoObligatorio">
          <option value=" ">Sin c&oacute;digo</option>
          <%
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
                %>
		
                            </select>&nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label>Texto ayuda emergente&nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="tooltip" type="text" maxlength="80" class="CampoEntrada" size="20" value="<%=(data != null && data.length > 0 && data[0][9] != null && data[0][9].toString().length() > 0?data[0][9]:"")%>">&nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>
      breve<br>
      <input name="descAd" type="text" maxlength="25" class="CampoEntrada" size="15" value="<%=(data != null && data.length > 0 && data[0][5] != null && data[0][5].toString().length() > 0?data[0][5]:"")%>">&nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label></label></td>
                <td width="94%" nowrap><label></label>
&nbsp;&nbsp;&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="1%" nowrap><label>                  Icono&nbsp;&nbsp;&nbsp;</label>
                	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                		<tr>
						<!--			-->
						<!-- <ETIQUETA> -->
                      <td><input name="icono" type="text" maxlength="30" class="CampoSalida" size="15" value="<%=(data != null && data.length > 0 && data[0][7] != null && data[0][7].toString().length() > 0?data[0][7]:"")%>">&nbsp;<img name="imgicono" src="images/<%=(data != null && data.length > 0 && data[0][7] != null && data[0][7].toString().length() > 0?data[0][7]:"defecto")%>.gif" width="17" height="17"></td>
                      <td><img name="imagenIcono" src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarIcono();">&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                	</table></td>
                <td width="1%" nowrap><label></label>
                  <label>                  </label>
                  C&oacute;digo autorizaci&oacute;n <br>
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><input name="autorizacion" type="text" maxlength="8" class="CampoSalida" size="15" value="<%=(data != null && data.length > 0 && data[0][3] != null && data[0][3].toString().length() > 0?data[0][3]:"")%>">&nbsp;</td>
                      <td><img src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarAutorizacion();">&nbsp;&nbsp;&nbsp;</td>
                    </tr>
                  </table></td>
                <td width="1%" nowrap><label>                  Tipo&nbsp;&nbsp;&nbsp;</label>
                    <br>
                            <select size="1" name="tipoOperacion" class="CampoObligatorio" onChange="validaOp()">
          <option value=" ">Sin tipo</option>
          <%
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
                  %>
		
        </select>
              &nbsp;&nbsp;&nbsp;</td>
                <td width="98%" nowrap><label>Aplicaci&oacute;n/Instancia en la 
                  que se ejecutar&aacute; &nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="aplicacion" <%=(session.getValue("canal").equals("Ligeros")?"class='CampoEntrada'":"disabled class='CampoSalida'")%> type="text" maxlength="25" class="CampoEntrada" size="25" value="<%=(data != null && data.length > 0 && data[0][6] != null && data[0][6].toString().length() > 0 && !"null".equals(data[0][6])?data[0][6]:"")%>">
              &nbsp;&nbsp;&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="1%" nowrap><label>Tecla de<br> 
                acceso directo &nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="teclaMenu" type="text" maxlength="1" class="CampoEntrada" size="1" value="<%=(data != null && data.length > 0 && data[0][8] != null && data[0][8].toString().length() > 0?data[0][8]:"")%>">
              &nbsp;&nbsp;&nbsp;</td>
                <td width="1%" nowrap><label><br>
                  Pseudo c&oacute;digos &nbsp;&nbsp;&nbsp;</label>
                    <br>
                    <input name="pseudocodigo1" <%=(session.getValue("canal").equals("Pesados")?"enabled":"disabled")%> type="text" maxlength="8" class="CampoEntrada" size="6" value="<%=(data != null && data.length > 0 && data[0][17] != null && data[0][17].toString().length() > 0?data[0][17]:"")%>">&nbsp;<input name="pseudocodigo2" type="text" maxlength="8" <%=(session.getValue("canal").equals("Pesados")?"enabled":"disabled")%> class="CampoEntrada" size="6" value="<%=(data != null && data.length > 0 && data[0][18] != null && data[0][18].toString().length() > 0?data[0][18]:"")%>">              &nbsp;</td>
                <td width="1%" nowrap><label><br>
                Atributos&nbsp;&nbsp;&nbsp;</label>
                    <br>
                  <input name="atributos" type="text" maxlength="8" class="CampoEntrada" size="15" value="<%=(data != null && data.length > 0 && data[0][10] != null && data[0][10].toString().length() > 0?data[0][10]:"")%>">
              &nbsp;&nbsp;&nbsp;</td>
                <td width="97%" nowrap><label><br> 
                  Canal de distribuci&oacute;n
&nbsp;&nbsp;&nbsp;</label>
                  <br>
                          <select size="1" name="codCanal" class="CampoEntrada" onChange="actualiza();">
          <option SELECTED value=1>Atención Presencial</option>
		<option value=2>Autoservicio</option>
		<option value=3>Call Center</option>
		<option value=4>Banca a Distancia</option>
		
        </select>&nbsp;<input name="codigoCanal" disabled type="text" class="CampoSalida" value="1" size="1">
&nbsp;&nbsp;&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><fieldset><legend>Operación final</legend><table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td><label>Operaci&oacute;n final &nbsp;&nbsp;&nbsp;</label>
      <br>
              <select size="1" name="tipoOperacionFinal" class="CampoEntrada" disabled onChange="validaTipoOpFinal()">
          <%
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
                %>	
        </select></td>
  </tr>
  <tr>
    <td><label>Par&aacute;metro 1 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>
      <br>
      <input name="parametro1" type="text" class="CampoEntrada" maxlength="200" size="50" disabled value="<%=(data != null && data.length > 0 && data[0][12] != null && data[0][12].toString().length() > 0?data[0][12]:"")%>">
      <input name="parametro11" type="text" class="CampoSalida" size="75" disabled>
      </td>
  </tr>
  <tr>
    <td><label>Par&aacute;metro 2 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>
      <br>
      <input name="parametro2" type="text" class="CampoEntrada" maxlength="254" size="50" disabled value="<%=(data != null && data.length > 0 && data[0][13] != null && data[0][13].toString().length() > 0?data[0][13]:"")%>">
      <input name="parametro21" type="text" class="CampoSalida" size="75" disabled>
      </td>
  </tr>
  <tr>
    <td><label>Par&aacute;metro 3 para arrancar la operaci&oacute;n &nbsp;&nbsp;&nbsp;</label>
      <br>
      <input name="parametro3" type="text" class="CampoEntrada" maxlength="254" size="50" disabled value="<%=(data != null && data.length > 0 && data[0][14] != null && data[0][14].toString().length() > 0?data[0][14]:"")%>">
      <input name="parametro31" type="text" class="CampoSalida" size="75" disabled>
      </td>
  </tr>
  <tr>
    <td>Operaciones relacionadas </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
      <tr class="CabeceraTabla">
      	<td width="1%" valign="top" class="CabeceraTabla">&nbsp;</td>
      	<td width="5%" class="CabeceraTabla">Orden </td>
        <td width="7%" class="CabeceraTabla">C&oacute;digo </td>
        <td width="28%" class="CabeceraTabla">Nombre de Operaci&oacute;n </td>
        <td width="5%" nowrap class="CabeceraTabla">Entorno </td>
        <td width="5%" nowrap class="CabeceraTabla">Icono </td>
        <td width="5%" nowrap class="CabeceraTabla">Tipo Elem. </td>
        <td width="27%" class="CabeceraTabla">Par&aacute;metro 1 </td>
        <td width="7%" nowrap class="CabeceraTabla">Autorizaci&oacute;n</td>
        <td width="10%" class="CabeceraTabla">Fecha Modificaci&oacute;n</td>
      </tr>
      <%
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
      %>
    </table></td>
  </tr>
  <tr>
  	<td align="left"><label>Fecha de modificaci&oacute;n &nbsp;&nbsp;&nbsp;</label><label><%out.print((request.getParameter("fecModificacionIn")!=null && !"null".equals(request.getParameter("fecModificacionIn").toString()))?request.getParameter("fecModificacionIn"):((data != null && data.length > 19)?data[0][19]:""));%></td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="Button3" value="Añadir" class="Boton" onclick="addOpRel();" disabled>&nbsp;&nbsp;<input type="button" name="Button31" value="Suprimir" class="Boton" onclick="eliminarOpRel();" disabled>
  </tr>
</table>
</fieldset></td>
          </tr>
        </table>
    </fieldset></td>
  </tr>

  <tr>
    <td align="right"><input type="button" name="Button" value="Aceptar" class="Boton" onclick="aceptar();" <%=((accionRef!=null && "INICIAR".equals(accionRef) && destinoCancelar.indexOf("operacionesRelacionadas") != -1)?"disabled":"enabled")%> <%=disabled%>>
    <input type="button" name="Button2" value="Cancelar" class="Boton" onclick="cancelar();"></td>
  </tr>
</table>
</body>
</html>
