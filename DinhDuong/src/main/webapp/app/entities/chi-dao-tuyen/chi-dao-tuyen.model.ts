import dayjs from 'dayjs/esm';
import { ILyDoCongTac } from 'app/entities/ly-do-cong-tac/ly-do-cong-tac.model';
import { INhanVienTiepNhan } from 'app/entities/nhan-vien-tiep-nhan/nhan-vien-tiep-nhan.model';

export interface IChiDaoTuyen {
  id?: number;
  maCdt?: number | null;
  soQuyetDinh?: number | null;
  ngayQuyetDinh?: dayjs.Dayjs | null;
  soHD?: number | null;
  ngayHD?: dayjs.Dayjs | null;
  lyDoCT?: string | null;
  noiDung?: string | null;
  noiCongTac?: string | null;
  ngayBatDau?: dayjs.Dayjs | null;
  ngayKetThuc?: dayjs.Dayjs | null;
  ghiChu?: string | null;
  noiDungHoTro?: number | null;
  baoCaoTaiChinh?: number | null;
  lyDoCongTac?: ILyDoCongTac | null;
  nhanVienTiepNhan?: INhanVienTiepNhan | null;
}

export class ChiDaoTuyen implements IChiDaoTuyen {
  constructor(
    public id?: number,
    public maCdt?: number | null,
    public soQuyetDinh?: number | null,
    public ngayQuyetDinh?: dayjs.Dayjs | null,
    public soHD?: number | null,
    public ngayHD?: dayjs.Dayjs | null,
    public lyDoCT?: string | null,
    public noiDung?: string | null,
    public noiCongTac?: string | null,
    public ngayBatDau?: dayjs.Dayjs | null,
    public ngayKetThuc?: dayjs.Dayjs | null,
    public ghiChu?: string | null,
    public noiDungHoTro?: number | null,
    public baoCaoTaiChinh?: number | null,
    public lyDoCongTac?: ILyDoCongTac | null,
    public nhanVienTiepNhan?: INhanVienTiepNhan | null
  ) {}
}

export function getChiDaoTuyenIdentifier(chiDaoTuyen: IChiDaoTuyen): number | undefined {
  return chiDaoTuyen.id;
}
