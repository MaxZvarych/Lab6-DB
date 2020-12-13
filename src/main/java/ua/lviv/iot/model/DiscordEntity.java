package ua.lviv.iot.model;

import javax.persistence.*;

@Entity
@Table(name = "discord", schema = "max_zvaryh_db_lab3")
public class DiscordEntity {
    private Integer version;
    private Byte updateAvailable;
    private String settings;

    @Id
    @Column(name = "version")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "update_available")
    public Byte getUpdateAvailable() {
        return updateAvailable;
    }

    public void setUpdateAvailable(Byte updateAvailable) {
        this.updateAvailable = updateAvailable;
    }

    @Basic
    @Column(name = "settings")
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public DiscordEntity(Integer version, Byte updateAvailable, String settings) {
        this.version = version;
        this.updateAvailable = updateAvailable;
        this.settings = settings;
    }

    public DiscordEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscordEntity that = (DiscordEntity) o;

        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (updateAvailable != null ? !updateAvailable.equals(that.updateAvailable) : that.updateAvailable != null)
            return false;
        if (settings != null ? !settings.equals(that.settings) : that.settings != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = version != null ? version.hashCode() : 0;
        result = 31 * result + (updateAvailable != null ? updateAvailable.hashCode() : 0);
        result = 31 * result + (settings != null ? settings.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DiscordEntity{" +
                "version=" + version +
                ", updateAvailable=" + updateAvailable +
                ", settings='" + settings + '\'' +
                '}';
    }
}
