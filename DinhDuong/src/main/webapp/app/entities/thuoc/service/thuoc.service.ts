import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IThuoc, getThuocIdentifier } from '../thuoc.model';

export type EntityResponseType = HttpResponse<IThuoc>;
export type EntityArrayResponseType = HttpResponse<IThuoc[]>;

@Injectable({ providedIn: 'root' })
export class ThuocService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/thuocs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(thuoc: IThuoc): Observable<EntityResponseType> {
    return this.http.post<IThuoc>(this.resourceUrl, thuoc, { observe: 'response' });
  }

  update(thuoc: IThuoc): Observable<EntityResponseType> {
    return this.http.put<IThuoc>(`${this.resourceUrl}/${getThuocIdentifier(thuoc) as number}`, thuoc, { observe: 'response' });
  }

  partialUpdate(thuoc: IThuoc): Observable<EntityResponseType> {
    return this.http.patch<IThuoc>(`${this.resourceUrl}/${getThuocIdentifier(thuoc) as number}`, thuoc, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IThuoc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IThuoc[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addThuocToCollectionIfMissing(thuocCollection: IThuoc[], ...thuocsToCheck: (IThuoc | null | undefined)[]): IThuoc[] {
    const thuocs: IThuoc[] = thuocsToCheck.filter(isPresent);
    if (thuocs.length > 0) {
      const thuocCollectionIdentifiers = thuocCollection.map(thuocItem => getThuocIdentifier(thuocItem)!);
      const thuocsToAdd = thuocs.filter(thuocItem => {
        const thuocIdentifier = getThuocIdentifier(thuocItem);
        if (thuocIdentifier == null || thuocCollectionIdentifiers.includes(thuocIdentifier)) {
          return false;
        }
        thuocCollectionIdentifiers.push(thuocIdentifier);
        return true;
      });
      return [...thuocsToAdd, ...thuocCollection];
    }
    return thuocCollection;
  }
}
