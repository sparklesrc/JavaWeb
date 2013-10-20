<!-- Estamos separando los href en otro jsp y luego en el index utilizaremos el include -->

<%
//Este request.getContextPath() ayuda a crear una ruta absoulta
//y se podra importar los recursos desde una ruta absoluta... ver q hay un slash
//luego del scriplet
    
//Se debe colocar el REL... para q carguen los estilos
%>


<link href="<%=request.getContextPath()%>/public/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/public/app/css/overrideBootstrap.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/public/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"/>
<%  String contextPath = (String) request.getContextPath(); %>