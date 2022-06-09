package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ChiDaoTuyen} entity.
 */
public class ChiDaoTuyenDTO implements Serializable {

    private Long id;

    private Integer maCdt;

    private Integer soQuyetDinh;

    private ZonedDateTime ngayQuyetDinh;

    private Integer soHD;

    private ZonedDateTime ngayHD;

    private String lyDoCT;

    private String noiDung;

    private String noiCongTac;

    private ZonedDateTime ngayBatDau;

    private ZonedDateTime ngayKetThuc;

    private String ghiChu;

    private Integer noiDungHoTro;

    private Integer baoCaoTaiChinh;

    private LyDoCongTacDTO lyDoCongTac;

    private NhanVienTiepNhanDTO nhanVienTiepNhan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaCdt() {
        return maCdt;
    }

    public void setMaCdt(Integer maCdt) {
        this.maCdt = maCdt;
    }

    public Integer getSoQuyetDinh() {
        return soQuyetDinh;
    }

    public void setSoQuyetDinh(Integer soQuyetDinh) {
        this.soQuyetDinh = soQuyetDinh;
    }

    public ZonedDateTime getNgayQuyetDinh() {
        return ngayQuyetDinh;
    }

    public void setNgayQuyetDinh(ZonedDateTime ngayQuyetDinh) {
        this.ngayQuyetDinh = ngayQuyetDinh;
    }

    public Integer getSoHD() {
        return soHD;
    }

    public void setSoHD(Integer soHD) {
        this.soHD = soHD;
    }

    public ZonedDateTime getNgayHD() {
        return ngayHD;
    }

    public void setNgayHD(ZonedDateTime ngayHD) {
        this.ngayHD = ngayHD;
    }

    public String getLyDoCT() {
        return lyDoCT;
    }

    public void setLyDoCT(String lyDoCT) {
        this.lyDoCT = lyDoCT;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    public ZonedDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(ZonedDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public ZonedDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(ZonedDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getNoiDungHoTro() {
        return noiDungHoTro;
    }

    public void setNoiDungHoTro(Integer noiDungHoTro) {
        this.noiDungHoTro = noiDungHoTro;
    }

    public Integer getBaoCaoTaiChinh() {
        return baoCaoTaiChinh;
    }

    public void setBaoCaoTaiChinh(Integer baoCaoTaiChinh) {
        this.baoCaoTaiChinh = baoCaoTaiChinh;
    }

    public LyDoCongTacDTO getLyDoCongTac() {
        return lyDoCongTac;
    }

    public void setLyDoCongTac(LyDoCongTacDTO lyDoCongTac) {
        this.lyDoCongTac = lyDoCongTac;
    }

    public NhanVienTiepNhanDTO getNhanVienTiepNhan() {
        return nhanVienTiepNhan;
    }

    public void setNhanVienTiepNhan(NhanVienTiepNhanDTO nhanVienTiepNhan) {
        this.nhanVienTiepNhan = nhanVienTiepNhan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiDaoTuyenDTO)) {
            return false;
        }

        ChiDaoTuyenDTO chiDaoTuyenDTO = (ChiDaoTuyenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiDaoTuyenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiDaoTuyenDTO{" +
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
            ", lyDoCongTac=" + getLyDoCongTac() +
            ", nhanVienTiepNhan=" + getNhanVienTiepNhan() +
            "}";
    }
}
