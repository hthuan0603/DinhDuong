package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A KetQuaCongTac.
 */
@Entity
@Table(name = "ket_qua_cong_tac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KetQuaCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ket_qua")
    private Integer maKetQua;

    @Column(name = "ten_ket_qua")
    private String tenKetQua;

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

    public KetQuaCongTac id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaKetQua() {
        return this.maKetQua;
    }

    public KetQuaCongTac maKetQua(Integer maKetQua) {
        this.setMaKetQua(maKetQua);
        return this;
    }

    public void setMaKetQua(Integer maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return this.tenKetQua;
    }

    public KetQuaCongTac tenKetQua(String tenKetQua) {
        this.setTenKetQua(tenKetQua);
        return this;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
    }

    public Integer getThuTuSX() {
        return this.thuTuSX;
    }

    public KetQuaCongTac thuTuSX(Integer thuTuSX) {
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

    public KetQuaCongTac hoTro(HoTro hoTro) {
        this.setHoTro(hoTro);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KetQuaCongTac)) {
            return false;
        }
        return id != null && id.equals(((KetQuaCongTac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KetQuaCongTac{" +
            "id=" + getId() +
            ", maKetQua=" + getMaKetQua() +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            "}";
    }
}
