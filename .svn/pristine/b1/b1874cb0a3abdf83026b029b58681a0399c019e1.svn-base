/**
 * Created on 26-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.bookmark;

import org.apache.struts.action.ActionForm;

import communication.administration.bookmark.tables.BookmarkData;

public class BookmarkUpdateForm extends ActionForm {

    private String id;
    private String name;
    private String start;
    
    public BookmarkUpdateForm() {
    }
    
    public BookmarkUpdateForm(BookmarkData data) {
        id = String.valueOf(data.getId());
        name = data.getName();
        start = String.valueOf(data.isStart());
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getStart() {
        return start;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setStart(String start) {
        this.start = start;
    }

}
