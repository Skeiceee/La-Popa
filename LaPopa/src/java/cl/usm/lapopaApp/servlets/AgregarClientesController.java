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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Q w e r t y
 */
public class AgregarClientesController extends HttpServlet {

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
       String[] estado = UtilConstants.ESTADOS;
        //Agregar atributos
        //Desde Servlet a JSP
       request.setAttribute("estado", estado);
    }
    
    public static boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
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
        request.getRequestDispatcher("WEB-INF/agregarClientes.jsp")
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
        
        //Desde JSP al Servlet se reciben parametros;
        processRequest(request, response);
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String rut = request.getParameter("rut");
        
        int numeroDeSerie;
        
        try {
           numeroDeSerie = Integer.parseInt(request.getParameter("numeroDeSerie"));
        }catch (NumberFormatException e) {
           numeroDeSerie = 0;
           request.setAttribute("NoValidoNDS", "El campo Numero de serie debe ser un valor numerico.");
        }
         
        String estado = request.getParameter("estado");
        
        if (nombre.isEmpty() || apellidos.isEmpty() || rut.isEmpty() || numeroDeSerie == 0 || estado.isEmpty()){
            request.setAttribute("errorVacio", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("WEB-INF/agregarClientes.jsp")
                   .forward(request, response);
        }else if(!validarRut(rut)){
            request.setAttribute("errorRut", "El rut no es valido.");
            request.getRequestDispatcher("WEB-INF/agregarClientes.jsp")
                   .forward(request, response);
        }
        else{
            //Contruyo el objeto
             Cliente cliente = new Cliente();
             cliente.setNombre(nombre);
             cliente.setApellidos(apellidos);
             cliente.setRut(rut);
             cliente.setNumeroDeSerie(numeroDeSerie);
             cliente.setEstado(estado);

             List<Cliente> clientes = (List<Cliente>)getServletContext().getAttribute("clientes");
             clientes.add(cliente);
             request.setAttribute("mensaje", "Registro exitoso");
             request.getRequestDispatcher("WEB-INF/agregarClientes.jsp")
                    .forward(request, response);              
        }
       
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
