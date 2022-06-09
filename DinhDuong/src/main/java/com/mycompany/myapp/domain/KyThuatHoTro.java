package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A KyThuatHoTro.
 */
@Entity
@Table(name = "ky_thuat_ho_tro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KyThuatHoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ky_thuat")
    private Integer maKyThuat;

    @Column(name = "ten_ky_thuat")
    private String tenKyThuat;

    @Column(name = "thu_tu_sx")
    private Integer thuTuSX;

    @JsonIgnoreProperties(value = { "chiDaoTuyen" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private HoTro hoTro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public KyThuatHoTro id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaKyThuat() {
        return this.maKyThuat;
    }

    public KyThuatHoTro maKyThuat(Integer maKyThuat) {
        this.setMaKyThuat(maKyThuat);
        return this;
    }

    public void setMaKyThuat(Integer maKyThuat) {
        this.maKyThuat = maKyThuat;
    }

    public String getTenKyThuat() {
        return this.tenKyThuat;
    }

    public KyThuatHoTro tenKyThuat(String tenKyThuat) {
        this.setTenKyThuat(tenKyThuat);
        return this;
    }

    public void setTenKyThuat(String tenKyThuat) {
        this.tenKyThuat = tenKyThuat;
    }

    public Integer getThuTuSX() {
        return this.thuTuSX;
    }

    public KyThuatHoTro thuTuSX(Integer thuTuSX) {
        this.setThuTuSX(thuTuSX);
        return this;
    }

    public void setThuTuSX(Integer thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public HoTro getHoTro() {
        return this.hoTro;
    }

    public void setHoTro(HoTro hoTro) {
        this.hoTro = hoTro;
    }

    public KyThuatHoTro hoTro(HoTro hoTro) {
        this.setHoTro(hoTro);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KyThuatHoTro)) {
            return false;
        }
        return id != null && id.equals(((KyThuatHoTro) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KyThuatHoTro{" +
            "id=" + getId() +
            ", maKyThuat=" + getMaKyThuat() +
            ", tenKyThuat='" + getTenKyThuat() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            "}";
    }
}
