<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "common/header.jspf" %>

<h3>Please provide the username associated with your account</h3>

<c:url var="loginUrl" value="/forgotPassword"/>
	<form action="${loginUrl}" method="POST">
    <div class="form-group">
        <label class="loginLabel" for="username">Username</label>
        <input type="text" name="username" placeholder="Username"/>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<p class="errors"><c:out value="${loginMessage}"/></p>

<%@ include file = "common/footer.jspf" %>