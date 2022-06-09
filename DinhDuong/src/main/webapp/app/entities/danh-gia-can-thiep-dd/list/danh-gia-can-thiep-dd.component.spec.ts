import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';

import { DanhGiaCanThiepDDComponent } from './danh-gia-can-thiep-dd.component';

describe('DanhGiaCanThiepDD Management Component', () => {
  let comp: DanhGiaCanThiepDDComponent;
  let fixture: ComponentFixture<DanhGiaCanThiepDDComponent>;
  let service: DanhGiaCanThiepDDService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [DanhGiaCanThiepDDComponent],
    })
      .overrideTemplate(DanhGiaCanThiepDDComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DanhGiaCanThiepDDComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DanhGiaCanThiepDDService);

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
    expect(comp.danhGiaCanThiepDDS?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
