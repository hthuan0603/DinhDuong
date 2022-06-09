package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DieuTri.
 */
@Entity
@Table(name = "dieu_tri")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DieuTri implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_dieu_tri")
    private String maDieuTri;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ma_benh_an")
    private String maBenhAn;

    @Column(name = "ten_bs_dieu_tri")
    private String tenBSDieuTri;

    @Column(name = "nguoi_nha")
    private String nguoiNha;

    @Column(name = "giuong")
    private String giuong;

    @Column(name = "ma_the_bhyt")
    private String maTheBHYT;

    @Column(name = "ngay_vao_khoa")
    private ZonedDateTime ngayVaoKhoa;

    @Column(name = "ngay_ra_vien")
    private ZonedDateTime ngayRaVien;

    @Column(name = "b_v_chuyen")
    private String bVChuyen;

    @Column(name = "ket_qua_dieu_tri")
    private String ketQuaDieuTri;

    @Column(name = "lich_su_chuyen_khoa")
    private String lichSuChuyenKhoa;

    @OneToOne
    @JoinColumn(unique = true)
    private HoaDon hoaDon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DieuTri id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDieuTri() {
        return this.maDieuTri;
    }

    public DieuTri maDieuTri(String maDieuTri) {
        this.setMaDieuTri(maDieuTri);
        return this;
    }

    public void setMaDieuTri(String maDieuTri) {
        this.maDieuTri = maDieuTri;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public DieuTri hoTen(String hoTen) {
        this.setHoTen(hoTen);
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaBenhAn() {
        return this.maBenhAn;
    }

    public DieuTri maBenhAn(String maBenhAn) {
        this.setMaBenhAn(maBenhAn);
        return this;
    }

    public void setMaBenhAn(String maBenhAn) {
        this.maBenhAn = maBenhAn;
    }

    public String getTenBSDieuTri() {
        return this.tenBSDieuTri;
    }

    public DieuTri tenBSDieuTri(String tenBSDieuTri) {
        this.setTenBSDieuTri(tenBSDieuTri);
        return this;
    }

    public void setTenBSDieuTri(String tenBSDieuTri) {
        this.tenBSDieuTri = tenBSDieuTri;
    }

    public String getNguoiNha() {
        return this.nguoiNha;
    }

    public DieuTri nguoiNha(String nguoiNha) {
        this.setNguoiNha(nguoiNha);
        return this;
    }

    public void setNguoiNha(String nguoiNha) {
        this.nguoiNha = nguoiNha;
    }

    public String getGiuong() {
        return this.giuong;
    }

    public DieuTri giuong(String giuong) {
        this.setGiuong(giuong);
        return this;
    }

    public void setGiuong(String giuong) {
        this.giuong = giuong;
    }

    public String getMaTheBHYT() {
        return this.maTheBHYT;
    }

    public DieuTri maTheBHYT(String maTheBHYT) {
        this.setMaTheBHYT(maTheBHYT);
        return this;
    }

    public void setMaTheBHYT(String maTheBHYT) {
        this.maTheBHYT = maTheBHYT;
    }

    public ZonedDateTime getNgayVaoKhoa() {
        return this.ngayVaoKhoa;
    }

    public DieuTri ngayVaoKhoa(ZonedDateTime ngayVaoKhoa) {
        this.setNgayVaoKhoa(ngayVaoKhoa);
        return this;
    }

    public void setNgayVaoKhoa(ZonedDateTime ngayVaoKhoa) {
        this.ngayVaoKhoa = ngayVaoKhoa;
    }

    public ZonedDateTime getNgayRaVien() {
        return this.ngayRaVien;
    }

    public DieuTri ngayRaVien(ZonedDateTime ngayRaVien) {
        this.setNgayRaVien(ngayRaVien);
        return this;
    }

    public void setNgayRaVien(ZonedDateTime ngayRaVien) {
        this.ngayRaVien = ngayRaVien;
    }

    public String getbVChuyen() {
        return this.bVChuyen;
    }

    public DieuTri bVChuyen(String bVChuyen) {
        this.setbVChuyen(bVChuyen);
        return this;
    }

    public void setbVChuyen(String bVChuyen) {
        this.bVChuyen = bVChuyen;
    }

    public String getKetQuaDieuTri() {
        return this.ketQuaDieuTri;
    }

    public DieuTri ketQuaDieuTri(String ketQuaDieuTri) {
        this.setKetQuaDieuTri(ketQuaDieuTri);
        return this;
    }

    public void setKetQuaDieuTri(String ketQuaDieuTri) {
        this.ketQuaDieuTri = ketQuaDieuTri;
    }

    public String getLichSuChuyenKhoa() {
        return this.lichSuChuyenKhoa;
    }

    public DieuTri lichSuChuyenKhoa(String lichSuChuyenKhoa) {
        this.setLichSuChuyenKhoa(lichSuChuyenKhoa);
        return this;
    }

    public void setLichSuChuyenKhoa(String lichSuChuyenKhoa) {
        this.lichSuChuyenKhoa = lichSuChuyenKhoa;
    }

    public HoaDon getHoaDon() {
        return this.hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public DieuTri hoaDon(HoaDon hoaDon) {
        this.setHoaDon(hoaDon);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DieuTri)) {
            return false;
        }
        return id != null && id.equals(((DieuTri) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DieuTri{" +
            "id=" + getId() +
            ", maDieuTri='" + getMaDieuTri() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", maBenhAn='" + getMaBenhAn() + "'" +
            ", tenBSDieuTri='" + getTenBSDieuTri() + "'" +
            ", nguoiNha='" + getNguoiNha() + "'" +
            ", giuong='" + getGiuong() + "'" +
            ", maTheBHYT='" + getMaTheBHYT() + "'" +
            ", ngayVaoKhoa='" + getNgayVaoKhoa() + "'" +
            ", ngayRaVien='" + getNgayRaVien() + "'" +
            ", bVChuyen='" + getbVChuyen() + "'" +
            ", ketQuaDieuTri='" + getKetQuaDieuTri() + "'" +
            ", lichSuChuyenKhoa='" + getLichSuChuyenKhoa() + "'" +
            "}";
    }
}
