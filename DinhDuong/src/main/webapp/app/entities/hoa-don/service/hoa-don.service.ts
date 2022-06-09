import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHoaDon, getHoaDonIdentifier } from '../hoa-don.model';

export type EntityResponseType = HttpResponse<IHoaDon>;
export type EntityArrayResponseType = HttpResponse<IHoaDon[]>;

@Injectable({ providedIn: 'root' })
export class HoaDonService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hoa-dons');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(hoaDon: IHoaDon): Observable<EntityResponseType> {
    return this.http.post<IHoaDon>(this.resourceUrl, hoaDon, { observe: 'response' });
  }

  update(hoaDon: IHoaDon): Observable<EntityResponseType> {
    return this.http.put<IHoaDon>(`${this.resourceUrl}/${getHoaDonIdentifier(hoaDon) as number}`, hoaDon, { observe: 'response' });
  }

  partialUpdate(hoaDon: IHoaDon): Observable<EntityResponseType> {
    return this.http.patch<IHoaDon>(`${this.resourceUrl}/${getHoaDonIdentifier(hoaDon) as number}`, hoaDon, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHoaDon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHoaDon[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addHoaDonToCollectionIfMissing(hoaDonCollection: IHoaDon[], ...hoaDonsToCheck: (IHoaDon | null | undefined)[]): IHoaDon[] {
    const hoaDons: IHoaDon[] = hoaDonsToCheck.filter(isPresent);
    if (hoaDons.length > 0) {
      const hoaDonCollectionIdentifiers = hoaDonCollection.map(hoaDonItem => getHoaDonIdentifier(hoaDonItem)!);
      const hoaDonsToAdd = hoaDons.filter(hoaDonItem => {
        const hoaDonIdentifier = getHoaDonIdentifier(hoaDonItem);
        if (hoaDonIdentifier == null || hoaDonCollectionIdentifiers.includes(hoaDonIdentifier)) {
          return false;
        }
        hoaDonCollectionIdentifiers.push(hoaDonIdentifier);
        return true;
      });
      return [...hoaDonsToAdd, ...hoaDonCollection];
    }
    return hoaDonCollection;
  }
}
