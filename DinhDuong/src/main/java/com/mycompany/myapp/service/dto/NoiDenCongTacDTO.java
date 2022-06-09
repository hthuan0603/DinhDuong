package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.NoiDenCongTac} entity.
 */
public class NoiDenCongTacDTO implements Serializable {

    private Long id;

    private Integer maNoiDen;

    private String tenNoiDen;

    private Integer thuTuSX;

    private ChiDaoTuyenDTO chiDaoTuyen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaNoiDen() {
        return maNoiDen;
    }

    public void setMaNoiDen(Integer maNoiDen) {
        this.maNoiDen = maNoiDen;
    }

    public String getTenNoiDen() {
        return tenNoiDen;
    }

    public void setTenNoiDen(String tenNoiDen) {
        this.tenNoiDen = tenNoiDen;
    }

    public Integer getThuTuSX() {
        return thuTuSX;
    }

    public void setThuTuSX(Integer thuTuSX) {
        this.thuTuSX = thuTuSX;
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
        if (!(o instanceof NoiDenCongTacDTO)) {
            return false;
        }

        NoiDenCongTacDTO noiDenCongTacDTO = (NoiDenCongTacDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, noiDenCongTacDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoiDenCongTacDTO{" +
            "id=" + getId() +
            ", maNoiDen=" + getMaNoiDen() +
            ", tenNoiDen='" + getTenNoiDen() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            ", chiDaoTuyen=" + getChiDaoTuyen() +
            "}";
    }
}
