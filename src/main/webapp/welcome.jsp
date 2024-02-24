<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%
        // Retrieve parameters from the URL
        String param1 = request.getParameter("company");
        String param2 = request.getParameter("role");

        // Check if parameters are present
        if (param1 != null && param2 != null) {
    %>
            Welcome!
            <p>Company Name: <%= param1 %></p>
            <p>Role: <%= param2 %></p>
    <%
        } else {
    %>
            <p>No parameters found in the URL.</p>
    <%
        }
    %>
</body>
</html>