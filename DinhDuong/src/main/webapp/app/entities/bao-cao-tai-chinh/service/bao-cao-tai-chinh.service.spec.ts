import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBaoCaoTaiChinh, BaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';

import { BaoCaoTaiChinhService } from './bao-cao-tai-chinh.service';

describe('BaoCaoTaiChinh Service', () => {
  let service: BaoCaoTaiChinhService;
  let httpMock: HttpTestingController;
  let elemDefault: IBaoCaoTaiChinh;
  let expectedResult: IBaoCaoTaiChinh | IBaoCaoTaiChinh[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BaoCaoTaiChinhService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maBaoCao: 0,
      luuTru: 0,
      tienAn: 0,
      tienO: 0,
      tienDiLai: 0,
      taiLieu: 0,
      giangDay: 0,
      khac: 0,
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

    it('should create a BaoCaoTaiChinh', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new BaoCaoTaiChinh()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BaoCaoTaiChinh', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBaoCao: 1,
          luuTru: 1,
          tienAn: 1,
          tienO: 1,
          tienDiLai: 1,
          taiLieu: 1,
          giangDay: 1,
          khac: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BaoCaoTaiChinh', () => {
      const patchObject = Object.assign(
        {
          maBaoCao: 1,
          luuTru: 1,
          tienO: 1,
          tienDiLai: 1,
          khac: 1,
        },
        new BaoCaoTaiChinh()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BaoCaoTaiChinh', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBaoCao: 1,
          luuTru: 1,
          tienAn: 1,
          tienO: 1,
          tienDiLai: 1,
          taiLieu: 1,
          giangDay: 1,
          khac: 1,
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

    it('should delete a BaoCaoTaiChinh', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBaoCaoTaiChinhToCollectionIfMissing', () => {
      it('should add a BaoCaoTaiChinh to an empty array', () => {
        const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 123 };
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing([], baoCaoTaiChinh);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(baoCaoTaiChinh);
      });

      it('should not add a BaoCaoTaiChinh to an array that contains it', () => {
        const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 123 };
        const baoCaoTaiChinhCollection: IBaoCaoTaiChinh[] = [
          {
            ...baoCaoTaiChinh,
          },
          { id: 456 },
        ];
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing(baoCaoTaiChinhCollection, baoCaoTaiChinh);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BaoCaoTaiChinh to an array that doesn't contain it", () => {
        const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 123 };
        const baoCaoTaiChinhCollection: IBaoCaoTaiChinh[] = [{ id: 456 }];
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing(baoCaoTaiChinhCollection, baoCaoTaiChinh);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(baoCaoTaiChinh);
      });

      it('should add only unique BaoCaoTaiChinh to an array', () => {
        const baoCaoTaiChinhArray: IBaoCaoTaiChinh[] = [{ id: 123 }, { id: 456 }, { id: 88737 }];
        const baoCaoTaiChinhCollection: IBaoCaoTaiChinh[] = [{ id: 123 }];
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing(baoCaoTaiChinhCollection, ...baoCaoTaiChinhArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 123 };
        const baoCaoTaiChinh2: IBaoCaoTaiChinh = { id: 456 };
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing([], baoCaoTaiChinh, baoCaoTaiChinh2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(baoCaoTaiChinh);
        expect(expectedResult).toContain(baoCaoTaiChinh2);
      });

      it('should accept null and undefined values', () => {
        const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 123 };
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing([], null, baoCaoTaiChinh, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(baoCaoTaiChinh);
      });

      it('should return initial array if no BaoCaoTaiChinh is added', () => {
        const baoCaoTaiChinhCollection: IBaoCaoTaiChinh[] = [{ id: 123 }];
        expectedResult = service.addBaoCaoTaiChinhToCollectionIfMissing(baoCaoTaiChinhCollection, undefined, null);
        expect(expectedResult).toEqual(baoCaoTaiChinhCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
