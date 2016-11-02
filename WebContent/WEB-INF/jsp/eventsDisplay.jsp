<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<p>${message}</p>
	<div align="center">
		<c:forEach items="${eventsList}" var="event">
			<p>Event Name := ${event.event_desc}</p>
			<p>Place := ${event.place}</p>
		</c:forEach>
	</div>
	
