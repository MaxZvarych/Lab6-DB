package ua.lviv.iot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat", schema = "max_zvaryh_db_lab3")
@IdClass(ChatEntityPK.class)
public class ChatEntity {
    private Integer id;
    private String name;
    private String region;
    private Integer discordVersion;
    private String chatStyleName;
    private Set<UserEntity> users;
    private Set<MessageEntity> messages;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "Discord_version")
    public Integer getDiscordVersion() {
        return discordVersion;
    }

    public void setDiscordVersion(Integer discordVersion) {
        this.discordVersion = discordVersion;
    }

    @Id
    @Column(name = "chat_style_name")
    public String getChatStyleName() {
        return chatStyleName;
    }

    public void setChatStyleName(String chatStyleName) {
        this.chatStyleName = chatStyleName;
    }

    public ChatEntity(Integer id, String name, String region, Integer discordVersion, String chatStyleName) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.discordVersion = discordVersion;
        this.chatStyleName = chatStyleName;
    }

    public ChatEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (discordVersion != null ? !discordVersion.equals(that.discordVersion) : that.discordVersion != null)
            return false;
        if (chatStyleName != null ? !chatStyleName.equals(that.chatStyleName) : that.chatStyleName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (discordVersion != null ? discordVersion.hashCode() : 0);
        result = 31 * result + (chatStyleName != null ? chatStyleName.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "chats")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "chats")
    public Set<MessageEntity> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", discordVersion=" + discordVersion +
                ", chatStyleName='" + chatStyleName + '\'' +
                '}';
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }
}
