import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDieuTri, getDieuTriIdentifier } from '../dieu-tri.model';

export type EntityResponseType = HttpResponse<IDieuTri>;
export type EntityArrayResponseType = HttpResponse<IDieuTri[]>;

@Injectable({ providedIn: 'root' })
export class DieuTriService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dieu-tris');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(dieuTri: IDieuTri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dieuTri);
    return this.http
      .post<IDieuTri>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dieuTri: IDieuTri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dieuTri);
    return this.http
      .put<IDieuTri>(`${this.resourceUrl}/${getDieuTriIdentifier(dieuTri) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(dieuTri: IDieuTri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dieuTri);
    return this.http
      .patch<IDieuTri>(`${this.resourceUrl}/${getDieuTriIdentifier(dieuTri) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDieuTri>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDieuTri[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDieuTriToCollectionIfMissing(dieuTriCollection: IDieuTri[], ...dieuTrisToCheck: (IDieuTri | null | undefined)[]): IDieuTri[] {
    const dieuTris: IDieuTri[] = dieuTrisToCheck.filter(isPresent);
    if (dieuTris.length > 0) {
      const dieuTriCollectionIdentifiers = dieuTriCollection.map(dieuTriItem => getDieuTriIdentifier(dieuTriItem)!);
      const dieuTrisToAdd = dieuTris.filter(dieuTriItem => {
        const dieuTriIdentifier = getDieuTriIdentifier(dieuTriItem);
        if (dieuTriIdentifier == null || dieuTriCollectionIdentifiers.includes(dieuTriIdentifier)) {
          return false;
        }
        dieuTriCollectionIdentifiers.push(dieuTriIdentifier);
        return true;
      });
      return [...dieuTrisToAdd, ...dieuTriCollection];
    }
    return dieuTriCollection;
  }

  protected convertDateFromClient(dieuTri: IDieuTri): IDieuTri {
    return Object.assign({}, dieuTri, {
      ngayVaoKhoa: dieuTri.ngayVaoKhoa?.isValid() ? dieuTri.ngayVaoKhoa.toJSON() : undefined,
      ngayRaVien: dieuTri.ngayRaVien?.isValid() ? dieuTri.ngayRaVien.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayVaoKhoa = res.body.ngayVaoKhoa ? dayjs(res.body.ngayVaoKhoa) : undefined;
      res.body.ngayRaVien = res.body.ngayRaVien ? dayjs(res.body.ngayRaVien) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((dieuTri: IDieuTri) => {
        dieuTri.ngayVaoKhoa = dieuTri.ngayVaoKhoa ? dayjs(dieuTri.ngayVaoKhoa) : undefined;
        dieuTri.ngayRaVien = dieuTri.ngayRaVien ? dayjs(dieuTri.ngayRaVien) : undefined;
      });
    }
    return res;
  }
}
