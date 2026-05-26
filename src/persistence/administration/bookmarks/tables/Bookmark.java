/**
 * Created on 04-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.administration.bookmarks.tables;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.user.tables.User;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.user.UserSessionData;

public class Bookmark {

    private Integer id;
    private User userId;
    private Corporation corporation;
    private String name;
    private Integer action;
    private boolean start;
    
    private Set<BookmarkParameter> parameters;
    
    public Bookmark() {
        super();
    }

    public Bookmark(BookmarkData data,UserSessionData sessionData,Session session) {
        userId = (User)session.load(User.class,sessionData.getFilter().getLoginName());
        corporation = (Corporation)session.load(Corporation.class,sessionData.getFilter().getCorporationId());
        name = data.getName();
        action = data.getAction();
        start = data.isStart();
        parameters = new HashSet();
        Enumeration keys = data.getParameters().keys();
        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            parameters.add(new BookmarkParameter(key,(String)data.getParameters().get(key),this));
        }
    }

    public Integer getAction() {
        return action;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<BookmarkParameter> getParameters() {
        return parameters;
    }

    public boolean isStart() {
        return start;
    }

    public User getUserId() {
        return userId;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(Set<BookmarkParameter> parameters) {
        this.parameters = parameters;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public BookmarkData getData() {
        Hashtable hash = new Hashtable();
        Iterator it = parameters.iterator();
        while(it.hasNext()) {
            BookmarkParameter parameter = (BookmarkParameter)it.next();
            hash.put(parameter.getType(),parameter.getValue());
        }
        return new BookmarkData(id,name,start,action.intValue(),hash);
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

}
