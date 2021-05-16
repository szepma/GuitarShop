package Service;

import Model.Orders;
import Repository.OrdersRepo;

public class OrdersService {
    public static String addNewOrder(Orders order) {
        if (order.getCustomerId() > 0) {
            if (OrdersRepo.addNewOrder(order)) {
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
