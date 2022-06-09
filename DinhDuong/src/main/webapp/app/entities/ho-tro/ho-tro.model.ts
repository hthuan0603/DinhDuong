import dayjs from 'dayjs/esm';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IHoTro {
  id?: number;
  maNoiDung?: number | null;
  ngayTao?: dayjs.Dayjs | null;
  nhanVienCV?: string | null;
  kyThuatHoTro?: string | null;
  vatTuHoTro?: string | null;
  soBnKhamDieuTri?: number | null;
  soBnPhauThuat?: number | null;
  soCanBoChuyenGiao?: number | null;
  ketQuaCongTac?: string | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class HoTro implements IHoTro {
  constructor(
    public id?: number,
    public maNoiDung?: number | null,
    public ngayTao?: dayjs.Dayjs | null,
    public nhanVienCV?: string | null,
    public kyThuatHoTro?: string | null,
    public vatTuHoTro?: string | null,
    public soBnKhamDieuTri?: number | null,
    public soBnPhauThuat?: number | null,
    public soCanBoChuyenGiao?: number | null,
    public ketQuaCongTac?: string | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getHoTroIdentifier(hoTro: IHoTro): number | undefined {
  return hoTro.id;
}
