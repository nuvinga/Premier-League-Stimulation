import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { AppService } from './services/app.service';
import { AppHttpInterceptorService } from './services/http-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClubsViewComponent } from './clubs-view/clubs-view.component';
import { FooterComponent } from './footer/footer.component';
import { GeneratorMatchesComponent } from './generator-matches/generator-matches.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { MatchPopUpComponent } from './match-pop-up/match-pop-up.component';
import { MatchesViewComponent } from './matches-view/matches-view.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SearchMatchesComponent } from './search-matches/search-matches.component';
import { SearchPopUpComponent } from './search-pop-up/search-pop-up.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from "@angular/material/icon";
import { MatDialogModule } from "@angular/material/dialog";
import { MatButtonToggleModule } from "@angular/material/button-toggle";

const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent
  },
  {
    path: 'clubs',
    component: ClubsViewComponent
  },
  {
    path: 'matches',
    component: MatchesViewComponent
  },
  {
    path: 'generate',
    component: GeneratorMatchesComponent
  },
  {
    path: 'search',
    component: SearchMatchesComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ClubsViewComponent,
    FooterComponent,
    GeneratorMatchesComponent,
    LandingPageComponent,
    MatchPopUpComponent,
    MatchesViewComponent,
    NavBarComponent,
    SearchMatchesComponent,
    SearchPopUpComponent
  ],

  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({ cookieName: 'Csrf-Token', headerName: 'Csrf-Token'}),
    RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy', scrollPositionRestoration:"top" }),
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatIconModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonToggleModule
  ],

  providers: [
    AppService, { multi: true, provide: HTTP_INTERCEPTORS, useClass: AppHttpInterceptorService }
  ],

  bootstrap: [AppComponent]
})

export class AppModule { }
