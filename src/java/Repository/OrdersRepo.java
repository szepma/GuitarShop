package Repository;

import Model.Database;
import Model.Orders;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class OrdersRepo {
    public static boolean addNewOrder(Orders order) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewOrder");
            spq.registerStoredProcedureParameter("customerIN", Integer.class, ParameterMode.IN);
            spq.setParameter("customerIN", order.getCustomerId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
