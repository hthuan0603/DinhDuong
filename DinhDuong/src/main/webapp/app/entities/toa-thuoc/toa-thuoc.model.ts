import dayjs from 'dayjs/esm';
import { IThuoc } from 'app/entities/thuoc/thuoc.model';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { IBenhNhan } from 'app/entities/benh-nhan/benh-nhan.model';

export interface IToaThuoc {
  id?: number;
  iCD10?: string | null;
  maBA?: string | null;
  maBN?: string | null;
  loaiThuoc?: string | null;
  doiTuong?: string | null;
  tiLe?: number | null;
  soNgayHenTaiKham?: number | null;
  capPhieuHenKham?: boolean | null;
  loiDanBacSi?: string | null;
  ngayChiDinh?: dayjs.Dayjs | null;
  ngayDung?: dayjs.Dayjs | null;
  soNgaykeDon?: number | null;
  khoThuoc?: boolean | null;
  thuoc?: IThuoc | null;
  hoaDon?: IHoaDon | null;
  benhNhan?: IBenhNhan | null;
}

export class ToaThuoc implements IToaThuoc {
  constructor(
    public id?: number,
    public iCD10?: string | null,
    public maBA?: string | null,
    public maBN?: string | null,
    public loaiThuoc?: string | null,
    public doiTuong?: string | null,
    public tiLe?: number | null,
    public soNgayHenTaiKham?: number | null,
    public capPhieuHenKham?: boolean | null,
    public loiDanBacSi?: string | null,
    public ngayChiDinh?: dayjs.Dayjs | null,
    public ngayDung?: dayjs.Dayjs | null,
    public soNgaykeDon?: number | null,
    public khoThuoc?: boolean | null,
    public thuoc?: IThuoc | null,
    public hoaDon?: IHoaDon | null,
    public benhNhan?: IBenhNhan | null
  ) {
    this.capPhieuHenKham = this.capPhieuHenKham ?? false;
    this.khoThuoc = this.khoThuoc ?? false;
  }
}

export function getToaThuocIdentifier(toaThuoc: IToaThuoc): number | undefined {
  return toaThuoc.id;
}
