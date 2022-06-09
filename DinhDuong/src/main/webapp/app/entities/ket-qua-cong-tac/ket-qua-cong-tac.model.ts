import { IHoTro } from 'app/entities/ho-tro/ho-tro.model';

export interface IKetQuaCongTac {
  id?: number;
  maKetQua?: number | null;
  tenKetQua?: string | null;
  thuTuSX?: number | null;
  hoTro?: IHoTro | null;
}

export class KetQuaCongTac implements IKetQuaCongTac {
  constructor(
    public id?: number,
    public maKetQua?: number | null,
    public tenKetQua?: string | null,
    public thuTuSX?: number | null,
    public hoTro?: IHoTro | null
  ) {}
}

export function getKetQuaCongTacIdentifier(ketQuaCongTac: IKetQuaCongTac): number | undefined {
  return ketQuaCongTac.id;
}
