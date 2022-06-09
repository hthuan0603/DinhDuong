import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ILyDoCongTac, LyDoCongTac } from '../ly-do-cong-tac.model';

import { LyDoCongTacService } from './ly-do-cong-tac.service';

describe('LyDoCongTac Service', () => {
  let service: LyDoCongTacService;
  let httpMock: HttpTestingController;
  let elemDefault: ILyDoCongTac;
  let expectedResult: ILyDoCongTac | ILyDoCongTac[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(LyDoCongTacService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maLyDo: 0,
      tenLyDo: 'AAAAAAA',
      thuTuSX: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a LyDoCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new LyDoCongTac()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a LyDoCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maLyDo: 1,
          tenLyDo: 'BBBBBB',
          thuTuSX: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a LyDoCongTac', () => {
      const patchObject = Object.assign(
        {
          tenLyDo: 'BBBBBB',
        },
        new LyDoCongTac()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of LyDoCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maLyDo: 1,
          tenLyDo: 'BBBBBB',
          thuTuSX: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a LyDoCongTac', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addLyDoCongTacToCollectionIfMissing', () => {
      it('should add a LyDoCongTac to an empty array', () => {
        const lyDoCongTac: ILyDoCongTac = { id: 123 };
        expectedResult = service.addLyDoCongTacToCollectionIfMissing([], lyDoCongTac);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(lyDoCongTac);
      });

      it('should not add a LyDoCongTac to an array that contains it', () => {
        const lyDoCongTac: ILyDoCongTac = { id: 123 };
        const lyDoCongTacCollection: ILyDoCongTac[] = [
          {
            ...lyDoCongTac,
          },
          { id: 456 },
        ];
        expectedResult = service.addLyDoCongTacToCollectionIfMissing(lyDoCongTacCollection, lyDoCongTac);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a LyDoCongTac to an array that doesn't contain it", () => {
        const lyDoCongTac: ILyDoCongTac = { id: 123 };
        const lyDoCongTacCollection: ILyDoCongTac[] = [{ id: 456 }];
        expectedResult = service.addLyDoCongTacToCollectionIfMissing(lyDoCongTacCollection, lyDoCongTac);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(lyDoCongTac);
      });

      it('should add only unique LyDoCongTac to an array', () => {
        const lyDoCongTacArray: ILyDoCongTac[] = [{ id: 123 }, { id: 456 }, { id: 37296 }];
        const lyDoCongTacCollection: ILyDoCongTac[] = [{ id: 123 }];
        expectedResult = service.addLyDoCongTacToCollectionIfMissing(lyDoCongTacCollection, ...lyDoCongTacArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const lyDoCongTac: ILyDoCongTac = { id: 123 };
        const lyDoCongTac2: ILyDoCongTac = { id: 456 };
        expectedResult = service.addLyDoCongTacToCollectionIfMissing([], lyDoCongTac, lyDoCongTac2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(lyDoCongTac);
        expect(expectedResult).toContain(lyDoCongTac2);
      });

      it('should accept null and undefined values', () => {
        const lyDoCongTac: ILyDoCongTac = { id: 123 };
        expectedResult = service.addLyDoCongTacToCollectionIfMissing([], null, lyDoCongTac, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(lyDoCongTac);
      });

      it('should return initial array if no LyDoCongTac is added', () => {
        const lyDoCongTacCollection: ILyDoCongTac[] = [{ id: 123 }];
        expectedResult = service.addLyDoCongTacToCollectionIfMissing(lyDoCongTacCollection, undefined, null);
        expect(expectedResult).toEqual(lyDoCongTacCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
