import {Injectable} from '@angular/core';
import "rxjs/Rx";
import {Observable, throwError as observableThrowError} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {ApplicantsDto} from "../dto/ApplicantsDto";
import {Applicant} from "../model/Applicant";

@Injectable()
export class ApplicantService {

  constructor(private http:HttpClient) { }

  getApplicants(): Observable<ApplicantsDto> {
    const params = new HttpParams();

    const headers = this.prepareHeaders();

    return this.http.get<ApplicantsDto>("http://localhost:8095/socialtrust/applicants", {headers, params})
      .catch(this.handleError);
  }

  createApplicant(applicant: Applicant): Observable<Applicant> {

    const headers = this.prepareHeaders();

    return this.http.post<Applicant>("http://localhost:8095/socialtrust/applicants", applicant,{headers})
      .catch(this.handleError);
  }

  generateTrustScore(applicantId: number): Observable<Applicant> {
    const params = new HttpParams();

    const headers = this.prepareHeaders();

    return this.http.get<Applicant>("http://localhost:8095/socialtrust/applicants/" + applicantId + "/generatetrustscore", {headers, params})
      .catch(this.handleError);
  }

  prepareHeaders(){
    return new HttpHeaders()
      .set("Content-Type", "application/json")
      .set("Accept", "application/json");
  }

  private handleError(error:HttpErrorResponse) {
    console.error(error);
    return observableThrowError(error || 'Server error');
  }
}
