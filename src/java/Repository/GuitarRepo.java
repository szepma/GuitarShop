package Repository;

import Model.Database;
import Model.Guitar;
import java.util.ArrayList;
import java.util.List;
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
    
    public static List<Guitar> getAllGuitars() {
        List<Guitar> result = new ArrayList<>();
        
        try {
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllGuitars");
            
            List<Object[]> guitars = spq.getResultList();
            for (Object[] guitar : guitars) {
                int id = Integer.parseInt(guitar[0].toString());
                Guitar g = em.find(Guitar.class, id);
                result.add(g);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            return result;
        }
    }
    
    public static boolean updateGuitarById(Guitar guitar) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateGuitarById");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("brandIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("typeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("stringsIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("yearIN", Integer.class, ParameterMode.IN);

            spq.setParameter("idIN", guitar.getGuitarId());            
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
    
    public static boolean deleteGuitarById(Guitar guitar) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteGuitarById");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", guitar.getGuitarId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
