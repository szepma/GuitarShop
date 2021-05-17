package Controller;

import Model.Guitar;
import Service.GuitarService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class GuitarController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            //addNewGuitar
            if (request.getParameter("task").equals("addNewGuitar")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("brand").isEmpty() && !request.getParameter("type").isEmpty() && !request.getParameter("strings").isEmpty() && !request.getParameter("year").isEmpty()) {
                    Integer brand = Integer.parseInt(request.getParameter("brand"));
                    String type = request.getParameter("type");
                    Integer strings = Integer.parseInt(request.getParameter("strings"));
                    Integer year = Integer.parseInt(request.getParameter("year"));
                    
                    Guitar guitar = new Guitar(0, brand, type, strings, year);
                    String result = GuitarService.addNewGuitar(guitar);
                    
                    returnValue.put("result", result);
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.print(returnValue);
            }
            
            //getAllGuitars
            if (request.getParameter("task").equals("getAllGuitars")) {
                JSONArray returnValue = new JSONArray();
                List<Guitar> guitars = GuitarService.getAllGuitars();
                
                if (!guitars.isEmpty()) {
                    for (Guitar guitar : guitars) {
                        returnValue.put(guitar.toJson());
                    }
                }
                else {
                    returnValue.put(new JSONObject("result", "Nincs megjeleníthető gitár"));
                }
                out.print(returnValue);
            }
        }
        catch (Exception ex) {
            System.out.println("JSON hiba");
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
