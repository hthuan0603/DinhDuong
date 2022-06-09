import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface IBaoCaoTaiChinh {
  id?: number;
  maBaoCao?: number | null;
  luuTru?: number | null;
  tienAn?: number | null;
  tienO?: number | null;
  tienDiLai?: number | null;
  taiLieu?: number | null;
  giangDay?: number | null;
  khac?: number | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class BaoCaoTaiChinh implements IBaoCaoTaiChinh {
  constructor(
    public id?: number,
    public maBaoCao?: number | null,
    public luuTru?: number | null,
    public tienAn?: number | null,
    public tienO?: number | null,
    public tienDiLai?: number | null,
    public taiLieu?: number | null,
    public giangDay?: number | null,
    public khac?: number | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getBaoCaoTaiChinhIdentifier(baoCaoTaiChinh: IBaoCaoTaiChinh): number | undefined {
  return baoCaoTaiChinh.id;
}
