/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author wewlad
 */
public class addTeam extends HttpServlet {

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
            DataSource ds = ( DataSource ) new InitialContext().lookup( "java:/comp/env/jdbc/ManagerProiecte" );
            int nrStudenti = Integer.parseInt( request.getParameter("nrStudenti") );
            boolean flag = true;
            
            for ( int i = 1; i <= nrStudenti; i++ ) {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                
                try {
                    conn = ds.getConnection();
                    String qrySQL = "select * " +
                                    "from studenti " +
                                    "where email=? and nume=? and prenume=?";
                    ps = conn.prepareStatement(qrySQL);
                    ps.setString( 1, request.getParameter( "email" + i ) );
                    ps.setString( 2, request.getParameter( "nume" + i ) );
                    ps.setString( 3, request.getParameter( "prenume" + i ) );
                    rs = ps.executeQuery();
                    
                    if( !rs.next() ) {
                        flag = false;
                        request.setAttribute( "eroare", "Studentul " + request.getParameter( "nume" + i ) + " " + request.getParameter( "prenume" + i ) + " nu exista!" );
                        request.setAttribute( "succes", "" );
                    } else {
                        int test = rs.getInt( "id_echipa" );
                        if ( !rs.wasNull() ) {
                            flag = false;
                            request.setAttribute("eroare", "Studentul " + request.getParameter( "nume" + i ) + " " + request.getParameter( "prenume" + i ) + " este deja intr-o echipa!" );
                            request.setAttribute( "succes", "" );
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if( rs != null )
                            rs.close();
                        
                        if( ps != null )
                            ps.close();
                        
                        if( conn != null )
                            conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if ( flag ) {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                
                try {
                    conn = ds.getConnection();
                    String qrySQL = "insert into echipa ( id_proiect ) values( ? )";
                    ps = conn.prepareStatement( qrySQL, Statement.RETURN_GENERATED_KEYS );
                    ps.setInt( 1, Integer.parseInt( request.getParameter( "idProiect" ) ) );
                    ps.executeUpdate();
                    
                    rs = ps.getGeneratedKeys();
                    if ( rs.next() ) {
                        int idTeam = rs.getInt( 1 );
                        for ( int i = 1; i <= nrStudenti; i++ ) {
                            Connection conn2 = null;
                            PreparedStatement ps2 = null;
                            try {
                                conn2 = ds.getConnection();
                                String qrySQL2 = "update studenti set id_echipa = ? where email = ?";
                                ps2 = conn2.prepareStatement( qrySQL2 );
                                ps2.setString( 2, request.getParameter( "email" + i ) );
                                ps2.setInt( 1, idTeam );
                                ps2.executeUpdate();

          
                            } catch (SQLException ex) {
                                Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                try {
                                    if( ps2 != null )
                                        ps.close();

                                    if( conn2 != null )
                                        conn.close();
                                } catch (SQLException ex) {
                                    Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if( rs != null )
                            rs.close();
                        
                        if( ps != null )
                            ps.close();
                        
                        if( conn != null )
                            conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                request.setAttribute( "succes", "Echipa a fost creata cu succes!" );
                request.setAttribute( "eroare", "" );
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/addTeam.jsp");
            requestDispatcher.forward(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(addTeam.class.getName()).log(Level.SEVERE, null, ex);
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
