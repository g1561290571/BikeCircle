package bike.circle.dto;

/**
 * Created by MrH on 2017/5/5.
 */

public class TravelNoteZoom  {
    private String userLoginName;
    private String userImagePath;
    private String partContent;
    private Long id;
    private int thumbsUpCount;
    private int commentCount;
    private String[] image;

    public TravelNoteZoom(String userLoginName, String userImagePath, String partContent, Long id, int thumbsUpCount, int commentCount, String[] image) {
        this.userLoginName = userLoginName;
        this.userImagePath = userImagePath;
        this.partContent = partContent;
        this.id = id;
        this.thumbsUpCount = thumbsUpCount;
        this.commentCount = commentCount;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getPartContent() {
        return partContent;
    }

    public void setPartContent(String partContent) {
        this.partContent = partContent;
    }

    public int getThumbsUpCount() {
        return thumbsUpCount;
    }

    public void setThumbsUpCount(int thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
