package it.univr.houdini.JSONParser;

import it.univr.houdini.LogicModel.LogicModel;

class JSONLogicModel {
    private String version;
    private LogicModel logicModel;
    
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public LogicModel getLogicModel() {
        return logicModel;
    }
    public void setLogicModel(LogicModel logicModel) {
        this.logicModel = logicModel;
    }
    
}