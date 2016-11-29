<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<%-- <p>UserId: ${user_id}</p>
	<p>${isUserAdm}</p> --%>
	<div align="center">
		<form:form action="saveEvent" method="post" commandName="eventsForm">
			<!-- <table border="1" style="background-color:#85c1e9;color:#34495e;font:Arial;border-radius:6px;font-size:12px;width:50%;"> -->
			<table class="rwd-table">
				<tr>
					<td colspan="2" ><h2>Enter these fields to
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
					<td><form:input path="event_date_time" id="datetimepicker" type="text" style="width:250px"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center"><input type="submit" value="Submit" class="submitBtn"/></td>
				</tr>
			</table>
		</form:form>
	</div>
