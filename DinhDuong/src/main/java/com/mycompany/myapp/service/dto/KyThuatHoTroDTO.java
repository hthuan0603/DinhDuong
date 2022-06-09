package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.KyThuatHoTro} entity.
 */
public class KyThuatHoTroDTO implements Serializable {

    private Long id;

    private Integer maKyThuat;

    private String tenKyThuat;

    private Integer thuTuSX;

    private HoTroDTO hoTro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaKyThuat() {
        return maKyThuat;
    }

    public void setMaKyThuat(Integer maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public String getTenKyThuat() {
        return tenKyThuat;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public Integer getThuTuSX() {
        return thuTuSX;
    }

    public void setThuTuSX(Integer thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public HoTroDTO getHoTro() {
        return hoTro;
    }

    public void setHoTro(HoTroDTO hoTro) {
        this.hoTro = hoTro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KyThuatHoTroDTO)) {
            return false;
        }

        KyThuatHoTroDTO kyThuatHoTroDTO = (KyThuatHoTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, kyThuatHoTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyThuatHoTroDTO{" +
            "id=" + getId() +
            ", maKyThuat=" + getMaKyThuat() +
            ", tenKyThuat='" + getTenKyThuat() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            ", hoTro=" + getHoTro() +
            "}";
    }
}
