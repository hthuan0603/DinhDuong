import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface INoiDenCongTac {
  id?: number;
  maNoiDen?: number | null;
  tenNoiDen?: string | null;
  thuTuSX?: number | null;
  chiDaoTuyen?: IChiDaoTuyen | null;
}

export class NoiDenCongTac implements INoiDenCongTac {
  constructor(
    public id?: number,
    public maNoiDen?: number | null,
    public tenNoiDen?: string | null,
    public thuTuSX?: number | null,
    public chiDaoTuyen?: IChiDaoTuyen | null
  ) {}
}

export function getNoiDenCongTacIdentifier(noiDenCongTac: INoiDenCongTac): number | undefined {
  return noiDenCongTac.id;
}
