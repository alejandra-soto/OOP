import { Component, OnInit, Input, Output } from '@angular/core';
import { TaskService } from '../task.service';
import { Task } from '../task';
import { ActivatedRoute, Router } from '@angular/router';

// Creates and handles a new task form data
@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  task: Task = new Task();
  currentListId : number;

  constructor(private taskService: TaskService,
    private router: Router, 
    private route: ActivatedRoute) { 
    }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.task.taskListId = id;
    this.taskService.getCategory(id)
      .subscribe(current => this.currentListId = current);
  }
  onSubmit() {
    this.taskService.createTask(this.task, this.task.taskListId)
      .subscribe(() => this.gotoList());     
  }

  gotoList() {
    this.router.navigate(['/tasklist']);
  }

}
