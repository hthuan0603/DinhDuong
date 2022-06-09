package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BaoHiem.
 */
@Entity
@Table(name = "bao_hiem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BaoHiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_the_bhyt")
    private String maTheBHYT;

    @Column(name = "doi_tuong")
    private String doiTuong;

    @Column(name = "bao_hiem_thanh_toan")
    private Integer baoHiemThanhToan;

    @OneToOne
    @JoinColumn(unique = true)
    private HoaDon hoaDon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BaoHiem id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaTheBHYT() {
        return this.maTheBHYT;
    }

    public BaoHiem maTheBHYT(String maTheBHYT) {
        this.setMaTheBHYT(maTheBHYT);
        return this;
    }

    public void setMaTheBHYT(String maTheBHYT) {
        this.maTheBHYT = maTheBHYT;
    }

    public String getDoiTuong() {
        return this.doiTuong;
    }

    public BaoHiem doiTuong(String doiTuong) {
        this.setDoiTuong(doiTuong);
        return this;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public Integer getBaoHiemThanhToan() {
        return this.baoHiemThanhToan;
    }

    public BaoHiem baoHiemThanhToan(Integer baoHiemThanhToan) {
        this.setBaoHiemThanhToan(baoHiemThanhToan);
        return this;
    }

    public void setBaoHiemThanhToan(Integer baoHiemThanhToan) {
        this.baoHiemThanhToan = baoHiemThanhToan;
    }

    public HoaDon getHoaDon() {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public BaoHiem hoaDon(HoaDon hoaDon) {
        this.setHoaDon(hoaDon);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaoHiem)) {
            return false;
        }
        return id != null && id.equals(((BaoHiem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BaoHiem{" +
            "id=" + getId() +
            ", maTheBHYT='" + getMaTheBHYT() + "'" +
            ", doiTuong='" + getDoiTuong() + "'" +
            ", baoHiemThanhToan=" + getBaoHiemThanhToan() +
            "}";
    }
}
