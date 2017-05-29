/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import common.Student;
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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author wewlad
 */
public class teamStatus extends HttpServlet {

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
            String emailCheck = request.getParameter( "emailCheck" );
            
            Connection conn = null;
            PreparedStatement ps = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            
            String qrySQL = "select * from studenti s1 join studenti s2 on s1.id_echipa = s2.id_echipa where s2.email = ?";
            String qrySQL2 = "select * \n" +
                            "from ManagerProiecte.proiecte p join ManagerProiecte.echipa e \n" +
                            "on p.id_proiect = e.id_proiect join ManagerProiecte.studenti s\n" +
                            "on e.id_echipa = s.id_echipa \n" +
                            "where s.email = ?";
            
            try {
                conn = ds.getConnection();
                ps = conn.prepareStatement( qrySQL );
                ps.setString( 1, emailCheck );
                rs = ps.executeQuery();
                ArrayList< Student > students = new ArrayList<>();
                while ( rs.next() ) {
                    students.add(new Student(
                            rs.getString( "nume" ),
                            rs.getString( "prenume" ),
                            rs.getString( "email" ),
                            rs.getInt( "nr_prezente" )
                    ));
                }
                request.setAttribute( "students", students );
                
                ps2 = conn.prepareStatement( qrySQL2 );
                ps2.setString( 1, emailCheck );
                rs2 = ps2.executeQuery();
                String numeProiect = "";
                if ( rs2.next() ) {
                    numeProiect = rs2.getString( "titlu" );
                }
                request.setAttribute( "titlu", numeProiect );
                
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/teamStatus.jsp");
                requestDispatcher.forward(request, response);
                
            } catch (SQLException ex) {
                Logger.getLogger(teamStatus.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if( rs != null )
                        rs.close();
                    
                    if( rs2 != null )
                        rs2.close();
                    
                    if( ps != null )
                        ps.close();
                    
                    if( ps2 != null )
                        ps2.close();
                    
                    if( conn != null )
                        conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(teamStatus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(teamStatus.class.getName()).log(Level.SEVERE, null, ex);
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
