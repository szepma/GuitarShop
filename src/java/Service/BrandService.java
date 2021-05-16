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
}
