package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NhanVienTiepNhan.
 */
@Entity
@Table(name = "nhan_vien_tiep_nhan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NhanVienTiepNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_nhan_vien")
    private Integer maNhanVien;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @OneToMany(mappedBy = "nhanVienTiepNhan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyDoCongTac", "nhanVienTiepNhan" }, allowSetters = true)
    private Set<ChiDaoTuyen> chiDaoTuyens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NhanVienTiepNhan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaNhanVien() {
        return this.maNhanVien;
    }

    public NhanVienTiepNhan maNhanVien(Integer maNhanVien) {
        this.setMaNhanVien(maNhanVien);
        return this;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return this.tenNhanVien;
    }

    public NhanVienTiepNhan tenNhanVien(String tenNhanVien) {
        this.setTenNhanVien(tenNhanVien);
        return this;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Set<ChiDaoTuyen> getChiDaoTuyens() {
        return this.chiDaoTuyens;
    }

    public void setChiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        if (this.chiDaoTuyens != null) {
            this.chiDaoTuyens.forEach(i -> i.setNhanVienTiepNhan(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setNhanVienTiepNhan(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public NhanVienTiepNhan chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public NhanVienTiepNhan addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setNhanVienTiepNhan(this);
        return this;
    }

    public NhanVienTiepNhan removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setNhanVienTiepNhan(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhanVienTiepNhan)) {
            return false;
        }
        return id != null && id.equals(((NhanVienTiepNhan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanVienTiepNhan{" +
            "id=" + getId() +
            ", maNhanVien=" + getMaNhanVien() +
            ", tenNhanVien='" + getTenNhanVien() + "'" +
            "}";
    }
}
