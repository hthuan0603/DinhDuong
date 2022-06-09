import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBenhNhan, BenhNhan } from '../benh-nhan.model';

import { BenhNhanService } from './benh-nhan.service';

describe('BenhNhan Service', () => {
  let service: BenhNhanService;
  let httpMock: HttpTestingController;
  let elemDefault: IBenhNhan;
  let expectedResult: IBenhNhan | IBenhNhan[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BenhNhanService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      hoTen: 'AAAAAAA',
      maBN: 'AAAAAAA',
      ngaySinh: currentDate,
      gioiTinh: false,
      diaChi: 'AAAAAAA',
      ngheNghiep: 'AAAAAAA',
      chieuCao: 0,
      canHT: 0,
      canTC: 0,
      vongCT: 0,
      bMI: 0,
      maTheBHYT: 'AAAAAAA',
      sDT: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          ngaySinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a BenhNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          ngaySinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngaySinh: currentDate,
        },
        returnedFromService
      );

      service.create(new BenhNhan()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BenhNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          hoTen: 'BBBBBB',
          maBN: 'BBBBBB',
          ngaySinh: currentDate.format(DATE_TIME_FORMAT),
          gioiTinh: true,
          diaChi: 'BBBBBB',
          ngheNghiep: 'BBBBBB',
          chieuCao: 1,
          canHT: 1,
          canTC: 1,
          vongCT: 1,
          bMI: 1,
          maTheBHYT: 'BBBBBB',
          sDT: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngaySinh: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BenhNhan', () => {
      const patchObject = Object.assign(
        {
          hoTen: 'BBBBBB',
          ngaySinh: currentDate.format(DATE_TIME_FORMAT),
          gioiTinh: true,
          diaChi: 'BBBBBB',
          bMI: 1,
          maTheBHYT: 'BBBBBB',
        },
        new BenhNhan()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          ngaySinh: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BenhNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          hoTen: 'BBBBBB',
          maBN: 'BBBBBB',
          ngaySinh: currentDate.format(DATE_TIME_FORMAT),
          gioiTinh: true,
          diaChi: 'BBBBBB',
          ngheNghiep: 'BBBBBB',
          chieuCao: 1,
          canHT: 1,
          canTC: 1,
          vongCT: 1,
          bMI: 1,
          maTheBHYT: 'BBBBBB',
          sDT: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngaySinh: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a BenhNhan', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBenhNhanToCollectionIfMissing', () => {
      it('should add a BenhNhan to an empty array', () => {
        const benhNhan: IBenhNhan = { id: 123 };
        expectedResult = service.addBenhNhanToCollectionIfMissing([], benhNhan);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(benhNhan);
      });

      it('should not add a BenhNhan to an array that contains it', () => {
        const benhNhan: IBenhNhan = { id: 123 };
        const benhNhanCollection: IBenhNhan[] = [
          {
            ...benhNhan,
          },
          { id: 456 },
        ];
        expectedResult = service.addBenhNhanToCollectionIfMissing(benhNhanCollection, benhNhan);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BenhNhan to an array that doesn't contain it", () => {
        const benhNhan: IBenhNhan = { id: 123 };
        const benhNhanCollection: IBenhNhan[] = [{ id: 456 }];
        expectedResult = service.addBenhNhanToCollectionIfMissing(benhNhanCollection, benhNhan);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(benhNhan);
      });

      it('should add only unique BenhNhan to an array', () => {
        const benhNhanArray: IBenhNhan[] = [{ id: 123 }, { id: 456 }, { id: 28748 }];
        const benhNhanCollection: IBenhNhan[] = [{ id: 123 }];
        expectedResult = service.addBenhNhanToCollectionIfMissing(benhNhanCollection, ...benhNhanArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const benhNhan: IBenhNhan = { id: 123 };
        const benhNhan2: IBenhNhan = { id: 456 };
        expectedResult = service.addBenhNhanToCollectionIfMissing([], benhNhan, benhNhan2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(benhNhan);
        expect(expectedResult).toContain(benhNhan2);
      });

      it('should accept null and undefined values', () => {
        const benhNhan: IBenhNhan = { id: 123 };
        expectedResult = service.addBenhNhanToCollectionIfMissing([], null, benhNhan, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(benhNhan);
      });

      it('should return initial array if no BenhNhan is added', () => {
        const benhNhanCollection: IBenhNhan[] = [{ id: 123 }];
        expectedResult = service.addBenhNhanToCollectionIfMissing(benhNhanCollection, undefined, null);
        expect(expectedResult).toEqual(benhNhanCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
