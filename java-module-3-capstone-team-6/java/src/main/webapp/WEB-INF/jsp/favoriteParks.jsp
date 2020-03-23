<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file = "common/header.jspf" %>

<div class="favPark">
<h1>Favorite Parks</h1>
<p>Was your favorite park everyone else's favorite? Check out the results below!</p>
</div>

<c:forEach var="aFavPark" items="${favoriteParks}">

	<div class="favPark-flex-container">
	
		<c:set var="parkCodeUpperCase" value="${aFavPark[2]}"/>
		<c:set var="parkCodeLowerCase" value="${fn:toLowerCase(parkCodeUpperCase)}"/>
		
		<c:url var="imageSource" value="/img/parks/${parkCodeLowerCase}.jpg"/>
		
		<div>
		<img src="${imageSource}" alt ="${aFavPark[0]} image" height=100px; width=auto;>
		</div>
		
		<div>
		<p>${aFavPark[0]} received ${aFavPark[1]} submission(s)!</p>
		</div>
				
	</div>
	
</c:forEach>

<%@ include file = "common/footer.jspf" %>

