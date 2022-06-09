import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBaoCaoTaiChinh, getBaoCaoTaiChinhIdentifier } from '../bao-cao-tai-chinh.model';

export type EntityResponseType = HttpResponse<IBaoCaoTaiChinh>;
export type EntityArrayResponseType = HttpResponse<IBaoCaoTaiChinh[]>;

@Injectable({ providedIn: 'root' })
export class BaoCaoTaiChinhService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bao-cao-tai-chinhs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(baoCaoTaiChinh: IBaoCaoTaiChinh): Observable<EntityResponseType> {
    return this.http.post<IBaoCaoTaiChinh>(this.resourceUrl, baoCaoTaiChinh, { observe: 'response' });
  }

  update(baoCaoTaiChinh: IBaoCaoTaiChinh): Observable<EntityResponseType> {
    return this.http.put<IBaoCaoTaiChinh>(`${this.resourceUrl}/${getBaoCaoTaiChinhIdentifier(baoCaoTaiChinh) as number}`, baoCaoTaiChinh, {
      observe: 'response',
    });
  }

  partialUpdate(baoCaoTaiChinh: IBaoCaoTaiChinh): Observable<EntityResponseType> {
    return this.http.patch<IBaoCaoTaiChinh>(
      `${this.resourceUrl}/${getBaoCaoTaiChinhIdentifier(baoCaoTaiChinh) as number}`,
      baoCaoTaiChinh,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBaoCaoTaiChinh>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBaoCaoTaiChinh[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBaoCaoTaiChinhToCollectionIfMissing(
    baoCaoTaiChinhCollection: IBaoCaoTaiChinh[],
    ...baoCaoTaiChinhsToCheck: (IBaoCaoTaiChinh | null | undefined)[]
  ): IBaoCaoTaiChinh[] {
    const baoCaoTaiChinhs: IBaoCaoTaiChinh[] = baoCaoTaiChinhsToCheck.filter(isPresent);
    if (baoCaoTaiChinhs.length > 0) {
      const baoCaoTaiChinhCollectionIdentifiers = baoCaoTaiChinhCollection.map(
        baoCaoTaiChinhItem => getBaoCaoTaiChinhIdentifier(baoCaoTaiChinhItem)!
      );
      const baoCaoTaiChinhsToAdd = baoCaoTaiChinhs.filter(baoCaoTaiChinhItem => {
        const baoCaoTaiChinhIdentifier = getBaoCaoTaiChinhIdentifier(baoCaoTaiChinhItem);
        if (baoCaoTaiChinhIdentifier == null || baoCaoTaiChinhCollectionIdentifiers.includes(baoCaoTaiChinhIdentifier)) {
          return false;
        }
        baoCaoTaiChinhCollectionIdentifiers.push(baoCaoTaiChinhIdentifier);
        return true;
      });
      return [...baoCaoTaiChinhsToAdd, ...baoCaoTaiChinhCollection];
    }
    return baoCaoTaiChinhCollection;
  }
}
