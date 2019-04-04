package timing.ukulele.api.model.system;

import java.util.List;

public class ModuleAndSystemModel {
    private String id;

    private String moduleName;

    private List<ModuleAndSystemModel> subModules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ModuleAndSystemModel> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<ModuleAndSystemModel> subModules) {
        this.subModules = subModules;
    }
}
