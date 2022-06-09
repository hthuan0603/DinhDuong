import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDanhGiaCanThiepDD, getDanhGiaCanThiepDDIdentifier } from '../danh-gia-can-thiep-dd.model';

export type EntityResponseType = HttpResponse<IDanhGiaCanThiepDD>;
export type EntityArrayResponseType = HttpResponse<IDanhGiaCanThiepDD[]>;

@Injectable({ providedIn: 'root' })
export class DanhGiaCanThiepDDService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/danh-gia-can-thiep-dds');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(danhGiaCanThiepDD: IDanhGiaCanThiepDD): Observable<EntityResponseType> {
    return this.http.post<IDanhGiaCanThiepDD>(this.resourceUrl, danhGiaCanThiepDD, { observe: 'response' });
  }

  update(danhGiaCanThiepDD: IDanhGiaCanThiepDD): Observable<EntityResponseType> {
    return this.http.put<IDanhGiaCanThiepDD>(
      `${this.resourceUrl}/${getDanhGiaCanThiepDDIdentifier(danhGiaCanThiepDD) as number}`,
      danhGiaCanThiepDD,
      { observe: 'response' }
    );
  }

  partialUpdate(danhGiaCanThiepDD: IDanhGiaCanThiepDD): Observable<EntityResponseType> {
    return this.http.patch<IDanhGiaCanThiepDD>(
      `${this.resourceUrl}/${getDanhGiaCanThiepDDIdentifier(danhGiaCanThiepDD) as number}`,
      danhGiaCanThiepDD,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDanhGiaCanThiepDD>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDanhGiaCanThiepDD[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDanhGiaCanThiepDDToCollectionIfMissing(
    danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[],
    ...danhGiaCanThiepDDSToCheck: (IDanhGiaCanThiepDD | null | undefined)[]
  ): IDanhGiaCanThiepDD[] {
    const danhGiaCanThiepDDS: IDanhGiaCanThiepDD[] = danhGiaCanThiepDDSToCheck.filter(isPresent);
    if (danhGiaCanThiepDDS.length > 0) {
      const danhGiaCanThiepDDCollectionIdentifiers = danhGiaCanThiepDDCollection.map(
        danhGiaCanThiepDDItem => getDanhGiaCanThiepDDIdentifier(danhGiaCanThiepDDItem)!
      );
      const danhGiaCanThiepDDSToAdd = danhGiaCanThiepDDS.filter(danhGiaCanThiepDDItem => {
        const danhGiaCanThiepDDIdentifier = getDanhGiaCanThiepDDIdentifier(danhGiaCanThiepDDItem);
        if (danhGiaCanThiepDDIdentifier == null || danhGiaCanThiepDDCollectionIdentifiers.includes(danhGiaCanThiepDDIdentifier)) {
          return false;
        }
        danhGiaCanThiepDDCollectionIdentifiers.push(danhGiaCanThiepDDIdentifier);
        return true;
      });
      return [...danhGiaCanThiepDDSToAdd, ...danhGiaCanThiepDDCollection];
    }
    return danhGiaCanThiepDDCollection;
  }
}
