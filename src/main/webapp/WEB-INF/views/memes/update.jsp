<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Meme: ${meme.id}</title>
</head>
<div>
	
	<label for="top-text-input">Top Text:</label>
	<input type="text" id="top-text-input" value="${meme.topText.text}"/>
	
	<label for="bottom-text-input">Bottom Text:</label> 
	<input type="text" id="bottom-text-input" value="${meme.bottomText.text}"/>
	
	
</div>