<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div align="center">
	<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;">
		<c:forEach items="${eventsList}" var="event">
		<c:url value="${pageContext.request.contextPath}/editEvent" var="eventEditUrl">
				<c:param name="event_id" value="${event.event_id}" />
			</c:url>
		<tr>
			<td><p>${event.event_desc}</p></td>
			<td><p>${event.place}</p></td>
			<td><a href="${pageContext.request.contextPath}/editEvent?event_id=${event.event_id}">Edit this Event</a></td>
			<td><a href="${pageContext.request.contextPath}/deleteEvent?event_id=${event.event_id}">Delete this Event</a></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	
