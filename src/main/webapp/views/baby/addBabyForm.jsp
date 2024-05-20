<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Baby Record</title>
</head>
<body>
    <h2>Create Baby Record</h2>
    <form action="/addBaby" method="post">
        <label for="code">Code:</label><br>
        <input type="text" id="code" name="code"><br>
        
        <label for="nickname">Nickname:</label><br>
        <input type="text" id="nickname" name="nickname" required><br>
        
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        
        <label for="gender">Gender:</label><br>
        <input type="text" id="gender" name="gender"><br>
        
        <label for="expected_date">Expected Date:</label><br>
        <input type="text" id="expected_date" name="expected_date" required><br>
        
        <label for="blood_type">Blood Type:</label><br>
        <input type="text" id="blood_type" name="blood_type"><br>
        
        <label for="photo">Photo:</label><br>
        <input type="file" id="photo" name="photo"><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>