import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVatTuHoTro, getVatTuHoTroIdentifier } from '../vat-tu-ho-tro.model';

export type EntityResponseType = HttpResponse<IVatTuHoTro>;
export type EntityArrayResponseType = HttpResponse<IVatTuHoTro[]>;

@Injectable({ providedIn: 'root' })
export class VatTuHoTroService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/vat-tu-ho-tros');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(vatTuHoTro: IVatTuHoTro): Observable<EntityResponseType> {
    return this.http.post<IVatTuHoTro>(this.resourceUrl, vatTuHoTro, { observe: 'response' });
  }

  update(vatTuHoTro: IVatTuHoTro): Observable<EntityResponseType> {
    return this.http.put<IVatTuHoTro>(`${this.resourceUrl}/${getVatTuHoTroIdentifier(vatTuHoTro) as number}`, vatTuHoTro, {
      observe: 'response',
    });
  }

  partialUpdate(vatTuHoTro: IVatTuHoTro): Observable<EntityResponseType> {
    return this.http.patch<IVatTuHoTro>(`${this.resourceUrl}/${getVatTuHoTroIdentifier(vatTuHoTro) as number}`, vatTuHoTro, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVatTuHoTro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVatTuHoTro[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addVatTuHoTroToCollectionIfMissing(
    vatTuHoTroCollection: IVatTuHoTro[],
    ...vatTuHoTrosToCheck: (IVatTuHoTro | null | undefined)[]
  ): IVatTuHoTro[] {
    const vatTuHoTros: IVatTuHoTro[] = vatTuHoTrosToCheck.filter(isPresent);
    if (vatTuHoTros.length > 0) {
      const vatTuHoTroCollectionIdentifiers = vatTuHoTroCollection.map(vatTuHoTroItem => getVatTuHoTroIdentifier(vatTuHoTroItem)!);
      const vatTuHoTrosToAdd = vatTuHoTros.filter(vatTuHoTroItem => {
        const vatTuHoTroIdentifier = getVatTuHoTroIdentifier(vatTuHoTroItem);
        if (vatTuHoTroIdentifier == null || vatTuHoTroCollectionIdentifiers.includes(vatTuHoTroIdentifier)) {
          return false;
        }
        vatTuHoTroCollectionIdentifiers.push(vatTuHoTroIdentifier);
        return true;
      });
      return [...vatTuHoTrosToAdd, ...vatTuHoTroCollection];
    }
    return vatTuHoTroCollection;
  }
}
