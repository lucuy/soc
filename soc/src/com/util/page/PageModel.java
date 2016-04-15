package com.util.page;

/** *//**  
* List分页 
* 实现：利用List的获取子List方法，实现对List的分页 
* @author 显武 
* @date 2010-1-8 16:27:31 
* 
*/ 

import java.util.List; 

public class PageModel { 
    private int page = 1; // 当前页 

    public int totalPages = 0; // 总页数 

    private int pageRecorders;// 每页5条数据 
    
    private int totalRows = 0; // 总数据数 

    private int pageStartRow = 0;// 每页的起始数 

    private int pageEndRow = 0; // 每页显示数据的终止数 

    private boolean hasNextPage = false; // 是否有下一页 

    private boolean hasPreviousPage = false; // 是否有前一页 

    private List list; 

    // private Iterator it; 

    public PageModel(List list, int pageRecorders) { 
        init(list, pageRecorders);// 通过对象集，记录总数划分 
    } 
    /** *//** 
     * 初始化list，并告之该list每页的记录数 
     * @param list 
     * @param pageRecorders 
     */ 
    public void init(List list, int pageRecorders) { 
        this.pageRecorders = pageRecorders; 
        this.list = list; 
        totalRows = list.size();//统计一共有多少行的数据 
        // it = list.iterator(); 
        hasPreviousPage = false; //初始化时默认没有前一页
        if ((totalRows % pageRecorders) == 0) { //总数据条数除以每页要显示的条数取余，如果整除了则总页数为数以取整
            totalPages = totalRows / pageRecorders; 
        } else { 
            totalPages = totalRows / pageRecorders + 1; 
        } 

        if (page >= totalPages) { //当前页如果大于总页数则无下一页，否则有下一页
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 

        if (totalRows < pageRecorders) { //总行数小于要求的页数的话开始行为0 结束行为行数否则总行数为要求的行数
            this.pageStartRow = 0; 
            this.pageEndRow = totalRows; 
        } else { 
            this.pageStartRow = 0; 
            this.pageEndRow = pageRecorders; 
        } 
    } 


    // 判断要不要分页 
    public boolean isNext() { 
        return list.size() > 5; 
    } 

    public void setHasPreviousPage(boolean hasPreviousPage) { 
        this.hasPreviousPage = hasPreviousPage; 
    } 

    public String toString(int temp) { 
        String str = Integer.toString(temp); 
        return str; 
    } 

    public void description() { 
        String description = "共有数据数:" + this.getTotalRows() + 
        "共有页数: " + this.getTotalPages() + 

        "当前页数为:" + this.getPage() + 

        " 是否有前一页: " + this.isHasPreviousPage() + 

        " 是否有下一页:" + this.isHasNextPage() + 

        " 开始行数:" + this.getPageStartRow() + 

        " 终止行数:" + this.getPageEndRow(); 

        //System.out.println(description); 
    } 

   public List getNextPage() { 
	      page = page + 1; 
        disposePage(); 
        //System.out.println("用户凋用的是第" + page + "页"); 
        this.description(); 
        return getObjectLists(page); 
    } 

    /** *//** 
     * 处理分页 
     */ 
    private void disposePage() { 

        if (page == 0) { 
            page = 1; 
        } 

        if ((page - 1) > 0) { 
            hasPreviousPage = true; 
        } else { 
            hasPreviousPage = false; 
        } 

        if (page >= totalPages) { 
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 
    } 

    public List getPreviousPage() { 

        page = page - 1; 

        if ((page - 1) > 0) { 
            hasPreviousPage = true; 
        } else { 
            hasPreviousPage = false; 
        } 
        if (page >= totalPages) { 
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 
        this.description(); 
        return getObjectLists(page); 
    } 

    /** *//** 
     * 获取第几页的内容 
     * 
     * @param page 
     * @return 
     */ 
    public List getObjectLists(int page) { 
        if (page == 0) 
            this.setPage(1); 
        else 
            this.setPage(page); 
        this.disposePage(); 
        if (page * pageRecorders < totalRows) {// 判断是否为最后一页 
            pageEndRow = page * pageRecorders; 
            pageStartRow = pageEndRow - pageRecorders; 
        } else { 
            pageEndRow = totalRows; 
            pageStartRow = pageRecorders * (totalPages - 1); 
        } 
        List objectLists = null; 
        if (!list.isEmpty()) { 
        	objectLists = list.subList(pageStartRow, pageEndRow); 
        } 
        return objectLists; 
    } 

    public List getFistPage() { 
        if (this.isNext()) { 
            return list.subList(0, pageRecorders); 
        } else { 
            return list; 
        } 
    } 

    public boolean isHasNextPage() { 
        return hasNextPage; 
    } 


    public void setHasNextPage(boolean hasNextPage) { 
        this.hasNextPage = hasNextPage; 
    } 


    public List getList() { 
        return list; 
    } 


    public void setList(List list) { 
        this.list = list; 
    } 


    public int getPage() { 
        return page; 
    } 


    public void setPage(int page) { 
        this.page = page; 
    } 


    public int getPageEndRow() { 
        return pageEndRow; 
    } 


    public void setPageEndRow(int pageEndRow) { 
        this.pageEndRow = pageEndRow; 
    } 


    public int getPageRecorders() { 
        return pageRecorders; 
    } 


    public void setPageRecorders(int pageRecorders) { 
        this.pageRecorders = pageRecorders; 
    } 


    public int getPageStartRow() { 
        return pageStartRow; 
    } 


    public void setPageStartRow(int pageStartRow) { 
        this.pageStartRow = pageStartRow; 
    } 


    public int getTotalPages() { 
        return totalPages; 
    } 


    public void setTotalPages(int totalPages) { 
        this.totalPages = totalPages; 
    } 


    public int getTotalRows() { 
        return totalRows; 
    } 


    public void setTotalRows(int totalRows) { 
        this.totalRows = totalRows; 
    } 


    public boolean isHasPreviousPage() { 
        return hasPreviousPage; 
    }

} 

 

