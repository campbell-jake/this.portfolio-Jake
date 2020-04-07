<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file = "common/header.jspf" %>

	<c:set var="parkCodeUpperCase" value="${park.parkCode}"/>
	<c:set var="parkCodeLowerCase" value="${fn:toLowerCase(parkCodeUpperCase)}"/>

	<c:url var="parkImageHref" value="/img/parks/${parkCodeLowerCase}.jpg"></c:url>
	<img src="${parkImageHref}" alt ="${park.parkName} image" class="park-detail-image">
	
	<h2>${park.parkName}</h2>
	
	<p>${park.parkDescription}</p>

<div id="inspirational-quote">
	<p>${park.inspirationalQuote}</p>
	<p>	- ${park.quoteSource}</p>
</div>	
		
<table>
		<tr>
			<td>State: </td>
			<td>${park.state}</td>
		</tr>
		<tr>
			<td>Acreage: </td>
			<td><fmt:formatNumber value="${park.acreage}" pattern="###,###"/> acres</td>
		</tr>
		<tr>
			<td>Elevation: </td>
			<td><fmt:formatNumber value="${park.elevation}" pattern="###,###"/> feet</td>
		</tr>
		<tr>
			<td>Total Trail Length: </td>
			<td>${park.milesOfTrails} miles</td>
		</tr>
		<tr>
			<td>Number of Campsites: </td>
			<td>${park.numberOfCampsites}</td>
		</tr>
		<tr>
			<td>Climate: </td>
			<td>${park.climate}</td>
		</tr>
		<tr>
			<td>Year Founded: </td>
			<td>${park.yearFounded}</td>
		</tr>
		<tr>
			<td>Annual Visitor Count: </td>
			<td><fmt:formatNumber value="${park.annualVisitorCount}" pattern="###,###"/></td>
		</tr>
		<tr>
			<td>Entry Fee: </td>
			<td>$${park.entryFee}</td>
		</tr>
		<tr>
			<td>Number of Animal Species: </td>
			<td>${park.numberAnimalSpecies}</td>
		</tr>
</table>

<hr>

<c:url var="formAction" value="/getTempPreference"></c:url> 
<form method="POST" action="${formAction}">
	<label for="tempPreference">Display temperatures as: </label>
	<select name="tempPreference">
		<option value="fahrenheit">Fahrenheit</option>
		<option value="celsius">Celsius</option>
	</select>
	<button type="submit">Select</button>
</form>

<h1 class="parkDetailCenter">Five Day Forecast</h1>

<div class="five-dates-flex-container">
	<div>
		<jsp:useBean id="ourDate" class="java.util.Date"/>
		<jsp:setProperty name="ourDate" property="time" value="${ourDate.time}"/>
		<fmt:formatDate value="${ourDate}" dateStyle="long"/>
	</div>
	<c:forEach begin="1" end="4" var="aDay">
		<div>
			<jsp:setProperty name="ourDate" property="time" value="${ourDate.time + 86400000}"/>
			<fmt:formatDate value="${ourDate}" dateStyle="long"/>
		</div>	
	</c:forEach>
</div>

	<c:choose>
		<c:when test="${tempPreference=='celsius'}">
			<c:set var="forecastTemperature" value="${forecastCelsius}"/>
			<c:set var="degreeSymbol" value=" &deg;C"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="forecastTemperature" value="${forecast}"/>
			<c:set var="degreeSymbol" value=" &deg;F"></c:set>
		</c:otherwise>
	</c:choose>

<div class="parkDetail-flex-container">	
<c:forEach var="dailyForecast" items="${forecastTemperature}">
	<div class="${dailyForecast.forecastDay}">
	<c:url var="weatherImageHref" value="/img/weather/${dailyForecast.forecast}.png"/>
		<c:if test="${dailyForecast.forecast == 'partly cloudy' }">
			<c:set var="weatherImageHref" value="img/weather/partlyCloudy.png"/>
		</c:if>
		<img src="${weatherImageHref}"/>
						
	<p class="parkDetailCenter">High: <fmt:formatNumber value="${dailyForecast.highTemp}" pattern="0.0"></fmt:formatNumber>${degreeSymbol}</p>
	<p class="parkDetailCenter">Low: <fmt:formatNumber value="${dailyForecast.lowTemp}" pattern="0.0"></fmt:formatNumber>${degreeSymbol}</p>
	</div>		
</c:forEach>
</div>
	
<div class="parkWeather-flex-container">		
<c:forEach var="dailyRecommendation" items="${recommendations.displayRecommendation}">
	<div>
	<p class="parkDetailCenter">${dailyRecommendation}</p>
	</div>	
</c:forEach>
</div>	
	
<%@ include file = "common/footer.jspf" %>