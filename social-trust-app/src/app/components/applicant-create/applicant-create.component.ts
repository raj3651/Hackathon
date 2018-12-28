import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ApplicantService} from "../../services/applicant.service";
import {Applicant} from "../../model/Applicant";

@Component({
  selector: 'app-applicant-create',
  templateUrl: './applicant-create.component.html',
  styleUrls: ['./applicant-create.component.scss']
})
export class ApplicantCreateComponent implements OnInit {

  newApplicantForm: FormGroup;

  @Output() doRefresh = new EventEmitter<boolean>();

  constructor(private fb:FormBuilder, private applicantService: ApplicantService) { }

  ngOnInit() {
    this.newApplicantForm = this.fb.group({
      firstNameControl: [],
      lastNameControl: [],
      ageControl: [],
      twitterHandleControl: []
    });
  }

  createNewApplicant() {

    let newApplicant: Applicant = new Applicant();
    newApplicant.firstName = this.newApplicantForm.value["firstNameControl"];
    newApplicant.lastName = this.newApplicantForm.value["lastNameControl"];
    newApplicant.age = this.newApplicantForm.value["ageControl"];
    newApplicant.twitterHandle = this.newApplicantForm.value["twitterHandleControl"];

    this.applicantService.createApplicant(newApplicant).subscribe(res => {
      this.doRefresh.emit(true);
    });
  }

}
