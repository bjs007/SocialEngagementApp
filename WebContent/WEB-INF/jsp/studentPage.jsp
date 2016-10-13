<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<div align="center">
		<form:form action="getStudentData" method="post" commandName="studentForm">
			<table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;">
				<tr>
					<td colspan="2" align="center"><h2>Enter these fields to
							fetch data</h2></td>
				</tr>
				<tr>
					<td>Student Id :</td>
					<td><form:input path="student_id" /></td>
				</tr>
				<tr>
					<td>First name:</td>
					<td><form:input path="first_name" /></td>
				</tr>
				<tr>
					<td>Last name:</td>
					<td><form:input path="last_name" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td><form:input path="gender" /></td>
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
	</div>
	
</body>
</html>