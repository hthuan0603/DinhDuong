import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILyDoCongTac, getLyDoCongTacIdentifier } from '../ly-do-cong-tac.model';

export type EntityResponseType = HttpResponse<ILyDoCongTac>;
export type EntityArrayResponseType = HttpResponse<ILyDoCongTac[]>;

@Injectable({ providedIn: 'root' })
export class LyDoCongTacService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ly-do-cong-tacs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(lyDoCongTac: ILyDoCongTac): Observable<EntityResponseType> {
    return this.http.post<ILyDoCongTac>(this.resourceUrl, lyDoCongTac, { observe: 'response' });
  }

  update(lyDoCongTac: ILyDoCongTac): Observable<EntityResponseType> {
    return this.http.put<ILyDoCongTac>(`${this.resourceUrl}/${getLyDoCongTacIdentifier(lyDoCongTac) as number}`, lyDoCongTac, {
      observe: 'response',
    });
  }

  partialUpdate(lyDoCongTac: ILyDoCongTac): Observable<EntityResponseType> {
    return this.http.patch<ILyDoCongTac>(`${this.resourceUrl}/${getLyDoCongTacIdentifier(lyDoCongTac) as number}`, lyDoCongTac, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILyDoCongTac>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILyDoCongTac[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addLyDoCongTacToCollectionIfMissing(
    lyDoCongTacCollection: ILyDoCongTac[],
    ...lyDoCongTacsToCheck: (ILyDoCongTac | null | undefined)[]
  ): ILyDoCongTac[] {
    const lyDoCongTacs: ILyDoCongTac[] = lyDoCongTacsToCheck.filter(isPresent);
    if (lyDoCongTacs.length > 0) {
      const lyDoCongTacCollectionIdentifiers = lyDoCongTacCollection.map(lyDoCongTacItem => getLyDoCongTacIdentifier(lyDoCongTacItem)!);
      const lyDoCongTacsToAdd = lyDoCongTacs.filter(lyDoCongTacItem => {
        const lyDoCongTacIdentifier = getLyDoCongTacIdentifier(lyDoCongTacItem);
        if (lyDoCongTacIdentifier == null || lyDoCongTacCollectionIdentifiers.includes(lyDoCongTacIdentifier)) {
          return false;
        }
        lyDoCongTacCollectionIdentifiers.push(lyDoCongTacIdentifier);
        return true;
      });
      return [...lyDoCongTacsToAdd, ...lyDoCongTacCollection];
    }
    return lyDoCongTacCollection;
  }
}
