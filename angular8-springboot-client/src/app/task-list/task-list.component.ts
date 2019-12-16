import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from "rxjs";
import { TaskService } from "../task.service";
import { Task } from "../task";
import { Category } from "../category"
import { ActivatedRoute,Router } from '@angular/router';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks: Observable<Task[]>;
  @Input() currentList: Category;
  
  //@Output() currentListId = new EventEmitter<Category>();

  constructor(private taskService: TaskService, 
    private router: Router) { 
    }

  ngOnInit() {
	  this.reloadData();
  }
  
  reloadData() {
    this.taskService.getTasksList(this.currentList.id).subscribe(data => {
      this.tasks = data;
    });
  }



}

