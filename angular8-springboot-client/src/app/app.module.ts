import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatTableModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateTaskComponent } from './create-task/create-task.component';
import { TaskListComponent } from './task-list/task-list.component';
import { CreateListComponent } from './create-list/create-list.component';
import { CategoryListComponent } from './category-list/category-list.component'
import { LocationStrategy, HashLocationStrategy } from '../../node_modules/@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    CreateTaskComponent,
    TaskListComponent,
    CreateListComponent,
    CategoryListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatTableModule
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy }],
  bootstrap: [AppComponent]
})
export class AppModule { }
