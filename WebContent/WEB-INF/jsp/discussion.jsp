<!-- Created by Prateek Gupta -->
<!-- <p>Menu</p> -->

<% boolean s = (boolean)request.getSession().getAttribute("isUserAdm"); 

if(!s){
%>
<div class="container" style="background: #16A085;">

	<ul class="nice-menu">
		<li class="orange"><a href="${pageContext.request.contextPath}">&lt;&lt;== Get Back to Home</a></li>
		<li class="red"><a href="${pageContext.request.contextPath}/addDiscussion">CreateDiscussion</a></li>
		<li class="blue"><a href="${pageContext.request.contextPath}/displayDiscussion">DisplayDiscussion</a></li>

	</ul>
</div>
<%
} else {
%>
<div class="container" style="background: #16A085;">

	<ul class="nice-menu">
	<li class="orange"><a href="${pageContext.request.contextPath}">&lt;&lt;== Get Back to Home</a></li>
	<li class="blue"><a href="${pageContext.request.contextPath}/responseDiscussion">Response to Discussion</a></li>
	<li class="blue"><a href="${pageContext.request.contextPath}/displayDiscussiontoadmin">Display Discussion of Users</a></li>
	
	</ul>
</div>

<% } %>