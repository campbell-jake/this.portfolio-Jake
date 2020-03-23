<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file = "common/header.jspf" %>

<div id="survey-page">
<h1>Favorite National Park Survey</h1>
<p>Are you a fan of National Parks? Take a moment to share with us which park is your favorite!</p>

<c:url var="formAction" value="/survey"/>
<form:form method="POST" action="${formAction}" modelAttribute="survey">

	<div>
	<label for="parkCode">Favorite National Park:</label>
	<form:select path = "parkCode">
		<c:forEach var="aPark" items="${parks}">
			<form:option value="${aPark.parkCode}">${aPark.parkName}</form:option>
		</c:forEach>
	</form:select> 
	</div>
	
	<div>
	<label for="emailAddress">Your email address:</label>
		<form:input path="emailAddress" placeholder="Email"/>
		<form:errors path="emailAddress" cssClass="errors"/>
	</div>
	
	<div>
	<label for="state">State of Residence:</label>
	<form:select path = "state">
			<form:option value="AL">Alabama</form:option>
			<form:option value="AK">Alaska</form:option>
			<form:option value="AZ">Arizona</form:option>
			<form:option value="AR">Arkansas</form:option>
			<form:option value="CA">California</form:option>
			<form:option value="CO">Colorado</form:option>
			<form:option value="CT">Connecticut</form:option>
			<form:option value="DE">Delaware</form:option>
			<form:option value="DC">District Of Columbia</form:option>
			<form:option value="FL">Florida</form:option>
			<form:option value="GA">Georgia</form:option>
			<form:option value="HI">Hawaii</form:option>
			<form:option value="ID">Idaho</form:option>
			<form:option value="IL">Illinois</form:option>
			<form:option value="IN">Indiana</form:option>
			<form:option value="IA">Iowa</form:option>
			<form:option value="KS">Kansas</form:option>
			<form:option value="KY">Kentucky</form:option>
			<form:option value="LA">Louisiana</form:option>
			<form:option value="ME">Maine</form:option>
			<form:option value="MD">Maryland</form:option>
			<form:option value="MA">Massachusetts</form:option>
			<form:option value="MI">Michigan</form:option>
			<form:option value="MN">Minnesota</form:option>
			<form:option value="MS">Mississippi</form:option>
			<form:option value="MO">Missouri</form:option>
			<form:option value="MT">Montana</form:option>
			<form:option value="NE">Nebraska</form:option>
			<form:option value="NV">Nevada</form:option>
			<form:option value="NH">New Hampshire</form:option>
			<form:option value="NJ">New Jersey</form:option>
			<form:option value="NM">New Mexico</form:option>
			<form:option value="NY">New York</form:option>
			<form:option value="NC">North Carolina</form:option>
			<form:option value="ND">North Dakota</form:option>
			<form:option value="OH">Ohio</form:option>
			<form:option value="OK">Oklahoma</form:option>
			<form:option value="OR">Oregon</form:option>
			<form:option value="PA">Pennsylvania</form:option>
			<form:option value="RI">Rhode Island</form:option>
			<form:option value="SC">South Carolina</form:option>
			<form:option value="SD">South Dakota</form:option>
			<form:option value="TN">Tennessee</form:option>
			<form:option value="TX">Texas</form:option>
			<form:option value="UT">Utah</form:option>
			<form:option value="VT">Vermont</form:option>
			<form:option value="VA">Virginia</form:option>
			<form:option value="WA">Washington</form:option>
			<form:option value="WV">West Virginia</form:option>
			<form:option value="WI">Wisconsin</form:option>
			<form:option value="WY">Wyoming</form:option>
	</form:select>
	</div>
	
	<fieldset>
        <legend>Activity Level: </legend>
        <form:errors path="activityLevel"/>
        <div class="radio">
            <label>
                <form:radiobutton path="activityLevel" value="inactive" checked="checked"/>
                Inactive
            </label>
        </div>
        <div class="radio">
            <label>
                <form:radiobutton path="activityLevel" value="sedentary"/>
                Sedentary
            </label>
        </div>
        <div class="radio">
            <label>
                <form:radiobutton path="activityLevel" value="active"/>
                Active
            </label>
        </div>
        <div class="radio">
            <label>
                <form:radiobutton path="activityLevel" value="extremely active"/>
                Extremely Active
            </label>
        </div>
    </fieldset>
	
	 <button type="submit">Submit Response</button>
	
</form:form>
</div>

<%@ include file = "common/footer.jspf" %>