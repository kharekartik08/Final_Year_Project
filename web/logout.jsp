<%
session.removeAttribute("userid");
session.removeAttribute("usertype");
response.sendRedirect("login.jsp");
%>