<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<table border="1" style="width: 100%; border-collapse: separate; ">
		<tr>
			<%-- <td height="30" colspan="2" style="background-image:url('${pageContext.request.contextPath}/images/web-header.jpg')"/> --%>
			<td  colspan="2" class="layout_header"/>
			<tiles:insertAttribute name="header" />
		</tr>
		<tr>
			<td height="40%"  style="background: #16A085; width:30%;align:left;valign:top" ><tiles:insertAttribute name="menu" /></td>
			<td style="width:70%"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td colspan ="2" class="layout_header"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>
	   
    
</body>
</html>