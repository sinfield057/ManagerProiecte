/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author wewlad
 */
public class addStudent extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            DataSource ds = ( DataSource ) new InitialContext().lookup("java:/comp/env/jdbc/ManagerProiecte");
            String nume = request.getParameter( "nume" );
            String prenume = request.getParameter( "prenume" );
            String email = request.getParameter( "email" );
            
            Connection conn = null;
            String qrySQL = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            try {
                conn = ds.getConnection();
                qrySQL = "SELECT * FROM studenti WHERE nume=? AND prenume=? AND email=?";
                ps = conn.prepareStatement( qrySQL );
                ps.setString( 1, nume );
                ps.setString( 2, prenume );
                ps.setString( 3, email );
                
                rs = ps.executeQuery();
                if ( rs.next() ) {
                    int newPrezenta = rs.getInt( "nr_prezente" ) + 1;
                    try ( Connection conn2 = ds.getConnection() ) {
                        String updSQL = "update studenti set nr_prezente = ? where email = ?";
                        PreparedStatement ps2 = conn2.prepareStatement( updSQL );
                        ps2.setString( 2, email );
                        ps2.setInt( 1, newPrezenta );
                        ps2.executeUpdate();
                    } catch ( SQLException ex ) {
                        Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try ( Connection conn2 = ds.getConnection() ) {
                        String updSQL = "insert into studenti (nume, prenume, email, nr_prezente) values( ?, ?, ?, ? )";
                        PreparedStatement ps2 = conn2.prepareStatement( updSQL );
                        ps2.setString( 1, nume);
                        ps2.setString( 2, prenume );
                        ps2.setString( 3, email );
                        ps2.setInt( 4, 1 );
                        ps2.executeUpdate();
                    } catch ( SQLException ex ) {
                        Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RequestDispatcher requestDispatcher; 
                    requestDispatcher = request.getRequestDispatcher("/admin.jsp");
                    requestDispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try
                {
                    if( rs != null )
                        rs.close();

                    if( ps != null )
                        ps.close();

                    if( conn != null )
                        conn.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("Eroare la închiderea conexiunii cu BD!");
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
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
