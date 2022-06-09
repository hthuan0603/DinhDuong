import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { IToaThuoc } from 'app/entities/toa-thuoc/toa-thuoc.model';

export interface IDanhGiaCanThiepDD {
  id?: number;
  maBN?: string | null;
  chuanDoanLS?: string | null;
  bSDieuTri?: string | null;
  thayDoiCanNangTrong1Thang?: string | null;
  danhGiaCN?: number | null;
  khauPhanAn?: string | null;
  danhGiaKPA?: number | null;
  trieuChungTieuHoa?: string | null;
  mucDo?: string | null;
  danhGiaMD?: number | null;
  giamChucNangHoatDong?: string | null;
  danhGiaCNHD?: number | null;
  stress?: string | null;
  danhGiaStress?: number | null;
  refeeding?: boolean | null;
  danhGiaRefeeding?: number | null;
  tongDiem?: number | null;
  phieuSuatAn?: IPhieuSuatAn | null;
  toaThuoc?: IToaThuoc | null;
}

export class DanhGiaCanThiepDD implements IDanhGiaCanThiepDD {
  constructor(
    public id?: number,
    public maBN?: string | null,
    public chuanDoanLS?: string | null,
    public bSDieuTri?: string | null,
    public thayDoiCanNangTrong1Thang?: string | null,
    public danhGiaCN?: number | null,
    public khauPhanAn?: string | null,
    public danhGiaKPA?: number | null,
    public trieuChungTieuHoa?: string | null,
    public mucDo?: string | null,
    public danhGiaMD?: number | null,
    public giamChucNangHoatDong?: string | null,
    public danhGiaCNHD?: number | null,
    public stress?: string | null,
    public danhGiaStress?: number | null,
    public refeeding?: boolean | null,
    public danhGiaRefeeding?: number | null,
    public tongDiem?: number | null,
    public phieuSuatAn?: IPhieuSuatAn | null,
    public toaThuoc?: IToaThuoc | null
  ) {
    this.refeeding = this.refeeding ?? false;
  }
}

export function getDanhGiaCanThiepDDIdentifier(danhGiaCanThiepDD: IDanhGiaCanThiepDD): number | undefined {
  return danhGiaCanThiepDD.id;
}
