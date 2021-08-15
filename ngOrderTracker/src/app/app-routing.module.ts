import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { OrderListComponent } from './components/order-list/order-list.component';

const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'order', component: OrderListComponent},
  { path: '**', component: NotFoundComponent } // '**' is a wild card catch all path. We use this for our 404 page

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
