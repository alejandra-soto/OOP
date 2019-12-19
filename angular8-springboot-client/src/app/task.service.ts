import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private api ='/todo/tasklist';
  private baseUrl = 'http://todolistapplication-env.zai5j9k4bk.us-west-1.elasticbeanstalk.com:8080' + this.api;

  constructor(private http: HttpClient) {   }
  // get task list under a chosen list
  getTasksList(listId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${listId}/tasks`);
  }
  // create a task under a chosen list
  createTask(task: import("./task").Task, listId: number) {
    return this.http.post(`${this.baseUrl}/${listId}/tasks`, task);
  }
  // create a new list category
  createList(category: import("./category").Category) : Observable<any> {
    return this.http.post(`${this.baseUrl}`, category);
  }
  // get the list of the category lists
  getCategoryList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  // get a single list by id
  getCategory(id: number): Observable<any> {
    console.log("Current id value .... > "+id);
    return this.http.get(`${this.baseUrl}/${id}`);
  }
  
}
