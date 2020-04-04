package po;

public class Draft {
	private int draftId;
	private String title;
	private int userId;
	private String text;
	private String imgPath;
	private int isEssay;
	public int getDraftId() {
		return draftId;
	}
	public void setDraftId(int draftId) {
		this.draftId = draftId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getIsEssay() {
		return isEssay;
	}
	public void setIsEssay(int isEssay) {
		this.isEssay = isEssay;
	}
}
