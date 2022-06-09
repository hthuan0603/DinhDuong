import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBaoHiem, BaoHiem } from '../bao-hiem.model';

import { BaoHiemService } from './bao-hiem.service';

describe('BaoHiem Service', () => {
  let service: BaoHiemService;
  let httpMock: HttpTestingController;
  let elemDefault: IBaoHiem;
  let expectedResult: IBaoHiem | IBaoHiem[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BaoHiemService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maTheBHYT: 'AAAAAAA',
      doiTuong: 'AAAAAAA',
      baoHiemThanhToan: 0,
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

    it('should create a BaoHiem', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new BaoHiem()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BaoHiem', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maTheBHYT: 'BBBBBB',
          doiTuong: 'BBBBBB',
          baoHiemThanhToan: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BaoHiem', () => {
      const patchObject = Object.assign(
        {
          doiTuong: 'BBBBBB',
        },
        new BaoHiem()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BaoHiem', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maTheBHYT: 'BBBBBB',
          doiTuong: 'BBBBBB',
          baoHiemThanhToan: 1,
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

    it('should delete a BaoHiem', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBaoHiemToCollectionIfMissing', () => {
      it('should add a BaoHiem to an empty array', () => {
        const baoHiem: IBaoHiem = { id: 123 };
        expectedResult = service.addBaoHiemToCollectionIfMissing([], baoHiem);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(baoHiem);
      });

      it('should not add a BaoHiem to an array that contains it', () => {
        const baoHiem: IBaoHiem = { id: 123 };
        const baoHiemCollection: IBaoHiem[] = [
          {
            ...baoHiem,
          },
          { id: 456 },
        ];
        expectedResult = service.addBaoHiemToCollectionIfMissing(baoHiemCollection, baoHiem);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BaoHiem to an array that doesn't contain it", () => {
        const baoHiem: IBaoHiem = { id: 123 };
        const baoHiemCollection: IBaoHiem[] = [{ id: 456 }];
        expectedResult = service.addBaoHiemToCollectionIfMissing(baoHiemCollection, baoHiem);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(baoHiem);
      });

      it('should add only unique BaoHiem to an array', () => {
        const baoHiemArray: IBaoHiem[] = [{ id: 123 }, { id: 456 }, { id: 21213 }];
        const baoHiemCollection: IBaoHiem[] = [{ id: 123 }];
        expectedResult = service.addBaoHiemToCollectionIfMissing(baoHiemCollection, ...baoHiemArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const baoHiem: IBaoHiem = { id: 123 };
        const baoHiem2: IBaoHiem = { id: 456 };
        expectedResult = service.addBaoHiemToCollectionIfMissing([], baoHiem, baoHiem2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(baoHiem);
        expect(expectedResult).toContain(baoHiem2);
      });

      it('should accept null and undefined values', () => {
        const baoHiem: IBaoHiem = { id: 123 };
        expectedResult = service.addBaoHiemToCollectionIfMissing([], null, baoHiem, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(baoHiem);
      });

      it('should return initial array if no BaoHiem is added', () => {
        const baoHiemCollection: IBaoHiem[] = [{ id: 123 }];
        expectedResult = service.addBaoHiemToCollectionIfMissing(baoHiemCollection, undefined, null);
        expect(expectedResult).toEqual(baoHiemCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
