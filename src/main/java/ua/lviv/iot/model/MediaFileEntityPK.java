package ua.lviv.iot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class MediaFileEntityPK implements Serializable {
    private Integer id;
    private String fileType;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "file_type")
    @Id
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MediaFileEntityPK(Integer id, String fileType) {
        this.id = id;
        this.fileType = fileType;
    }

    public MediaFileEntityPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaFileEntityPK that = (MediaFileEntityPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }
}
