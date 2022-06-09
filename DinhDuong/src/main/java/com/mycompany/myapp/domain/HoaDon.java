package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HoaDon.
 */
@Entity
@Table(name = "hoa_don")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "ma_the_bhyt")
    private String maTheBHYT;

    @Column(name = "ma_dv")
    private String maDV;

    @Column(name = "tam_ung")
    private Integer tamUng;

    @Column(name = "da_nop")
    private Integer daNop;

    @Column(name = "tong")
    private Integer tong;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HoaDon id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public HoaDon noiDung(String noiDung) {
        this.setNoiDung(noiDung);
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public HoaDon maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getMaTheBHYT() {
        return this.maTheBHYT;
    }

    public HoaDon maTheBHYT(String maTheBHYT) {
        this.setMaTheBHYT(maTheBHYT);
        return this;
    }

    public void setMaTheBHYT(String maTheBHYT) {
        this.maTheBHYT = maTheBHYT;
    }

    public String getMaDV() {
        return this.maDV;
    }

    public HoaDon maDV(String maDV) {
        this.setMaDV(maDV);
        return this;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public Integer getTamUng() {
        return this.tamUng;
    }

    public HoaDon tamUng(Integer tamUng) {
        this.setTamUng(tamUng);
        return this;
    }

    public void setTamUng(Integer tamUng) {
        this.tamUng = tamUng;
    }

    public Integer getDaNop() {
        return this.daNop;
    }

    public HoaDon daNop(Integer daNop) {
        this.setDaNop(daNop);
        return this;
    }

    public void setDaNop(Integer daNop) {
        this.daNop = daNop;
    }

    public Integer getTong() {
        return this.tong;
    }

    public HoaDon tong(Integer tong) {
        this.setTong(tong);
        return this;
    }

    public void setTong(Integer tong) {
        this.tong = tong;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HoaDon)) {
            return false;
        }
        return id != null && id.equals(((HoaDon) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HoaDon{" +
            "id=" + getId() +
            ", noiDung='" + getNoiDung() + "'" +
            ", maBN='" + getMaBN() + "'" +
            ", maTheBHYT='" + getMaTheBHYT() + "'" +
            ", maDV='" + getMaDV() + "'" +
            ", tamUng=" + getTamUng() +
            ", daNop=" + getDaNop() +
            ", tong=" + getTong() +
            "}";
    }
}
