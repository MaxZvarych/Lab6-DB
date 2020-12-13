package ua.lviv.iot.model;

import javax.persistence.*;

@Entity
@Table(name = "media_file", schema = "max_zvaryh_db_lab3")
@IdClass(MediaFileEntityPK.class)
public class MediaFileEntity {
    private Integer id;
    private String fileType;
    private Integer chatId;

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
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "chat_id")
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public MediaFileEntity(Integer id, String fileType, Integer chatId) {
        this.id = id;
        this.fileType = fileType;
        this.chatId = chatId;
    }

    public MediaFileEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaFileEntity that = (MediaFileEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;
        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MediaFileEntity{" +
                "id=" + id +
                ", fileType='" + fileType + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}
