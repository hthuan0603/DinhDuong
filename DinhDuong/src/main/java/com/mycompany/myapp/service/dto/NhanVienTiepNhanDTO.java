package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.NhanVienTiepNhan} entity.
 */
public class NhanVienTiepNhanDTO implements Serializable {

    private Long id;

    private Integer maNhanVien;

    private String tenNhanVien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhanVienTiepNhanDTO)) {
            return false;
        }

        NhanVienTiepNhanDTO nhanVienTiepNhanDTO = (NhanVienTiepNhanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nhanVienTiepNhanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanVienTiepNhanDTO{" +
            "id=" + getId() +
            ", maNhanVien=" + getMaNhanVien() +
            ", tenNhanVien='" + getTenNhanVien() + "'" +
            "}";
    }
}
