/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author szepma
 */
@Entity
@Table(name = "order_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o"),
    @NamedQuery(name = "OrderItem.findByItemId", query = "SELECT o FROM OrderItem o WHERE o.itemId = :itemId"),
    @NamedQuery(name = "OrderItem.findByGuitarId", query = "SELECT o FROM OrderItem o WHERE o.guitarId = :guitarId"),
    @NamedQuery(name = "OrderItem.findByOrderId", query = "SELECT o FROM OrderItem o WHERE o.orderId = :orderId")})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Basic(optional = false)
    @Column(name = "guitar_id")
    private int guitarId;
    @Basic(optional = false)
    @Column(name = "order_id")
    private int orderId;

    public OrderItem() {
    }

    public OrderItem(Integer itemId) {
        this.itemId = itemId;
    }

    public OrderItem(Integer itemId, int guitarId, int orderId) {
        this.itemId = itemId;
        this.guitarId = guitarId;
        this.orderId = orderId;
    }
    
    public static OrderItem getOrderItemById(int id) {
        return Database.getDbConn().find(OrderItem.class, id);
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getGuitarId() {
        return guitarId;
    }

    public void setGuitarId(int guitarId) {
        this.guitarId = guitarId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderItem)) {
            return false;
        }
        OrderItem other = (OrderItem) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrderItem[ itemId=" + itemId + " ]";
    }
    
}
