package notice.domain;

public class PageHandler {
    private SearchCondition sc;
//    private int currentPage;
//    private int pageSize;  //한페이지에 보여지는 게시물 수
    private int totalCnt;
    private int totalPage;
    private int beginPage;
    private int endPage;
    private int blockSize = 10;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt,SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt,sc);
    }

    public void doPaging(Integer totalCnt,SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage()-1) / blockSize * blockSize + 1;
        endPage = Math.min(beginPage + blockSize - 1,totalPage);

        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", blockSize=" + blockSize +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
