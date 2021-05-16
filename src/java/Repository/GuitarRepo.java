package Repository;

import Model.Database;
import Model.Guitar;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class GuitarRepo {
    public static boolean addNewGuitar(Guitar guitar) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewGuitar");
            
            spq.registerStoredProcedureParameter("brandIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("typeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("stringsIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("yearIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("brandIN", guitar.getBrandId());
            spq.setParameter("typeIN", guitar.getType());
            spq.setParameter("stringsIN", guitar.getNoOfStrings());
            spq.setParameter("yearIN", guitar.getYear());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
