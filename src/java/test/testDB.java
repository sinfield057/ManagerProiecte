/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author wewlad
 */
public class testDB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testDB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testDB at " + request.getContextPath() + "</h1>");
            
           
            //DataSource ds = ( DataSource ) context.lookup( "jdbc/ManagerProiecte" );
            try
            {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch (ClassNotFoundException ex)
            {
                out.println("Driver-ul nu a fost gasit!");
            }
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try
            {
                
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ManagerProiecte" , "root" , "");

                stmt = conn.createStatement();

                
                String qrySQL = "SELECT * FROM profesor";
                rs = stmt.executeQuery(qrySQL);


                while(rs.next())
                    out.println(rs.getString("id") + " " + rs.getString("username") + " " + rs.getString("password"));
                }
            catch (SQLException ex)
            {
                out.println("Eroare la conectarea la BD: " + ex);
            }
            finally
            {
                try
                {
                    if(rs != null)
                        rs.close();

                    if(stmt != null)
                        stmt.close();

                    if(conn != null)
                        conn.close();
                }
                catch (SQLException ex)
                {
                    out.println("Eroare la închiderea conexiunii cu BD!");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
