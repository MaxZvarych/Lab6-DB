package ua.lviv.iot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_style", schema = "max_zvaryh_db_lab3")
public class ChatStyleEntity {
    private String name;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatStyleEntity that = (ChatStyleEntity) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    public ChatStyleEntity(String name) {
        this.name = name;
    }

    public ChatStyleEntity() {
    }

    @Override
    public String toString() {
        return "ChatStyleEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
