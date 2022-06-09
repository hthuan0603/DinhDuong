import dayjs from 'dayjs/esm';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';

export interface ITTSanglocVaDanhGiaDD {
  id?: number;
  maBN?: string | null;
  mangThai?: boolean | null;
  bMI?: number | null;
  danhGiaBMI?: number | null;
  phanTramCanNangTrong3Thang?: number | null;
  danhGiaCanNang?: number | null;
  anUongTrongTuan?: string | null;
  danhGiaAnUong?: number | null;
  thoiGianChiDinh?: dayjs.Dayjs | null;
  nguyCoSDD?: boolean | null;
  cheDoAn?: string | null;
  danhGiaCanThiepDD?: IDanhGiaCanThiepDD | null;
}

export class TTSanglocVaDanhGiaDD implements ITTSanglocVaDanhGiaDD {
  constructor(
    public id?: number,
    public maBN?: string | null,
    public mangThai?: boolean | null,
    public bMI?: number | null,
    public danhGiaBMI?: number | null,
    public phanTramCanNangTrong3Thang?: number | null,
    public danhGiaCanNang?: number | null,
    public anUongTrongTuan?: string | null,
    public danhGiaAnUong?: number | null,
    public thoiGianChiDinh?: dayjs.Dayjs | null,
    public nguyCoSDD?: boolean | null,
    public cheDoAn?: string | null,
    public danhGiaCanThiepDD?: IDanhGiaCanThiepDD | null
  ) {
    this.mangThai = this.mangThai ?? false;
    this.nguyCoSDD = this.nguyCoSDD ?? false;
  }
}

export function getTTSanglocVaDanhGiaDDIdentifier(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): number | undefined {
  return tTSanglocVaDanhGiaDD.id;
}
