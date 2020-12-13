package ua.lviv.iot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "max_zvaryh_db_lab3")
@IdClass(UserEntityPK.class)
public class UserEntity {
    private Integer id;
    private String nickname;
    private Integer discordVersion;
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
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "Discord_version")
    public Integer getDiscordVersion() {
        return discordVersion;
    }

    public void setDiscordVersion(Integer discordVersion) {
        this.discordVersion = discordVersion;
    }

    public UserEntity(Integer id, String nickname, Integer discordVersion) {
        this.id = id;
        this.nickname = nickname;
        this.discordVersion = discordVersion;
    }

    public UserEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (discordVersion != null ? !discordVersion.equals(that.discordVersion) : that.discordVersion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (discordVersion != null ? discordVersion.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "user_has_chat", schema = "max_zvaryh_db_lab3", joinColumns ={
    @JoinColumn(name = "user_nickname", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "user_id", referencedColumnName = "nickname", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false),
                    @JoinColumn(name = "chat_style_name", referencedColumnName = "chat_style_name", nullable = false)
    })
    public Set<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(Set<ChatEntity> chats) {
        this.chats = chats;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", discordVersion=" + discordVersion +
                '}';
    }
}
