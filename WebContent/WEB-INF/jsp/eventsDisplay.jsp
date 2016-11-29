<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="center">
	<table border="1" style="background-color: #85c1e9; color: #34495e; font: Arial; border-radius: 6px; font-size: 12px; width: 90%;">
	<tr>
				<th><p>Event Description</p></th>
				<th><p>Location</p></th>
				<th><p>Date &amp; Time</p></th>
				<th><p>Resources Needed</p></th>
				<th><p>Are Resources Satisfied ?</p></th>
				<th><p>Edit Link</p></th>
				<th><p>Delete Link</p></th>
			</tr>
		<c:forEach items="${eventsList}" var="event">
			<%-- <c:url value="${pageContext.request.contextPath}/editEvent"	var="eventEditUrl">
				<c:param name="event_id" value="${event.event_id}" />
			</c:url> --%>
			<tr>
				<td><p>${event.event_desc}</p></td>
				<td><p>${event.place}</p></td>
				<fmt:formatDate value="${event.event_date_time}" var="dateString" pattern="yyyy/MMM/dd HH:mm" />
				<td><p>${dateString}</p></td>
				<td><p>${event.resources_needed}</p></td>
				<c:choose>
					<c:when test="${(event.is_resources_satisfied =='true')}">
						<td><p style="text-align: center">Yes</p></td>
					</c:when>
					<c:otherwise>
						<td><p style="text-align: center">No</p></td>
					</c:otherwise>
				</c:choose>
				<td><a href="${pageContext.request.contextPath}/editEvent?event_id=${event.event_id}">Edit this Event</a></td>
				<c:choose>
  				<c:when test="${(((not empty isUserAdm) && isUserAdm == 'true') || (event.user_id eq session_user_id))}">
					<%-- <c:set var="editUrl">${pageContext.request.contextPath}/editEvent?event_id=${event.event_id}</c:set> --%>
					<c:set var="deleteUrl">${pageContext.request.contextPath}/deleteEvent?event_id=${event.event_id}</c:set>
					<%-- <td><a href="${editUrl}">Edit this Event</a></td> --%>
					<td><a href="${deleteUrl}">Delete this Event</a></td>
				</c:when>
				<c:otherwise>
				<!-- <td><p>You cannot Edit this Event</p></td> -->
				<td><p>You cannot Delete this Event</p></td>
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
			<td colspan="7">
				<c:set var="randomImageIndex"><%= java.lang.Math.round(1+java.lang.Math.random()*3) %></c:set>
				<%-- <p>${randomImageIndex}</p> --%>
				<c:set var="imageUrl" value="${pageContext.request.contextPath}/images/event_image_${randomImageIndex}.jpg" />
				<%-- <p>${imageUrl}</p> --%>
				<img alt="ImageIcon" style="height: 320px;width: 100%;" src="${imageUrl}">
			
			</td>
			
			</tr>
			<tr><td colspan="7" style="background-color: orange;height:10px"></td></tr>
		</c:forEach>
	</table>
</div>

