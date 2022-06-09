import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';

export interface IBaoHiem {
  id?: number;
  maTheBHYT?: string | null;
  doiTuong?: string | null;
  baoHiemThanhToan?: number | null;
  hoaDon?: IHoaDon | null;
}

export class BaoHiem implements IBaoHiem {
  constructor(
    public id?: number,
    public maTheBHYT?: string | null,
    public doiTuong?: string | null,
    public baoHiemThanhToan?: number | null,
    public hoaDon?: IHoaDon | null
  ) {}
}

export function getBaoHiemIdentifier(baoHiem: IBaoHiem): number | undefined {
  return baoHiem.id;
}
