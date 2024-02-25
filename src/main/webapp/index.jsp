<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
    <div class="container">
        <div class="content">
            <div class="element"><h2>HostedFtp Coding Test</h2></div>
            <form action="login" method="post">
                <div class="element">    
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required><br>
                    <label for="password">&nbsp;Password:</label>
                    <input type="password" id="password" name="password" required><br>

                   

                    <button type="submit">Login</button>

                </div>
                
            </form>
            <%
            // Retrieve parameters from the URL
            String param1 = request.getParameter("error");
            // Check if parameters are present
            if (param1 != null) {
                %>
                ERROR - Invalid Username or Password
                <%
            }
                %>
        </div>
    </div>
</body>
</html>