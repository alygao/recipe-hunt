import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Moderator } from '../../moderator';
import { ModeratorService } from '../../moderator.service';

@Component({
  selector: 'app-moderators-list',
  templateUrl: './moderators-list.component.html',
  styleUrls: ['./moderators-list.component.css']
})
export class ModeratorsListComponent implements OnInit {

  moderators$ : Observable<Moderator[]>;
  moderators: Moderator[] = [];

  constructor(
    private moderatorService: ModeratorService,
    private router: Router,
    private location: Location,
    ) { }

  ngOnInit(): void {
    this.moderators$ = this.moderatorService.getModerators();
    this.moderators$.subscribe(m => this.moderators = m);
    }

    promote(moderator:Moderator) {
      if (confirm('Are you sure you want to promote ' + moderator.name)) {
        moderator.level += 1;
        this.moderatorService.saveModerator(moderator).subscribe(m => {
          this.moderators$ = this.moderatorService.getModerators();
          this.moderators$.subscribe(m => this.moderators = m);
        })
      }
    }

    updateStatus(moderator: Moderator) {
      moderator.active = !moderator.active;
      this.moderatorService.saveModerator(moderator).subscribe(m => {
        this.moderators$ = this.moderatorService.getModerators();
        this.moderators$.subscribe(m => this.moderators = m);
      })
    }

    goToModeratorApplications() {
      this.router.navigate(['/moderator/moderators/applications']);
    }

    backToPreviousPage() {
      this.location.back();
    }


}
