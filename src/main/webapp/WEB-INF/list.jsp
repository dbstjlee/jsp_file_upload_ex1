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
	<h1>�̹��� ����Ʈ ����ϱ�</h1>
	<ul>
	<%
		List<String> fileNames = (List<String>) request.getAttribute("fileNames");
		if(fileNames != null && !fileNames.isEmpty()){
			// ���ϵ��� �����Ѵٸ�
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