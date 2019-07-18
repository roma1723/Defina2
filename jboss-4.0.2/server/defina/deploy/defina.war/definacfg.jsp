<%
	application.setAttribute("cfg.aplicacion", "/defina");
	application.setAttribute("cfg.prefijo", "D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/BD/");
	application.setAttribute("cfg.bd.conector", "JDBC");
	application.setAttribute("cfg.bd.versiones", "versionesBD.mdb");
	application.setAttribute("cfg.bd.pool.capacidad", "1");
	application.setAttribute("cfg.bd.conexion.cierre", "true");
	application.setAttribute("cfg.canales", new String[] {"Pesados", "Ligeros"});
	application.setAttribute("cfg.entornos", new String[] {"Desarrollo", "Integrado", "Producci�n"});
	application.setAttribute("cfg.paginacion.filasPorPagina", "20");
	application.setAttribute("cfg.menus.autorizaciones.link", "mantenimiento.jsp?menu=AUTORIZACIONES&campos=C�digo,Descripci�n");
	application.setAttribute("cfg.menus.jerarquia.link", "mantenimiento.jsp?menu=JERARQUIA&campos=C�digo de operaci�n padre,C�digo de operaci�n hijo,N�mero de orden");
	application.setAttribute("cfg.menus.versiones.link", "mantenimiento.jsp?menu=VERSIONES&campos=C�digo,Descripci�n");
	application.setAttribute("cfg.menus.generar.link", "generar.jsp");
	application.setAttribute("cfg.menus.validar.link", "validar.jsp");
	application.setAttribute("cfg.menus.operaciones.link", "operacion.jsp");
	application.setAttribute("cfg.menus.perfiles.link", "mantenimiento.jsp?menu=PERFILES&campos=C�digo,Descripci�n");
	application.setAttribute("cfg.menus.escenarios.link", "mantenimiento.jsp?menu=ESCENARIOS&campos=C�digo,Nombre,Autorizaci�n,Icono,Orden,Op. Home,Fecha modificaci�n");
	application.setAttribute("cfg.menus.escenarios.ordenar.link", "ordenar.jsp?tipoOper=E&titulo=ESCENARIOS :: ORDENAR");
	application.setAttribute("cfg.menus.escenarios.arbol.link", "escenariosArbol.jsp?");
	application.setAttribute("cfg.menus.escenarios.arbol.ordenar.link", "ordenar.jsp?titulo=ESCENARIOS :: VER ARBOL :: ORDENAR");
	application.setAttribute("cfg.menus.operaciones.arbol.ordenar.link", "ordenar.jsp?titulo=OPERACIONES :: VER ARBOL :: ORDENAR");
	application.setAttribute("cfg.menus.simulacion.link", "simulacion.jsp");
	application.setAttribute("cfg.menus.promocion.link", "promocion.jsp");
	application.setAttribute("cfg.menus.iconos.link", "mantenimiento.jsp?menu=ICONOS&campos=Nombre,Icono");
	application.setAttribute("cfg.menus.documentacion.link", "ayuda/Guia_Defina.zip");
	application.setAttribute("cfg.menus.misAccesosArbol.link", "misAccesosArbol.jsp");
	application.setAttribute("cfg.menus.opFueraMenuArbol.link", "opFueraMenuArbol.jsp");
	// 0: nivel de informaci�n (para depuraci�n)
	// 1: nivel de producci�n (s�lo errores)
	application.setAttribute("cfg.trazas.nivel", "0");

	// variable que indica dos cosas: si est� informada a un valor diferente de ""
	// se generan en el directorio indicado por su contenido los ficheros
	application.setAttribute("cfg.ficheros.directorio", "D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/ficheros/%CANAL%/%ENTORNO%/");
	
	// variable utilizada para guardar los ficheros de bases de datos access generados
	application.setAttribute("cfg.directorio.versionado","D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/BD/Papelera/");
	
	// variable utilizada para recuperar los iconos
	application.setAttribute("cfg.directorio.iconos","D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/images/");

	// variable que especifica la constante para el acceso de tipo m�s restrictivo (perfil de usuario)
	application.setAttribute("cfg.acceso.usuario", "1");
%>