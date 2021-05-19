package Repository;

import Model.Database;
import Model.Brand;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class BrandRepo {
    public static boolean addNewBrand(Brand brand) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewBrand");
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.setParameter("nameIN", brand.getName());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateBrandById(Brand brand) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateBrandById");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);

            spq.setParameter("idIN", brand.getBrandId());            
            spq.setParameter("nameIN", brand.getName());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteBrandById(Brand brand) {
        try {
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteBrandById");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", brand.getBrandId());
            
            spq.execute();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
