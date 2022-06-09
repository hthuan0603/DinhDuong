import { IHoTro } from 'app/entities/ho-tro/ho-tro.model';

export interface IVatTuHoTro {
  id?: number;
  maVatTu?: number | null;
  tenVatTu?: string | null;
  thuTuSX?: number | null;
  hoTro?: IHoTro | null;
}

export class VatTuHoTro implements IVatTuHoTro {
  constructor(
    public id?: number,
    public maVatTu?: number | null,
    public tenVatTu?: string | null,
    public thuTuSX?: number | null,
    public hoTro?: IHoTro | null
  ) {}
}

export function getVatTuHoTroIdentifier(vatTuHoTro: IVatTuHoTro): number | undefined {
  return vatTuHoTro.id;
}
