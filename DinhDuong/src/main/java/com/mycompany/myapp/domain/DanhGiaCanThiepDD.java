package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhGiaCanThiepDD.
 */
@Entity
@Table(name = "danh_gia_can_thiep_dd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DanhGiaCanThiepDD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "chuan_doan_ls")
    private String chuanDoanLS;

    @Column(name = "b_s_dieu_tri")
    private String bSDieuTri;

    @Column(name = "thay_doi_can_nang_trong_1_thang")
    private String thayDoiCanNangTrong1Thang;

    @Column(name = "danh_gia_cn")
    private Integer danhGiaCN;

    @Column(name = "khau_phan_an")
    private String khauPhanAn;

    @Column(name = "danh_gia_kpa")
    private Integer danhGiaKPA;

    @Column(name = "trieu_chung_tieu_hoa")
    private String trieuChungTieuHoa;

    @Column(name = "muc_do")
    private String mucDo;

    @Column(name = "danh_gia_md")
    private Integer danhGiaMD;

    @Column(name = "giam_chuc_nang_hoat_dong")
    private String giamChucNangHoatDong;

    @Column(name = "danh_gia_cnhd")
    private Integer danhGiaCNHD;

    @Column(name = "stress")
    private String stress;

    @Column(name = "danh_gia_stress")
    private Integer danhGiaStress;

    @Column(name = "refeeding")
    private Boolean refeeding;

    @Column(name = "danh_gia_refeeding")
    private Integer danhGiaRefeeding;

    @Column(name = "tong_diem")
    private Integer tongDiem;

    @JsonIgnoreProperties(value = { "hoaDon" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private PhieuSuatAn phieuSuatAn;

    @JsonIgnoreProperties(value = { "thuoc", "hoaDon", "benhNhan" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private ToaThuoc toaThuoc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhGiaCanThiepDD id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public DanhGiaCanThiepDD maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getChuanDoanLS() {
        return this.chuanDoanLS;
    }

    public DanhGiaCanThiepDD chuanDoanLS(String chuanDoanLS) {
        this.setChuanDoanLS(chuanDoanLS);
        return this;
    }

    public void setChuanDoanLS(String chuanDoanLS) {
        this.chuanDoanLS = chuanDoanLS;
    }

    public String getbSDieuTri() {
        return this.bSDieuTri;
    }

    public DanhGiaCanThiepDD bSDieuTri(String bSDieuTri) {
        this.setbSDieuTri(bSDieuTri);
        return this;
    }

    public void setbSDieuTri(String bSDieuTri) {
        this.bSDieuTri = bSDieuTri;
    }

    public String getThayDoiCanNangTrong1Thang() {
        return this.thayDoiCanNangTrong1Thang;
    }

    public DanhGiaCanThiepDD thayDoiCanNangTrong1Thang(String thayDoiCanNangTrong1Thang) {
        this.setThayDoiCanNangTrong1Thang(thayDoiCanNangTrong1Thang);
        return this;
    }

    public void setThayDoiCanNangTrong1Thang(String thayDoiCanNangTrong1Thang) {
        this.thayDoiCanNangTrong1Thang = thayDoiCanNangTrong1Thang;
    }

    public Integer getDanhGiaCN() {
        return this.danhGiaCN;
    }

    public DanhGiaCanThiepDD danhGiaCN(Integer danhGiaCN) {
        this.setDanhGiaCN(danhGiaCN);
        return this;
    }

    public void setDanhGiaCN(Integer danhGiaCN) {
        this.danhGiaCN = danhGiaCN;
    }

    public String getKhauPhanAn() {
        return this.khauPhanAn;
    }

    public DanhGiaCanThiepDD khauPhanAn(String khauPhanAn) {
        this.setKhauPhanAn(khauPhanAn);
        return this;
    }

    public void setKhauPhanAn(String khauPhanAn) {
        this.khauPhanAn = khauPhanAn;
    }

    public Integer getDanhGiaKPA() {
        return this.danhGiaKPA;
    }

    public DanhGiaCanThiepDD danhGiaKPA(Integer danhGiaKPA) {
        this.setDanhGiaKPA(danhGiaKPA);
        return this;
    }

    public void setDanhGiaKPA(Integer danhGiaKPA) {
        this.danhGiaKPA = danhGiaKPA;
    }

    public String getTrieuChungTieuHoa() {
        return this.trieuChungTieuHoa;
    }

    public DanhGiaCanThiepDD trieuChungTieuHoa(String trieuChungTieuHoa) {
        this.setTrieuChungTieuHoa(trieuChungTieuHoa);
        return this;
    }

    public void setTrieuChungTieuHoa(String trieuChungTieuHoa) {
        this.trieuChungTieuHoa = trieuChungTieuHoa;
    }

    public String getMucDo() {
        return this.mucDo;
    }

    public DanhGiaCanThiepDD mucDo(String mucDo) {
        this.setMucDo(mucDo);
        return this;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public Integer getDanhGiaMD() {
        return this.danhGiaMD;
    }

    public DanhGiaCanThiepDD danhGiaMD(Integer danhGiaMD) {
        this.setDanhGiaMD(danhGiaMD);
        return this;
    }

    public void setDanhGiaMD(Integer danhGiaMD) {
        this.danhGiaMD = danhGiaMD;
    }

    public String getGiamChucNangHoatDong() {
        return this.giamChucNangHoatDong;
    }

    public DanhGiaCanThiepDD giamChucNangHoatDong(String giamChucNangHoatDong) {
        this.setGiamChucNangHoatDong(giamChucNangHoatDong);
        return this;
    }

    public void setGiamChucNangHoatDong(String giamChucNangHoatDong) {
        this.giamChucNangHoatDong = giamChucNangHoatDong;
    }

    public Integer getDanhGiaCNHD() {
        return this.danhGiaCNHD;
    }

    public DanhGiaCanThiepDD danhGiaCNHD(Integer danhGiaCNHD) {
        this.setDanhGiaCNHD(danhGiaCNHD);
        return this;
    }

    public void setDanhGiaCNHD(Integer danhGiaCNHD) {
        this.danhGiaCNHD = danhGiaCNHD;
    }

    public String getStress() {
        return this.stress;
    }

    public DanhGiaCanThiepDD stress(String stress) {
        this.setStress(stress);
        return this;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public Integer getDanhGiaStress() {
        return this.danhGiaStress;
    }

    public DanhGiaCanThiepDD danhGiaStress(Integer danhGiaStress) {
        this.setDanhGiaStress(danhGiaStress);
        return this;
    }

    public void setDanhGiaStress(Integer danhGiaStress) {
        this.danhGiaStress = danhGiaStress;
    }

    public Boolean getRefeeding() {
        return this.refeeding;
    }

    public DanhGiaCanThiepDD refeeding(Boolean refeeding) {
        this.setRefeeding(refeeding);
        return this;
    }

    public void setRefeeding(Boolean refeeding) {
        this.refeeding = refeeding;
    }

    public Integer getDanhGiaRefeeding() {
        return this.danhGiaRefeeding;
    }

    public DanhGiaCanThiepDD danhGiaRefeeding(Integer danhGiaRefeeding) {
        this.setDanhGiaRefeeding(danhGiaRefeeding);
        return this;
    }

    public void setDanhGiaRefeeding(Integer danhGiaRefeeding) {
        this.danhGiaRefeeding = danhGiaRefeeding;
    }

    public Integer getTongDiem() {
        return this.tongDiem;
    }

    public DanhGiaCanThiepDD tongDiem(Integer tongDiem) {
        this.setTongDiem(tongDiem);
        return this;
    }

    public void setTongDiem(Integer tongDiem) {
        this.tongDiem = tongDiem;
    }

    public PhieuSuatAn getPhieuSuatAn() {
        return this.phieuSuatAn;
    }

    public void setPhieuSuatAn(PhieuSuatAn phieuSuatAn) {
        this.phieuSuatAn = phieuSuatAn;
    }

    public DanhGiaCanThiepDD phieuSuatAn(PhieuSuatAn phieuSuatAn) {
        this.setPhieuSuatAn(phieuSuatAn);
        return this;
    }

    public ToaThuoc getToaThuoc() {
        return this.toaThuoc;
    }

    public void setToaThuoc(ToaThuoc toaThuoc) {
        this.toaThuoc = toaThuoc;
    }

    public DanhGiaCanThiepDD toaThuoc(ToaThuoc toaThuoc) {
        this.setToaThuoc(toaThuoc);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhGiaCanThiepDD)) {
            return false;
        }
        return id != null && id.equals(((DanhGiaCanThiepDD) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhGiaCanThiepDD{" +
            "id=" + getId() +
            ", maBN='" + getMaBN() + "'" +
            ", chuanDoanLS='" + getChuanDoanLS() + "'" +
            ", bSDieuTri='" + getbSDieuTri() + "'" +
            ", thayDoiCanNangTrong1Thang='" + getThayDoiCanNangTrong1Thang() + "'" +
            ", danhGiaCN=" + getDanhGiaCN() +
            ", khauPhanAn='" + getKhauPhanAn() + "'" +
            ", danhGiaKPA=" + getDanhGiaKPA() +
            ", trieuChungTieuHoa='" + getTrieuChungTieuHoa() + "'" +
            ", mucDo='" + getMucDo() + "'" +
            ", danhGiaMD=" + getDanhGiaMD() +
            ", giamChucNangHoatDong='" + getGiamChucNangHoatDong() + "'" +
            ", danhGiaCNHD=" + getDanhGiaCNHD() +
            ", stress='" + getStress() + "'" +
            ", danhGiaStress=" + getDanhGiaStress() +
            ", refeeding='" + getRefeeding() + "'" +
            ", danhGiaRefeeding=" + getDanhGiaRefeeding() +
            ", tongDiem=" + getTongDiem() +
            "}";
    }
}
