package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LyDoCongTac.
 */
@Entity
@Table(name = "ly_do_cong_tac")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LyDoCongTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_ly_do")
    private Integer maLyDo;

    @Column(name = "ten_ly_do")
    private String tenLyDo;

    @Column(name = "thu_tu_sx")
    private Integer thuTuSX;

    @OneToMany(mappedBy = "lyDoCongTac")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "lyDoCongTac", "nhanVienTiepNhan" }, allowSetters = true)
    private Set<ChiDaoTuyen> chiDaoTuyens = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LyDoCongTac id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaLyDo() {
        return this.maLyDo;
    }

    public LyDoCongTac maLyDo(Integer maLyDo) {
        this.setMaLyDo(maLyDo);
        return this;
    }

    public void setMaLyDo(Integer maLyDo) {
        this.maLyDo = maLyDo;
    }

    public String getTenLyDo() {
        return this.tenLyDo;
    }

    public LyDoCongTac tenLyDo(String tenLyDo) {
        this.setTenLyDo(tenLyDo);
        return this;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
    }

    public Integer getThuTuSX() {
        return this.thuTuSX;
    }

    public LyDoCongTac thuTuSX(Integer thuTuSX) {
        this.setThuTuSX(thuTuSX);
        return this;
    }

    public void setThuTuSX(Integer thuTuSX) {
        this.thuTuSX = thuTuSX;
    }

    public Set<ChiDaoTuyen> getChiDaoTuyens() {
        return this.chiDaoTuyens;
    }

    public void setChiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        if (this.chiDaoTuyens != null) {
            this.chiDaoTuyens.forEach(i -> i.setLyDoCongTac(null));
        }
        if (chiDaoTuyens != null) {
            chiDaoTuyens.forEach(i -> i.setLyDoCongTac(this));
        }
        this.chiDaoTuyens = chiDaoTuyens;
    }

    public LyDoCongTac chiDaoTuyens(Set<ChiDaoTuyen> chiDaoTuyens) {
        this.setChiDaoTuyens(chiDaoTuyens);
        return this;
    }

    public LyDoCongTac addChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.add(chiDaoTuyen);
        chiDaoTuyen.setLyDoCongTac(this);
        return this;
    }

    public LyDoCongTac removeChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyens.remove(chiDaoTuyen);
        chiDaoTuyen.setLyDoCongTac(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LyDoCongTac)) {
            return false;
        }
        return id != null && id.equals(((LyDoCongTac) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LyDoCongTac{" +
            "id=" + getId() +
            ", maLyDo=" + getMaLyDo() +
            ", tenLyDo='" + getTenLyDo() + "'" +
            ", thuTuSX=" + getThuTuSX() +
            "}";
    }
}
