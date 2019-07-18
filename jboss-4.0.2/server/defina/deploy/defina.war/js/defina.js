var req = new ActiveXObject("Microsoft.XMLHTTP");

function mostrarDiv() {
	window.status='Procesando petición...';
	var div = document.getElementById("divprocesandopeticion");
	if (div == null) {
		div = document.createElement("div");
		with(div) {
			id="divprocesandopeticion";
			with(style) {
				position = "absolute";
				visibility = "hidden";
				left = "0px";
				top = "0px";
				width = 0;
				height = 0;
			}
		}

		div.innerHTML="<table bgcolor='#F0ECDD' style='filter: alpha(opacity=75);' border=0 width=100% height=100%><tr><td align=center valign=center><h3 fgcolor=#000000></h3></td></tr></table>";
		div.style.width=document.body.clientWidth;
		div.style.height=document.body.clientHeight;
		div.style.left = window.document.body.scrollLeft;
		div.style.top = document.body.scrollTop; 
		document.body.appendChild(div);
	}
	div.style.visibility = "visible";
}

function ocultarDiv() {
	window.status='';
	var div = document.getElementById("divprocesandopeticion");
	if (div != null) {
		div.style.visibility = "hidden";
	}
}

function tratarRespuestaAjax() {
	if (req.readyState == 4) {
		if (req.status == 200) {
            		array = req.responseText.split('<sep>');
            		if (array[1] != '') {
				alert(array[1]);
			}
            		if (array[0] != '') location.href=array[0];
			else ocultarDiv();
        	} else {
            		alert('Se ha recibido una respuesta inesperada');
            		document.write(req.responseText);
        	}
	}
}

function cancelarDialogo() {
	window.returnValue = null;
	close();
}

function aceptarDialogo() {
	if (filaSel == null) alert('Debe seleccionar un registro');
	else {
		window.returnValue=filaSel;
		close();
	}
}

function mostrarDialogoOperaciones() {
	
	// Obligamos a recargar la página para que no la coja de caché.
	var miFecha = new Date();
	var cadenaPasada = 'operacion.jsp?dialog=true&newHour=' + miFecha.getTime();
	
	return window.showModalDialog(cadenaPasada,'','dialogWidth:900px;dialogHeight:690px');
}

function caparEnlaceDescarga() {
	alert('Para descargar el fichero use la opción "Guardar destino como"');
	return false;
}

function mostrarAyuda(str) {
	window.open('ayuda/' + str + '.html', 'Ayuda', 'fullscreen');
}

function forzarSeleccion(){
	var radioGrp = document.all.radiobutton;
	for (var i=0;i<radioGrp.length;i++){
		if(radioGrp[i].checked){
			seleccion(radioGrp[i].value);
		}
	}
}