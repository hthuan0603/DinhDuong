package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ChiDaoTuyen.
 */
@Entity
@Table(name = "chi_dao_tuyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChiDaoTuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_cdt")
    private Integer maCdt;

    @Column(name = "so_quyet_dinh")
    private Integer soQuyetDinh;

    @Column(name = "ngay_quyet_dinh")
    private ZonedDateTime ngayQuyetDinh;

    @Column(name = "so_hd")
    private Integer soHD;

    @Column(name = "ngay_hd")
    private ZonedDateTime ngayHD;

    @Column(name = "ly_do_ct")
    private String lyDoCT;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "noi_cong_tac")
    private String noiCongTac;

    @Column(name = "ngay_bat_dau")
    private ZonedDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private ZonedDateTime ngayKetThuc;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "noi_dung_ho_tro")
    private Integer noiDungHoTro;

    @Column(name = "bao_cao_tai_chinh")
    private Integer baoCaoTaiChinh;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private LyDoCongTac lyDoCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "chiDaoTuyens" }, allowSetters = true)
    private NhanVienTiepNhan nhanVienTiepNhan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChiDaoTuyen id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaCdt() {
        return this.maCdt;
    }

    public ChiDaoTuyen maCdt(Integer maCdt) {
        this.setMaCdt(maCdt);
        return this;
    }

    public void setMaCdt(Integer maCdt) {
        this.maCdt = maCdt;
    }

    public Integer getSoQuyetDinh() {
        return this.soQuyetDinh;
    }

    public ChiDaoTuyen soQuyetDinh(Integer soQuyetDinh) {
        this.setSoQuyetDinh(soQuyetDinh);
        return this;
    }

    public void setSoQuyetDinh(Integer soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public ZonedDateTime getNgayQuyetDinh() {
        return this.ngayQuyetDinh;
    }

    public ChiDaoTuyen ngayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.setNgayQuyetDinh(ngayQuyetDinh);
        return this;
    }

    public void setNgayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public Integer getSoHD() {
        return this.soHD;
    }

    public ChiDaoTuyen soHD(Integer soHD) {
        this.setSoHD(soHD);
        return this;
    }

    public void setSoHD(Integer soHD) {
        this.soHD = soHD;
    }

    public ZonedDateTime getNgayHD() {
        return this.ngayHD;
    }

    public ChiDaoTuyen ngayHD(ZonedDateTime ngayHD) {
        this.setNgayHD(ngayHD);
        return this;
    }

    public void setNgayHD(ZonedDateTime ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getLyDoCT() {
        return this.lyDoCT;
    }

    public ChiDaoTuyen lyDoCT(String lyDoCT) {
        this.setLyDoCT(lyDoCT);
        return this;
    }

    public void setLyDoCT(String lyDoCT) {
        this.lyDoCT = lyDoCT;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public ChiDaoTuyen noiDung(String noiDung) {
        this.setNoiDung(noiDung);
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiCongTac() {
        return this.noiCongTac;
    }

    public ChiDaoTuyen noiCongTac(String noiCongTac) {
        this.setNoiCongTac(noiCongTac);
        return this;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    public ZonedDateTime getNgayBatDau() {
        return this.ngayBatDau;
    }

    public ChiDaoTuyen ngayBatDau(ZonedDateTime ngayBatDau) {
        this.setNgayBatDau(ngayBatDau);
        return this;
    }

    public void setNgayBatDau(ZonedDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public ZonedDateTime getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public ChiDaoTuyen ngayKetThuc(ZonedDateTime ngayKetThuc) {
        this.setNgayKetThuc(ngayKetThuc);
        return this;
    }

    public void setNgayKetThuc(ZonedDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public ChiDaoTuyen ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getNoiDungHoTro() {
        return this.noiDungHoTro;
    }

    public ChiDaoTuyen noiDungHoTro(Integer noiDungHoTro) {
        this.setNoiDungHoTro(noiDungHoTro);
        return this;
    }

    public void setNoiDungHoTro(Integer noiDungHoTro) {
        this.noiDungHoTro = noiDungHoTro;
    }

    public Integer getBaoCaoTaiChinh() {
        return this.baoCaoTaiChinh;
    }

    public ChiDaoTuyen baoCaoTaiChinh(Integer baoCaoTaiChinh) {
        this.setBaoCaoTaiChinh(baoCaoTaiChinh);
        return this;
    }

    public void setBaoCaoTaiChinh(Integer baoCaoTaiChinh) {
        this.baoCaoTaiChinh = baoCaoTaiChinh;
    }

    public LyDoCongTac getLyDoCongTac() {
        return this.lyDoCongTac;
    }

    public void setLyDoCongTac(LyDoCongTac lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public ChiDaoTuyen lyDoCongTac(LyDoCongTac lyDoCongTac) {
        this.setLyDoCongTac(lyDoCongTac);
        return this;
    }

    public NhanVienTiepNhan getNhanVienTiepNhan() {
        return this.nhanVienTiepNhan;
    }

    public void setNhanVienTiepNhan(NhanVienTiepNhan nhanVienTiepNhan) {
        this.nhanVienTiepNhan = nhanVienTiepNhan;
    }

    public ChiDaoTuyen nhanVienTiepNhan(NhanVienTiepNhan nhanVienTiepNhan) {
        this.setNhanVienTiepNhan(nhanVienTiepNhan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiDaoTuyen)) {
            return false;
        }
        return id != null && id.equals(((ChiDaoTuyen) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyen{" +
            "id=" + getId() +
            ", maCdt=" + getMaCdt() +
            ", soQuyetDinh=" + getSoQuyetDinh() +
            ", ngayQuyetDinh='" + getNgayQuyetDinh() + "'" +
            ", soHD=" + getSoHD() +
            ", ngayHD='" + getNgayHD() + "'" +
            ", lyDoCT='" + getLyDoCT() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", noiCongTac='" + getNoiCongTac() + "'" +
            ", ngayBatDau='" + getNgayBatDau() + "'" +
            ", ngayKetThuc='" + getNgayKetThuc() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", noiDungHoTro=" + getNoiDungHoTro() +
            ", baoCaoTaiChinh=" + getBaoCaoTaiChinh() +
            "}";
    }
}
