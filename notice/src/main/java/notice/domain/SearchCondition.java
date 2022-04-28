package notice.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    //private Integer offset = 0;
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword ="";
    private Integer noticeCode = 1;
    private Integer option = 0;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getOffset() {
        return (this.page - 1) * this.pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public Integer getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(Integer noticeCode) {
        this.noticeCode = noticeCode;
    }

    public SearchCondition(){}

    public SearchCondition(Integer page, Integer pageSize, String keyword, Integer noticeCode, Integer option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.noticeCode = noticeCode;
        this.option = option;
    }

    public String getQueryString(Integer page) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page",page)
                .queryParam("noticeCode",noticeCode)
                .queryParam("option",option)
                .queryParam("keyword",keyword)
                .build().toString();
    }

    public String getQueryString() {
        return getQueryString(page);
    }
}
