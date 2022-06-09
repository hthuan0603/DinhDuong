import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { BaoHiemService } from '../service/bao-hiem.service';

import { BaoHiemComponent } from './bao-hiem.component';

describe('BaoHiem Management Component', () => {
  let comp: BaoHiemComponent;
  let fixture: ComponentFixture<BaoHiemComponent>;
  let service: BaoHiemService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BaoHiemComponent],
    })
      .overrideTemplate(BaoHiemComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BaoHiemComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BaoHiemService);

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
    expect(comp.baoHiems?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
