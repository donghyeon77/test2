<!-- 검출목록 -->
<!-- CWE79_OutputCrossSiteScript - 크로스사이트 스크립트 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<h1>XSS Sample</h1>

<%--
// <%@ page import="com.hsecure.xwm.aca.utils.*"%>
// ${hsAcaTarget.lineSplitMid} : request.getParameter("name")
// HsHtmlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
--%>

<%
String bad = request.getParameter("name");
%>
<p>BAD:<%=bad%></p>


<%
String good = request.getParameter("name");
if( good != null )
{
	good = good.replaceAll("<", "&lt;");
	good = good.replaceAll(">", "&gt;");
	good = good.replaceAll("&", "&amp;");
	good = good.replaceAll("\"", "&quot;");
	good = good.replaceAll("'", "&#x27;");
	good = good.replaceAll("/", "&#x2F;");
}
else
{
	good = "";
}
%>
<p>GOOD:<%=good%></p>

<%
String secondGood = request.getParameter("name");
if( secondGood.indexOf("&") > 0 )
{
	int temp = 0;
}
%>
<p>SECONDGOOD:<%=secondGood%></p>