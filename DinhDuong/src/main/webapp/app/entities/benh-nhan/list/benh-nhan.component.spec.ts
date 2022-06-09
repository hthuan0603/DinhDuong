import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { BenhNhanService } from '../service/benh-nhan.service';

import { BenhNhanComponent } from './benh-nhan.component';

describe('BenhNhan Management Component', () => {
  let comp: BenhNhanComponent;
  let fixture: ComponentFixture<BenhNhanComponent>;
  let service: BenhNhanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BenhNhanComponent],
    })
      .overrideTemplate(BenhNhanComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BenhNhanComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BenhNhanService);

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
    expect(comp.benhNhans?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
