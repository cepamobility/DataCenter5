/**
 * Created on 04-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.administration.bookmarks.tables;

public class BookmarkParameter {

    private Integer id;
    private Bookmark bookmark;
    private String type;
    private String value;
    
    public BookmarkParameter() {
        super();
    }

    public BookmarkParameter(String key, String value,Bookmark bookmark) {
        type = key;
        this.value = value;
        this.bookmark = bookmark;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
