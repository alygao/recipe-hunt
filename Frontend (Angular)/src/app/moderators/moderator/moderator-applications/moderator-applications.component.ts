import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-moderator-applications',
  templateUrl: './moderator-applications.component.html',
  styleUrls: ['./moderator-applications.component.css']
})
export class ModeratorApplicationsComponent implements OnInit {

  constructor(
    private moderatorService : ModeratorService,
    private router: Router,
    private location : Location,
    private authService : AuthService
  ) { }

  ngOnInit(): void {
  }

  backToPreviousPage() {
    this.location.back();
  }


}
