package Service;

import Model.OrderItem;
import Repository.OrderItemRepo;

public class OrderItemService {
    public static String addNewOrderItem(OrderItem item) {
        if (item.getGuitarId() > 0 && item.getOrderId()> 0) {
            if (OrderItemRepo.addNewOrderItem(item)) {
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
    
    public static String deleteOrderItemById(OrderItem orderItem) {
        if (orderItem.getItemId() > 0) {
            if (OrderItemRepo.deleteOrderItemById(orderItem)) {
                return "A törlés sikeres";
            }
            else {
                return "Az adatok helyesek, de a törlés sikertelen - OrderItem";
            }
        }
        else {
            return "A megadott adatok helytelenek";
        }
    }
}
