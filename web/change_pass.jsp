
<div class="body">
    <div>

        <div style="width: 100%;height:300px">
  <form  action="change_pass_db.jsp" method="post" >


          <table  border="0" cellspacing="20" cellpadding="8" style align="center">

                                            <tr>
                                                <td>
                                                    <fieldset>
                                                        <legend>Change Password</legend>
                                                        <table cellpadding="5" cellspacing="5">
                                                              <tr>
                                                               <td class="text">Old Password:</td>
                                                                <td>
                                                                    <input type= password size="25"  id="txtoldpassword" name="txtoldpassword"/>

                                                                </td>
                                                              </tr>
                                                              <tr>
                                                                 <td class="text">New Password:</td>
                                                                <td>
                                                                    <input type= password size="25"  id="txtnewpassword" name="txtnewpassword"/>

                                                                </td>
                                                                </tr>
                                                                <tr>
                                                                 <td class="text">Confirm Password:</td>
                                                                <td>
                                                                    <input type= password size="25"  id="txtconfirmpassword" name="txtconfirmpassword"/>

                                                                </td>

                                                            </tr>
                                                        </table>
                                                    </fieldset>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center" style="color: red">  <%=session.getAttribute("Message")%>   <%session.setAttribute("Message","");%></td>
                                            </tr>
                                            <tr>
                                                <td align="center" colspan="2" >
                                                    <input type="submit" class="btnSubmit1" onclick= "return valid2()"value="Submit" />
                                                    <input type= "reset" class="btnSubmit1" value="Reset"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>

        </div>
    </div>
</div>
