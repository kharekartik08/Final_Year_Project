<%

            String txtemailid = request.getParameter("txtemailid");
        String txtfirstname = request.getParameter("txtfname");

        String txtlastname = request.getParameter("txtlastname");
        String txtdob = request.getParameter("txtdob");
        String cmbgender = request.getParameter("cmbgender");

        if(txtemailid.length()<6){
              session.setAttribute("Message", "ID should be of six digit");
            response.sendRedirect("new_user.jsp");
        }
        String txtmobile = request.getParameter("txtmobile");

        String txtaddress = request.getParameter("txtaddress");
      
        String txtConfirm_password = request.getParameter("txtConfirm_password");
        String txtpassword = request.getParameter("txtpassword");
        String message="";
        System.out.print("details==" + txtfirstname + ":" + cmbgender);
        boolean b = false;


        if (txtpassword.equalsIgnoreCase(txtConfirm_password)) {
            b = true;
            if (txtpassword.equalsIgnoreCase("") || txtConfirm_password.equalsIgnoreCase("")) {
                b = false;

            }
        }
         int i=0;
        if(b){
           i = DB.Connect.saveUsers(txtfirstname, txtlastname, txtemailid, txtaddress, "True", "user", txtpassword, txtmobile, cmbgender, txtdob);
        }
       if (i > 0) {
            message="Registration Successfull";
              session.setAttribute("msg", message);
            response.sendRedirect("new_user.jsp");
        } else {
               message="Registration Failed";
                 session.setAttribute("msg", message);
            response.sendRedirect("new_user.jsp");
        }
       %>