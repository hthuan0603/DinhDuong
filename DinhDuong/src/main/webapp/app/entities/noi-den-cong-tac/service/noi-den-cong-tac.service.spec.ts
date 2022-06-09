import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';

import { NoiDenCongTacService } from './noi-den-cong-tac.service';

describe('NoiDenCongTac Service', () => {
  let service: NoiDenCongTacService;
  let httpMock: HttpTestingController;
  let elemDefault: INoiDenCongTac;
  let expectedResult: INoiDenCongTac | INoiDenCongTac[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NoiDenCongTacService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maNoiDen: 0,
      tenNoiDen: 'AAAAAAA',
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

    it('should create a NoiDenCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new NoiDenCongTac()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a NoiDenCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNoiDen: 1,
          tenNoiDen: 'BBBBBB',
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

    it('should partial update a NoiDenCongTac', () => {
      const patchObject = Object.assign(
        {
          maNoiDen: 1,
          tenNoiDen: 'BBBBBB',
          thuTuSX: 1,
        },
        new NoiDenCongTac()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of NoiDenCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNoiDen: 1,
          tenNoiDen: 'BBBBBB',
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

    it('should delete a NoiDenCongTac', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addNoiDenCongTacToCollectionIfMissing', () => {
      it('should add a NoiDenCongTac to an empty array', () => {
        const noiDenCongTac: INoiDenCongTac = { id: 123 };
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing([], noiDenCongTac);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(noiDenCongTac);
      });

      it('should not add a NoiDenCongTac to an array that contains it', () => {
        const noiDenCongTac: INoiDenCongTac = { id: 123 };
        const noiDenCongTacCollection: INoiDenCongTac[] = [
          {
            ...noiDenCongTac,
          },
          { id: 456 },
        ];
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing(noiDenCongTacCollection, noiDenCongTac);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NoiDenCongTac to an array that doesn't contain it", () => {
        const noiDenCongTac: INoiDenCongTac = { id: 123 };
        const noiDenCongTacCollection: INoiDenCongTac[] = [{ id: 456 }];
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing(noiDenCongTacCollection, noiDenCongTac);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(noiDenCongTac);
      });

      it('should add only unique NoiDenCongTac to an array', () => {
        const noiDenCongTacArray: INoiDenCongTac[] = [{ id: 123 }, { id: 456 }, { id: 74304 }];
        const noiDenCongTacCollection: INoiDenCongTac[] = [{ id: 123 }];
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing(noiDenCongTacCollection, ...noiDenCongTacArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const noiDenCongTac: INoiDenCongTac = { id: 123 };
        const noiDenCongTac2: INoiDenCongTac = { id: 456 };
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing([], noiDenCongTac, noiDenCongTac2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(noiDenCongTac);
        expect(expectedResult).toContain(noiDenCongTac2);
      });

      it('should accept null and undefined values', () => {
        const noiDenCongTac: INoiDenCongTac = { id: 123 };
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing([], null, noiDenCongTac, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(noiDenCongTac);
      });

      it('should return initial array if no NoiDenCongTac is added', () => {
        const noiDenCongTacCollection: INoiDenCongTac[] = [{ id: 123 }];
        expectedResult = service.addNoiDenCongTacToCollectionIfMissing(noiDenCongTacCollection, undefined, null);
        expect(expectedResult).toEqual(noiDenCongTacCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
