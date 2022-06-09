package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VatTuHoTro.
 */
@Entity
@Table(name = "vat_tu_ho_tro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VatTuHoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_vat_tu")
    private Integer maVatTu;

    @Column(name = "ten_vat_tu")
    private String tenVatTu;

    @Column(name = "thu_tu_sx")
    private Integer thuTuSX;

    @JsonIgnoreProperties(value = { "chiDaoTuyen" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private HoTro hoTro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VatTuHoTro id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaVatTu() {
        return this.maVatTu;
    }

    public VatTuHoTro maVatTu(Integer maVatTu) {
        this.setMaVatTu(maVatTu);
        return this;
    }

    public void setMaVatTu(Integer maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return this.tenVatTu;
    }

    public VatTuHoTro tenVatTu(String tenVatTu) {
        this.setTenVatTu(tenVatTu);
        return this;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public Integer getThuTuSX() {
        return this.thuTuSX;
    }

    public VatTuHoTro thuTuSX(Integer thuTuSX) {
        this.setThuTuSX(thuTuSX);
        return this;
    }

    public void setThuTuSX(Integer thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public HoTro getHoTro() {
        return this.hoTro;
    }

    public void setHoTro(HoTro hoTro) {
        this.hoTro = hoTro;
    }

    public VatTuHoTro hoTro(HoTro hoTro) {
        this.setHoTro(hoTro);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VatTuHoTro)) {
            return false;
        }
        return id != null && id.equals(((VatTuHoTro) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VatTuHoTro{" +
            "id=" + getId() +
            ", maVatTu=" + getMaVatTu() +
            ", tenVatTu='" + getTenVatTu() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            "}";
    }
}
