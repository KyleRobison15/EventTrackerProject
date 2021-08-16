import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { OrderListComponent } from './components/order-list/order-list.component';

const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: 'order' },
  { path: 'order', component: OrderListComponent},
  { path: 'customer', component: CustomerListComponent},
  { path: '**', component: NotFoundComponent } // '**' is a wild card catch all path. We use this for our 404 page

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
