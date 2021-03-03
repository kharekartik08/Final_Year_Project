<%@page import="java.sql.ResultSet"%>

<script type="text/javascript" src="js/scw.js" >

</script>
<div class="body">
    <div>
        <div align="center">
            <form  action="new_user_db.jsp" method="post">


                <span class="mySpan">
                   Self User Creation
                </span>

                <hr>
                <table>
                    <tr>
                        <td>

                            <input type="text" id="txtemail" name="txtemailid" placeHolder="Email ID" class="txtLogin" title="Email id" maxlength="30">

                        </td>

                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="txtfname" name="txtfname" placeHolder="First Name" class="txtLogin" title="Middle Name" maxlength="20">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="txtlastname" name="txtlastname" placeHolder="Last Name" class="txtLogin" title="Last Name" maxlength="20">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="txtdob" name="txtdob" placeHolder="yyyy-mm-dd" class="txtLogin" title="Date of Birth" maxlength="10" onclick="scwShow(this,event)" readonly>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select  id="cmbgender" name="cmbgender" class="txtLogin">
                                <option value="Select">------ Select Gender------ </option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="txtmobile" name="txtmobile" placeHolder="Mobile No." class="txtLogin" title="Mobile no." maxlength="10">


                        </td>
                    </tr>
                    <tr>
                        <td>
                            <textarea rows="5" cols="25" id="txtaddress" name="txtaddress" placeHolder="Address" class="txtAddress" title="Address" maxlength="50" ></textarea>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" id="txtpassword" name="txtpassword" placeHolder="Password"  class="txtPass" title="Password" maxlength="25">

                        </td>
                        <td>
                            <input type="password" id="txtpassword" name="txtConfirm_password" placeHolder="Confirm Password"  class="txtPass" title="Password" maxlength="25">

                        </td>
                    </tr>
                    <tr>
                        <td>

                            <input name="btnSignup" type="submit" class="btnLogin" id="btnSignup" value="Register" align="right" title="Sign up"  >

                        </td>
                    </tr>
                    <input type="hidden" name="cmbmember" value="user">
                    <tr>
                        <td>
                            <p style="color: red">
                                <%=session.getAttribute("msg")%>
                                <%session.setAttribute("msg", "");%>
                            </p>
                        </td>
                    </tr>
                </table>



            </form>
        </div>
    </div>


</div>
