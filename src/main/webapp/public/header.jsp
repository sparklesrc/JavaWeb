<!-- Estamos separando los href en otro jsp y luego en el index utilizaremos el include -->

<%
//Este request.getContextPath() ayuda a crear una ruta absoulta
//y se podra importar los recursos desde una ruta absoluta... ver q hay un slash
//luego del scriplet
%>


<link href="<%=request.getContextPath()%>/public/bootstrap/css/bootstrap.min.css" type="text/css"/>
<link href="<%=request.getContextPath()%>/public/bootstrap/css/bootstrap-responsive.min.css" type="text/css"/>