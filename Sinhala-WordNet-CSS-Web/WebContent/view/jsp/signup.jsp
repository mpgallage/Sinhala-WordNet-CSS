<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="theme/css/wordnetstyle.css">
<link rel="shortcut icon" href="theme/images/wordnet1.jpg" />
</head>
<body>
    <div class="top_div">
        <table>
            <tr>
                <td>
                    <h3>Sinhala WordNet CrowdSourcing System</h3>
                </td>


                <td><a href="<c:url value="login.jsp"/>">Login</a></td>
            </tr>
        </table>
    </div>


    <div id="wrap">
        <form:form method="POST" modelAttribute="user" action="SignUp">
            <div id="loginDiv">
                <div>
                    <h3>Signup</h3>
                    <p style="color: #ff0000">${error}</p>
                </div>
                <table class="word_table">
                    <tbody>

                        <tr>
                            <td><label>First Name</label></td>
                            <td><form:input class="formText" path="firstName"
                                    type="text/html; charset=UTF-8" maxlength="255" size="22" /></td>
                        </tr>

                        <tr>
                            <td><label>Last Name</label></td>
                            <td><form:input class="formText" path="lastName" type="text"
                                    maxlength="255" size="22" /></td>
                        </tr>
                        <tr>
                            <td><label>User Name</label></td>
                            <td><form:input class="formText" path="username"
                                    type="text/html; charset=UTF-8" maxlength="255" size="22" /></td>
                        </tr>

                        <tr>
                            <td><label>Password</label></td>
                            <td><form:input class="formText" path="password"
                                    type="password" maxlength="255" size="22" /></td>
                        </tr>
                        <tr>
                            <td><label>E-mail</label></td>
                            <td><form:input class="formText" path="email" type="text"
                                    maxlength="255" size="22" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Signup" id="signupBtn"
                                class="button" style="float: right" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </form:form>
    </div>
</body>
</html>