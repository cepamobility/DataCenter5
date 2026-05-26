/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import persistence.cenapra.tables.CENAPRAActivityRegistry;



public class CENAPRAActivityRegistryLog {

    private CENAPRAActivityRegistryLogPK pk;

    private Integer action;
    private Integer activity;
    private Integer participant;

    private String observations;
    private boolean result;

    public CENAPRAActivityRegistryLog() {
    }

    public CENAPRAActivityRegistryLog(CENAPRAActivityRegistry registry,Integer action,String user) {
        pk = new CENAPRAActivityRegistryLogPK(registry.getId(),user);
        this.action = action;
        activity = registry.getActivity().getId();
        participant = registry.getParticipant().getId();
        observations = registry.getObservations();
        result = registry.isResult();
    }

    public Integer getAction() {
        return action;
    }

    public Integer getActivity() {
        return activity;
    }

    public Integer getParticipant() {
        return participant;
    }

    public CENAPRAActivityRegistryLogPK getPk() {
        return pk;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public void setPk(CENAPRAActivityRegistryLogPK pk) {
        this.pk = pk;
    }

    public String getObservations() {
        return observations;
    }

    public boolean isResult() {
        return result;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
