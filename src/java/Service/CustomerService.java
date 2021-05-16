package Service;

import Model.Customer;
import Repository.CustomerRepo;

public class CustomerService {
    public static String addNewCustomer(Customer customer) {
        if (customer.getEmail().length() > 0 && customer.getEmail().length() <= 240 &&
                customer.getCity().length() > 0 && customer.getCity().length() <=45 &&
                customer.getStreet().length() > 0 && customer.getStreet().length() <=45 &&
                customer.getHouseNo() > 0 && customer.getZipcode() > 0) {
            if (CustomerRepo.addNewCustomer(customer)) {
                return "A rögzítés sikeres";
            }
            else {
                return "Az adatok helyesek, de a rögzítés sikertelen";
            }
        }
        else {
            return "A megadott értékek helytelenek";
        }
    }
}
