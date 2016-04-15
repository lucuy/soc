package com.util.reportForm.util.page;


import java.util.List;

/**
 * <p>Title: 检索结果集实体类</p>
 * <p>Description: 保存分页参数及数据库查询的结果,用于页面显示</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 四方人才网</p>
 * @author Yeno.hhr
 * @version 1.0
 */
public class Result {
    /**分页状态变量实体*/
    private Page page;
    /**数据库检索到的当前页结果集*/
    private List content;

    /**
     * The default constructor
     */
    public Result() {
        super();
    }

    /**
     * The constructor using fields
     *
     * @param page
     * @param content
     */
    public Result(Page page, List content) {

        this.page = page;
        this.content = content;
    }

    /**
     * @return Returns the content.
     */
    public List getContent() {
        return content;
    }

    /**
     * @return Returns the page.
     */
    public Page getPage() {
        return page;
    }

    /**
     * The content to set.
     * @param content
     */
    public void setContent(List content) {
        this.content = content;
    }

    /**
     * The page to set.
     * @param page
     */
    public void setPage(Page page) {
        this.page = page;
    }
}
