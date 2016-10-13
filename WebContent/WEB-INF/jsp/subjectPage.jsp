<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div align="center">
		<form:form action="getSubjectData" method="post" commandName="subjectForm">
			<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;">
				<tr>
					<td colspan="2" align="center"><h2>Enter these fields to
							fetch data</h2></td>
				</tr>
				<tr>
					<td>Subject Id :</td>
					<td><form:input path="subject_id" /></td>
				</tr>
				<tr>
					<td>Subject name:</td>
					<td><form:input path="subject_name" /></td>
				</tr>
				<tr>
					<td>Proff name:</td>
					<td><form:input path="proff_name" /></td>
				</tr>
				<tr>
					<td>Time :</td>
					<td><form:input path="time" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<%-- <p>${isDataPresent}</p> --%>
	
	<div class="dataTables_wrapper" id="example_wrapper" align="center" >
	<div align="center" style="width:50%">
		<c:if test="${ not empty isDataPresent}">
			<p>Hello
			<table id="dataTableExp" width="50%" class="display" 
				style="background-color: #aed6f1 ; color:  #34495e ; font: Arial; border-radius:6px; font-size: 12px">
				<thead>
					<tr>
						<th>Subject Id</th>
						<th>Subject name</th>
						<th>Proff name</th>
						<th>Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${subject}" var="current">
						<tr>
							<td><c:out value="${current.subject_id}" />
							<td><c:out value="${current.subject_name}" />
							<td><c:out value="${current.proff_name}" />
							<td><c:out value="${current.time}" />
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	</div>
	
</body>
</html>