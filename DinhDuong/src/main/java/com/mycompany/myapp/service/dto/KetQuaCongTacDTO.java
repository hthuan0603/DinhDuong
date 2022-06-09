package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.KetQuaCongTac} entity.
 */
public class KetQuaCongTacDTO implements Serializable {

    private Long id;

    private Integer maKetQua;

    private String tenKetQua;

    private Integer thuTuSX;

    private HoTroDTO hoTro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaKetQua() {
        return maKetQua;
    }

    public void setMaKetQua(Integer maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return tenKetQua;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
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
        if (!(o instanceof KetQuaCongTacDTO)) {
            return false;
        }

        KetQuaCongTacDTO ketQuaCongTacDTO = (KetQuaCongTacDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ketQuaCongTacDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KetQuaCongTacDTO{" +
            "id=" + getId() +
            ", maKetQua=" + getMaKetQua() +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            ", hoTro=" + getHoTro() +
            "}";
    }
}
