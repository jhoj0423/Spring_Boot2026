package board;

public class PageHandler {
	private int totalCnt;
	private int pageNum;
	private int pageSize;
	private int pageBlock = 3;
	
	
	private int startRow;
	private int endRow;
	
	private int totalPage;
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	public PageHandler(int totalCnt, int pageNum, int pageSize) {
		this.totalCnt= totalCnt;
		this.pageNum= pageNum;
		this.pageSize = pageSize;
		
		calcPaging(); //-> 실행 되자마자 실행
	}
	
	private void calcPaging() {
		totalPage = (int)Math.ceil(totalCnt /(double)pageSize);
		
		startRow = (pageNum-1) * pageSize;
		endRow = pageSize;
		
		startPage = ((pageNum -1) / pageBlock)* pageBlock +1;
		endPage = startPage + (pageBlock -1);
		
		
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		
		prev = startPage>1;
		next = endPage <totalPage;
		
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	
}
