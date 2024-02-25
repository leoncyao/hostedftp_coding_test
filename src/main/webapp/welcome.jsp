
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Hostedftp Coding Test</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <!-- <link href="../../dist/css/bootstrap.min.css" rel="stylesheet"> -->

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <div class="container">
        <div class="content">
      <%
      // Retrieve parameters from the URL
      String param1 = request.getParameter("company");
      String param2 = request.getParameter("role");
      
      // Check if parameters are present
      if (param1 != null && param2 != null) {
          %>
          <div class="element">
              <h1 class="h3 mb-3 font-weight-normal">Welcome!</h1>
          </div>
          <div class="element">
              <h1 class="h3 mb-3 font-weight-normal">Company Name: <%= param1 %></h1>
          </div>
          <div class="element">
              <h1 class="h3 mb-3 font-weight-normal">Role: <%= param2 %></h1>
          </div>
          <%
      } else {
          %>
          <p>No parameters found in the URL.</p>
          <%
      }
      %>

        </div>
        </div>
        
  </body>
</html>
