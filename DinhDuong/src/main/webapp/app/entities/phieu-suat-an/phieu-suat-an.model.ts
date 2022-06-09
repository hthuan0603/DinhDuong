import dayjs from 'dayjs/esm';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';

export interface IPhieuSuatAn {
  id?: number;
  tenDv?: string | null;
  maDV?: string | null;
  maBN?: string | null;
  maTheBHYT?: string | null;
  tGSuDung?: dayjs.Dayjs | null;
  tGChiDinh?: dayjs.Dayjs | null;
  chuanDoan?: string | null;
  chuanDoanKT?: string | null;
  ghiChu?: string | null;
  hoaDon?: IHoaDon | null;
}

export class PhieuSuatAn implements IPhieuSuatAn {
  constructor(
    public id?: number,
    public tenDv?: string | null,
    public maDV?: string | null,
    public maBN?: string | null,
    public maTheBHYT?: string | null,
    public tGSuDung?: dayjs.Dayjs | null,
    public tGChiDinh?: dayjs.Dayjs | null,
    public chuanDoan?: string | null,
    public chuanDoanKT?: string | null,
    public ghiChu?: string | null,
    public hoaDon?: IHoaDon | null
  ) {}
}

export function getPhieuSuatAnIdentifier(phieuSuatAn: IPhieuSuatAn): number | undefined {
  return phieuSuatAn.id;
}
