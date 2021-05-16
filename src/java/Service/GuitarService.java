package Service;

import Model.Guitar;
import Repository.GuitarRepo;

public class GuitarService {
    public static String addNewGuitar(Guitar guitar) {
        if (guitar.getBrandId() > 0 && guitar.getType().length() <= 45 && guitar.getNoOfStrings() > 0 && guitar.getYear() > 1900) {
            if (GuitarRepo.addNewGuitar(guitar)) {
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
