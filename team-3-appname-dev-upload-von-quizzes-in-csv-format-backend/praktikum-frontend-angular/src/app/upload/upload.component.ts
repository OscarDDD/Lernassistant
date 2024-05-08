import { Component } from '@angular/core';
import {DragDropDirective} from "./upload.directive";
import {HeaderComponent} from "../header/header.component";
import {ProgressComponent} from "../progress/progress.component";
import {NgForOf} from "@angular/common";


@Component({
  selector: 'app-upload',
  standalone: true,
  templateUrl: './upload.component.html',
  imports: [
    DragDropDirective,
    HeaderComponent,
    ProgressComponent,
    NgForOf
  ],
  styleUrl: './upload.component.scss'
})

export class UploadComponent {
  files: any[] = [];

  onFileDropped($event: any[]) {
    this.prepareFilesList($event);
  }

  fileBrowseHandler($event: any) {
    const target = $event.target as HTMLInputElement;
    const files = target.files as any;
    this.prepareFilesList(files);
  }

  deleteFile(index: number) {
    this.files.splice(index, 1);
  }

  prepareFilesList(files: Array<any>) {
    for (const item of files) {
      item.progress = 0;
      this.files.push(item);
    }
    this.uploadFilesSimulator(0);
  }

  uploadFilesSimulator(index: number) {
    setTimeout(() => {
      if (index === this.files.length) {
        return;
      } else {
        const progressInterval = setInterval(() => {
          if (this.files[index].progress === 100) {
            clearInterval(progressInterval);
            this.uploadFilesSimulator(index + 1);
          } else {
            this.files[index].progress += 5;
          }
        }, 200);
      }
    }, 1000);
  }

  uploadFiles() {
    alert("Successfully calling backend to upload files");
  }
}


/*
  isdragover:boolean=false;
  selectedFilesName:string[]=[];
  selectedFilesCount:number = 0;
  constructor(private http: HttpClient){}
  dragover(e:Event){
    e.stopPropagation();
    e.preventDefault();
    this.isdragover=true;
    console.log("dragover");
  }
  dragleave(e:Event){
    e.stopPropagation();
    e.preventDefault();
    this.isdragover=false;
    console.log("dragleave");
  }
  dragenter(e:Event){
    e.stopPropagation();
    e.preventDefault();
    console.log("dragenter");
  }
  drop(e:any){
    e.stopPropagation();
    e.preventDefault();
    this.isdragover=false;
    let dataTransfer=e.dataTransfer;
    let files=dataTransfer.files;
    console.log("files:");
    console.log(files);
    this.showSelectedFiles(files);
    this.handleFiles(files).subscribe();
  }
  inputFile(e:any){
    console.log(e.target.files);
    this.showSelectedFiles(e.target.files);
    this.handleFiles(e.target.files).subscribe();
  }
  showSelectedFiles(files: FileList): void{
    this.selectedFilesName = [];
    this.selectedFilesCount = files.length;
    for(let i=0;i<files.length;i++){
      this.selectedFilesName.push(files[i].name);
    }

  }
  handleFiles(filesToUp: FileList): Observable<{message:string}> {
    const url: string = "http://127.0.0.1:5000/up_file";
    const formData: FormData = new FormData();
    for(let i=0;i<filesToUp.length;i++){
      formData.append('files', filesToUp[i]);
    }
    return this.http
      .post<any>(url, formData).pipe(
        switchMap((res: {message:string}) => { console.log(res); return of(res); }),
        catchError(er=>{console.log(er);return of({message:"error"})})
      );
  }

 */
