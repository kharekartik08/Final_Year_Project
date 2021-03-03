
	<div class="body">
		<div>
			
                    <div style="width: 100%;height:300px">
                         <div align="center">
                                 <table>
                         <form action="login_db.jsp" method="post" style="width: 30%;height: 20%; " >
                       
                           
                        
                                <tr>
                                    <td>
                                        <input type="text" id="txtusername" name="txtusername" placeHolder="Email ID" class="contact"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="password" id="txtpassword"  name="txtpassword" placeHolder="Password"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="submit" id="submit" value="Login" name="submit" style="width: 150px;"/>

                                    </td>
                                </tr>
                                                </form>
                             <tr>

                                    <td>
                                        <form action="new_user.jsp">
                                            <input type="submit" value="New User" style="width: 150px;">
                                        </form>
                                    </td>

                             </tr>
                             </table>
                        </div>
                           
                                <table align="center" style="margin-top: -10px;">
                         <tr>
                            <td>
                                <p style="color: red">
                                    <%
                                                try {
                                                    String msg = session.getAttribute("msg").toString();
                                                    if (msg != null) {
                                                        out.println(msg);
                                                        session.setAttribute("msg", "");
                                                    } else {
                                                        session.setAttribute("msg", "");
                                                    }

                                                } catch (Exception e) {
                                                    session.setAttribute("msg", "");
                                                }
                                    %>
                                </p>
                            </td>
                        </tr>
                    </table>

                             
			</div>
		</div>
	</div>
	