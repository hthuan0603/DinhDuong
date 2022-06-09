import { IHoTro } from 'app/entities/ho-tro/ho-tro.model';

export interface IKyThuatHoTro {
  id?: number;
  maKyThuat?: number | null;
  tenKyThuat?: string | null;
  thuTuSX?: number | null;
  hoTro?: IHoTro | null;
}

export class KyThuatHoTro implements IKyThuatHoTro {
  constructor(
    public id?: number,
    public maKyThuat?: number | null,
    public tenKyThuat?: string | null,
    public thuTuSX?: number | null,
    public hoTro?: IHoTro | null
  ) {}
}

export function getKyThuatHoTroIdentifier(kyThuatHoTro: IKyThuatHoTro): number | undefined {
  return kyThuatHoTro.id;
}
