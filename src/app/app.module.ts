import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { PropertypageComponent } from './propertypage/propertypage.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ListpropertiesComponent } from './listproperties/listproperties.component';


@NgModule({
  declarations: [
    AppComponent,
    UserLoginComponent,
    DashboardComponent,
    HeaderComponent,
    PropertypageComponent,
    RegisterPageComponent,
    ListpropertiesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
