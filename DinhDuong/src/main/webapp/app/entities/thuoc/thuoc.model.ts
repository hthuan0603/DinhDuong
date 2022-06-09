export interface IThuoc {
  id?: number;
  tenThuoc?: string | null;
  duongDung?: string | null;
  soLuong?: number | null;
  cachDung?: string | null;
  hoatChat?: string | null;
  dVT?: string | null;
  donGia?: number | null;
  thanhTien?: number | null;
  loaiTTCu?: string | null;
  loaiTTMoi?: string | null;
  lieuDung?: string | null;
  kS?: string | null;
}

export class Thuoc implements IThuoc {
  constructor(
    public id?: number,
    public tenThuoc?: string | null,
    public duongDung?: string | null,
    public soLuong?: number | null,
    public cachDung?: string | null,
    public hoatChat?: string | null,
    public dVT?: string | null,
    public donGia?: number | null,
    public thanhTien?: number | null,
    public loaiTTCu?: string | null,
    public loaiTTMoi?: string | null,
    public lieuDung?: string | null,
    public kS?: string | null
  ) {}
}

export function getThuocIdentifier(thuoc: IThuoc): number | undefined {
  return thuoc.id;
}
