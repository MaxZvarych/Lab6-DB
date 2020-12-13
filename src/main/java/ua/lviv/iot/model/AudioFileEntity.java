package ua.lviv.iot.model;

import javax.persistence.*;

@Entity
@Table(name = "audio_file", schema = "max_zvaryh_db_lab3")
public class AudioFileEntity {
    private Integer id;
    private Byte isVoiceMessage;
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

    @Basic
    @Column(name = "is_voice_message")
    public Byte getIsVoiceMessage() {
        return isVoiceMessage;
    }

    public void setIsVoiceMessage(Byte isVoiceMessage) {
        this.isVoiceMessage = isVoiceMessage;
    }

    @Basic
    @Column(name = "chat_id")
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public AudioFileEntity(Integer id, Byte isVoiceMessage, Integer chatId) {
        this.id = id;
        this.isVoiceMessage = isVoiceMessage;
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioFileEntity that = (AudioFileEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isVoiceMessage != null ? !isVoiceMessage.equals(that.isVoiceMessage) : that.isVoiceMessage != null)
            return false;
        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;

        return true;
    }

    public AudioFileEntity() {
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isVoiceMessage != null ? isVoiceMessage.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AudioFileEntity{" +
                "id=" + id +
                ", isVoiceMessage=" + isVoiceMessage +
                ", chatId=" + chatId +
                '}';
    }
}
