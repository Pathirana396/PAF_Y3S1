<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project submission Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Project submission Management V10.1</h1>
<form id="formItem" name="formItem">
 Project Name:
 <input id="projectName" name="projectName" type="text"
 class="form-control form-control-sm">
 <br> Project Email:
 <input id="projectEmail" name="projectEmail" type="text"
 class="form-control form-control-sm">
 <br> Project Title:
 <input id="projectTitle" name="projectTitle" type="text"
 class="form-control form-control-sm">
 <br> Project description:
 <input id="projectDesc" name="projectDesc" type="text"
 class="form-control form-control-sm">
 <br> project Link:
 <input id="projectLink" name="projectLink" type="text"
 class="form-control form-control-sm">
 <br> project Video:
 <input id="projectVideo" name="projectVideo" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidProjectIDSave"
 name="hidProjectIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Project projectObj = new Project();
  out.print(projectObj.readProjects());
 %>
</div>
</div> </div> </div>
</body>
</html>