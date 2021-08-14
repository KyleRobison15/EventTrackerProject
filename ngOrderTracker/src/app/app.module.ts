import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderService } from './services/order.service';
import { OrderListComponent } from './components/order-list/order-list.component';

@NgModule({

  declarations: [
    AppComponent,
    OrderListComponent
  ],
  //Stuff that lives in angular, that we want to use in our app live in IMPORTS:
  imports: [
    BrowserModule,  //Free
    AppRoutingModule, //Free
    FormsModule, // Must add
    HttpClientModule
  ],

  //Stuff that we want to be able to inject into constructors (Any Pipes or Services or other Angular created stuff)
  providers: [
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
