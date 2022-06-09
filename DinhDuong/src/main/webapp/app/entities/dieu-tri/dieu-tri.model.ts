import dayjs from 'dayjs/esm';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';

export interface IDieuTri {
  id?: number;
  maDieuTri?: string | null;
  hoTen?: string | null;
  maBenhAn?: string | null;
  tenBSDieuTri?: string | null;
  nguoiNha?: string | null;
  giuong?: string | null;
  maTheBHYT?: string | null;
  ngayVaoKhoa?: dayjs.Dayjs | null;
  ngayRaVien?: dayjs.Dayjs | null;
  bVChuyen?: string | null;
  ketQuaDieuTri?: string | null;
  lichSuChuyenKhoa?: string | null;
  hoaDon?: IHoaDon | null;
}

export class DieuTri implements IDieuTri {
  constructor(
    public id?: number,
    public maDieuTri?: string | null,
    public hoTen?: string | null,
    public maBenhAn?: string | null,
    public tenBSDieuTri?: string | null,
    public nguoiNha?: string | null,
    public giuong?: string | null,
    public maTheBHYT?: string | null,
    public ngayVaoKhoa?: dayjs.Dayjs | null,
    public ngayRaVien?: dayjs.Dayjs | null,
    public bVChuyen?: string | null,
    public ketQuaDieuTri?: string | null,
    public lichSuChuyenKhoa?: string | null,
    public hoaDon?: IHoaDon | null
  ) {}
}

export function getDieuTriIdentifier(dieuTri: IDieuTri): number | undefined {
  return dieuTri.id;
}
