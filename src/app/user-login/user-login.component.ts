import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit{

  user: User = new User();

  constructor(private userservice: UserService){}

  cred = 
  {
    email: '',
    password: ''
  }

   

  
  ngOnInit(): void {
    console.log("hcxchjc")
  }

  // udatae = JSON.parse(this.user.email);
  // pstring = this.user.password.toString();
  // udatap = JSON.parse(this.pstring);

 

  loginevent()
  {
    console.log(typeof(this.cred))
    this.userservice.getLogin(this.cred).subscribe((data:any) =>
      {
        // console.log(this.user);
        // console.log(data.token);
        this.userservice.loginUser(data.token);

        console.log(data.jwttoken)

        var token = "Bearer "+data.jwttoken; 
        console.log(token)
        this.userservice.loginUser(token);
        
        // window.location.href='/dashboard'

      },error=>console.log(error));
  }

  onSubmit()
  {
    if(this.user.email!="" && this.user.password !== null)
    {
      this.loginevent();
    }
    else
    {
      console.log("Fields are empty")
    }
   
  }

}
