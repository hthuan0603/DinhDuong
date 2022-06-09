import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'ly-do-cong-tac',
        data: { pageTitle: 'dinhDuongApp.lyDoCongTac.home.title' },
        loadChildren: () => import('./ly-do-cong-tac/ly-do-cong-tac.module').then(m => m.LyDoCongTacModule),
      },
      {
        path: 'ket-qua-cong-tac',
        data: { pageTitle: 'dinhDuongApp.ketQuaCongTac.home.title' },
        loadChildren: () => import('./ket-qua-cong-tac/ket-qua-cong-tac.module').then(m => m.KetQuaCongTacModule),
      },
      {
        path: 'ky-thuat-ho-tro',
        data: { pageTitle: 'dinhDuongApp.kyThuatHoTro.home.title' },
        loadChildren: () => import('./ky-thuat-ho-tro/ky-thuat-ho-tro.module').then(m => m.KyThuatHoTroModule),
      },
      {
        path: 'noi-den-cong-tac',
        data: { pageTitle: 'dinhDuongApp.noiDenCongTac.home.title' },
        loadChildren: () => import('./noi-den-cong-tac/noi-den-cong-tac.module').then(m => m.NoiDenCongTacModule),
      },
      {
        path: 'vat-tu-ho-tro',
        data: { pageTitle: 'dinhDuongApp.vatTuHoTro.home.title' },
        loadChildren: () => import('./vat-tu-ho-tro/vat-tu-ho-tro.module').then(m => m.VatTuHoTroModule),
      },
      {
        path: 'chi-dao-tuyen',
        data: { pageTitle: 'dinhDuongApp.chiDaoTuyen.home.title' },
        loadChildren: () => import('./chi-dao-tuyen/chi-dao-tuyen.module').then(m => m.ChiDaoTuyenModule),
      },
      {
        path: 'ho-tro',
        data: { pageTitle: 'dinhDuongApp.hoTro.home.title' },
        loadChildren: () => import('./ho-tro/ho-tro.module').then(m => m.HoTroModule),
      },
      {
        path: 'bao-cao-tai-chinh',
        data: { pageTitle: 'dinhDuongApp.baoCaoTaiChinh.home.title' },
        loadChildren: () => import('./bao-cao-tai-chinh/bao-cao-tai-chinh.module').then(m => m.BaoCaoTaiChinhModule),
      },
      {
        path: 'nhan-vien-tiep-nhan',
        data: { pageTitle: 'dinhDuongApp.nhanVienTiepNhan.home.title' },
        loadChildren: () => import('./nhan-vien-tiep-nhan/nhan-vien-tiep-nhan.module').then(m => m.NhanVienTiepNhanModule),
      },
      {
        path: 'benh-nhan',
        data: { pageTitle: 'dinhDuongApp.benhNhan.home.title' },
        loadChildren: () => import('./benh-nhan/benh-nhan.module').then(m => m.BenhNhanModule),
      },
      {
        path: 'dieu-tri',
        data: { pageTitle: 'dinhDuongApp.dieuTri.home.title' },
        loadChildren: () => import('./dieu-tri/dieu-tri.module').then(m => m.DieuTriModule),
      },
      {
        path: 'tt-sangloc-va-danh-gia-dd',
        data: { pageTitle: 'dinhDuongApp.tTSanglocVaDanhGiaDD.home.title' },
        loadChildren: () => import('./tt-sangloc-va-danh-gia-dd/tt-sangloc-va-danh-gia-dd.module').then(m => m.TTSanglocVaDanhGiaDDModule),
      },
      {
        path: 'giay-moi-danh-gia',
        data: { pageTitle: 'dinhDuongApp.giayMoiDanhGia.home.title' },
        loadChildren: () => import('./giay-moi-danh-gia/giay-moi-danh-gia.module').then(m => m.GiayMoiDanhGiaModule),
      },
      {
        path: 'hoa-don',
        data: { pageTitle: 'dinhDuongApp.hoaDon.home.title' },
        loadChildren: () => import('./hoa-don/hoa-don.module').then(m => m.HoaDonModule),
      },
      {
        path: 'danh-gia-can-thiep-dd',
        data: { pageTitle: 'dinhDuongApp.danhGiaCanThiepDD.home.title' },
        loadChildren: () => import('./danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.module').then(m => m.DanhGiaCanThiepDDModule),
      },
      {
        path: 'toa-thuoc',
        data: { pageTitle: 'dinhDuongApp.toaThuoc.home.title' },
        loadChildren: () => import('./toa-thuoc/toa-thuoc.module').then(m => m.ToaThuocModule),
      },
      {
        path: 'thuoc',
        data: { pageTitle: 'dinhDuongApp.thuoc.home.title' },
        loadChildren: () => import('./thuoc/thuoc.module').then(m => m.ThuocModule),
      },
      {
        path: 'phieu-suat-an',
        data: { pageTitle: 'dinhDuongApp.phieuSuatAn.home.title' },
        loadChildren: () => import('./phieu-suat-an/phieu-suat-an.module').then(m => m.PhieuSuatAnModule),
      },
      {
        path: 'bao-hiem',
        data: { pageTitle: 'dinhDuongApp.baoHiem.home.title' },
        loadChildren: () => import('./bao-hiem/bao-hiem.module').then(m => m.BaoHiemModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
