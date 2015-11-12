<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Search - Index</title>
</head>
<body>
	<h1>Calculate your flight now!</h1>
	<form action="flightResult" method="POST">
		Travel from:
		<select name="origin">
			<option value="MAD">Madrid</option>
			<option value="BCN">Barcelona</option>
			<option value="LHR">London</option>
			<option value="CDG">Paris</option>
			<option value="FRA">Frankfurt</option>
			<option value="IST">Istambul</option>
			<option value="AMS">Amsterdam</option>
			<option value="FCO">Rome</option>
			<option value="CPH">Copenhagen</option>
		</select>
		to:
		<select name="destination">
			<option value="BCN">Barcelona</option>
			<option value="MAD">Madrid</option>
			<option value="LHR">London</option>
			<option value="CDG">Paris</option>
			<option value="FRA">Frankfurt</option>
			<option value="IST">Istambul</option>
			<option value="AMS">Amsterdam</option>
			<option value="FCO">Rome</option>
			<option value="CPH">Copenhagen</option>
		</select>
		<p>How many days left from flight?</p>
		Days: <input type="text" name="days" value=30 />
		<p>How many adults?</p>
		Adults: <input type="text" name="adults" value=1 />
		<p>How many children?</p>
		Children: <input type="text" name="children" value=0 />
		<p>How many infants?</p>
		Infants: <input type="text" name="infants" value=0 />
		
		<br /><br />
		<input type="submit" value="Calculate flights!" />
	</form>
</body>
</html>