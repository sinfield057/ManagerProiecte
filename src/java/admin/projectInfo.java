/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import common.Student;
import common.Team;
import java.io.IOException;
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
public class projectInfo extends HttpServlet {

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
        
        if (request.getSession().getAttribute("UserName") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
            
            return;
        }
        
        try {
            response.setContentType("text/html;charset=UTF-8");
            DataSource ds = ( DataSource ) new InitialContext().lookup("java:/comp/env/jdbc/ManagerProiecte");
            int id_proiect = Integer.parseInt(request.getParameter("id_proiect"));
            
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = ds.getConnection();
                String qrySQL = 
                        "select nume, prenume, email, nr_prezente\n" +
                        "from studenti\n" +
                        "where id_echipa in (\n" +
                        "    select id_echipa\n" +
                        "    from echipa\n" +
                        "    where id_proiect = ?\n" +
                        ")";
                
                ps = conn.prepareStatement( qrySQL );
                ps.setInt(1, id_proiect);
                rs = ps.executeQuery();
                
                PreparedStatement ps2 = null;
                ResultSet rs2 = null;
                
                try {
                    qrySQL =
                        "select count(*) \"membri_echipa\"\n" +
                        "from studenti\n" +
                        "where id_echipa = (\n" +
                        "    select id_echipa\n" +
                        "    from echipa\n" +
                        "    where id_proiect = ?\n" +
                        "    limit 1\n" +
                        ")";
                    
                    ps2 = conn.prepareStatement(qrySQL);
                    ps2.setInt(1, id_proiect);
                    rs2 = ps2.executeQuery();
                    
                    ArrayList<Student> students = new ArrayList<>();
                    ArrayList<Team> echipe = new ArrayList<>();
                    
                    int membri_prima_echipa = rs2.next() ? rs2.getInt("membri_echipa") : Integer.MAX_VALUE;
                    
                    while (rs.next()) {
                        if (rs.getRow() > membri_prima_echipa) {
                            if (students.size() > 0) {
                                echipe.add(new Team(students));
                                students.clear();
                                membri_prima_echipa = Integer.MAX_VALUE;
                            }
                        }
                        
                        students.add(new Student(
                                rs.getString("nume"),
                                rs.getString("prenume"),
                                rs.getString("email"),
                                rs.getInt("nr_prezente")
                        ));
                    }
                    
                    if (students.size() > 0) {
                        echipe.add(new Team(students));
                        students.clear();
                    }
                    
                    request.setAttribute("echipe", echipe);
                    
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/projectInfo.jsp");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(projectInfo.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    
                    if (ps2 != null) {
                        ps2.close();
                    }
                }
            } catch ( SQLException ex ) {
                Logger.getLogger(projectInfo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(projectInfo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.sendRedirect(request.getContextPath() + "/adminProjects");
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
