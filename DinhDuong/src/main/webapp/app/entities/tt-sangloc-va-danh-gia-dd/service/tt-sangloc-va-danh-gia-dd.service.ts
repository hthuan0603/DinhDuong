import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITTSanglocVaDanhGiaDD, getTTSanglocVaDanhGiaDDIdentifier } from '../tt-sangloc-va-danh-gia-dd.model';

export type EntityResponseType = HttpResponse<ITTSanglocVaDanhGiaDD>;
export type EntityArrayResponseType = HttpResponse<ITTSanglocVaDanhGiaDD[]>;

@Injectable({ providedIn: 'root' })
export class TTSanglocVaDanhGiaDDService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/tt-sangloc-va-danh-gia-dds');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tTSanglocVaDanhGiaDD);
    return this.http
      .post<ITTSanglocVaDanhGiaDD>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tTSanglocVaDanhGiaDD);
    return this.http
      .put<ITTSanglocVaDanhGiaDD>(`${this.resourceUrl}/${getTTSanglocVaDanhGiaDDIdentifier(tTSanglocVaDanhGiaDD) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tTSanglocVaDanhGiaDD);
    return this.http
      .patch<ITTSanglocVaDanhGiaDD>(`${this.resourceUrl}/${getTTSanglocVaDanhGiaDDIdentifier(tTSanglocVaDanhGiaDD) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITTSanglocVaDanhGiaDD>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITTSanglocVaDanhGiaDD[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addTTSanglocVaDanhGiaDDToCollectionIfMissing(
    tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[],
    ...tTSanglocVaDanhGiaDDSToCheck: (ITTSanglocVaDanhGiaDD | null | undefined)[]
  ): ITTSanglocVaDanhGiaDD[] {
    const tTSanglocVaDanhGiaDDS: ITTSanglocVaDanhGiaDD[] = tTSanglocVaDanhGiaDDSToCheck.filter(isPresent);
    if (tTSanglocVaDanhGiaDDS.length > 0) {
      const tTSanglocVaDanhGiaDDCollectionIdentifiers = tTSanglocVaDanhGiaDDCollection.map(
        tTSanglocVaDanhGiaDDItem => getTTSanglocVaDanhGiaDDIdentifier(tTSanglocVaDanhGiaDDItem)!
      );
      const tTSanglocVaDanhGiaDDSToAdd = tTSanglocVaDanhGiaDDS.filter(tTSanglocVaDanhGiaDDItem => {
        const tTSanglocVaDanhGiaDDIdentifier = getTTSanglocVaDanhGiaDDIdentifier(tTSanglocVaDanhGiaDDItem);
        if (tTSanglocVaDanhGiaDDIdentifier == null || tTSanglocVaDanhGiaDDCollectionIdentifiers.includes(tTSanglocVaDanhGiaDDIdentifier)) {
          return false;
        }
        tTSanglocVaDanhGiaDDCollectionIdentifiers.push(tTSanglocVaDanhGiaDDIdentifier);
        return true;
      });
      return [...tTSanglocVaDanhGiaDDSToAdd, ...tTSanglocVaDanhGiaDDCollection];
    }
    return tTSanglocVaDanhGiaDDCollection;
  }

  protected convertDateFromClient(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): ITTSanglocVaDanhGiaDD {
    return Object.assign({}, tTSanglocVaDanhGiaDD, {
      thoiGianChiDinh: tTSanglocVaDanhGiaDD.thoiGianChiDinh?.isValid() ? tTSanglocVaDanhGiaDD.thoiGianChiDinh.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.thoiGianChiDinh = res.body.thoiGianChiDinh ? dayjs(res.body.thoiGianChiDinh) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD) => {
        tTSanglocVaDanhGiaDD.thoiGianChiDinh = tTSanglocVaDanhGiaDD.thoiGianChiDinh
          ? dayjs(tTSanglocVaDanhGiaDD.thoiGianChiDinh)
          : undefined;
      });
    }
    return res;
  }
}
