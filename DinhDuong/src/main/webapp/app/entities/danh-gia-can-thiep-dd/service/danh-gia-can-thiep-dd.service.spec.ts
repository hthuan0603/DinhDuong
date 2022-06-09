import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDanhGiaCanThiepDD, DanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';

import { DanhGiaCanThiepDDService } from './danh-gia-can-thiep-dd.service';

describe('DanhGiaCanThiepDD Service', () => {
  let service: DanhGiaCanThiepDDService;
  let httpMock: HttpTestingController;
  let elemDefault: IDanhGiaCanThiepDD;
  let expectedResult: IDanhGiaCanThiepDD | IDanhGiaCanThiepDD[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DanhGiaCanThiepDDService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maBN: 'AAAAAAA',
      chuanDoanLS: 'AAAAAAA',
      bSDieuTri: 'AAAAAAA',
      thayDoiCanNangTrong1Thang: 'AAAAAAA',
      danhGiaCN: 0,
      khauPhanAn: 'AAAAAAA',
      danhGiaKPA: 0,
      trieuChungTieuHoa: 'AAAAAAA',
      mucDo: 'AAAAAAA',
      danhGiaMD: 0,
      giamChucNangHoatDong: 'AAAAAAA',
      danhGiaCNHD: 0,
      stress: 'AAAAAAA',
      danhGiaStress: 0,
      refeeding: false,
      danhGiaRefeeding: 0,
      tongDiem: 0,
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

    it('should create a DanhGiaCanThiepDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DanhGiaCanThiepDD()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DanhGiaCanThiepDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          chuanDoanLS: 'BBBBBB',
          bSDieuTri: 'BBBBBB',
          thayDoiCanNangTrong1Thang: 'BBBBBB',
          danhGiaCN: 1,
          khauPhanAn: 'BBBBBB',
          danhGiaKPA: 1,
          trieuChungTieuHoa: 'BBBBBB',
          mucDo: 'BBBBBB',
          danhGiaMD: 1,
          giamChucNangHoatDong: 'BBBBBB',
          danhGiaCNHD: 1,
          stress: 'BBBBBB',
          danhGiaStress: 1,
          refeeding: true,
          danhGiaRefeeding: 1,
          tongDiem: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DanhGiaCanThiepDD', () => {
      const patchObject = Object.assign(
        {
          bSDieuTri: 'BBBBBB',
          danhGiaKPA: 1,
          mucDo: 'BBBBBB',
          refeeding: true,
        },
        new DanhGiaCanThiepDD()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DanhGiaCanThiepDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          chuanDoanLS: 'BBBBBB',
          bSDieuTri: 'BBBBBB',
          thayDoiCanNangTrong1Thang: 'BBBBBB',
          danhGiaCN: 1,
          khauPhanAn: 'BBBBBB',
          danhGiaKPA: 1,
          trieuChungTieuHoa: 'BBBBBB',
          mucDo: 'BBBBBB',
          danhGiaMD: 1,
          giamChucNangHoatDong: 'BBBBBB',
          danhGiaCNHD: 1,
          stress: 'BBBBBB',
          danhGiaStress: 1,
          refeeding: true,
          danhGiaRefeeding: 1,
          tongDiem: 1,
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

    it('should delete a DanhGiaCanThiepDD', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDanhGiaCanThiepDDToCollectionIfMissing', () => {
      it('should add a DanhGiaCanThiepDD to an empty array', () => {
        const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 123 };
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing([], danhGiaCanThiepDD);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(danhGiaCanThiepDD);
      });

      it('should not add a DanhGiaCanThiepDD to an array that contains it', () => {
        const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 123 };
        const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [
          {
            ...danhGiaCanThiepDD,
          },
          { id: 456 },
        ];
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing(danhGiaCanThiepDDCollection, danhGiaCanThiepDD);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DanhGiaCanThiepDD to an array that doesn't contain it", () => {
        const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 123 };
        const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [{ id: 456 }];
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing(danhGiaCanThiepDDCollection, danhGiaCanThiepDD);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(danhGiaCanThiepDD);
      });

      it('should add only unique DanhGiaCanThiepDD to an array', () => {
        const danhGiaCanThiepDDArray: IDanhGiaCanThiepDD[] = [{ id: 123 }, { id: 456 }, { id: 7424 }];
        const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [{ id: 123 }];
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing(danhGiaCanThiepDDCollection, ...danhGiaCanThiepDDArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 123 };
        const danhGiaCanThiepDD2: IDanhGiaCanThiepDD = { id: 456 };
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing([], danhGiaCanThiepDD, danhGiaCanThiepDD2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(danhGiaCanThiepDD);
        expect(expectedResult).toContain(danhGiaCanThiepDD2);
      });

      it('should accept null and undefined values', () => {
        const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 123 };
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing([], null, danhGiaCanThiepDD, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(danhGiaCanThiepDD);
      });

      it('should return initial array if no DanhGiaCanThiepDD is added', () => {
        const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [{ id: 123 }];
        expectedResult = service.addDanhGiaCanThiepDDToCollectionIfMissing(danhGiaCanThiepDDCollection, undefined, null);
        expect(expectedResult).toEqual(danhGiaCanThiepDDCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
