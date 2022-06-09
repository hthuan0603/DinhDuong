import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDieuTri, DieuTri } from '../dieu-tri.model';

import { DieuTriService } from './dieu-tri.service';

describe('DieuTri Service', () => {
  let service: DieuTriService;
  let httpMock: HttpTestingController;
  let elemDefault: IDieuTri;
  let expectedResult: IDieuTri | IDieuTri[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DieuTriService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      maDieuTri: 'AAAAAAA',
      hoTen: 'AAAAAAA',
      maBenhAn: 'AAAAAAA',
      tenBSDieuTri: 'AAAAAAA',
      nguoiNha: 'AAAAAAA',
      giuong: 'AAAAAAA',
      maTheBHYT: 'AAAAAAA',
      ngayVaoKhoa: currentDate,
      ngayRaVien: currentDate,
      bVChuyen: 'AAAAAAA',
      ketQuaDieuTri: 'AAAAAAA',
      lichSuChuyenKhoa: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          ngayVaoKhoa: currentDate.format(DATE_TIME_FORMAT),
          ngayRaVien: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a DieuTri', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          ngayVaoKhoa: currentDate.format(DATE_TIME_FORMAT),
          ngayRaVien: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayVaoKhoa: currentDate,
          ngayRaVien: currentDate,
        },
        returnedFromService
      );

      service.create(new DieuTri()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DieuTri', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maDieuTri: 'BBBBBB',
          hoTen: 'BBBBBB',
          maBenhAn: 'BBBBBB',
          tenBSDieuTri: 'BBBBBB',
          nguoiNha: 'BBBBBB',
          giuong: 'BBBBBB',
          maTheBHYT: 'BBBBBB',
          ngayVaoKhoa: currentDate.format(DATE_TIME_FORMAT),
          ngayRaVien: currentDate.format(DATE_TIME_FORMAT),
          bVChuyen: 'BBBBBB',
          ketQuaDieuTri: 'BBBBBB',
          lichSuChuyenKhoa: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayVaoKhoa: currentDate,
          ngayRaVien: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DieuTri', () => {
      const patchObject = Object.assign(
        {
          maDieuTri: 'BBBBBB',
          tenBSDieuTri: 'BBBBBB',
          nguoiNha: 'BBBBBB',
          maTheBHYT: 'BBBBBB',
          ngayRaVien: currentDate.format(DATE_TIME_FORMAT),
          ketQuaDieuTri: 'BBBBBB',
        },
        new DieuTri()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          ngayVaoKhoa: currentDate,
          ngayRaVien: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DieuTri', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maDieuTri: 'BBBBBB',
          hoTen: 'BBBBBB',
          maBenhAn: 'BBBBBB',
          tenBSDieuTri: 'BBBBBB',
          nguoiNha: 'BBBBBB',
          giuong: 'BBBBBB',
          maTheBHYT: 'BBBBBB',
          ngayVaoKhoa: currentDate.format(DATE_TIME_FORMAT),
          ngayRaVien: currentDate.format(DATE_TIME_FORMAT),
          bVChuyen: 'BBBBBB',
          ketQuaDieuTri: 'BBBBBB',
          lichSuChuyenKhoa: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayVaoKhoa: currentDate,
          ngayRaVien: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a DieuTri', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDieuTriToCollectionIfMissing', () => {
      it('should add a DieuTri to an empty array', () => {
        const dieuTri: IDieuTri = { id: 123 };
        expectedResult = service.addDieuTriToCollectionIfMissing([], dieuTri);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dieuTri);
      });

      it('should not add a DieuTri to an array that contains it', () => {
        const dieuTri: IDieuTri = { id: 123 };
        const dieuTriCollection: IDieuTri[] = [
          {
            ...dieuTri,
          },
          { id: 456 },
        ];
        expectedResult = service.addDieuTriToCollectionIfMissing(dieuTriCollection, dieuTri);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DieuTri to an array that doesn't contain it", () => {
        const dieuTri: IDieuTri = { id: 123 };
        const dieuTriCollection: IDieuTri[] = [{ id: 456 }];
        expectedResult = service.addDieuTriToCollectionIfMissing(dieuTriCollection, dieuTri);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dieuTri);
      });

      it('should add only unique DieuTri to an array', () => {
        const dieuTriArray: IDieuTri[] = [{ id: 123 }, { id: 456 }, { id: 52888 }];
        const dieuTriCollection: IDieuTri[] = [{ id: 123 }];
        expectedResult = service.addDieuTriToCollectionIfMissing(dieuTriCollection, ...dieuTriArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const dieuTri: IDieuTri = { id: 123 };
        const dieuTri2: IDieuTri = { id: 456 };
        expectedResult = service.addDieuTriToCollectionIfMissing([], dieuTri, dieuTri2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dieuTri);
        expect(expectedResult).toContain(dieuTri2);
      });

      it('should accept null and undefined values', () => {
        const dieuTri: IDieuTri = { id: 123 };
        expectedResult = service.addDieuTriToCollectionIfMissing([], null, dieuTri, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dieuTri);
      });

      it('should return initial array if no DieuTri is added', () => {
        const dieuTriCollection: IDieuTri[] = [{ id: 123 }];
        expectedResult = service.addDieuTriToCollectionIfMissing(dieuTriCollection, undefined, null);
        expect(expectedResult).toEqual(dieuTriCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
