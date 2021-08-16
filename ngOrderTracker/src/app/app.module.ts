import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderService } from './services/order.service';
import { OrderListComponent } from './components/order-list/order-list.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { DatePipe } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { ProductListComponent } from './components/product-list/product-list.component';

@NgModule({

  declarations: [
    AppComponent,
    OrderListComponent,
    NavigationComponent,
    NotFoundComponent,
    CustomerListComponent,
    ProductListComponent,
  ],
  //Stuff that lives in angular, that we want to use in our app live in IMPORTS:
  imports: [
    BrowserModule,  //Free
    AppRoutingModule, //Free
    FormsModule, // Must add
    HttpClientModule,
    NgbModule
  ],

  //Stuff that we want to be able to inject into constructors (Any Pipes or Services or other Angular created stuff)
  providers: [
    OrderService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
