import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModeratorApplication } from '../../moderator';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-moderator-sign-up',
  templateUrl: './moderator-sign-up.component.html',
  styleUrls: ['./moderator-sign-up.component.css']
})
export class ModeratorSignUpComponent implements OnInit {

  
  moderatorForm = this.fb.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', Validators.required],
    reasonForApplying: ['', Validators.required],
  });

  constructor(
    private moderatorService : ModeratorService,
    private fb : FormBuilder,
    private router: Router,
  ) { }

  ngOnInit(): void {

  }

  onSubmit() {
    let name = this.moderatorForm.get('name').value;
    let username = this.moderatorForm.get('username').value;
    let password = this.moderatorForm.get('password').value;
    let reasonForApplying = this.moderatorForm.get('reasonForApplying').value;

      let moderatorApplication : ModeratorApplication = {
        id : -1, 
        name: name, 
        username: username, 
        password: password, 
        accepted: false,
        reasonForApplying: reasonForApplying
      };
  
      this.moderatorService.createModeratorApplication(moderatorApplication)
      .subscribe ( r => this.router.navigate(['/moderator']),
                   error => alert ( "Communication Error: " + error ) ) ;
    }

}
