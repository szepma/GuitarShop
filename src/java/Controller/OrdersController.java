package Controller;

import Model.Orders;
import Service.OrdersService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class OrdersController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            //addNewOrder
            if (request.getParameter("task").equals("addNewOrder")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("customer").isEmpty()) {
                    Integer customer = Integer.parseInt(request.getParameter("customer"));
                    
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
                    Date timeOfOrder = formatter.parse(formatter.format(new Date()));
                    
                    Orders order = new Orders(0, customer, timeOfOrder);
                    Integer result = OrdersService.addNewOrder(order);
                    returnValue.put("result", result);
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.print(returnValue);
            }
            
            //getLastOrder
            if (request.getParameter("task").equals("getLastOrder")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("customer").isEmpty()) {
                    Integer customer = Integer.parseInt(request.getParameter("customer"));
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.print(returnValue);
            }
            
            //deleteOrderById
            if (request.getParameter("task").equals("deleteOrderById")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Orders order = Orders.getOrderById(id);
                    returnValue.put("result", OrdersService.deleteOrderById(order));
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
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
