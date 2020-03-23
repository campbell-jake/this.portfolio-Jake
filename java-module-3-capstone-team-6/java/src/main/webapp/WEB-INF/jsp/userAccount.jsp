<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file = "common/header.jspf" %>

<div id="userAccount">
<h1>Welcome, ${appCurrentUser.username}!</h1>


<c:set var="dateToDisplay" value="${lastLogin.loginDate}"/>
<c:set var="displayType" value="Last Login: "/>
<c:if test="${empty lastLogin.loginDate}">
	<c:set var="dateToDisplay" value="${registrationDate}"/>
	<c:set var="displayType" value="Registration Date: "/>
</c:if>	

<fmt:parseDate value="${dateToDisplay}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedToDate"  type="both"/>
<fmt:formatDate type="both" dateStyle ="long" timeStyle="medium" value ="${parsedToDate}" var="formattedDate"/>
<p>${displayType} ${formattedDate}</p>

<c:url var="formAction" value="/changePassword"/>
<form method="GET" action="${formAction}">
	<input type="submit" value="Change Password">
</form>

<p>${successfulUpdate}</p>
</div>

<%@ include file = "common/footer.jspf" %>