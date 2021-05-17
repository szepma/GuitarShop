package Service;

import Model.Customer;
import Model.Database;
import Repository.CustomerRepo;

public class CustomerService {

    public static String addNewCustomer(Customer customer) {
        if (customer.getEmail().length() > 0 && customer.getEmail().length() <= 240
                && customer.getCity().length() > 0 && customer.getCity().length() <= 45
                && customer.getStreet().length() > 0 && customer.getStreet().length() <= 45
                && customer.getHouseNo() > 0 && customer.getZipcode() > 0) {
            if (checkCustomer(customer.getEmail()) == null) {
                if (CustomerRepo.addNewCustomer(customer)) {
                    return "A rögzítés sikeres";
                }
                else {
                    return "Az adatok helyesek, de a rögzítés sikertelen";
                }
            }
            else {
                if (CustomerRepo.updateCustomerByEmail(customer)) {
                    return "A rögzítés sikeres, már van " + customer.getCustomerId() + " " + customer.getEmail() + " " + customer.getCity();
                }
                else {
                    return "Az adatok helyesek, de a rögzítés sikertelen";
                }
            }
        }
        else {
            return "A megadott értékek helytelenek";
        }
    }

    public static Customer checkCustomer(String email) {
        Customer customer = Customer.getCustomerById(CustomerRepo.checkCustomer(email));
        return customer;
    }
}
