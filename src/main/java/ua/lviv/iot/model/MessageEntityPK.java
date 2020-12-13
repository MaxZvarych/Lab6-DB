package ua.lviv.iot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class MessageEntityPK implements Serializable {
    private Integer id;
    private String addressee;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "addressee")
    @Id
    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public MessageEntityPK(Integer id, String addressee) {
        this.id = id;
        this.addressee = addressee;
    }

    public MessageEntityPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntityPK that = (MessageEntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (addressee != null ? !addressee.equals(that.addressee) : that.addressee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addressee != null ? addressee.hashCode() : 0);
        return result;
    }
}
