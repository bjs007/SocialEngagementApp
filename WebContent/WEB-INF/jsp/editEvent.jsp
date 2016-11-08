<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<p>${message}</p>
	<div align="center">
		<form:form action="saveEditedEvent" method="post" commandName="eventsForm">
			<form:hidden path="event_id"/>
			<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;">
				<tr>
					<td colspan="2" align="center"><h2>Enter these fields to
							fetch data</h2></td>
				</tr>
				<tr>
					<td>Enter Description of the event:</td>
					<td><form:input path="event_desc" /></td>
				</tr>
				<tr>
					<td>Mention Resource Needed :</td>
					<td><form:input path="resources_needed" /></td>
				</tr>
				
				<tr>
					<td>Are all resource satisfied :</td>
					<td><form:select path="is_resources_satisfied" >
						<form:option selected="true" value="false" label="NO"/>
   						<form:option value="true" label="YES"/>
   						</form:select>
					</td>
				</tr>
				
				<tr>
					<td>Place of event:</td>
					<td><form:input path="place" /></td>
				</tr>
				<tr>
					<td>Date/Time for event :</td>
					<td><form:input path="event_date_time" id="datetimepicker" type="text"/></td>
				</tr>
				<tr>
				<td>Validity :</td>
				<td>
					<table>
					<tr><td><form:radiobutton path="is_archived" value="false"/>Active</td></tr>
					<tr><td><form:radiobutton path="is_archived" value="true"/>Archived</td></tr>
					</table>
				<td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<%--
	<p>${isDataPresent}</p>
	
	<div class="dataTables_wrapper" id="example_wrapper" align="center" >
	<div align="center" style="width:50%">
		<c:if test="${ not empty isDataPresent}">
			<p>
			<table id="dataTableExp" width="50%" class="display" 
				style="background-color: #aed6f1 ; color:  #34495e ; font: Arial; border-radius:6px; font-size: 12px">
				<thead>
					<tr>
						<th>Student Id</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Gender name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${student}" var="current">
						<tr>
							<td><c:out value="${current.student_id}" />
							<td><c:out value="${current.first_name}" />
							<td><c:out value="${current.last_name}" />
							<td><c:out value="${current.gender}" />
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	</div> --%>
