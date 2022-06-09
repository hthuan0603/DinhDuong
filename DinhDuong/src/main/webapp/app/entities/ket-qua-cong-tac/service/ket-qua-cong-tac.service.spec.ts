import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IKetQuaCongTac, KetQuaCongTac } from '../ket-qua-cong-tac.model';

import { KetQuaCongTacService } from './ket-qua-cong-tac.service';

describe('KetQuaCongTac Service', () => {
  let service: KetQuaCongTacService;
  let httpMock: HttpTestingController;
  let elemDefault: IKetQuaCongTac;
  let expectedResult: IKetQuaCongTac | IKetQuaCongTac[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(KetQuaCongTacService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maKetQua: 0,
      tenKetQua: 'AAAAAAA',
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

    it('should create a KetQuaCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new KetQuaCongTac()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a KetQuaCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maKetQua: 1,
          tenKetQua: 'BBBBBB',
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

    it('should partial update a KetQuaCongTac', () => {
      const patchObject = Object.assign(
        {
          tenKetQua: 'BBBBBB',
        },
        new KetQuaCongTac()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of KetQuaCongTac', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maKetQua: 1,
          tenKetQua: 'BBBBBB',
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

    it('should delete a KetQuaCongTac', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addKetQuaCongTacToCollectionIfMissing', () => {
      it('should add a KetQuaCongTac to an empty array', () => {
        const ketQuaCongTac: IKetQuaCongTac = { id: 123 };
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing([], ketQuaCongTac);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ketQuaCongTac);
      });

      it('should not add a KetQuaCongTac to an array that contains it', () => {
        const ketQuaCongTac: IKetQuaCongTac = { id: 123 };
        const ketQuaCongTacCollection: IKetQuaCongTac[] = [
          {
            ...ketQuaCongTac,
          },
          { id: 456 },
        ];
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing(ketQuaCongTacCollection, ketQuaCongTac);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a KetQuaCongTac to an array that doesn't contain it", () => {
        const ketQuaCongTac: IKetQuaCongTac = { id: 123 };
        const ketQuaCongTacCollection: IKetQuaCongTac[] = [{ id: 456 }];
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing(ketQuaCongTacCollection, ketQuaCongTac);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ketQuaCongTac);
      });

      it('should add only unique KetQuaCongTac to an array', () => {
        const ketQuaCongTacArray: IKetQuaCongTac[] = [{ id: 123 }, { id: 456 }, { id: 78458 }];
        const ketQuaCongTacCollection: IKetQuaCongTac[] = [{ id: 123 }];
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing(ketQuaCongTacCollection, ...ketQuaCongTacArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ketQuaCongTac: IKetQuaCongTac = { id: 123 };
        const ketQuaCongTac2: IKetQuaCongTac = { id: 456 };
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing([], ketQuaCongTac, ketQuaCongTac2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ketQuaCongTac);
        expect(expectedResult).toContain(ketQuaCongTac2);
      });

      it('should accept null and undefined values', () => {
        const ketQuaCongTac: IKetQuaCongTac = { id: 123 };
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing([], null, ketQuaCongTac, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ketQuaCongTac);
      });

      it('should return initial array if no KetQuaCongTac is added', () => {
        const ketQuaCongTacCollection: IKetQuaCongTac[] = [{ id: 123 }];
        expectedResult = service.addKetQuaCongTacToCollectionIfMissing(ketQuaCongTacCollection, undefined, null);
        expect(expectedResult).toEqual(ketQuaCongTacCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
