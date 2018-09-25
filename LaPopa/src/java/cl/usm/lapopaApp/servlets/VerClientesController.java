/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.lapopaApp.servlets;

import cl.usm.lapopaApp.dto.Cliente;
import cl.usm.lapopaApp.utils.UtilConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Q w e r t y
 */
public class VerClientesController extends HttpServlet {

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
       String[] estado = UtilConstants.FILTRO;
        //Agregar atributos
        //Desde Servlet a JSP
       request.setAttribute("estado", estado);
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
        List<Cliente> clientes = (List<Cliente>)getServletContext()
                .getAttribute("clientes");
        
        request.setAttribute("clientes", clientes);
        
        request.getRequestDispatcher("WEB-INF/verClientes.jsp")
               .forward(request, response);
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
        String estado = request.getParameter("estado");
        
        List<Cliente> clientes = (List<Cliente>)getServletContext()
        .getAttribute("clientes");    
        
        if(!estado.equals("No filtrar")){

            List<Cliente> FiltradoCli = clientes.stream()
            .filter(p -> p.getEstado().equals(estado)).collect(Collectors.toList());
            request.setAttribute("FiltradoCli", FiltradoCli);
        }else{
            request.setAttribute("clientes", clientes);
        }
        
        
        request.getRequestDispatcher("WEB-INF/verClientes.jsp")
           .forward(request, response);    
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
