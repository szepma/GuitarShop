package Service;

import Model.Orders;
import Repository.OrdersRepo;

public class OrdersService {
    public static Integer addNewOrder(Orders order) {
        if (order.getCustomerId() > 0) {
            if (OrdersRepo.addNewOrder(order)) {
                return getLastOrder(order);
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }
    
    public static Integer getLastOrder(Orders order) {
        return OrdersRepo.getLastOrder(order);
    }
    
    public static String deleteOrderById(Orders order) {
        if (order.getOrderId() > 0) {
            if (OrdersRepo.deleteOrderById(order)) {
                return "A törlés sikeres";
            }
            else {
                return "Az adatok helyesek, de a törlés sikertelen - Orders";
            }
        }
        else {
            return "A megadott adatok helytelenek";
        }
    }
}
