package com.hgits.hotc.utils;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 针对layui框架，重构服务端分页插件
 * @param <T>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class LayUIPageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 查询状态码
    private int code;
    // 查询返回信息
    private String msg;
    //当前页
    private int page;
    //每页的数量
    private int limit;
    //当前页的数量
    private int size;
    //排序
    private String orderBy;

    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

    //当前页面第一个元素在数据库中的行号
    private int startRow;
    //当前页面最后一个元素在数据库中的行号
    private int endRow;
    //总记录数
    private long count;
    //总页数
    private int pages;
    //结果集
    private List<T> data;

    //第一页
    private int firstPage;
    //前一页
    private int prePage;
    //下一页
    private int nextPage;
    //最后一页
    private int lastPage;

    //是否为第一页
    private boolean isFirstPage = false;
    //是否为最后一页
    private boolean isLastPage = false;
    //是否有前一页
    private boolean hasPreviousPage = false;
    //是否有下一页
    private boolean hasNextPage = false;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepages;
    private String requestUrl;

    public LayUIPageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public LayUIPageInfo(List<T> list) {
        this(list, 8);
    }

    public LayUIPageInfo(int code, String msg, List<T> list){
        this(list, 8);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public LayUIPageInfo(List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.page = page.getPageNum();
            this.limit = page.getPageSize();
            this.orderBy = page.getOrderBy();

            this.pages = page.getPages();
            this.data = page;
            this.size = page.size();
            this.count = page.getTotal();
            //由于结果是>startRow的，所以实际的需要+1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                //计算实际的endRow（最后一页的时候特殊）
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof Collection) {
            this.page = 1;
            this.limit = list.size();

            this.pages = 1;
            this.data = list;
            this.size = list.size();
            this.count = list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            this.navigatePages = navigatePages;
            //计算导航页
            calcNavigatepages();
            //计算前后页，第一页，最后一页
            calcPage();
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 计算导航页
     */
    private void calcNavigatepages() {
        //当总页数小于或等于导航页码数时
        if (pages <= navigatePages) {
            navigatepages = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepages[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepages = new int[navigatePages];
            int startNum = page - navigatePages / 2;
            int endNum = page + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepages[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepages[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepages[i] = startNum++;
                }
            }
        }
    }

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (navigatepages != null && navigatepages.length > 0) {
            firstPage = navigatepages[0];
            lastPage = navigatepages[navigatepages.length - 1];
            if (page > 1) {
                prePage = page - 1;
            }
            if (page < pages) {
                nextPage = page + 1;
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = page == 1;
        isLastPage = page == pages;
        hasPreviousPage = page > 1;
        hasNextPage = page < pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepages() {
        return navigatepages;
    }

    public void setNavigatepages(int[] navigatepages) {
        this.navigatepages = navigatepages;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("page=").append(page);
        sb.append(", limit=").append(limit);
        sb.append(", size=").append(size);
        sb.append(", startRow=").append(startRow);
        sb.append(", endRow=").append(endRow);
        sb.append(", count=").append(count);
        sb.append(", pages=").append(pages);
        sb.append(", data=").append(data);
        sb.append(", firstPage=").append(firstPage);
        sb.append(", prePage=").append(prePage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", lastPage=").append(lastPage);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", hasPreviousPage=").append(hasPreviousPage);
        sb.append(", hasNextPage=").append(hasNextPage);
        sb.append(", navigatePages=").append(navigatePages);
        sb.append(", navigatepages=");
        if (navigatepages == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < navigatepages.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(navigatepages[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
