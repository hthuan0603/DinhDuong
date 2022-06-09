import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INhanVienTiepNhan, getNhanVienTiepNhanIdentifier } from '../nhan-vien-tiep-nhan.model';

export type EntityResponseType = HttpResponse<INhanVienTiepNhan>;
export type EntityArrayResponseType = HttpResponse<INhanVienTiepNhan[]>;

@Injectable({ providedIn: 'root' })
export class NhanVienTiepNhanService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/nhan-vien-tiep-nhans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(nhanVienTiepNhan: INhanVienTiepNhan): Observable<EntityResponseType> {
    return this.http.post<INhanVienTiepNhan>(this.resourceUrl, nhanVienTiepNhan, { observe: 'response' });
  }

  update(nhanVienTiepNhan: INhanVienTiepNhan): Observable<EntityResponseType> {
    return this.http.put<INhanVienTiepNhan>(
      `${this.resourceUrl}/${getNhanVienTiepNhanIdentifier(nhanVienTiepNhan) as number}`,
      nhanVienTiepNhan,
      { observe: 'response' }
    );
  }

  partialUpdate(nhanVienTiepNhan: INhanVienTiepNhan): Observable<EntityResponseType> {
    return this.http.patch<INhanVienTiepNhan>(
      `${this.resourceUrl}/${getNhanVienTiepNhanIdentifier(nhanVienTiepNhan) as number}`,
      nhanVienTiepNhan,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INhanVienTiepNhan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INhanVienTiepNhan[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addNhanVienTiepNhanToCollectionIfMissing(
    nhanVienTiepNhanCollection: INhanVienTiepNhan[],
    ...nhanVienTiepNhansToCheck: (INhanVienTiepNhan | null | undefined)[]
  ): INhanVienTiepNhan[] {
    const nhanVienTiepNhans: INhanVienTiepNhan[] = nhanVienTiepNhansToCheck.filter(isPresent);
    if (nhanVienTiepNhans.length > 0) {
      const nhanVienTiepNhanCollectionIdentifiers = nhanVienTiepNhanCollection.map(
        nhanVienTiepNhanItem => getNhanVienTiepNhanIdentifier(nhanVienTiepNhanItem)!
      );
      const nhanVienTiepNhansToAdd = nhanVienTiepNhans.filter(nhanVienTiepNhanItem => {
        const nhanVienTiepNhanIdentifier = getNhanVienTiepNhanIdentifier(nhanVienTiepNhanItem);
        if (nhanVienTiepNhanIdentifier == null || nhanVienTiepNhanCollectionIdentifiers.includes(nhanVienTiepNhanIdentifier)) {
          return false;
        }
        nhanVienTiepNhanCollectionIdentifiers.push(nhanVienTiepNhanIdentifier);
        return true;
      });
      return [...nhanVienTiepNhansToAdd, ...nhanVienTiepNhanCollection];
    }
    return nhanVienTiepNhanCollection;
  }
}
