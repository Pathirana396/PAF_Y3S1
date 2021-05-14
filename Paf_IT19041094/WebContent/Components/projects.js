$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();
 	
 	var status = validateProjectForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ProjectsAPI",
 type : type,
 data : $("#formProject").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onProjectSaveComplete(response.responseText, status);
 }
 });
});



function onProjectSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }
$("#hidProjectIDSave").val("");
$("#formItem")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidProjectIDSave").val($(this).data("projectid"));
		 $("#projectName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#projectEmail").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#projectTitle").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#projectDesc").val($(this).closest("tr").find('td:eq(3)').text());
		  $("#projectLink").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#projectVideo").val($(this).closest("tr").find('td:eq(5)').text());
		});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ProjectsAPI",
		 type : "DELETE",
		 data : "projectID=" + $(this).data("projectid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onProjectDeleteComplete(response.responseText, status);
		 }
		 });
		});



function onProjectDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divProjectsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}




//CLIENT-MODEL================================================================
function validateProjectForm()
{
// projectName
if ($("#projectName").val().trim() == "")
 {
 return "Insert projectName.";
 }
// projectEmail
if ($("#projectEmail").val().trim() == "")
 {
 return "Insert projectEmail.";
 }
// projectTitle
if ($("#projectTitle").val().trim() == "")
 {
 return "Insert projectTitle.";
 }
// DESCRIPTION
if ($("#projectDesc").val().trim() == "")
 {
 return "Insert project Description.";
 }
 // projectLink
if ($("#projectLink").val().trim() == "")
 {
 return "Insert projectLink.";
 }
 // projectVideo
if ($("#projectVideo").val().trim() == "")
 {
 return "Insert projectVideo.";
 }
return true;
}
 	