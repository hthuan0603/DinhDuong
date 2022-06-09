package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HoTro.
 */
@Entity
@Table(name = "ho_tro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HoTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_noi_dung")
    private Integer maNoiDung;

    @Column(name = "ngay_tao")
    private ZonedDateTime ngayTao;

    @Column(name = "nhan_vien_cv")
    private String nhanVienCV;

    @Column(name = "ky_thuat_ho_tro")
    private String kyThuatHoTro;

    @Column(name = "vat_tu_ho_tro")
    private String vatTuHoTro;

    @Column(name = "so_bn_kham_dieu_tri")
    private Integer soBnKhamDieuTri;

    @Column(name = "so_bn_phau_thuat")
    private Integer soBnPhauThuat;

    @Column(name = "so_can_bo_chuyen_giao")
    private Integer soCanBoChuyenGiao;

    @Column(name = "ket_qua_cong_tac")
    private String ketQuaCongTac;

    @ManyToOne
    @JsonIgnoreProperties(value = { "lyDoCongTac", "nhanVienTiepNhan" }, allowSetters = true)
    private ChiDaoTuyen chiDaoTuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HoTro id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaNoiDung() {
        return this.maNoiDung;
    }

    public HoTro maNoiDung(Integer maNoiDung) {
        this.setMaNoiDung(maNoiDung);
        return this;
    }

    public void setMaNoiDung(Integer maNoiDung) {
        this.maNoiDung = maNoiDung;
    }

    public ZonedDateTime getNgayTao() {
        return this.ngayTao;
    }

    public HoTro ngayTao(ZonedDateTime ngayTao) {
        this.setNgayTao(ngayTao);
        return this;
    }

    public void setNgayTao(ZonedDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNhanVienCV() {
        return this.nhanVienCV;
    }

    public HoTro nhanVienCV(String nhanVienCV) {
        this.setNhanVienCV(nhanVienCV);
        return this;
    }

    public void setNhanVienCV(String nhanVienCV) {
        this.nhanVienCV = nhanVienCV;
    }

    public String getKyThuatHoTro() {
        return this.kyThuatHoTro;
    }

    public HoTro kyThuatHoTro(String kyThuatHoTro) {
        this.setKyThuatHoTro(kyThuatHoTro);
        return this;
    }

    public void setKyThuatHoTro(String kyThuatHoTro) {
        this.kyThuatHoTro = kyThuatHoTro;
    }

    public String getVatTuHoTro() {
        return this.vatTuHoTro;
    }

    public HoTro vatTuHoTro(String vatTuHoTro) {
        this.setVatTuHoTro(vatTuHoTro);
        return this;
    }

    public void setVatTuHoTro(String vatTuHoTro) {
        this.vatTuHoTro = vatTuHoTro;
    }

    public Integer getSoBnKhamDieuTri() {
        return this.soBnKhamDieuTri;
    }

    public HoTro soBnKhamDieuTri(Integer soBnKhamDieuTri) {
        this.setSoBnKhamDieuTri(soBnKhamDieuTri);
        return this;
    }

    public void setSoBnKhamDieuTri(Integer soBnKhamDieuTri) {
        this.soBnKhamDieuTri = soBnKhamDieuTri;
    }

    public Integer getSoBnPhauThuat() {
        return this.soBnPhauThuat;
    }

    public HoTro soBnPhauThuat(Integer soBnPhauThuat) {
        this.setSoBnPhauThuat(soBnPhauThuat);
        return this;
    }

    public void setSoBnPhauThuat(Integer soBnPhauThuat) {
        this.soBnPhauThuat = soBnPhauThuat;
    }

    public Integer getSoCanBoChuyenGiao() {
        return this.soCanBoChuyenGiao;
    }

    public HoTro soCanBoChuyenGiao(Integer soCanBoChuyenGiao) {
        this.setSoCanBoChuyenGiao(soCanBoChuyenGiao);
        return this;
    }

    public void setSoCanBoChuyenGiao(Integer soCanBoChuyenGiao) {
        this.soCanBoChuyenGiao = soCanBoChuyenGiao;
    }

    public String getKetQuaCongTac() {
        return this.ketQuaCongTac;
    }

    public HoTro ketQuaCongTac(String ketQuaCongTac) {
        this.setKetQuaCongTac(ketQuaCongTac);
        return this;
    }

    public void setKetQuaCongTac(String ketQuaCongTac) {
        this.ketQuaCongTac = ketQuaCongTac;
    }

    public ChiDaoTuyen getChiDaoTuyen() {
        return this.chiDaoTuyen;
    }

    public void setChiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.chiDaoTuyen = chiDaoTuyen;
    }

    public HoTro chiDaoTuyen(ChiDaoTuyen chiDaoTuyen) {
        this.setChiDaoTuyen(chiDaoTuyen);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HoTro)) {
            return false;
        }
        return id != null && id.equals(((HoTro) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HoTro{" +
            "id=" + getId() +
            ", maNoiDung=" + getMaNoiDung() +
            ", ngayTao='" + getNgayTao() + "'" +
            ", nhanVienCV='" + getNhanVienCV() + "'" +
            ", kyThuatHoTro='" + getKyThuatHoTro() + "'" +
            ", vatTuHoTro='" + getVatTuHoTro() + "'" +
            ", soBnKhamDieuTri=" + getSoBnKhamDieuTri() +
            ", soBnPhauThuat=" + getSoBnPhauThuat() +
            ", soCanBoChuyenGiao=" + getSoCanBoChuyenGiao() +
            ", ketQuaCongTac='" + getKetQuaCongTac() + "'" +
            "}";
    }
}
