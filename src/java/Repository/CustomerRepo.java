package Repository;

import Model.Database;
import Model.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class CustomerRepo {
    public static boolean addNewCustomer(Customer customer) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCustomer");
            
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("cityIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("streetIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("houseIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zipIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", customer.getEmail());
            spq.setParameter("cityIN", customer.getCity());
            spq.setParameter("streetIN", customer.getStreet());
            spq.setParameter("houseIN", customer.getHouseNo());
            spq.setParameter("zipIN", customer.getZipcode());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static Integer checkCustomer(String email) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkCustomer");
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.setParameter("emailIN", email);
            
            List<Object[]> customers = spq.getResultList();
            for (Object[] customer : customers) {
                return Integer.parseInt(customer[0].toString());
            }
            
            return 0;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public static boolean updateCustomerByEmail(Customer customer) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCustomerByEmail");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("cityIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("streetIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("houseIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("zipIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", customer.getCustomerId());
            spq.setParameter("emailIN", customer.getEmail());
            spq.setParameter("cityIN", customer.getCity());
            spq.setParameter("streetIN", customer.getStreet());
            spq.setParameter("houseIN", customer.getHouseNo());
            spq.setParameter("zipIN", customer.getZipcode());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteCustomerById(Customer customer) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCustomerById");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", customer.getCustomerId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
