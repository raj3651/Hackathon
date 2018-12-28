import {Component, OnInit} from '@angular/core';
import {ApplicantService} from "../../services/applicant.service";
import {Applicant} from "../../model/Applicant";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-applicant-list',
  templateUrl: './applicant-list.component.html',
  styleUrls: ['./applicant-list.component.scss']
})
export class ApplicantListComponent implements OnInit {

  applicants: Applicant[] = [];

  closeResult: string;

  constructor(private applicantService: ApplicantService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getApplicants();
  }


  getApplicants() {
    this.applicantService.getApplicants().subscribe(res => {
      this.applicants = res.applicants;
    });
  }

  generateTrustScore(applicant: Applicant) {
    this.applicantService.generateTrustScore(applicant.applicantId).subscribe(res => {
      applicant.applicantTrustScore = res.applicantTrustScore;
    });
  }

  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onRefresh(doRefresh: boolean) {
    if (doRefresh) {
      this.getApplicants()
    }
  }
}
