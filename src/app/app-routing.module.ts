import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListpropertiesComponent } from './listproperties/listproperties.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { PropertypageComponent } from './propertypage/propertypage.component';
import { UserLoginComponent } from './user-login/user-login.component';


const routes: Routes = [
 {path: 'dashboard', component: DashboardComponent},
  {path: 'properties', component: ListpropertiesComponent},
  {path: 'property', component: PropertypageComponent},
  {path: 'login', component: UserLoginComponent},
  {path: 'register', component: RegisterPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
