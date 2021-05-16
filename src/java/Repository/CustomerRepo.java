package Repository;

import Model.Database;
import Model.Customer;
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
}
