<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
    <h2>Create Baby Record</h2>
    <form action="/baby" method="post" enctype="multipart/form-data">
    
		<input type="hidden" name="command" value="create">
		
        <label for="nickname">Nickname:</label><br>
        <input type="text" id="nickname" name="nickname" required><br>
        
        <input type="hidden" name="user_id" value="wish801">
        <input type="hidden" id='position' name="position" value="엄마">

        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        
        <label for="gender">Gender:</label><br>
        <input type="text" id="gender" name="gender"><br>
        
        <label for="expected_date">Expected Date:</label><br>
        <input type="text" id="expected_date" name="expected_date" required><br>
        
        <label for="blood_type">Blood Type:</label><br>
        <input type="text" id="blood_type" name="blood_type"><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>