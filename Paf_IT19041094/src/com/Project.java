package com;
import java.sql.*;
public class Project
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/test2", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readProjects()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Publisher Name</th><th>Publisher Email</th>" + "<th>Project Title</th>"
			+ "<th>Project Desc</th>" + "<th>Project Link</th>" + "<th>Project Video</th>"
			+ "<th>Update</th><th>Remove</th></tr>";
 String query = "select * from projects";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
	 String projectID = Integer.toString(rs.getInt("projectID"));
		String projectName = rs.getString("projectName");
		String projectEmail = rs.getString("projectEmail");
		String projectTitle = rs.getString("projectTitle");
		String projectDesc = rs.getString("projectDesc");
		String projectLink = rs.getString("projectLink");
		String projectVideo = rs.getString("projectVideo");
 // Add into the html table
		
		output += "<tr><td>" + projectName + "</td>";
		output += "<td>" + projectEmail + "</td>";
		output += "<td>" + projectTitle + "</td>";
		output += "<td>" + projectDesc + "</td>";
		output += "<td>" + projectLink + "</td>";
		output += "<td>" + projectVideo + "</td>";
		// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				+ "<td><form method='post' action='projects.jsp'>"
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='projectID' type='hidden' value='" + projectID + "'>" + "</form></td></tr>";
	}
	con.close();
	
	// Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the projects.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertProject(String name, String email, String title, String desc, String link, String video)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement
 String query = "INSERT INTO `project`(`projectID`, `projectName`, `projectEmail`, `projectTitle`, `projectDesc`, `projectLink`, `projectVideo`) "
			+ " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, title);
			preparedStmt.setString(5, desc);
			preparedStmt.setString(6, link);
			preparedStmt.setString(7, video);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newProjects = readProjects();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newProjects + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the project.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
public String updateProject(String ID,String name, String email, String title, String desc, String link, String video)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for updating.";
		 }
		 // create a prepared statement
			String query = "UPDATE project SET projectName=?,projectEmail=?,projectTitle=?,projectDesc=?,projectLink=?,projectVideo=? WHERE projectID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, title);
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, link);
			preparedStmt.setString(6, video);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newProjects = readProjects();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newProjects + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while updating the project.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteProject(String ProjectID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String query = "delete from projects where projectID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ProjectID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newProjects = readProjects();
		 output = "{\"status\":\"success\", \"data\": \"" +
		 newProjects + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the project.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		}