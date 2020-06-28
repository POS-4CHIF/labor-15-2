package at.michaelkoenig.labor152.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Michael König
 */
@Entity
public class Taetigkeit {
    private Integer taetId;
    private String taetBeschreibung;
    private LocalDate taetDatum;
    private Integer taetDauer;
    private String taetMitId;
    private Mitarbeiter mitarbeiterByTaetMitId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taet_id", nullable = false)
    public Integer getTaetId() {
        return taetId;
    }

    public void setTaetId(Integer taetId) {
        this.taetId = taetId;
    }

    @Basic
    @Column(name = "taet_beschreibung", nullable = true, length = 255)
    public String getTaetBeschreibung() {
        return taetBeschreibung;
    }

    public void setTaetBeschreibung(String taetBeschreibung) {
        this.taetBeschreibung = taetBeschreibung;
    }

    @Basic
    @Column(name = "taet_datum", nullable = true, length = 10, columnDefinition = "VARCHAR")
    public LocalDate getTaetDatum() {
        return taetDatum;
    }

    public void setTaetDatum(LocalDate taetDatum) {
        this.taetDatum = taetDatum;
    }

    @Basic
    @Min(value = 1)
    @Column(name = "taet_dauer", nullable = true)
    public Integer getTaetDauer() {
        return taetDauer;
    }

    public void setTaetDauer(Integer taetDauer) {
        this.taetDauer = taetDauer;
    }

    @Basic
    @Column(name = "taet_mit_id", nullable = false, length = 6)
    public String getTaetMitId() {
        return taetMitId;
    }

    public void setTaetMitId(String taetMitId) {
        this.taetMitId = taetMitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taetigkeit that = (Taetigkeit) o;

        if (taetId != null ? !taetId.equals(that.taetId) : that.taetId != null) return false;
        if (taetBeschreibung != null ? !taetBeschreibung.equals(that.taetBeschreibung) : that.taetBeschreibung != null)
            return false;
        if (taetDatum != null ? !taetDatum.equals(that.taetDatum) : that.taetDatum != null) return false;
        if (taetDauer != null ? !taetDauer.equals(that.taetDauer) : that.taetDauer != null) return false;
        if (taetMitId != null ? !taetMitId.equals(that.taetMitId) : that.taetMitId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taetId != null ? taetId.hashCode() : 0;
        result = 31 * result + (taetBeschreibung != null ? taetBeschreibung.hashCode() : 0);
        result = 31 * result + (taetDatum != null ? taetDatum.hashCode() : 0);
        result = 31 * result + (taetDauer != null ? taetDauer.hashCode() : 0);
        result = 31 * result + (taetMitId != null ? taetMitId.hashCode() : 0);
        return result;
    }
//
//    @ManyToOne
//    @JoinColumn(name = "taet_mit_id", referencedColumnName = "mit_id", nullable = false)
//    public Mitarbeiter getMitarbeiterByTaetMitId() {
//        return mitarbeiterByTaetMitId;
//    }

    public void setMitarbeiterByTaetMitId(Mitarbeiter mitarbeiterByTaetMitId) {
        this.mitarbeiterByTaetMitId = mitarbeiterByTaetMitId;
    }
}
