package Repository;

import Model.Database;
import Model.OrderItem;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class OrderItemRepo {
    public static boolean addNewOrderItem(OrderItem item) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewOrderItem");
            
            spq.registerStoredProcedureParameter("guitarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("orderIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("guitarIN", item.getGuitarId());
            spq.setParameter("orderIN", item.getOrderId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateOrderItemById(OrderItem orderItem) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateOrderItemById");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("guitarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("orderIN", Integer.class, ParameterMode.IN);

            spq.setParameter("idIN", orderItem.getItemId());
            spq.setParameter("guitarIN", orderItem.getGuitarId());
            spq.setParameter("orderIN", orderItem.getOrderId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteOrderItemById(OrderItem orderItem) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteOrderItemById");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", orderItem.getItemId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
