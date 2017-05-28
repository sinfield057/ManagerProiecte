/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import common.ProjectDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author ovidiugiorgi
 */
public class adminProjects extends HttpServlet {

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
        
        try {
            response.setContentType("text/html;charset=UTF-8");
            DataSource ds = ( DataSource ) new InitialContext().lookup("java:/comp/env/jdbc/ManagerProiecte");
            
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try {
                conn = ds.getConnection();
                String qrySQL = "SELECT * FROM proiecte";
                ps = conn.prepareStatement( qrySQL );
                
                rs = ps.executeQuery();
                
                ArrayList<ProjectDetails> projects = new ArrayList<>();
                
                while (rs.next()) {
                    ProjectDetails projectDetails = new ProjectDetails(
                        rs.getString("titlu"),
                        rs.getString("descriere"),
                        rs.getInt("nr_max_studenti"),
                        0
                    );
                    
                    projects.add(projectDetails);
                }
                
                request.setAttribute("projects", projects);
            } catch ( SQLException ex ) {
                Logger.getLogger(adminProjects.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if( rs != null )
                        rs.close();

                    if( ps != null )
                        ps.close();

                    if( conn != null )
                        conn.close();
                } catch (SQLException ex) {
                    System.out.println("Eroare la închiderea conexiunii cu BD!");
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(adminProjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");
        requestDispatcher.forward(request, response);
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
