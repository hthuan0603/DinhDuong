package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BenhNhan.
 */
@Entity
@Table(name = "benh_nhan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BenhNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "ngay_sinh")
    private ZonedDateTime ngaySinh;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "nghe_nghiep")
    private String ngheNghiep;

    @Column(name = "chieu_cao")
    private Float chieuCao;

    @Column(name = "can_ht")
    private Float canHT;

    @Column(name = "can_tc")
    private Float canTC;

    @Column(name = "vong_ct")
    private Float vongCT;

    @Column(name = "b_mi")
    private Float bMI;

    @Column(name = "ma_the_bhyt")
    private String maTheBHYT;

    @Column(name = "s_dt")
    private Integer sDT;

    @JsonIgnoreProperties(value = { "hoaDon" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private BaoHiem baoHiem;

    @JsonIgnoreProperties(value = { "hoaDon" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private DieuTri dieuTri;

    @JsonIgnoreProperties(value = { "danhGiaCanThiepDD" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD;

    @OneToOne
    @JoinColumn(unique = true)
    private HoaDon hoaDon;

    @JsonIgnoreProperties(value = { "phieuSuatAn", "toaThuoc" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private DanhGiaCanThiepDD danhGiaCanThiepDD;

    @JsonIgnoreProperties(value = { "hoaDon" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private PhieuSuatAn phieuSuatAn;

    @OneToOne
    @JoinColumn(unique = true)
    private GiayMoiDanhGia giayMoiDanhGia;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BenhNhan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public BenhNhan hoTen(String hoTen) {
        this.setHoTen(hoTen);
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public BenhNhan maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public ZonedDateTime getNgaySinh() {
        return this.ngaySinh;
    }

    public BenhNhan ngaySinh(ZonedDateTime ngaySinh) {
        this.setNgaySinh(ngaySinh);
        return this;
    }

    public void setNgaySinh(ZonedDateTime ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return this.gioiTinh;
    }

    public BenhNhan gioiTinh(Boolean gioiTinh) {
        this.setGioiTinh(gioiTinh);
        return this;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public BenhNhan diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgheNghiep() {
        return this.ngheNghiep;
    }

    public BenhNhan ngheNghiep(String ngheNghiep) {
        this.setNgheNghiep(ngheNghiep);
        return this;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public Float getChieuCao() {
        return this.chieuCao;
    }

    public BenhNhan chieuCao(Float chieuCao) {
        this.setChieuCao(chieuCao);
        return this;
    }

    public void setChieuCao(Float chieuCao) {
        this.chieuCao = chieuCao;
    }

    public Float getCanHT() {
        return this.canHT;
    }

    public BenhNhan canHT(Float canHT) {
        this.setCanHT(canHT);
        return this;
    }

    public void setCanHT(Float canHT) {
        this.canHT = canHT;
    }

    public Float getCanTC() {
        return this.canTC;
    }

    public BenhNhan canTC(Float canTC) {
        this.setCanTC(canTC);
        return this;
    }

    public void setCanTC(Float canTC) {
        this.canTC = canTC;
    }

    public Float getVongCT() {
        return this.vongCT;
    }

    public BenhNhan vongCT(Float vongCT) {
        this.setVongCT(vongCT);
        return this;
    }

    public void setVongCT(Float vongCT) {
        this.vongCT = vongCT;
    }

    public Float getbMI() {
        return this.bMI;
    }

    public BenhNhan bMI(Float bMI) {
        this.setbMI(bMI);
        return this;
    }

    public void setbMI(Float bMI) {
        this.bMI = bMI;
    }

    public String getMaTheBHYT() {
        return this.maTheBHYT;
    }

    public BenhNhan maTheBHYT(String maTheBHYT) {
        this.setMaTheBHYT(maTheBHYT);
        return this;
    }

    public void setMaTheBHYT(String maTheBHYT) {
        this.maTheBHYT = maTheBHYT;
    }

    public Integer getsDT() {
        return this.sDT;
    }

    public BenhNhan sDT(Integer sDT) {
        this.setsDT(sDT);
        return this;
    }

    public void setsDT(Integer sDT) {
        this.sDT = sDT;
    }

    public BaoHiem getBaoHiem() {
        return this.baoHiem;
    }

    public void setBaoHiem(BaoHiem baoHiem) {
        this.baoHiem = baoHiem;
    }

    public BenhNhan baoHiem(BaoHiem baoHiem) {
        this.setBaoHiem(baoHiem);
        return this;
    }

    public DieuTri getDieuTri() {
        return this.dieuTri;
    }

    public void setDieuTri(DieuTri dieuTri) {
        this.dieuTri = dieuTri;
    }

    public BenhNhan dieuTri(DieuTri dieuTri) {
        this.setDieuTri(dieuTri);
        return this;
    }

    public TTSanglocVaDanhGiaDD getTTSanglocVaDanhGiaDD() {
        return this.tTSanglocVaDanhGiaDD;
    }

    public void setTTSanglocVaDanhGiaDD(TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD) {
        this.tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDD;
    }

    public BenhNhan tTSanglocVaDanhGiaDD(TTSanglocVaDanhGiaDD tTSanglocVaDanhGiaDD) {
        this.setTTSanglocVaDanhGiaDD(tTSanglocVaDanhGiaDD);
        return this;
    }

    public HoaDon getHoaDon() {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public BenhNhan hoaDon(HoaDon hoaDon) {
        this.setHoaDon(hoaDon);
        return this;
    }

    public DanhGiaCanThiepDD getDanhGiaCanThiepDD() {
        return this.danhGiaCanThiepDD;
    }

    public void setDanhGiaCanThiepDD(DanhGiaCanThiepDD danhGiaCanThiepDD) {
        this.danhGiaCanThiepDD = danhGiaCanThiepDD;
    }

    public BenhNhan danhGiaCanThiepDD(DanhGiaCanThiepDD danhGiaCanThiepDD) {
        this.setDanhGiaCanThiepDD(danhGiaCanThiepDD);
        return this;
    }

    public PhieuSuatAn getPhieuSuatAn() {
        return this.phieuSuatAn;
    }

    public void setPhieuSuatAn(PhieuSuatAn phieuSuatAn) {
        this.phieuSuatAn = phieuSuatAn;
    }

    public BenhNhan phieuSuatAn(PhieuSuatAn phieuSuatAn) {
        this.setPhieuSuatAn(phieuSuatAn);
        return this;
    }

    public GiayMoiDanhGia getGiayMoiDanhGia() {
        return this.giayMoiDanhGia;
    }

    public void setGiayMoiDanhGia(GiayMoiDanhGia giayMoiDanhGia) {
        this.giayMoiDanhGia = giayMoiDanhGia;
    }

    public BenhNhan giayMoiDanhGia(GiayMoiDanhGia giayMoiDanhGia) {
        this.setGiayMoiDanhGia(giayMoiDanhGia);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BenhNhan)) {
            return false;
        }
        return id != null && id.equals(((BenhNhan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BenhNhan{" +
            "id=" + getId() +
            ", hoTen='" + getHoTen() + "'" +
            ", maBN='" + getMaBN() + "'" +
            ", ngaySinh='" + getNgaySinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", ngheNghiep='" + getNgheNghiep() + "'" +
            ", chieuCao=" + getChieuCao() +
            ", canHT=" + getCanHT() +
            ", canTC=" + getCanTC() +
            ", vongCT=" + getVongCT() +
            ", bMI=" + getbMI() +
            ", maTheBHYT='" + getMaTheBHYT() + "'" +
            ", sDT=" + getsDT() +
            "}";
    }
}
