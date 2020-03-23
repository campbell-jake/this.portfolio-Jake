<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file = "common/header.jspf" %>

<div id="changePasswordForm">
<h3>${appCurrentUser.username}- Please be certain you'd like to change your password before proceeding.</h3>

<c:url var="formAction" value="/changePassword"/>
<form:form method="POST" action="${formAction}" modelAttribute="register">
	<div class="form-group">
    	<div>
        <label class="changePasswordLabel" for="currentPassword">Current Password</label>
        <input type="password" name="currentPassword" placeholder="Current Password"/>
        </div>
    </div>
	<div class="form-group">
    	<div>
        <label class="changePasswordLabel" for="password">New Password</label>
        <form:password path="password" placeholder="New Password"/>
        </div>
        <div>
        <form:errors path="password" cssClass="errors"/>
        </div>
    </div>
    <div class="form-group">
    	<div>
        <label class="changePasswordLabel" for="confirmPassword">Confirm New Password</label>
        <form:password path="confirmPassword" placeholder="Matching Password"/>
        </div>
        <div>
        <form:errors path="passwordMatching" cssClass="errors"/>
        <form:errors path="confirmPassword" cssClass="errors"/>
        </div>
    </div>
	<button type="submit">Update Password</button>
</form:form>
<p class="errors"><c:out value="${unsuccessfulUpdate}"/></p>
</div>
<%@ include file = "common/footer.jspf" %>