<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "common/header.jspf" %>

<c:url var="loginUrl" value="/login"/>
	<form action="${loginUrl}" method="POST" modelAttribute="login">
    <div class="form-group">
        <label class="loginLabel" for="username">Username</label>
        <input type="text" name="username" placeholder="Username"/>
    </div>
    <div class="form-group">
        <label class="loginLabel" for="password">Password</label>
        <input type="password" name="password" placeholder="Password"/>
    </div>
    <div class="form-group">
    	<c:if test="${empty passwordHint || passwordHint == 'Invalid Username'}">
    	<c:url var="forgotPasswordHref" value = "/forgotPassword"/>
    	<a href="${forgotPasswordHref}" id="forgotPassword">Forgot Password?</a>
    	</c:if>
    	<c:if test = "${not empty passwordHint}">
    		<p id="passwordHintDisplay">${passwordHint}</p>
    	</c:if>
    </div>
    <button type="submit" class="btn btn-default">Login</button>
</form>
<p class="errors"><c:out value="${loginMessage}"/></p>

<%@ include file = "common/footer.jspf" %>