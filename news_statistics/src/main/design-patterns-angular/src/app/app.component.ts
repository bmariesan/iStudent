import {Component, OnInit} from '@angular/core';
import {Post} from "../model/post";
import {HttpClient, HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  public barChartLabels: string[] = [];
  public barChartType = 'bar';
  public barChartLegend = true;

  public barChartData: any[] = [
    {data: [], label: 'Negative'},
    {data: [], label: 'Neutral'},
    {data: [], label: 'Positive'}
  ];
  public newComm: string;
  private posts: Post[] = [
    {
      author: 'Author1', created_at: '2017-12-1', updated_at: '2017-12-1', comments: [
      // 'He didn’t want to go to the dentist, yet he went anyway.',
      // 'Tom got a small piece of pie.',
      // 'The memory we used to share is no longer coherent.',
      // 'He told us a very exciting adventure story.',
      // 'A purple pig and a green donkey flew a kite in the middle of the night and ended up sunburnt.',
      // 'The sky is clear; the stars are twinkling.',
      // 'Someone I know recently combined Maple Syrup & buttered Popcorn thinking it would ' +
      // 'taste like caramel popcorn. It didn’t and they don’t recommend anyone else do it either.',
      // 'There were white out conditions in the town; subsequently, the roads were impassable.',
      // 'I am happy to take your donation; any amount will be greatly appreciated.',
      // 'Rock music approaches at high velocity.',
      // 'If you like tuna and tomato sauce- try combining the two. It’s really not as bad as it sounds.',
      // 'Please wait outside of the house.',
      // 'Writing a list of random sentences is harder than I initially thought it would be.',
      // 'This is the last random sentence I will be writing and I am going to stop mid-sent',
      // 'I would have gotten the promotion, but my attendance wasn’t good enough.',
      // 'Cats are good pets, for they are clean and are not noisy.',
      // 'A glittering gem is not enough.',
      // 'I hear that Nancy is very pretty.',
      // 'He turned in the research paper on Friday; otherwise, he would have not passed the class.',
      // 'Let me help you with your baggage.'
    ]
    }
  ];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getSentiments();
  }

  getSentiments(){
    let params: HttpParams = new HttpParams();
    for (let x in this.posts) {
      for (let y in this.posts[x].comments) {
        params = params.append('comments' + x.toString(), this.posts[0].comments[y].replace(/ /g, '+'));
      }
      this.http.get('http://localhost:8080/comment-sentiment', {params: params})
        .subscribe((res) => {
          const data1 = [], data2 = [], data3 = [];
          data1.push(res['neg']);
          data2.push(res['neutral']);
          data3.push(res['pos']);
          const clone = JSON.parse(JSON.stringify(this.barChartData));
          clone[0].data = data1;
          clone[1].data = data2;
          clone[2].data = data3;
          this.barChartData = clone;
        });
    }
  }

  addComment(){
    this.posts[0].comments.push(this.newComm);
    this.newComm = "";
  }
}

