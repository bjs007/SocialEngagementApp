<!-- Created by Prateek Gupta -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="center">
	<table border="1" style="background-color: #85c1e9; color: #34495e; font: Arial; border-radius: 6px; font-size: 12px; width: 90%;">
	<tr>
				<th><p>User ID</p></th>
				<th><p>Message</p></th>
				<th><p>Posted Date &amp; Time</p></th>
				<th><p>Response</p></th>
			</tr>
		<c:forEach items="${discussionList}" var="discussion">
			<tr>
				<td><p>${discussion.userID}</p></td>
				<td><p>${discussion.message}</p></td>
				<td><p>${discussion.time}</p></td>
				<td><a href="${pageContext.request.contextPath}/responsediscussion?discussionID=${discussion.discussionID}">response to discussion</a></td>
			</tr>
			
		</c:forEach>
	</table>
</div>
