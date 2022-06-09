import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface INhanVienTiepNhan {
  id?: number;
  maNhanVien?: number | null;
  tenNhanVien?: string | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class NhanVienTiepNhan implements INhanVienTiepNhan {
  constructor(
    public id?: number,
    public maNhanVien?: number | null,
    public tenNhanVien?: string | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getNhanVienTiepNhanIdentifier(nhanVienTiepNhan: INhanVienTiepNhan): number | undefined {
  return nhanVienTiepNhan.id;
}
