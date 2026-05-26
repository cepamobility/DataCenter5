/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import java.io.Serializable;
import java.util.Date;

public class CENAPRAParticipantLogPK implements Serializable {

    private Integer participant;
    private String userId;
    private Date date;

    public CENAPRAParticipantLogPK() {
    }

    public CENAPRAParticipantLogPK(Integer id, String user) {
        participant = id;
        userId = user;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Integer getParticipant() {
        return participant;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
