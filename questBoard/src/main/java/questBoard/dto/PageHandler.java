package questBoard.dto;

public class PageHandler {
	// 1. 기본 입력값
	private int totalCnt;
	private int pageNum;
	private int pageSize;
	private int pageBlock;
	
	private int startRow;
	private int endRow;
	
	private int totalPage;
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	
	public PageHandler(int totalCnt, int pageNum, int pageSize) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		
		// 계산함수 콜할 예정
		calcPageing();
	}
	
	public void calcPageing() {
		// totalPage : 전체 페이지수 계산
		// [1][2][3], [4][5][6]
		// 게시글의 개수 계속 증가/감소
		// 한페이지에 5개, 게시글 11개 -> 블록 3
		// 11/5 => int(2.2)(X)
		//나누어서 소수자리수까지 모두 반올림이 되어야 페이지 하나 생성
		// Math.ceil() => 소수정을 무조건 반올림하여 정수 출력하는 메소드
		totalPage = (int)Math.ceil(totalCnt / (double)pageSize);
		
		//DB 조회하는 범위, 첫 번째
		//1페이지 -> 0부터 5개
		//2페이지 -> 5부터 5개
		//3페이지 -> 10부터 5개
		startRow = (pageNum -1)*pageSize;
		startPage = ((pageNum-1)/pageBlock)*pageBlock+1;
		// 현재 pageBlock =3으로 지정, 만약 pageBlock =5
		// endPage = StartPage + 2 =>[1][2][3][4][5]
		endPage = startPage + (pageBlock-1);
		
		// 실제 페이지는 [1] ~ [8]까지만 출력되어야 하는데
		// 위의 계산식으로는 [1]~[9]까지 출력된다.
		// 이런 경우 가장 마지막 페이지를 강제로 endPage에 담아준다.
		
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		
		// 4.이전/다음 버튼 여부
		prev = startPage>1;
		next = endPage < totalPage;
		
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
