package reviewboard.dto;

public class ReviewBoardDTO {

	private int num;
	private String title;
	private String content;
	private String writer;
	private int star;
	private int viewcnt;
	private String createdAT;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getCreatedAT() {
		return createdAT;
	}
	public void setCreatedAT(String createdAT) {
		this.createdAT = createdAT;
	}
}
