package notice.domain;

public class PageHandler {
    private int currentPage;
    private int totalCnt;
    private int totalPage;
    private int pageSize;  //한페이지에 보여지는 게시물 수
    private int beginPage;
    private int endPage;
    private int blockSize = 10;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(Integer totalCnt,Integer currentPage) {
        this(totalCnt,currentPage,10);
    }
    public PageHandler(Integer totalCnt,Integer currentPage, Integer pageSize) {
        this.totalCnt = totalCnt;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
        beginPage = (currentPage-1) / blockSize * blockSize + 1;
        endPage = Math.min(beginPage + blockSize - 1,totalPage);

        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
                "currentPage=" + currentPage +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", blockSize=" + blockSize +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
