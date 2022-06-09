import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { ToaThuocService } from '../service/toa-thuoc.service';

import { ToaThuocComponent } from './toa-thuoc.component';

describe('ToaThuoc Management Component', () => {
  let comp: ToaThuocComponent;
  let fixture: ComponentFixture<ToaThuocComponent>;
  let service: ToaThuocService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ToaThuocComponent],
    })
      .overrideTemplate(ToaThuocComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ToaThuocComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ToaThuocService);

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
    expect(comp.toaThuocs?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
