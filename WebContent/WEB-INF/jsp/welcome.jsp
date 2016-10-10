<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%-- ${message} --%>
 
	<br>
	<br>
	<div style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px; text-align:center;">
 
		<p><a href="<c:url value='fetchStudent'/>">Click here for fetching data for Students</a><p>
		<p><a href="<c:url value='fetchSubject'/>">Click here for fetching data for Subjects</a><p>
	</div>
