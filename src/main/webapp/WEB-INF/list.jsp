<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>이미지 리스트 출력하기</h1>
	<ul>
	<%
		List<String> fileNames = (List<String>) request.getAttribute("fileNames");
		if(fileNames != null && !fileNames.isEmpty()){
			// 파일들이 존재한다면
			for(String fileName : fileNames){
	%>			
				<li><img src="images/<%=fileName%>" width="200px">
	<%			
			}
		}
	
	%>
	</ul>
</body>
</html>