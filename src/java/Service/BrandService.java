package Service;

import Model.Brand;
import Repository.BrandRepo;

public class BrandService {
    public static String addNewBrand(Brand brand) {
        if (brand.getName().length() <= 45) {
            if (BrandRepo.addNewBrand(brand)) {
                return "A rögzítés sikeres";
            }
            else {
                return "Az adatok helyesek, de a rögzítés sikertelen";
            }
        }
        else {
            return "A megadott adatok helytelenek";
        }
    }
    
    public static String updateBrandById(Brand brand) {
        if (brand.getBrandId() > 0 && brand.getName().length() <= 45) {
            if (BrandRepo.updateBrandById(brand)) {
                return "A frissítés sikeres";
            }
            else {
                return "Az adatok helyesek, de a frissítés sikertelen - Brand";
            }
        }
        else {
            return "A megadott adatok helytelenek";
        }
    }
    
    public static String deleteBrandById(Brand brand) {
        if (brand.getBrandId() > 0) {
            if (BrandRepo.deleteBrandById(brand)) {
                return "A törlés sikeres";
            }
            else {
                return "Az adatok helyesek, de a törlés sikertelen - Brand";
            }
        }
        else {
            return "A megadott adatok helytelenek";
        }
    }
}
