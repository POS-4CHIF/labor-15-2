package at.michaelkoenig.labor152.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @author Michael König
 */
@Entity
public class Mitarbeiter {
    private String mitId;
    private String mitVorname;
    private String mitZuname;

    @Id
    @Size(max = 6)
    @Column(name = "mit_id", nullable = false, length = 6)
    public String getMitId() {
        return mitId;
    }

    public void setMitId(String mitId) {
        this.mitId = mitId;
    }

    @Basic
    @Column(name = "mit_vorname", nullable = true, length = 255)
    public String getMitVorname() {
        return mitVorname;
    }

    public void setMitVorname(String mitVorname) {
        this.mitVorname = mitVorname;
    }

    @Basic
    @Column(name = "mit_zuname", nullable = true, length = 255)
    public String getMitZuname() {
        return mitZuname;
    }

    public void setMitZuname(String mitZuname) {
        this.mitZuname = mitZuname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mitarbeiter that = (Mitarbeiter) o;

        if (mitId != null ? !mitId.equals(that.mitId) : that.mitId != null) return false;
        if (mitVorname != null ? !mitVorname.equals(that.mitVorname) : that.mitVorname != null) return false;
        if (mitZuname != null ? !mitZuname.equals(that.mitZuname) : that.mitZuname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mitId != null ? mitId.hashCode() : 0;
        result = 31 * result + (mitVorname != null ? mitVorname.hashCode() : 0);
        result = 31 * result + (mitZuname != null ? mitZuname.hashCode() : 0);
        return result;
    }
}
