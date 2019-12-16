import { Component, OnInit } from '@angular/core';
import { TaskService } from "../task.service";
import { Category } from "../category";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-create-list',
  templateUrl: './create-list.component.html',
  styleUrls: ['./create-list.component.css']
})
export class CreateListComponent implements OnInit {

  list: Category = new Category();

  constructor(private taskService: TaskService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
  }

  onSubmit() {
    this.taskService.createList(this.list)
      .subscribe(data => console.log(data), error => console.log(error),
        () => this.gotoList()); 
  }

  gotoList() {
    this.router.navigate(['/tasklist']);
  }

}
