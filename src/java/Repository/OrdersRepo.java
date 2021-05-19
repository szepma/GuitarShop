package Repository;

import Model.Database;
import Model.Orders;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class OrdersRepo {

    public static boolean addNewOrder(Orders order) {
        try {
            EntityManager em = Database.getDbConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewOrder");

            spq.registerStoredProcedureParameter("customerIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateIN", Date.class, ParameterMode.IN);

            spq.setParameter("customerIN", order.getCustomerId());
            spq.setParameter("dateIN", order.getTimeOfOrder());

            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static Integer getLastOrder(Orders order) {
        try {
            EntityManager em = Database.getDbConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastOrder");

            spq.registerStoredProcedureParameter("customerIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateIN", Date.class, ParameterMode.IN);

            spq.setParameter("customerIN", order.getCustomerId());
            spq.setParameter("dateIN", order.getTimeOfOrder());

            List<Object[]> orders = spq.getResultList();
            for (Object[] current : orders) {
                return Integer.parseInt(current[0].toString());
            }

            return 0;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public static boolean deleteOrderById(Orders order) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteOrderById");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", order.getOrderId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
