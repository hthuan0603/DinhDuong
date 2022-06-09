import dayjs from 'dayjs/esm';

export interface IGiayMoiDanhGia {
  id?: number;
  maBN?: string | null;
  maNguoiTao?: string | null;
  thoiGianChiDinh?: dayjs.Dayjs | null;
  chuanDoan?: string | null;
  chuanDoanPhu?: string | null;
  guiTu?: string | null;
  guiDen?: string | null;
  moi?: string | null;
  noiDungHoiChuan?: string | null;
  hoiChuanLan?: number | null;
  thoiGianHoiChuan?: dayjs.Dayjs | null;
  trangThai?: boolean | null;
}

export class GiayMoiDanhGia implements IGiayMoiDanhGia {
  constructor(
    public id?: number,
    public maBN?: string | null,
    public maNguoiTao?: string | null,
    public thoiGianChiDinh?: dayjs.Dayjs | null,
    public chuanDoan?: string | null,
    public chuanDoanPhu?: string | null,
    public guiTu?: string | null,
    public guiDen?: string | null,
    public moi?: string | null,
    public noiDungHoiChuan?: string | null,
    public hoiChuanLan?: number | null,
    public thoiGianHoiChuan?: dayjs.Dayjs | null,
    public trangThai?: boolean | null
  ) {
    this.trangThai = this.trangThai ?? false;
  }
}

export function getGiayMoiDanhGiaIdentifier(giayMoiDanhGia: IGiayMoiDanhGia): number | undefined {
  return giayMoiDanhGia.id;
}
