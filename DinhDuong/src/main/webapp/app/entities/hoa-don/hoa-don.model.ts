export interface IHoaDon {
  id?: number;
  noiDung?: string | null;
  maBN?: string | null;
  maTheBHYT?: string | null;
  maDV?: string | null;
  tamUng?: number | null;
  daNop?: number | null;
  tong?: number | null;
}

export class HoaDon implements IHoaDon {
  constructor(
    public id?: number,
    public noiDung?: string | null,
    public maBN?: string | null,
    public maTheBHYT?: string | null,
    public maDV?: string | null,
    public tamUng?: number | null,
    public daNop?: number | null,
    public tong?: number | null
  ) {}
}

export function getHoaDonIdentifier(hoaDon: IHoaDon): number | undefined {
  return hoaDon.id;
}
