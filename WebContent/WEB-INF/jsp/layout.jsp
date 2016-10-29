<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<style type="text/css">
	body {
		background-image: url('${pageContext.request.contextPath}/images/background.png');
	}
	</style>
	<c:forEach var="css" items="${stylesheets}">
       <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
     <!-- scripts-->
    <c:forEach var="script" items="${javascripts}">
        <script type="text/javascript" src="<c:url value="${script}"/>"></script>
    </c:forEach>
</head>
<body>
	<table border="1" cellpadding="3" cellspacing="2" align="center">
		<tr>
			<td height="30" colspan="2" style="background-image:url('${pageContext.request.contextPath}/images/web-header.jpg')"><tiles:insertAttribute name="header" />
		</tr>
		<tr>
			<td height="40%" width="1%" align="left" valign="top" style="background: rgb(42, 32, 30)"><tiles:insertAttribute name="menu" /></td>
			<td width="88%"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td height="30" colspan="2"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
	   
    
</body>
</html>