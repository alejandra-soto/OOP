import { Component, OnInit} from '@angular/core';
import { Observable } from "rxjs";
import { TaskService } from "../task.service";
import { Category } from "../category";
import { Router } from '@angular/router';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  lists: Observable<any>;

  constructor(private taskService: TaskService, private router: Router) { }

  ngOnInit() {
	  this.reloadData();
  }
  
  reloadData() {
    //this.lists = this.taskService.getCategoryList();
    this.taskService.getCategoryList().subscribe(data => {
      this.lists = data;
    });
  }

}
