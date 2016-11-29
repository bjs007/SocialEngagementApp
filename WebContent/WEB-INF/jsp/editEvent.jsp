<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<%-- <p>${user_name}</p> --%>
	<c:set var="userIdSession" value="${session_user_id}"/>
	<p>${userIdSession}</p>
	<div align="center">
		<form:form action="saveEditedEvent" method="post" commandName="eventsForm">
			<form:hidden path="event_id"/>
			<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:90%;">
				<tr>
					<td colspan="2" align="center"><h2>Update the information you want to edit</h2></td>
				</tr>
				<c:choose>
				<c:when test="${(((not empty isUserAdm) && isUserAdm == 'true') || (eventsForm.user_id eq userIdSession))}">
					<c:set var="isReadOnly" value="false"/>
				</c:when>
				<c:otherwise>
					<c:set var="isReadOnly" value="true"/>
				</c:otherwise>
				</c:choose>
			<%-- <tr>
				<td><form:input path="user_id"/> --- ${user_id == userIdSession} >> ${user_id}</td>
				<td>${userIdSession}-${isUserAdm}-${isReadOnly}</td>
			</tr> --%>
						<tr>
							<td style="width:30%">Enter Description of the event:</td>
							<td style="width:70%"><form:input path="event_desc" readonly="${isReadOnly}" style="width:inherit"/></td>
						</tr>
						<tr>
							<td>Mention Resource Needed :</td>
							<td><form:input path="resources_needed" readonly="${isReadOnly}"/></td>
						</tr>
						
						<tr>
							<td>Are all resource satisfied :</td>
							<td><form:select path="is_resources_satisfied" disabled="${isReadOnly}">
								<form:option selected="true" value="false" label="NO"/>
		   						<form:option value="true" label="YES"/>
		   						</form:select>
							</td>
						</tr>
						
						<tr>
							<td>Place of event:</td>
							<td><form:input path="place" readonly="${isReadOnly}"/></td>
						</tr>
						<tr>
							<td>Date/Time for event :</td>
							<fmt:formatDate value="${eventsForm.event_date_time}" var="dateString" pattern="yyyy/MMM/dd HH:mm" />
							<td><form:input path="event_date_time" id="datetimepicker" value ="${dateString}" type="date" readonly="${isReadOnly}" style="width:250px"/></td>
						</tr>
						<tr>
						<td>Validity :</td>
						<td>
							<table>
								<tr><td><form:radiobutton path="is_archived" value="false" disabled="${isReadOnly}"/>Active</td></tr>
								<tr><td><form:radiobutton path="is_archived" value="true" disabled="${isReadOnly}"/>Archived</td></tr>
							</table>
						</td>
						</tr>
				<tr>
				<td>Comments:</td>
				
				<td>
					<c:if test="${not empty eventsForm.prevComments}">
						<c:forEach items="${eventsForm.prevComments}" var="comment">
							<c:set var="commentParts" value="${fn:split(comment.commentString, '~')}" />
							<p><span style="color:INDIANRED;"><b><i><c:out value="${commentParts[0]}" /></i></b></span>  <c:out value="${commentParts[1]}" /></p>
						</c:forEach>
					</c:if>
				<form:input path="commentToAdd" style="height: 30px; width: 200px;" class="commentbox" type="text"/>
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
