package blog.nen.dto;

public class BoardDto {
    private int boardId;
    private String boardEmail;
    private String boardDate;
    private String boardTitle;
    private String boardCategory;
    private boolean boardPublic;
    private String boardText;
    private boolean boardSave;

    public BoardDto(int boardId, String boardEmail, String boardDate, String boardTitle, String boardCategory, boolean boardPublic, String boardText, boolean boardSave) {
        this.boardId = boardId;
        this.boardEmail = boardEmail;
        this.boardDate = boardDate;
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardPublic = boardPublic;
        this.boardText = boardText;
        this.boardSave = boardSave;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardEmail() {
        return boardEmail;
    }

    public void setBoardEmail(String boardEmail) {
        this.boardEmail = boardEmail;
    }

    public String getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(String boardDate) {
        this.boardDate = boardDate;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    public boolean isBoardPublic() {
        return boardPublic;
    }

    public void setBoardPublic(boolean boardPublic) {
        this.boardPublic = boardPublic;
    }

    public String getBoardText() {
        return boardText;
    }

    public void setBoardText(String boardText) {
        this.boardText = boardText;
    }

    public boolean isBoardSave() {
        return boardSave;
    }

    public void setBoardSave(boolean boardSave) {
        this.boardSave = boardSave;
    }
}