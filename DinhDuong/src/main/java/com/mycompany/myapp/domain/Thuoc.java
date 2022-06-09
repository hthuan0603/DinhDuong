package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Thuoc.
 */
@Entity
@Table(name = "thuoc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Thuoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_thuoc")
    private String tenThuoc;

    @Column(name = "duong_dung")
    private String duongDung;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "cach_dung")
    private String cachDung;

    @Column(name = "hoat_chat")
    private String hoatChat;

    @Column(name = "d_vt")
    private String dVT;

    @Column(name = "don_gia")
    private Integer donGia;

    @Column(name = "thanh_tien")
    private Integer thanhTien;

    @Column(name = "loai_tt_cu")
    private String loaiTTCu;

    @Column(name = "loai_tt_moi")
    private String loaiTTMoi;

    @Column(name = "lieu_dung")
    private String lieuDung;

    @Column(name = "k_s")
    private String kS;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Thuoc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return this.tenThuoc;
    }

    public Thuoc tenThuoc(String tenThuoc) {
        this.setTenThuoc(tenThuoc);
        return this;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getDuongDung() {
        return this.duongDung;
    }

    public Thuoc duongDung(String duongDung) {
        this.setDuongDung(duongDung);
        return this;
    }

    public void setDuongDung(String duongDung) {
        this.duongDung = duongDung;
    }

    public Integer getSoLuong() {
        return this.soLuong;
    }

    public Thuoc soLuong(Integer soLuong) {
        this.setSoLuong(soLuong);
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getCachDung() {
        return this.cachDung;
    }

    public Thuoc cachDung(String cachDung) {
        this.setCachDung(cachDung);
        return this;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public String getHoatChat() {
        return this.hoatChat;
    }

    public Thuoc hoatChat(String hoatChat) {
        this.setHoatChat(hoatChat);
        return this;
    }

    public void setHoatChat(String hoatChat) {
        this.hoatChat = hoatChat;
    }

    public String getdVT() {
        return this.dVT;
    }

    public Thuoc dVT(String dVT) {
        this.setdVT(dVT);
        return this;
    }

    public void setdVT(String dVT) {
        this.dVT = dVT;
    }

    public Integer getDonGia() {
        return this.donGia;
    }

    public Thuoc donGia(Integer donGia) {
        this.setDonGia(donGia);
        return this;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    public Integer getThanhTien() {
        return this.thanhTien;
    }

    public Thuoc thanhTien(Integer thanhTien) {
        this.setThanhTien(thanhTien);
        return this;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getLoaiTTCu() {
        return this.loaiTTCu;
    }

    public Thuoc loaiTTCu(String loaiTTCu) {
        this.setLoaiTTCu(loaiTTCu);
        return this;
    }

    public void setLoaiTTCu(String loaiTTCu) {
        this.loaiTTCu = loaiTTCu;
    }

    public String getLoaiTTMoi() {
        return this.loaiTTMoi;
    }

    public Thuoc loaiTTMoi(String loaiTTMoi) {
        this.setLoaiTTMoi(loaiTTMoi);
        return this;
    }

    public void setLoaiTTMoi(String loaiTTMoi) {
        this.loaiTTMoi = loaiTTMoi;
    }

    public String getLieuDung() {
        return this.lieuDung;
    }

    public Thuoc lieuDung(String lieuDung) {
        this.setLieuDung(lieuDung);
        return this;
    }

    public void setLieuDung(String lieuDung) {
        this.lieuDung = lieuDung;
    }

    public String getkS() {
        return this.kS;
    }

    public Thuoc kS(String kS) {
        this.setkS(kS);
        return this;
    }

    public void setkS(String kS) {
        this.kS = kS;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thuoc)) {
            return false;
        }
        return id != null && id.equals(((Thuoc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Thuoc{" +
            "id=" + getId() +
            ", tenThuoc='" + getTenThuoc() + "'" +
            ", duongDung='" + getDuongDung() + "'" +
            ", soLuong=" + getSoLuong() +
            ", cachDung='" + getCachDung() + "'" +
            ", hoatChat='" + getHoatChat() + "'" +
            ", dVT='" + getdVT() + "'" +
            ", donGia=" + getDonGia() +
            ", thanhTien=" + getThanhTien() +
            ", loaiTTCu='" + getLoaiTTCu() + "'" +
            ", loaiTTMoi='" + getLoaiTTMoi() + "'" +
            ", lieuDung='" + getLieuDung() + "'" +
            ", kS='" + getkS() + "'" +
            "}";
    }
}
