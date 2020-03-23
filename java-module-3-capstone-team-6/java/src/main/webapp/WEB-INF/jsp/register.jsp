<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file = "common/header.jspf" %>

<div id="registrationForm">
<c:url var="registerUrl" value="/register"/>
<form:form action="${registerUrl}" method="POST" modelAttribute="register">
    <div class="form-group">
        <div>
        <label class="registrationLabel" for="username">Username</label>
        <form:input path="username" placeholder="Username"/>
        </div>
        <div>
        <form:errors path="username" cssClass="errors"/>
        </div>
    </div>
    <div class="form-group">
    	<div>
        <label class="registrationLabel" for="password">Password</label>
        <form:password path="password" placeholder="Password"/>
        </div>
        <div>
        <form:errors path="password" cssClass="errors"/>
        </div>
    </div>
    <div class="form-group">
    	<div>
        <label class="registrationLabel" for="confirmPassword">Confirm Password</label>
        <form:password path="confirmPassword" placeholder="Matching Password"/>
        </div>
        <div>
        <form:errors path="passwordMatching" cssClass="errors"/>
        <form:errors path="confirmPassword" cssClass="errors"/>
        </div>
    </div>
       <div class="form-group">
    	<div>
        <label class="registrationLabel" for="Password Hint">Password Hint</label>
        <form:input path="passwordHint" placeholder="Password Hint"/>
        </div>
        <div>
        <form:errors path="passwordHint" cssClass="errors"/>
        </div>
    </div>
     <div class="form-group">
     	<div>
        <label class="registrationLabel" for="email">Email</label>
        <form:input  path="email" placeholder="Email"/>
        </div>
        <div>
        <form:errors path="email" cssClass="errors"/>
        </div>
    </div>
    <div class="form-group">
    	<div>
        <label class="registrationLabel" for="confirmEmail">Confirm Email</label>
        <form:input path="confirmEmail" placeholder="Matching Email"/>
        </div>
        <div>
        <form:errors path="emailMatching" cssClass="errors"/>
        <form:errors path="confirmEmail" cssClass="errors"/>
        </div>
    </div>
     <button type="submit" class="btn btn-default">Register</button> 
</form:form>
</div>
<p class="errors"><c:out value="${registerMessage}"/></p>

<%@ include file = "common/footer.jspf" %>