import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthService } from "../../auth.service";
import { first } from 'rxjs/operators';
import { Moderator } from "../moderator";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
  })
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    loginFailed: boolean = false;
    
    constructor ( 
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        public authenticationService: AuthService
    ) {

    }

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';   
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.authenticate(this.f.username.value, this.f.password.value)
            // .pipe(first())
            .subscribe(
                data => {
                    // this.router.navigate([this.returnUrl]);
                    console.log('CURRENT MODERATOR LOGGED IN = ', this.authenticationService.currentModerator);
                    this.router.navigate([this.authenticationService.redirectURL]);
                },
             error => {
                    // this.alertService.error(error);
                    this.loading = false;
                    this.loginFailed = true;
                    // alert ( "error" );
                    // console.log ( "error", error );
                });
    }

    logout() {
        this.authenticationService.logout();
    }
}

