import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IKyThuatHoTro, getKyThuatHoTroIdentifier } from '../ky-thuat-ho-tro.model';

export type EntityResponseType = HttpResponse<IKyThuatHoTro>;
export type EntityArrayResponseType = HttpResponse<IKyThuatHoTro[]>;

@Injectable({ providedIn: 'root' })
export class KyThuatHoTroService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ky-thuat-ho-tros');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(kyThuatHoTro: IKyThuatHoTro): Observable<EntityResponseType> {
    return this.http.post<IKyThuatHoTro>(this.resourceUrl, kyThuatHoTro, { observe: 'response' });
  }

  update(kyThuatHoTro: IKyThuatHoTro): Observable<EntityResponseType> {
    return this.http.put<IKyThuatHoTro>(`${this.resourceUrl}/${getKyThuatHoTroIdentifier(kyThuatHoTro) as number}`, kyThuatHoTro, {
      observe: 'response',
    });
  }

  partialUpdate(kyThuatHoTro: IKyThuatHoTro): Observable<EntityResponseType> {
    return this.http.patch<IKyThuatHoTro>(`${this.resourceUrl}/${getKyThuatHoTroIdentifier(kyThuatHoTro) as number}`, kyThuatHoTro, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKyThuatHoTro>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKyThuatHoTro[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addKyThuatHoTroToCollectionIfMissing(
    kyThuatHoTroCollection: IKyThuatHoTro[],
    ...kyThuatHoTrosToCheck: (IKyThuatHoTro | null | undefined)[]
  ): IKyThuatHoTro[] {
    const kyThuatHoTros: IKyThuatHoTro[] = kyThuatHoTrosToCheck.filter(isPresent);
    if (kyThuatHoTros.length > 0) {
      const kyThuatHoTroCollectionIdentifiers = kyThuatHoTroCollection.map(
        kyThuatHoTroItem => getKyThuatHoTroIdentifier(kyThuatHoTroItem)!
      );
      const kyThuatHoTrosToAdd = kyThuatHoTros.filter(kyThuatHoTroItem => {
        const kyThuatHoTroIdentifier = getKyThuatHoTroIdentifier(kyThuatHoTroItem);
        if (kyThuatHoTroIdentifier == null || kyThuatHoTroCollectionIdentifiers.includes(kyThuatHoTroIdentifier)) {
          return false;
        }
        kyThuatHoTroCollectionIdentifiers.push(kyThuatHoTroIdentifier);
        return true;
      });
      return [...kyThuatHoTrosToAdd, ...kyThuatHoTroCollection];
    }
    return kyThuatHoTroCollection;
  }
}
