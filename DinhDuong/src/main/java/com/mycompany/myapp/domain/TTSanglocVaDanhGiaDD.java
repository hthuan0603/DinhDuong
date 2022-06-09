package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TTSanglocVaDanhGiaDD.
 */
@Entity
@Table(name = "tt_sangloc_va_danh_gia_dd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TTSanglocVaDanhGiaDD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "mang_thai")
    private Boolean mangThai;

    @Column(name = "b_mi")
    private Float bMI;

    @Column(name = "danh_gia_bmi")
    private Integer danhGiaBMI;

    @Column(name = "phan_tram_can_nang_trong_3_thang")
    private Float phanTramCanNangTrong3Thang;

    @Column(name = "danh_gia_can_nang")
    private Integer danhGiaCanNang;

    @Column(name = "an_uong_trong_tuan")
    private String anUongTrongTuan;

    @Column(name = "danh_gia_an_uong")
    private Integer danhGiaAnUong;

    @Column(name = "thoi_gian_chi_dinh")
    private ZonedDateTime thoiGianChiDinh;

    @Column(name = "nguy_co_sdd")
    private Boolean nguyCoSDD;

    @Column(name = "che_do_an")
    private String cheDoAn;

    @JsonIgnoreProperties(value = { "phieuSuatAn", "toaThuoc" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private DanhGiaCanThiepDD danhGiaCanThiepDD;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TTSanglocVaDanhGiaDD id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public TTSanglocVaDanhGiaDD maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public Boolean getMangThai() {
        return this.mangThai;
    }

    public TTSanglocVaDanhGiaDD mangThai(Boolean mangThai) {
        this.setMangThai(mangThai);
        return this;
    }

    public void setMangThai(Boolean mangThai) {
        this.mangThai = mangThai;
    }

    public Float getbMI() {
        return this.bMI;
    }

    public TTSanglocVaDanhGiaDD bMI(Float bMI) {
        this.setbMI(bMI);
        return this;
    }

    public void setbMI(Float bMI) {
        this.bMI = bMI;
    }

    public Integer getDanhGiaBMI() {
        return this.danhGiaBMI;
    }

    public TTSanglocVaDanhGiaDD danhGiaBMI(Integer danhGiaBMI) {
        this.setDanhGiaBMI(danhGiaBMI);
        return this;
    }

    public void setDanhGiaBMI(Integer danhGiaBMI) {
        this.danhGiaBMI = danhGiaBMI;
    }

    public Float getPhanTramCanNangTrong3Thang() {
        return this.phanTramCanNangTrong3Thang;
    }

    public TTSanglocVaDanhGiaDD phanTramCanNangTrong3Thang(Float phanTramCanNangTrong3Thang) {
        this.setPhanTramCanNangTrong3Thang(phanTramCanNangTrong3Thang);
        return this;
    }

    public void setPhanTramCanNangTrong3Thang(Float phanTramCanNangTrong3Thang) {
        this.phanTramCanNangTrong3Thang = phanTramCanNangTrong3Thang;
    }

    public Integer getDanhGiaCanNang() {
        return this.danhGiaCanNang;
    }

    public TTSanglocVaDanhGiaDD danhGiaCanNang(Integer danhGiaCanNang) {
        this.setDanhGiaCanNang(danhGiaCanNang);
        return this;
    }

    public void setDanhGiaCanNang(Integer danhGiaCanNang) {
        this.danhGiaCanNang = danhGiaCanNang;
    }

    public String getAnUongTrongTuan() {
        return this.anUongTrongTuan;
    }

    public TTSanglocVaDanhGiaDD anUongTrongTuan(String anUongTrongTuan) {
        this.setAnUongTrongTuan(anUongTrongTuan);
        return this;
    }

    public void setAnUongTrongTuan(String anUongTrongTuan) {
        this.anUongTrongTuan = anUongTrongTuan;
    }

    public Integer getDanhGiaAnUong() {
        return this.danhGiaAnUong;
    }

    public TTSanglocVaDanhGiaDD danhGiaAnUong(Integer danhGiaAnUong) {
        this.setDanhGiaAnUong(danhGiaAnUong);
        return this;
    }

    public void setDanhGiaAnUong(Integer danhGiaAnUong) {
        this.danhGiaAnUong = danhGiaAnUong;
    }

    public ZonedDateTime getThoiGianChiDinh() {
        return this.thoiGianChiDinh;
    }

    public TTSanglocVaDanhGiaDD thoiGianChiDinh(ZonedDateTime thoiGianChiDinh) {
        this.setThoiGianChiDinh(thoiGianChiDinh);
        return this;
    }

    public void setThoiGianChiDinh(ZonedDateTime thoiGianChiDinh) {
        this.thoiGianChiDinh = thoiGianChiDinh;
    }

    public Boolean getNguyCoSDD() {
        return this.nguyCoSDD;
    }

    public TTSanglocVaDanhGiaDD nguyCoSDD(Boolean nguyCoSDD) {
        this.setNguyCoSDD(nguyCoSDD);
        return this;
    }

    public void setNguyCoSDD(Boolean nguyCoSDD) {
        this.nguyCoSDD = nguyCoSDD;
    }

    public String getCheDoAn() {
        return this.cheDoAn;
    }

    public TTSanglocVaDanhGiaDD cheDoAn(String cheDoAn) {
        this.setCheDoAn(cheDoAn);
        return this;
    }

    public void setCheDoAn(String cheDoAn) {
        this.cheDoAn = cheDoAn;
    }

    public DanhGiaCanThiepDD getDanhGiaCanThiepDD() {
        return this.danhGiaCanThiepDD;
    }

    public void setDanhGiaCanThiepDD(DanhGiaCanThiepDD danhGiaCanThiepDD) {
        this.danhGiaCanThiepDD = danhGiaCanThiepDD;
    }

    public TTSanglocVaDanhGiaDD danhGiaCanThiepDD(DanhGiaCanThiepDD danhGiaCanThiepDD) {
        this.setDanhGiaCanThiepDD(danhGiaCanThiepDD);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TTSanglocVaDanhGiaDD)) {
            return false;
        }
        return id != null && id.equals(((TTSanglocVaDanhGiaDD) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TTSanglocVaDanhGiaDD{" +
            "id=" + getId() +
            ", maBN='" + getMaBN() + "'" +
            ", mangThai='" + getMangThai() + "'" +
            ", bMI=" + getbMI() +
            ", danhGiaBMI=" + getDanhGiaBMI() +
            ", phanTramCanNangTrong3Thang=" + getPhanTramCanNangTrong3Thang() +
            ", danhGiaCanNang=" + getDanhGiaCanNang() +
            ", anUongTrongTuan='" + getAnUongTrongTuan() + "'" +
            ", danhGiaAnUong=" + getDanhGiaAnUong() +
            ", thoiGianChiDinh='" + getThoiGianChiDinh() + "'" +
            ", nguyCoSDD='" + getNguyCoSDD() + "'" +
            ", cheDoAn='" + getCheDoAn() + "'" +
            "}";
    }
}
