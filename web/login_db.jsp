<%@page import="JSON.JSONObject"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.awt.image.Raster"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>


<%
            //find project location for matching thumb impression

            Connection con = null;
            ResultSet rs = null;
            PreparedStatement pst = null;
            String usertype = "";
            String txtusername = request.getParameter("txtusername");
            String txtpassword = request.getParameter("txtpassword");


            String userid = null;
            String fname = "";
            try {
                con = DB.Connect.openConnection();

                String query = "select usertype,userid,fname,lname from tbluser where emailid='" + txtusername + "' and password='" + txtpassword + "'";
                System.out.println("query : " + query);
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                if (rs.next()) {
                    // System.out.println("aaaaaaaaaaaaaaaaaaaa");
                    usertype = rs.getString(1);
                    userid = rs.getString("userid");
                    fname = rs.getString("fname") + " " + rs.getString("lname");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            if (usertype.equalsIgnoreCase("user")) {
                try {



                    File f = new File("D://db.txt");
                    System.out.print(f.getPath());
                     f.createNewFile();
                    System.out.print(f.getPath());
                   
                    //  String text = new Scanner(f).useDelimiter("\\A").next();
                    JSONObject Jobj = new JSONObject();
                    Jobj.put("user", txtusername);
                    Jobj.put("password", txtpassword);
                    FileWriter fw = new FileWriter(f);
                    fw.write(Jobj.toString());
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                session.setAttribute("usertype", usertype);
                session.setAttribute("userid", userid);
                session.setAttribute("fname", fname);
                response.sendRedirect("profile.jsp");
            } else {
                session.setAttribute("msg", "Sorry you are not authenticated!!");
                session.setAttribute("emailid", "");
                response.sendRedirect("login.jsp");
            }

%>