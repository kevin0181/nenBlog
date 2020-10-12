package blog.nen.dto;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

public class BoardDto {
    private int boardId;
    private String boardEmail;
    private Date boardDate;
    @NotBlank
    private String boardTitle;
    private int category_id;
    @NotBlank
    private String boardCategory;

    private String boardCategory_Ex;
    private boolean boardPublic;
    private String boardText;
    private boolean boardSave;

    public BoardDto() {

    }

    public BoardDto(int boardId, String boardEmail, Date boardDate, @NotBlank String boardTitle, String boardCategory, boolean boardPublic, String boardText, boolean boardSave) {
        this.boardId = boardId;
        this.boardEmail = boardEmail;
        this.boardDate = boardDate;
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardPublic = boardPublic;
        this.boardText = boardText;
        this.boardSave = boardSave;
    }

    public BoardDto(int category_id, String boardCategory, String boardCategory_Ex) {
        this.category_id = category_id;
        this.boardCategory = boardCategory;
        this.boardCategory_Ex = boardCategory_Ex;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getBoardCategory_Ex() {
        return boardCategory_Ex;
    }

    public void setBoardCategory_Ex(String boardCategory_Ex) {
        this.boardCategory_Ex = boardCategory_Ex;
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

    public Date getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(Date boardDate) {
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
