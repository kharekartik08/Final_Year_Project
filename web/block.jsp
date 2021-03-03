<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>

<div class="body">
    <div>
        <div style="width: 100%;height:300px" >
            <form action="block_db.jsp" method="post">
                <table border="1" cellspacing="2" cellpadding="2" style align="center" class="topMargin">
                    <tr>
                        <td>
                            Block URL(give domain preferred)
                        </td>


                        <td>
                            <input type="text" name="txtUrl"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Block"/>
                        </td>
                    </tr>
                </table>
            </form>
            <table border="1" cellspacing="2" cellpadding="2" style align="center" class="topMargin">
                <tr class="tblHeader">
                    <th>URL</th>


                    <th>Blocking Date</th>
                    <th>Un Block</th>


                </tr>
                <%

                    String query = "select * from tblblocked "
                            + "where emailid='" + DB.Connect.getField("tbluser", "emailid", "userid", session.getAttribute("userid").toString(), "=") + "'";
                    System.out.print(query);

                    Connection conn = DB.Connect.openConnection();
                    PreparedStatement stat = conn.prepareStatement(query);
                    ResultSet rs = stat.executeQuery();
                    int count = 0;
                    while (rs.next()) {
                        out.println("<tr>"
                                + "<td>" + rs.getString(2) + "</td>"
                                + "<td>" + rs.getString(3) + "</td>"
                                + "<td>" + "<a  href='unblock.jsp?url=" + rs.getString(2) + "'>" + " UnBlock</a>" + "</td>"
                                + "</tr>");
                        count++;
                    }
                    rs.close();
                    conn.close();
                    if (count == 0) {
                        out.println("<tr><td colspan='4'> No Blocked Urls Found..!! </td></tr>");
                    }
                %>

            </table>
            <table align="center">
                <tr>
                    <td>
                        <p><%=session.getAttribute("msg")%></p>
                        <%session.setAttribute("msg", "");%>
                    </td>
                </tr>
            </table>
        </div>
    </div>



</div>
