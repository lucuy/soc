package com.util.reportForm.util.page;

public class Pager{   
	private int totalRows; //总行数   
    private int pageSize; //每页显示的行数   
    private int currentPage=1; //当前页号   
    private int totalPages; //总页数   
    private int startRow=0; //当前页在数据库中的起始行
    private int endRow=0;
    private boolean hasNextPage=false; //是否有下一页
    private boolean hasPreviousPage=false; //是否有前一页
    private boolean hasFirstPage=true;
    private boolean hasLastPage=true;
  public Pager(){
	  pageSize=20;
	  currentPage=1;
	  startRow=0;
	  endRow=0;
  }
  public Pager(int Size){
	  pageSize=Size;
	  currentPage=1;
	  startRow=0;
	  endRow=0;
  }
  public void start(int totalRows){     
	  this.totalRows=totalRows;     
	  totalPages=totalRows/pageSize;     
	  int mod=totalRows%pageSize;     
	  if(mod>0){
		 totalPages++;     
	  }
 } 
  public int getStartRow(){     
	  return startRow;   
 }
  public int getEndtRow(){     
	  return endRow;   
 }
  public int getTotalPages(){
	  return totalPages;   
 } 
  public int getCurrentPage(){
	  return currentPage;   
 } 
  public int getPageSize(){     
	  return pageSize;   
 } 
  public int getTotalRows(){     
	  return totalRows;   
 }
  public boolean isHasNextPage(){
	  return hasNextPage;
 }
  public boolean isHasPreviousPage(){
	  return hasPreviousPage;
 }
  public boolean isHasFirstPage(){
	  return hasFirstPage;
 }
  public boolean isHasLastPage(){
	  return hasLastPage;
 }
  public void setTotalRows(int totalRows){
	  this.totalRows=totalRows;   
 } 
  public void setStartRow(int startRow){
	  this.startRow=startRow;   
 }
  public void setEndRow(int endRow){
	  this.endRow=endRow;   
 }
  public void setTotalPages(int totalPages){
	  this.totalPages=totalPages;   
 } 
  public void setCurrentPage(int currentPage){
	  this.currentPage=currentPage;   
 } 
  public void setPageSize(int pageSize){
	  this.pageSize=pageSize;   
 }
  public void setHasNextPage(boolean hasNextPage) {
	  this.hasNextPage = hasNextPage;
 }
  public void setHasPreviousPage(boolean hasPreviousPage){
	  this.hasPreviousPage = hasPreviousPage;
 }
  public void setHasFirstPage(boolean hasFirstPage){
	  this.hasFirstPage = hasFirstPage;
 }
  public void setHasLastPage(boolean hasLastPage){
	  this.hasLastPage = hasLastPage;
 }
  public void first(){     
	  currentPage=1;     
	  startRow=0;
	  endRow=0;
	  if(totalRows>pageSize){
		  hasNextPage=true;
		  endRow=pageSize;
		}
		else{
		  hasNextPage=false;
		  endRow=totalRows;
		}
	  hasPreviousPage=false;
 } 
  public void previous(){     
	  currentPage--;
	  if (currentPage==0){
		  currentPage =1;
	  }
	  startRow=(currentPage-1)*pageSize;	  
	  endRow=0;
	  if(currentPage>=totalPages){
		  hasNextPage=false;
	  }
	  else{
		  hasNextPage=true;
	  }
	  if((currentPage-1)>0){
		  hasPreviousPage=true;
	  }
	  else{
		  hasPreviousPage=false;
	  }  
	  endRow=startRow+pageSize-1;  
 } 
  public void next(){     
	  currentPage++;
	  if(currentPage>=totalPages){
		  currentPage=totalPages;
		  hasNextPage=false;
	  }
	  startRow=(currentPage-1)*pageSize;
	  if (currentPage>=totalPages){
		  hasNextPage=false;
		  endRow=totalRows;
     }
	  else{
		  hasNextPage=true;
		  endRow=startRow+pageSize-1;
	  }
	  if((currentPage-1)>0){
		  hasPreviousPage=true;
	  }
	  else{
		  hasPreviousPage=false;
	  }    
 } 
  public void last(){
	  currentPage=totalPages;     
	  startRow=(currentPage-1)*pageSize;
	  endRow=0;
	  if(totalRows>pageSize){
		 hasPreviousPage=true; 
	  }
	  else{
		 hasPreviousPage=false;
	  }
	  endRow=totalRows;
	  hasNextPage=false;
	  
 }
  public void find(int page){
	  currentPage=page;
	  startRow=(currentPage-1)*pageSize;	  
	  endRow=0;
	  if (currentPage>=totalPages){
		  hasNextPage=false;
		  endRow=totalRows;
     }
	  else{
		  hasNextPage=true;
		  endRow=startRow+pageSize-1;
	  }
	  if((currentPage-1)>0){
		  hasPreviousPage=true;
	  }
	  else{
		  hasPreviousPage=false;
	  }
	  
 }
}