<%
String url=request.getParameter("txtUrl");
String emailid=DB.Connect.getField("tbluser", "emailid", "userid", session.getAttribute("userid").toString(), "=");
DB.Connect.openConnection();
int i=DB.Connect.blockUrl(url, emailid);
DB.Connect.closeConnection();
if(i>0){
session.setAttribute("msg", "URL blocked successfully");
}else{
session.setAttribute("msg", "Failed to block url");
}
response.sendRedirect("block.jsp");
%>