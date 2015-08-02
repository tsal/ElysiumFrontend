<html>
<head>
    <link rel="stylesheet" href = "/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Underground Theater - ${pageContext.request.userPrincipal.name}</a>
    </div>
    <div>
      <form id="logoutForm" action="/logout" method="post"></form>
      <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
      </script>      
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="/admin/page/users">Manage Users</a></li>
        <li><a href="/admin/page/troupes">Manage Troupes</a></li>
        <li><a href="/admin/page/events">Manage Events</a></li>
        <li><a href="javascript:formSubmit()">Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
</div>
</body>
</html>