/**
 * Created on 28-nov-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.group.tables;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;

import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupMainAttributes;

public class AbstractGroup {

    private Integer id;
    
    private String name;
    private String description;
    
    private boolean active;
    
    private Integer period;
    private Integer type;

    protected Corporation corporation;

    public AbstractGroup() {
    }

    public AbstractGroup(GroupMainAttributes groupData, Session session, Integer corporationId) {
        id = groupData.getId();
        name = groupData.getName();
        description = groupData.getDescription();
        active = groupData.isActive();
        period = groupData.getPeriod();
        type = groupData.getType();
        
        corporation = (Corporation)session.load(Corporation.class, corporationId);
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getType() {
        return type;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public GroupMainAttributes getMainAttributes() {
        return new GroupMainAttributes(id,name,description,active,period,type);
    }

    public GroupData getData() {
        GroupData groupData = new GroupData(id,name,description,active,period,type);
        return groupData;
    }
}
