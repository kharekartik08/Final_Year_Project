<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>


<div class="body">
    <div >
        <div>
            <div align="center">

                <%
                            try {
                                String s = session.getAttribute("userid").toString();
                                String s1 = session.getAttribute("usertype").toString();
                                if (s.isEmpty()) {
                                    response.sendRedirect("Logout.jsp");
                                }
                                if (!s1.equalsIgnoreCase("user")) {//to check wheter someone else is not downloading files
                                    response.sendRedirect("Logout.jsp");
                                }
                            } catch (Exception e) {
                                // e.printStackTrace();
                                response.sendRedirect("Logout.jsp");
                            }
                            session.setAttribute("Message", "");
                %>
                <%

                            String id = session.getAttribute("userid").toString();

                            //String id = "1";
                            String txtname = "";
                            String txtlname = "";
                            String cmbgender = "";
                            String txtdb = "";
                            String number = "";
                            String txtemail = "";
                            String txtaddress = "";

                            String doj = "";


                            String query = "select * from tbluser where userid='" + id + "'";
                            System.out.println(query);
                            Connection conn=DB.Connect.openConnection();
PreparedStatement stat=conn.prepareStatement(query);
                            ResultSet rs = stat.executeQuery();

                            if (rs.next()) {

                                txtname = rs.getString("fname");
                                txtlname = rs.getString("lname");

                                txtaddress = rs.getString("address");
                                txtemail = rs.getString("emailid");

                                number = rs.getString("mobile");

                                cmbgender = rs.getString("gender");
                                doj = rs.getString("rdate");

                            }
                            rs.close();
                            conn.close();

                %>
                <%=session.getAttribute("Message")%> <%session.setAttribute("Message", "");%>
                <table border="1" class="leftuser">
                    <tr class="tblHeader">
                        <th>User</th>
                        <th>Details</th>

                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><%=txtname + " " + txtlname%></td>
                    </tr>
                    <tr>
                        <td>Gender</td>
                        <td><%=cmbgender%></td>
                    </tr>

                    <tr>
                        <td>Email id</td>
                        <td><%=txtemail%></td>
                    </tr>
                    <tr>
                        <td>Mobile No.</td>
                        <td><%=number%></td>
                    </tr>



                    <tr>
                        <td>Address</td>
                        <td><%=txtaddress%></td>
                    </tr>
                    <tr>
                        <td>Date of Joining</td>
                        <td><%=doj%></td>
                    </tr>

                    <tr>
                        <td>   <form action="change_pass.jsp" method="post">
                                <input type="submit" class="btnChangePass"  value="Change Password"/>
                            </form>
                        </td>
                        <td>   <form action="block.jsp" method="post">
                                <input type="submit" class="btnChangePass"  value="Block URL"/>
                            </form>
                        </td>
                        <td>   <form action="block.jsp" method="post">
                                <input type="submit" class="btnChangePass"  value="Block List"/>
                            </form>
                        </td>
                        <td>   <form action="logout.jsp" method="post">
                                <input type="submit" class="btnChangePass"  value="Log out"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            You can Restart the Browser now!!
                        </td>
                    </tr>

                </table>
            </div>
        </div>

    </div>
</div>
