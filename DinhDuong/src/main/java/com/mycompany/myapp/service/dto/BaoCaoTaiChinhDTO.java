package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.BaoCaoTaiChinh} entity.
 */
public class BaoCaoTaiChinhDTO implements Serializable {

    private Long id;

    private Integer maBaoCao;

    private Integer luuTru;

    private Integer tienAn;

    private Integer tienO;

    private Integer tienDiLai;

    private Integer taiLieu;

    private Integer giangDay;

    private Integer khac;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaBaoCao() {
        return maBaoCao;
    }

    public void setMaBaoCao(Integer maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public Integer getLuuTru() {
        return luuTru;
    }

    public void setLuuTru(Integer luuTru) {
        this.luuTru = luuTru;
    }

    public Integer getTienAn() {
        return tienAn;
    }

    public void setTienAn(Integer tienAn) {
        this.tienAn = tienAn;
    }

    public Integer getTienO() {
        return tienO;
    }

    public void setTienO(Integer tienO) {
        this.tienO = tienO;
    }

    public Integer getTienDiLai() {
        return tienDiLai;
    }

    public void setTienDiLai(Integer tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public Integer getTaiLieu() {
        return taiLieu;
    }

    public void setTaiLieu(Integer taiLieu) {
        this.taiLieu = taiLieu;
    }

    public Integer getGiangDay() {
        return giangDay;
    }

    public void setGiangDay(Integer giangDay) {
        this.giangDay = giangDay;
    }

    public Integer getKhac() {
        return khac;
    }

    public void setKhac(Integer khac) {
        this.khac = khac;
    }

    public ChiDaoTuyenDTO getChiDaoTuyen() {
        return chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyenDTO chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaoCaoTaiChinhDTO)) {
            return false;
        }

        BaoCaoTaiChinhDTO baoCaoTaiChinhDTO = (BaoCaoTaiChinhDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, baoCaoTaiChinhDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BaoCaoTaiChinhDTO{" +
            "id=" + getId() +
            ", maBaoCao=" + getMaBaoCao() +
            ", luuTru=" + getLuuTru() +
            ", tienAn=" + getTienAn() +
            ", tienO=" + getTienO() +
            ", tienDiLai=" + getTienDiLai() +
            ", taiLieu=" + getTaiLieu() +
            ", giangDay=" + getGiangDay() +
            ", khac=" + getKhac() +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
