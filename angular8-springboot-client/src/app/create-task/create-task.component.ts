import { Component, OnInit, Input, Output } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task';
import { Category } from "../category";
import { ActivatedRoute, Router } from '@angular/router';
import { EventEmitter } from 'protractor';

// Creates and handles a new task form data
@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task: Task = new Task();
  @Input() currentList: Category;
  currentListId : number;
  //@Output() currentListId : number;

  constructor(private taskService: TaskService,
    private router: Router, 
    private route: ActivatedRoute) { 
      //this.route.parent.params.subscribe(params => console.log(params));
    }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.taskService.getCategory(id)
      .subscribe(current => this.currentListId = current);
  }

  onSubmit() {
    this.taskService.createTask(this.task, this.currentListId)
      .subscribe(data => console.log(data), error => console.log(error),
        () => this.gotoList());     
  }

  gotoList() {
    this.router.navigate(['/tasklist']);
  }

}
