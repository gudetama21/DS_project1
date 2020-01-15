<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>	MasterSearch</title>
</head>
<body bgColor="#BBFFEE">
<center>
	<form action= '${requestUri}' method='get'>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<H1><font color=steelblue>Master Search</font></H1>
		<H3><font color=#008888>Search Engine for Graduate Schools in Taiwan</font></H3>
		<br>
		<input type='text' name='keyword' placeholder='關鍵字'/>
		<input type='submit' value='搜尋' />
		<br>
		<br>
		<input type="button" value="Dcard" onclick="location.href='https://www.dcard.tw/f/graduate_school'"> 
		<input type="button" value="PTT" onclick="location.href='https://www.ptt.cc/bbs/graduate/index.html '"> 


	</form>
</center>
</form>
</body>
</html>