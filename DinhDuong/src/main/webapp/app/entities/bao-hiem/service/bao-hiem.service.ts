import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBaoHiem, getBaoHiemIdentifier } from '../bao-hiem.model';

export type EntityResponseType = HttpResponse<IBaoHiem>;
export type EntityArrayResponseType = HttpResponse<IBaoHiem[]>;

@Injectable({ providedIn: 'root' })
export class BaoHiemService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bao-hiems');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(baoHiem: IBaoHiem): Observable<EntityResponseType> {
    return this.http.post<IBaoHiem>(this.resourceUrl, baoHiem, { observe: 'response' });
  }

  update(baoHiem: IBaoHiem): Observable<EntityResponseType> {
    return this.http.put<IBaoHiem>(`${this.resourceUrl}/${getBaoHiemIdentifier(baoHiem) as number}`, baoHiem, { observe: 'response' });
  }

  partialUpdate(baoHiem: IBaoHiem): Observable<EntityResponseType> {
    return this.http.patch<IBaoHiem>(`${this.resourceUrl}/${getBaoHiemIdentifier(baoHiem) as number}`, baoHiem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBaoHiem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBaoHiem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBaoHiemToCollectionIfMissing(baoHiemCollection: IBaoHiem[], ...baoHiemsToCheck: (IBaoHiem | null | undefined)[]): IBaoHiem[] {
    const baoHiems: IBaoHiem[] = baoHiemsToCheck.filter(isPresent);
    if (baoHiems.length > 0) {
      const baoHiemCollectionIdentifiers = baoHiemCollection.map(baoHiemItem => getBaoHiemIdentifier(baoHiemItem)!);
      const baoHiemsToAdd = baoHiems.filter(baoHiemItem => {
        const baoHiemIdentifier = getBaoHiemIdentifier(baoHiemItem);
        if (baoHiemIdentifier == null || baoHiemCollectionIdentifiers.includes(baoHiemIdentifier)) {
          return false;
        }
        baoHiemCollectionIdentifiers.push(baoHiemIdentifier);
        return true;
      });
      return [...baoHiemsToAdd, ...baoHiemCollection];
    }
    return baoHiemCollection;
  }
}
