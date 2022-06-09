import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';

import { GiayMoiDanhGiaComponent } from './giay-moi-danh-gia.component';

describe('GiayMoiDanhGia Management Component', () => {
  let comp: GiayMoiDanhGiaComponent;
  let fixture: ComponentFixture<GiayMoiDanhGiaComponent>;
  let service: GiayMoiDanhGiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [GiayMoiDanhGiaComponent],
    })
      .overrideTemplate(GiayMoiDanhGiaComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GiayMoiDanhGiaComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(GiayMoiDanhGiaService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.giayMoiDanhGias?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
