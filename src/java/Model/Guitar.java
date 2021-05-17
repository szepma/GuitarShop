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
import org.json.JSONObject;

/**
 *
 * @author szepma
 */
@Entity
@Table(name = "guitar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guitar.findAll", query = "SELECT g FROM Guitar g"),
    @NamedQuery(name = "Guitar.findByGuitarId", query = "SELECT g FROM Guitar g WHERE g.guitarId = :guitarId"),
    @NamedQuery(name = "Guitar.findByBrandId", query = "SELECT g FROM Guitar g WHERE g.brandId = :brandId"),
    @NamedQuery(name = "Guitar.findByType", query = "SELECT g FROM Guitar g WHERE g.type = :type"),
    @NamedQuery(name = "Guitar.findByNoOfStrings", query = "SELECT g FROM Guitar g WHERE g.noOfStrings = :noOfStrings"),
    @NamedQuery(name = "Guitar.findByYear", query = "SELECT g FROM Guitar g WHERE g.year = :year")})
public class Guitar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "guitar_id")
    private Integer guitarId;
    @Basic(optional = false)
    @Column(name = "brand_id")
    private int brandId;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "no_of_strings")
    private int noOfStrings;
    @Basic(optional = false)
    @Column(name = "year")
    private int year;

    public Guitar() {
    }

    public Guitar(Integer guitarId) {
        this.guitarId = guitarId;
    }

    public Guitar(Integer guitarId, int brandId, String type, int noOfStrings, int year) {
        this.guitarId = guitarId;
        this.brandId = brandId;
        this.type = type;
        this.noOfStrings = noOfStrings;
        this.year = year;
    }
    
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("guitarId", this.guitarId);
        object.put("brand", Brand.getBrandById(this.brandId).getName());
        object.put("type", this.type);
        object.put("noOfStrings", this.noOfStrings);
        object.put("year", this.year);
        
        return object;
    }
    
    public static Guitar getGuitarById(int id) {
        return Database.getDbConn().find(Guitar.class, id);
    }

    public Integer getGuitarId() {
        return guitarId;
    }

    public void setGuitarId(Integer guitarId) {
        this.guitarId = guitarId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoOfStrings() {
        return noOfStrings;
    }

    public void setNoOfStrings(int noOfStrings) {
        this.noOfStrings = noOfStrings;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guitarId != null ? guitarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guitar)) {
            return false;
        }
        Guitar other = (Guitar) object;
        if ((this.guitarId == null && other.guitarId != null) || (this.guitarId != null && !this.guitarId.equals(other.guitarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Guitar[ guitarId=" + guitarId + " ]";
    }
    
}
