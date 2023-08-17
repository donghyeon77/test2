<!-- 검출목록 -->
<!-- CWE352_REQUEST_FORGE_XSS_GET :: CWE352_크로스사이트 요청 위조 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- Bad Case -->
<form name="Bad" method="get" action=" customer.do">
	<input type=text name=" txt1 " >
	<input type=submit value=" send " >
</form>

<!-- Good Case -->
<form name="Good" method="post" action=" customer.do">
	<input type=text name=" txt1 " >
	<input type=submit value=" send " >
</form>