import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { PhieuSuatAnService } from '../service/phieu-suat-an.service';

import { PhieuSuatAnComponent } from './phieu-suat-an.component';

describe('PhieuSuatAn Management Component', () => {
  let comp: PhieuSuatAnComponent;
  let fixture: ComponentFixture<PhieuSuatAnComponent>;
  let service: PhieuSuatAnService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [PhieuSuatAnComponent],
    })
      .overrideTemplate(PhieuSuatAnComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PhieuSuatAnComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(PhieuSuatAnService);

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
    expect(comp.phieuSuatAns?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
