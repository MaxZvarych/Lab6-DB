package ua.lviv.iot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "message", schema = "max_zvaryh_db_lab3")
@IdClass(MessageEntityPK.class)
public class MessageEntity {
    private Integer id;
    private String addressee;
    private Set<ChatEntity> chats;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "addressee")
    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public MessageEntity(Integer id, String addressee) {
        this.id = id;
        this.addressee = addressee;
    }

    public MessageEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

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

    @ManyToMany
    @JoinTable(name = "chat_has_message", schema = "max_zvaryh_db_lab3", joinColumns =
            {@JoinColumn(name = "message_id", referencedColumnName = "id", nullable = false),
                    @JoinColumn(name = "message_addressee", referencedColumnName = "addressee", nullable = false)} ,
            inverseJoinColumns = {@JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false),
                    @JoinColumn(name = "chat_style_name", referencedColumnName = "chat_style_name", nullable = false)})
    public Set<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(Set<ChatEntity> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", addressee='" + addressee + '\'' +
                '}';
    }

}
