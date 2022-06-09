package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PhieuSuatAn.
 */
@Entity
@Table(name = "phieu_suat_an")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PhieuSuatAn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_dv")
    private String tenDv;

    @Column(name = "ma_dv")
    private String maDV;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "ma_the_bhyt")
    private String maTheBHYT;

    @Column(name = "t_g_su_dung")
    private ZonedDateTime tGSuDung;

    @Column(name = "t_g_chi_dinh")
    private ZonedDateTime tGChiDinh;

    @Column(name = "chuan_doan")
    private String chuanDoan;

    @Column(name = "chuan_doan_kt")
    private String chuanDoanKT;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @OneToOne
    @JoinColumn(unique = true)
    private HoaDon hoaDon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PhieuSuatAn id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDv() {
        return this.tenDv;
    }

    public PhieuSuatAn tenDv(String tenDv) {
        this.setTenDv(tenDv);
        return this;
    }

    public void setTenDv(String tenDv) {
        this.tenDv = tenDv;
    }

    public String getMaDV() {
        return this.maDV;
    }

    public PhieuSuatAn maDV(String maDV) {
        this.setMaDV(maDV);
        return this;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public PhieuSuatAn maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getMaTheBHYT() {
        return this.maTheBHYT;
    }

    public PhieuSuatAn maTheBHYT(String maTheBHYT) {
        this.setMaTheBHYT(maTheBHYT);
        return this;
    }

    public void setMaTheBHYT(String maTheBHYT) {
        this.maTheBHYT = maTheBHYT;
    }

    public ZonedDateTime gettGSuDung() {
        return this.tGSuDung;
    }

    public PhieuSuatAn tGSuDung(ZonedDateTime tGSuDung) {
        this.settGSuDung(tGSuDung);
        return this;
    }

    public void settGSuDung(ZonedDateTime tGSuDung) {
        this.tGSuDung = tGSuDung;
    }

    public ZonedDateTime gettGChiDinh() {
        return this.tGChiDinh;
    }

    public PhieuSuatAn tGChiDinh(ZonedDateTime tGChiDinh) {
        this.settGChiDinh(tGChiDinh);
        return this;
    }

    public void settGChiDinh(ZonedDateTime tGChiDinh) {
        this.tGChiDinh = tGChiDinh;
    }

    public String getChuanDoan() {
        return this.chuanDoan;
    }

    public PhieuSuatAn chuanDoan(String chuanDoan) {
        this.setChuanDoan(chuanDoan);
        return this;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getChuanDoanKT() {
        return this.chuanDoanKT;
    }

    public PhieuSuatAn chuanDoanKT(String chuanDoanKT) {
        this.setChuanDoanKT(chuanDoanKT);
        return this;
    }

    public void setChuanDoanKT(String chuanDoanKT) {
        this.chuanDoanKT = chuanDoanKT;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public PhieuSuatAn ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public HoaDon getHoaDon() {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public PhieuSuatAn hoaDon(HoaDon hoaDon) {
        this.setHoaDon(hoaDon);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhieuSuatAn)) {
            return false;
        }
        return id != null && id.equals(((PhieuSuatAn) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhieuSuatAn{" +
            "id=" + getId() +
            ", tenDv='" + getTenDv() + "'" +
            ", maDV='" + getMaDV() + "'" +
            ", maBN='" + getMaBN() + "'" +
            ", maTheBHYT='" + getMaTheBHYT() + "'" +
            ", tGSuDung='" + gettGSuDung() + "'" +
            ", tGChiDinh='" + gettGChiDinh() + "'" +
            ", chuanDoan='" + getChuanDoan() + "'" +
            ", chuanDoanKT='" + getChuanDoanKT() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
