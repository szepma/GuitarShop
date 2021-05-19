package Controller;

import Model.Customer;
import Service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class CustomerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            //addNewCustomer
            if (request.getParameter("task").equals("addNewCustomer")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("email").isEmpty() &&
                        !request.getParameter("city").isEmpty() &&
                        !request.getParameter("street").isEmpty() &&
                        !request.getParameter("house").isEmpty() &&
                        !request.getParameter("zip").isEmpty()) {
                    String email = request.getParameter("email");
                    String city = request.getParameter("city");
                    String street = request.getParameter("street");
                    Integer house = Integer.parseInt(request.getParameter("house"));
                    Integer zip = Integer.parseInt(request.getParameter("zip"));
                    
                    Customer customer = new Customer(0, email, city, street, house, zip);
                    String result = CustomerService.addNewCustomer(customer);
                    
                    returnValue.put("result", result);
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.print(returnValue);
            }
            
            //checkCustomer
            if (request.getParameter("task").equals("checkCustomer")) {
                JSONObject returnValue = new JSONObject();
                System.out.println("Itt vagyok");
                
                if (!request.getParameter("email").isEmpty()) {
                    String result = CustomerService.checkCustomer(request.getParameter("email")).getCustomerId().toString();
                    returnValue.put("result", result);
                }
                else {
                    returnValue.put("result", "A mezők nincsenek megfelelően kitöltve");
                }
                out.print(returnValue);
            }
            
            //deleteCustomerById
            if (request.getParameter("task").equals("deleteCustomerById")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Customer customer = Customer.getCustomerById(id);
                    returnValue.put("result", CustomerService.deleteCustomerById(customer));
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
