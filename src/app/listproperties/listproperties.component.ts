import { Component, OnInit } from '@angular/core';
import { Property } from '../property';
import { PropertyService } from '../property.service';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-listproperties',
  templateUrl: './listproperties.component.html',
  styleUrls: ['./listproperties.component.css']
})
export class ListpropertiesComponent implements OnInit {

  property: Property[];
  constructor(private propertyservice: PropertyService){}
  
  
  ngOnInit(): void {
    this.propertyservice.getProperties().subscribe((data: Property[])=>
    {
      console.log(data);
      this.property = data;
    })
  }
;



}
