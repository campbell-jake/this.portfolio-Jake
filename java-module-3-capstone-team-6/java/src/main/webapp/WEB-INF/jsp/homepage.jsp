<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<%@ include file = "common/header.jspf" %>

<h3 id="homepageCenter">Click a park for more information</h3>

<c:forEach var="aPark" items="${parks}">

	<div class="homepage-flex-container">
	
	<c:set var="parkCodeUpperCase" value="${aPark.parkCode}"/>
	<c:set var="parkCodeLowerCase" value="${fn:toLowerCase(parkCodeUpperCase)}"/>
	
	<c:url var="parkDetailHref" value="/parkDetail">
		<c:param name="parkCode" value="${aPark.parkCode}"></c:param>
	</c:url>
	<c:url var="parkImageHref" value="/img/parks/${parkCodeLowerCase}.jpg"></c:url>
	
	<div>
		<a href="${parkDetailHref}"><img src="${parkImageHref}" alt ="${aPark.parkName} image"></a>
	</div>
		
	<div id="parkStuff">
		<h1><a href="${parkDetailHref}">${aPark.parkName}</a></h1>
	
		<h3>Located in ${aPark.state}</h3>
	
		<p>${aPark.parkDescription}</p>
	</div>
	
	</div>
	
</c:forEach>

<%@ include file = "common/footer.jspf" %>