<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
String url=request.getParameter("url");
String emailid=DB.Connect.getField("tbluser", "emailid", "userid", session.getAttribute("userid").toString(), "=");
Connection conn=DB.Connect.openConnection();
PreparedStatement stat=conn.prepareStatement("delete from tblblocked where url='"+url+"' and emailid='"+emailid+"'");
int i=stat.executeUpdate();
conn.close();
if(i>0){
session.setAttribute("msg", "URL unblocked successfully");
}else{
session.setAttribute("msg", "Failed to unblock url");
}
response.sendRedirect("block.jsp");
%>