package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.VatTuHoTro} entity.
 */
public class VatTuHoTroDTO implements Serializable {

    private Long id;

    private Integer maVatTu;

    private String tenVatTu;

    private Integer thuTuSX;

    private HoTroDTO hoTro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(Integer maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
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
        if (!(o instanceof VatTuHoTroDTO)) {
            return false;
        }

        VatTuHoTroDTO vatTuHoTroDTO = (VatTuHoTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vatTuHoTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VatTuHoTroDTO{" +
            "id=" + getId() +
            ", maVatTu=" + getMaVatTu() +
            ", tenVatTu='" + getTenVatTu() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            ", hoTro=" + getHoTro() +
            "}";
    }
}
