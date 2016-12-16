<!-- 
* <h1>Broadcast Menu</h1>
* This displays the menu options a User should view when in the Broadcast Module
* 
* 
*
* @author  Nikita Tiwari
* @version 1.0
* 
-->

<div class="container" style="background: #16A085; ">

	<ul class="nice-menu">
		<li class="orange"><a href="${pageContext.request.contextPath}">Home</a></li>
	
		<li class="blue"><a href="${pageContext.request.contextPath}/broad">BroadcastHome</a></li>
		<li class="red"><a href="${pageContext.request.contextPath}/createbroadcast">Create Broadcast</a></li>
		<li class="red"><a href="${pageContext.request.contextPath}/editbroad">Delete Broadcast</a></li>
		<br>
		
		<li class="orange"><a href="${pageContext.request.contextPath}/fetchStudent">Go to Discussion board</a></li>
		
		<%-- <li class="bright"><a href="${pageContext.request.contextPath}/fetchSubject">Create a Broadcast</a></li> --%>
		<%-- <li class="red"><a href="${pageContext.request.contextPath}/fetchSubject">See all my Broadcasts</a></li> --%>
		<li class="blue"><a href="${pageContext.request.contextPath}/events">Let's go to Events</a></li>
		

	</ul>
</div>