package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GiayMoiDanhGia.
 */
@Entity
@Table(name = "giay_moi_danh_gia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GiayMoiDanhGia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "ma_nguoi_tao")
    private String maNguoiTao;

    @Column(name = "thoi_gian_chi_dinh")
    private ZonedDateTime thoiGianChiDinh;

    @Column(name = "chuan_doan")
    private String chuanDoan;

    @Column(name = "chuan_doan_phu")
    private String chuanDoanPhu;

    @Column(name = "gui_tu")
    private String guiTu;

    @Column(name = "gui_den")
    private String guiDen;

    @Column(name = "moi")
    private String moi;

    @Column(name = "noi_dung_hoi_chuan")
    private String noiDungHoiChuan;

    @Column(name = "hoi_chuan_lan")
    private Integer hoiChuanLan;

    @Column(name = "thoi_gian_hoi_chuan")
    private ZonedDateTime thoiGianHoiChuan;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GiayMoiDanhGia id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public GiayMoiDanhGia maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getMaNguoiTao() {
        return this.maNguoiTao;
    }

    public GiayMoiDanhGia maNguoiTao(String maNguoiTao) {
        this.setMaNguoiTao(maNguoiTao);
        return this;
    }

    public void setMaNguoiTao(String maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public ZonedDateTime getThoiGianChiDinh() {
        return this.thoiGianChiDinh;
    }

    public GiayMoiDanhGia thoiGianChiDinh(ZonedDateTime thoiGianChiDinh) {
        this.setThoiGianChiDinh(thoiGianChiDinh);
        return this;
    }

    public void setThoiGianChiDinh(ZonedDateTime thoiGianChiDinh) {
        this.thoiGianChiDinh = thoiGianChiDinh;
    }

    public String getChuanDoan() {
        return this.chuanDoan;
    }

    public GiayMoiDanhGia chuanDoan(String chuanDoan) {
        this.setChuanDoan(chuanDoan);
        return this;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getChuanDoanPhu() {
        return this.chuanDoanPhu;
    }

    public GiayMoiDanhGia chuanDoanPhu(String chuanDoanPhu) {
        this.setChuanDoanPhu(chuanDoanPhu);
        return this;
    }

    public void setChuanDoanPhu(String chuanDoanPhu) {
        this.chuanDoanPhu = chuanDoanPhu;
    }

    public String getGuiTu() {
        return this.guiTu;
    }

    public GiayMoiDanhGia guiTu(String guiTu) {
        this.setGuiTu(guiTu);
        return this;
    }

    public void setGuiTu(String guiTu) {
        this.guiTu = guiTu;
    }

    public String getGuiDen() {
        return this.guiDen;
    }

    public GiayMoiDanhGia guiDen(String guiDen) {
        this.setGuiDen(guiDen);
        return this;
    }

    public void setGuiDen(String guiDen) {
        this.guiDen = guiDen;
    }

    public String getMoi() {
        return this.moi;
    }

    public GiayMoiDanhGia moi(String moi) {
        this.setMoi(moi);
        return this;
    }

    public void setMoi(String moi) {
        this.moi = moi;
    }

    public String getNoiDungHoiChuan() {
        return this.noiDungHoiChuan;
    }

    public GiayMoiDanhGia noiDungHoiChuan(String noiDungHoiChuan) {
        this.setNoiDungHoiChuan(noiDungHoiChuan);
        return this;
    }

    public void setNoiDungHoiChuan(String noiDungHoiChuan) {
        this.noiDungHoiChuan = noiDungHoiChuan;
    }

    public Integer getHoiChuanLan() {
        return this.hoiChuanLan;
    }

    public GiayMoiDanhGia hoiChuanLan(Integer hoiChuanLan) {
        this.setHoiChuanLan(hoiChuanLan);
        return this;
    }

    public void setHoiChuanLan(Integer hoiChuanLan) {
        this.hoiChuanLan = hoiChuanLan;
    }

    public ZonedDateTime getThoiGianHoiChuan() {
        return this.thoiGianHoiChuan;
    }

    public GiayMoiDanhGia thoiGianHoiChuan(ZonedDateTime thoiGianHoiChuan) {
        this.setThoiGianHoiChuan(thoiGianHoiChuan);
        return this;
    }

    public void setThoiGianHoiChuan(ZonedDateTime thoiGianHoiChuan) {
        this.thoiGianHoiChuan = thoiGianHoiChuan;
    }

    public Boolean getTrangThai() {
        return this.trangThai;
    }

    public GiayMoiDanhGia trangThai(Boolean trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GiayMoiDanhGia)) {
            return false;
        }
        return id != null && id.equals(((GiayMoiDanhGia) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GiayMoiDanhGia{" +
            "id=" + getId() +
            ", maBN='" + getMaBN() + "'" +
            ", maNguoiTao='" + getMaNguoiTao() + "'" +
            ", thoiGianChiDinh='" + getThoiGianChiDinh() + "'" +
            ", chuanDoan='" + getChuanDoan() + "'" +
            ", chuanDoanPhu='" + getChuanDoanPhu() + "'" +
            ", guiTu='" + getGuiTu() + "'" +
            ", guiDen='" + getGuiDen() + "'" +
            ", moi='" + getMoi() + "'" +
            ", noiDungHoiChuan='" + getNoiDungHoiChuan() + "'" +
            ", hoiChuanLan=" + getHoiChuanLan() +
            ", thoiGianHoiChuan='" + getThoiGianHoiChuan() + "'" +
            ", trangThai='" + getTrangThai() + "'" +
            "}";
    }
}
