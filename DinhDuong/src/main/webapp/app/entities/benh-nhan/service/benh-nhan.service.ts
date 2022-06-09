import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBenhNhan, getBenhNhanIdentifier } from '../benh-nhan.model';

export type EntityResponseType = HttpResponse<IBenhNhan>;
export type EntityArrayResponseType = HttpResponse<IBenhNhan[]>;

@Injectable({ providedIn: 'root' })
export class BenhNhanService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/benh-nhans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(benhNhan: IBenhNhan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(benhNhan);
    return this.http
      .post<IBenhNhan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(benhNhan: IBenhNhan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(benhNhan);
    return this.http
      .put<IBenhNhan>(`${this.resourceUrl}/${getBenhNhanIdentifier(benhNhan) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(benhNhan: IBenhNhan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(benhNhan);
    return this.http
      .patch<IBenhNhan>(`${this.resourceUrl}/${getBenhNhanIdentifier(benhNhan) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBenhNhan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBenhNhan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addBenhNhanToCollectionIfMissing(benhNhanCollection: IBenhNhan[], ...benhNhansToCheck: (IBenhNhan | null | undefined)[]): IBenhNhan[] {
    const benhNhans: IBenhNhan[] = benhNhansToCheck.filter(isPresent);
    if (benhNhans.length > 0) {
      const benhNhanCollectionIdentifiers = benhNhanCollection.map(benhNhanItem => getBenhNhanIdentifier(benhNhanItem)!);
      const benhNhansToAdd = benhNhans.filter(benhNhanItem => {
        const benhNhanIdentifier = getBenhNhanIdentifier(benhNhanItem);
        if (benhNhanIdentifier == null || benhNhanCollectionIdentifiers.includes(benhNhanIdentifier)) {
          return false;
        }
        benhNhanCollectionIdentifiers.push(benhNhanIdentifier);
        return true;
      });
      return [...benhNhansToAdd, ...benhNhanCollection];
    }
    return benhNhanCollection;
  }

  protected convertDateFromClient(benhNhan: IBenhNhan): IBenhNhan {
    return Object.assign({}, benhNhan, {
      ngaySinh: benhNhan.ngaySinh?.isValid() ? benhNhan.ngaySinh.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngaySinh = res.body.ngaySinh ? dayjs(res.body.ngaySinh) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((benhNhan: IBenhNhan) => {
        benhNhan.ngaySinh = benhNhan.ngaySinh ? dayjs(benhNhan.ngaySinh) : undefined;
      });
    }
    return res;
  }
}
