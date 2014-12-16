<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<form id="logoutForm" action="/logout" method="post"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /></form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome : ${pageContext.request.userPrincipal.name}
		
        <p><a href="/admin/troupes">Manage troupes</a></h2>
        
        <p><a href="javascript:formSubmit()" >Logout</a></h2>  
	</c:if>
</body>
</html>