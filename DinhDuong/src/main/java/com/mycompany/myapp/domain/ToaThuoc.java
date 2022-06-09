package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ToaThuoc.
 */
@Entity
@Table(name = "toa_thuoc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ToaThuoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "i_cd_10")
    private String iCD10;

    @Column(name = "ma_ba")
    private String maBA;

    @Column(name = "ma_bn")
    private String maBN;

    @Column(name = "loai_thuoc")
    private String loaiThuoc;

    @Column(name = "doi_tuong")
    private String doiTuong;

    @Column(name = "ti_le")
    private Float tiLe;

    @Column(name = "so_ngay_hen_tai_kham")
    private Integer soNgayHenTaiKham;

    @Column(name = "cap_phieu_hen_kham")
    private Boolean capPhieuHenKham;

    @Column(name = "loi_dan_bac_si")
    private String loiDanBacSi;

    @Column(name = "ngay_chi_dinh")
    private ZonedDateTime ngayChiDinh;

    @Column(name = "ngay_dung")
    private ZonedDateTime ngayDung;

    @Column(name = "so_ngayke_don")
    private Integer soNgaykeDon;

    @Column(name = "kho_thuoc")
    private Boolean khoThuoc;

    @OneToOne
    @JoinColumn(unique = true)
    private Thuoc thuoc;

    @OneToOne
    @JoinColumn(unique = true)
    private HoaDon hoaDon;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "baoHiem", "dieuTri", "tTSanglocVaDanhGiaDD", "hoaDon", "danhGiaCanThiepDD", "phieuSuatAn", "giayMoiDanhGia" },
        allowSetters = true
    )
    private BenhNhan benhNhan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ToaThuoc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getiCD10() {
        return this.iCD10;
    }

    public ToaThuoc iCD10(String iCD10) {
        this.setiCD10(iCD10);
        return this;
    }

    public void setiCD10(String iCD10) {
        this.iCD10 = iCD10;
    }

    public String getMaBA() {
        return this.maBA;
    }

    public ToaThuoc maBA(String maBA) {
        this.setMaBA(maBA);
        return this;
    }

    public void setMaBA(String maBA) {
        this.maBA = maBA;
    }

    public String getMaBN() {
        return this.maBN;
    }

    public ToaThuoc maBN(String maBN) {
        this.setMaBN(maBN);
        return this;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getLoaiThuoc() {
        return this.loaiThuoc;
    }

    public ToaThuoc loaiThuoc(String loaiThuoc) {
        this.setLoaiThuoc(loaiThuoc);
        return this;
    }

    public void setLoaiThuoc(String loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public String getDoiTuong() {
        return this.doiTuong;
    }

    public ToaThuoc doiTuong(String doiTuong) {
        this.setDoiTuong(doiTuong);
        return this;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public Float getTiLe() {
        return this.tiLe;
    }

    public ToaThuoc tiLe(Float tiLe) {
        this.setTiLe(tiLe);
        return this;
    }

    public void setTiLe(Float tiLe) {
        this.tiLe = tiLe;
    }

    public Integer getSoNgayHenTaiKham() {
        return this.soNgayHenTaiKham;
    }

    public ToaThuoc soNgayHenTaiKham(Integer soNgayHenTaiKham) {
        this.setSoNgayHenTaiKham(soNgayHenTaiKham);
        return this;
    }

    public void setSoNgayHenTaiKham(Integer soNgayHenTaiKham) {
        this.soNgayHenTaiKham = soNgayHenTaiKham;
    }

    public Boolean getCapPhieuHenKham() {
        return this.capPhieuHenKham;
    }

    public ToaThuoc capPhieuHenKham(Boolean capPhieuHenKham) {
        this.setCapPhieuHenKham(capPhieuHenKham);
        return this;
    }

    public void setCapPhieuHenKham(Boolean capPhieuHenKham) {
        this.capPhieuHenKham = capPhieuHenKham;
    }

    public String getLoiDanBacSi() {
        return this.loiDanBacSi;
    }

    public ToaThuoc loiDanBacSi(String loiDanBacSi) {
        this.setLoiDanBacSi(loiDanBacSi);
        return this;
    }

    public void setLoiDanBacSi(String loiDanBacSi) {
        this.loiDanBacSi = loiDanBacSi;
    }

    public ZonedDateTime getNgayChiDinh() {
        return this.ngayChiDinh;
    }

    public ToaThuoc ngayChiDinh(ZonedDateTime ngayChiDinh) {
        this.setNgayChiDinh(ngayChiDinh);
        return this;
    }

    public void setNgayChiDinh(ZonedDateTime ngayChiDinh) {
        this.ngayChiDinh = ngayChiDinh;
    }

    public ZonedDateTime getNgayDung() {
        return this.ngayDung;
    }

    public ToaThuoc ngayDung(ZonedDateTime ngayDung) {
        this.setNgayDung(ngayDung);
        return this;
    }

    public void setNgayDung(ZonedDateTime ngayDung) {
        this.ngayDung = ngayDung;
    }

    public Integer getSoNgaykeDon() {
        return this.soNgaykeDon;
    }

    public ToaThuoc soNgaykeDon(Integer soNgaykeDon) {
        this.setSoNgaykeDon(soNgaykeDon);
        return this;
    }

    public void setSoNgaykeDon(Integer soNgaykeDon) {
        this.soNgaykeDon = soNgaykeDon;
    }

    public Boolean getKhoThuoc() {
        return this.khoThuoc;
    }

    public ToaThuoc khoThuoc(Boolean khoThuoc) {
        this.setKhoThuoc(khoThuoc);
        return this;
    }

    public void setKhoThuoc(Boolean khoThuoc) {
        this.khoThuoc = khoThuoc;
    }

    public Thuoc getThuoc() {
        return this.thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public ToaThuoc thuoc(Thuoc thuoc) {
        this.setThuoc(thuoc);
        return this;
    }

    public HoaDon getHoaDon() {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ToaThuoc hoaDon(HoaDon hoaDon) {
        this.setHoaDon(hoaDon);
        return this;
    }

    public BenhNhan getBenhNhan() {
        return this.benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public ToaThuoc benhNhan(BenhNhan benhNhan) {
        this.setBenhNhan(benhNhan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToaThuoc)) {
            return false;
        }
        return id != null && id.equals(((ToaThuoc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ToaThuoc{" +
            "id=" + getId() +
            ", iCD10='" + getiCD10() + "'" +
            ", maBA='" + getMaBA() + "'" +
            ", maBN='" + getMaBN() + "'" +
            ", loaiThuoc='" + getLoaiThuoc() + "'" +
            ", doiTuong='" + getDoiTuong() + "'" +
            ", tiLe=" + getTiLe() +
            ", soNgayHenTaiKham=" + getSoNgayHenTaiKham() +
            ", capPhieuHenKham='" + getCapPhieuHenKham() + "'" +
            ", loiDanBacSi='" + getLoiDanBacSi() + "'" +
            ", ngayChiDinh='" + getNgayChiDinh() + "'" +
            ", ngayDung='" + getNgayDung() + "'" +
            ", soNgaykeDon=" + getSoNgaykeDon() +
            ", khoThuoc='" + getKhoThuoc() + "'" +
            "}";
    }
}
