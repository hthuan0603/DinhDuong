package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BaoCaoTaiChinh.
 */
@Entity
@Table(name = "bao_cao_tai_chinh")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BaoCaoTaiChinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_bao_cao")
    private Integer maBaoCao;

    @Column(name = "luu_tru")
    private Integer luuTru;

    @Column(name = "tien_an")
    private Integer tienAn;

    @Column(name = "tien_o")
    private Integer tienO;

    @Column(name = "tien_di_lai")
    private Integer tienDiLai;

    @Column(name = "tai_lieu")
    private Integer taiLieu;

    @Column(name = "giang_day")
    private Integer giangDay;

    @Column(name = "khac")
    private Integer khac;

    @JsonIgnoreProperties(value = { "lyDoCongTac", "nhanVienTiepNhan" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private ChiDaoTuyen chiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BaoCaoTaiChinh id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaBaoCao() {
        return this.maBaoCao;
    }

    public BaoCaoTaiChinh maBaoCao(Integer maBaoCao) {
        this.setMaBaoCao(maBaoCao);
        return this;
    }

    public void setMaBaoCao(Integer maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public Integer getLuuTru() {
        return this.luuTru;
    }

    public BaoCaoTaiChinh luuTru(Integer luuTru) {
        this.setLuuTru(luuTru);
        return this;
    }

    public void setLuuTru(Integer luuTru) {
        this.luuTru = luuTru;
    }

    public Integer getTienAn() {
        return this.tienAn;
    }

    public BaoCaoTaiChinh tienAn(Integer tienAn) {
        this.setTienAn(tienAn);
        return this;
    }

    public void setTienAn(Integer tienAn) {
        this.tienAn = tienAn;
    }

    public Integer getTienO() {
        return this.tienO;
    }

    public BaoCaoTaiChinh tienO(Integer tienO) {
        this.setTienO(tienO);
        return this;
    }

    public void setTienO(Integer tienO) {
        this.tienO = tienO;
    }

    public Integer getTienDiLai() {
        return this.tienDiLai;
    }

    public BaoCaoTaiChinh tienDiLai(Integer tienDiLai) {
        this.setTienDiLai(tienDiLai);
        return this;
    }

    public void setTienDiLai(Integer tienDiLai) {
        this.tienDiLai = tienDiLai;
    }

    public Integer getTaiLieu() {
        return this.taiLieu;
    }

    public BaoCaoTaiChinh taiLieu(Integer taiLieu) {
        this.setTaiLieu(taiLieu);
        return this;
    }

    public void setTaiLieu(Integer taiLieu) {
        this.taiLieu = taiLieu;
    }

    public Integer getGiangDay() {
        return this.giangDay;
    }

    public BaoCaoTaiChinh giangDay(Integer giangDay) {
        this.setGiangDay(giangDay);
        return this;
    }

    public void setGiangDay(Integer giangDay) {
        this.giangDay = giangDay;
    }

    public Integer getKhac() {
        return this.khac;
    }

    public BaoCaoTaiChinh khac(Integer khac) {
        this.setKhac(khac);
        return this;
    }

    public void setKhac(Integer khac) {
        this.khac = khac;
    }

    public ChiDaoTuyen getChiDaoTuyen() {
        return this.chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    public BaoCaoTaiChinh chiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.setChiDaoTuyen(chiDaoTuyen);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaoCaoTaiChinh)) {
            return false;
        }
        return id != null && id.equals(((BaoCaoTaiChinh) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BaoCaoTaiChinh{" +
            "id=" + getId() +
            ", maBaoCao=" + getMaBaoCao() +
            ", luuTru=" + getLuuTru() +
            ", tienAn=" + getTienAn() +
            ", tienO=" + getTienO() +
            ", tienDiLai=" + getTienDiLai() +
            ", taiLieu=" + getTaiLieu() +
            ", giangDay=" + getGiangDay() +
            ", khac=" + getKhac() +
            "}";
    }
}
