import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DieuTriService } from '../service/dieu-tri.service';

import { DieuTriComponent } from './dieu-tri.component';

describe('DieuTri Management Component', () => {
  let comp: DieuTriComponent;
  let fixture: ComponentFixture<DieuTriComponent>;
  let service: DieuTriService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [DieuTriComponent],
    })
      .overrideTemplate(DieuTriComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DieuTriComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DieuTriService);

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
    expect(comp.dieuTris?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
