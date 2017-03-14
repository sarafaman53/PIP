<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<form action="EmailUtilityServlet" method="post">
<table>
<caption>Send An Email</caption>
<tr>
<td>From</td>
<td><input type="email" name="from" size="41"/></td>
</tr>
<tr>
<td>Recipients</td>
<% String[] recipients = application.getInitParameter("recipients").split(",");
for(String addr:recipients) {
	%> 
	<option><%= addr %></option>
	<% } %>
</select> 
</td>
</tr>
<tr>
<td>Subject</td>
<td><input type="text" name="subject"size="41"/></td>
</tr>
<tr>
<td>Body</td>
<td><textarea rows="10" cols="42" name="body"></textarea></td>
</tr>
<tr>
<td>Attachment</td>
<td><input type="file" name="file" multiple ></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="send" /></td>
</tr>
</table>
</form>

</body>
</html>
