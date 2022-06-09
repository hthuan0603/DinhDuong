import dayjs from 'dayjs/esm';
import { IBaoHiem } from 'app/entities/bao-hiem/bao-hiem.model';
import { IDieuTri } from 'app/entities/dieu-tri/dieu-tri.model';
import { ITTSanglocVaDanhGiaDD } from 'app/entities/tt-sangloc-va-danh-gia-dd/tt-sangloc-va-danh-gia-dd.model';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';
import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { IGiayMoiDanhGia } from 'app/entities/giay-moi-danh-gia/giay-moi-danh-gia.model';

export interface IBenhNhan {
  id?: number;
  hoTen?: string | null;
  maBN?: string | null;
  ngaySinh?: dayjs.Dayjs | null;
  gioiTinh?: boolean | null;
  diaChi?: string | null;
  ngheNghiep?: string | null;
  chieuCao?: number | null;
  canHT?: number | null;
  canTC?: number | null;
  vongCT?: number | null;
  bMI?: number | null;
  maTheBHYT?: string | null;
  sDT?: number | null;
  baoHiem?: IBaoHiem | null;
  dieuTri?: IDieuTri | null;
  tTSanglocVaDanhGiaDD?: ITTSanglocVaDanhGiaDD | null;
  hoaDon?: IHoaDon | null;
  danhGiaCanThiepDD?: IDanhGiaCanThiepDD | null;
  phieuSuatAn?: IPhieuSuatAn | null;
  giayMoiDanhGia?: IGiayMoiDanhGia | null;
}

export class BenhNhan implements IBenhNhan {
  constructor(
    public id?: number,
    public hoTen?: string | null,
    public maBN?: string | null,
    public ngaySinh?: dayjs.Dayjs | null,
    public gioiTinh?: boolean | null,
    public diaChi?: string | null,
    public ngheNghiep?: string | null,
    public chieuCao?: number | null,
    public canHT?: number | null,
    public canTC?: number | null,
    public vongCT?: number | null,
    public bMI?: number | null,
    public maTheBHYT?: string | null,
    public sDT?: number | null,
    public baoHiem?: IBaoHiem | null,
    public dieuTri?: IDieuTri | null,
    public tTSanglocVaDanhGiaDD?: ITTSanglocVaDanhGiaDD | null,
    public hoaDon?: IHoaDon | null,
    public danhGiaCanThiepDD?: IDanhGiaCanThiepDD | null,
    public phieuSuatAn?: IPhieuSuatAn | null,
    public giayMoiDanhGia?: IGiayMoiDanhGia | null
  ) {
    this.gioiTinh = this.gioiTinh ?? false;
  }
}

export function getBenhNhanIdentifier(benhNhan: IBenhNhan): number | undefined {
  return benhNhan.id;
}
