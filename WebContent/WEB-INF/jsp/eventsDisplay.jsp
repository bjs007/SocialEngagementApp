<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <p>${(((not empty isUserAdm) && isUserAdm == 'true') || (event.user_id eq user_id))}</p> --%>
<div align="center">
	<table border="1" style="background-color: #85c1e9; color: #34495e; font: Arial; border-radius: 6px; font-size: 12px; width: 50%;">
		<c:forEach items="${eventsList}" var="event">
		<c:if test="${(((not empty isUserAdm) && isUserAdm == 'true') || (event.user_id eq user_id))}">
			
			<c:url value="${pageContext.request.contextPath}/editEvent"	var="eventEditUrl">
				<c:param name="event_id" value="${event.event_id}" />
			</c:url>
			<tr>
				<th><p>Event Description</p></th>
				<th><p>Location</p></th>
				<th><p>Date &amp; Time</p></th>
				<th><p>Resources Needed</p></th>
				<th><p>Are Resources Satisfied ?</p></th>
				<th><p>Edit Link</p></th>
				<th><p>Delete Link</p></th>
				
			
			</tr>
			
			<tr>
				<td><p>${event.event_desc}</p></td>
				<td><p>${event.place}</p></td>
				<td><p>${event.event_date_time}</p></td>
				<td><p>${event.resources_needed}</p></td>
				<td><p>${event.is_resources_satisfied}</p></td>
					<td><a href="${pageContext.request.contextPath}/editEvent?event_id=${event.event_id}">Edit this Event</a></td>
					<td><a href="${pageContext.request.contextPath}/deleteEvent?event_id=${event.event_id}">Delete this Event</a></td>
			</tr>
		</c:if>
		
		</c:forEach>
	</table>
</div>

