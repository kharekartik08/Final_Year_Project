<%

            String op = request.getParameter("txtoldpassword");
            System.out.print("oldp="+op);
            String np = request.getParameter("txtnewpassword");
            String cp = request.getParameter("txtconfirmpassword");
            String id = session.getAttribute("userid").toString();
            String dbp = DB.Connect.getUserPassword(id);
            String message="";
            try {
                if (dbp.equalsIgnoreCase(op)) {
                    if (np.equalsIgnoreCase(cp)) {
                        if (!(np.isEmpty() || cp.isEmpty() || op.isEmpty())) {
                            DB.Connect.changePass(id, np);
                                System.out.print("Passwordchanged");
                                message="Password changed successfully";
                        } else {
                             message="Fields cannot be empty";

                        }
                    }else
                     message="Passwords do not match";
                } else {
                  message="Incorrect password";
                }
            } catch (Exception e) {

                e.printStackTrace();
                 message="An error occured";
            }
            session.setAttribute("Message", message);
            response.sendRedirect("change_pass.jsp");
%>