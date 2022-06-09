import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';

export interface ILyDoCongTac {
  id?: number;
  maLyDo?: number | null;
  tenLyDo?: string | null;
  thuTuSX?: number | null;
  chiDaoTuyens?: IChiDaoTuyen[] | null;
}

export class LyDoCongTac implements ILyDoCongTac {
  constructor(
    public id?: number,
    public maLyDo?: number | null,
    public tenLyDo?: string | null,
    public thuTuSX?: number | null,
    public chiDaoTuyens?: IChiDaoTuyen[] | null
  ) {}
}

export function getLyDoCongTacIdentifier(lyDoCongTac: ILyDoCongTac): number | undefined {
  return lyDoCongTac.id;
}
