package ua.lviv.iot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ChatEntityPK implements Serializable {
    private Integer id;
    private String chatStyleName;

    @Column(name = "id")
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "chat_style_name")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getChatStyleName() {
        return chatStyleName;
    }

    public void setChatStyleName(String chatStyleName) {
        this.chatStyleName = chatStyleName;
    }

    public ChatEntityPK(Integer id, String chatStyleName) {
        this.id = id;
        this.chatStyleName = chatStyleName;
    }

    public ChatEntityPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntityPK that = (ChatEntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (chatStyleName != null ? !chatStyleName.equals(that.chatStyleName) : that.chatStyleName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatStyleName != null ? chatStyleName.hashCode() : 0);
        return result;
    }
}
