import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateTaskComponent } from './create-task/create-task.component';
import { TaskListComponent } from './task-list/task-list.component';
import { CreateListComponent } from './create-list/create-list.component';
import { CategoryListComponent } from './category-list/category-list.component';


const routes: Routes = [
  { path: '', redirectTo: '/tasklist', pathMatch: 'full' },
  { path: 'tasklist', component: CategoryListComponent},
  { path: 'tasks', component: TaskListComponent },
  { path: 'addtask/:id', component: CreateTaskComponent },
  { path: 'addlist', component: CreateListComponent },
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
