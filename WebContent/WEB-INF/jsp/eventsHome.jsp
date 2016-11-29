<!-- Created by Dipanjan Karmakar -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<div align="center">
		<div id="scrollcontainer" style="postion:relative; overflow:hidden; width:80%;">
  			<span id="scrolltext" style="position:absolute; white-space:nowrap">Event is a mutipurpose module where you can do several activities ! Want to go to grocery or meet a friend - Schedule an event !</span>
		</div>
		<p></p>
		<div class="w3-content w3-section" style="max-width:500px">
	  		<img class="mySlides" src="images/events-home-1.jpg" style="width:500px; height: 300px">
	  		<img class="mySlides" src="images/events-home-2.jpg" style="width:500px; height: 300px">
	  		<img class="mySlides" src="images/events-home-3.jpg" style="width:500px; height: 300px">
	  		<img class="mySlides" src="images/events-home-4.jpg" style="width:500px; height: 300px">
	  		<img class="mySlides" src="images/events-home-5.jpg" style="width:500px; height: 300px">
	  		<!-- <img class="mySlides" src="img_ny.jpg" style="width:100%">
	  		<img class="mySlides" src="img_chicago.jpg" style="width:100%"> -->
		</div>
		
	</div>
	
<script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 2000); // Change image every 2 seconds
}

function scroll() {
	  $('#scrolltext').css('left', $('#scrollcontainer').width());
	  $('#scrolltext').animate({
	    left: '-='+($('#scrollcontainer').width()+$('#scrolltext').width())
	  }, 10000, function() {
	    scroll();
	  });
	}

	scroll();


</script>
	
