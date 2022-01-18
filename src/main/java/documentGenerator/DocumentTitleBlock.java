package documentGenerator;

import java.util.Arrays;
import java.util.Objects;

public class DocumentTitleBlock {
    private String blueprintCode;
    private String blueprintName;
    private String designerName;
    private byte[] designerSign;
    private String supervisorName;
    private byte[] supervisorSign;
    private String controllerName;
    private byte[] controllerSign;
    private String chiefEngineerName;
    private byte[] chiefEngineerSign;
    private String stageCode;
    private String projectName;
    private String objectAddress;
    private String releaseDate;
    private DocumentCompany company;

    public DocumentTitleBlock(String blueprintCode, String blueprintName, String designerName, byte[] designerSign, String supervisorName, byte[] supervisorSign, String controllerName, byte[] controllerSign, String chiefEngineerName, byte[] chiefEngineerSign, String stageCode, String projectName, String objectAddress, String releaseDate, DocumentCompany company) {
        this.blueprintCode = blueprintCode;
        this.blueprintName = blueprintName;
        this.designerName = designerName;
        this.designerSign = designerSign;
        this.supervisorName = supervisorName;
        this.supervisorSign = supervisorSign;
        this.controllerName = controllerName;
        this.controllerSign = controllerSign;
        this.chiefEngineerName = chiefEngineerName;
        this.chiefEngineerSign = chiefEngineerSign;
        this.stageCode = stageCode;
        this.projectName = projectName;
        this.objectAddress = objectAddress;
        this.releaseDate = releaseDate;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentTitleBlock)) return false;
        DocumentTitleBlock that = (DocumentTitleBlock) o;
        return Objects.equals(blueprintCode, that.blueprintCode) && Objects.equals(blueprintName, that.blueprintName) && Objects.equals(designerName, that.designerName) && Arrays.equals(designerSign, that.designerSign) && Objects.equals(supervisorName, that.supervisorName) && Arrays.equals(supervisorSign, that.supervisorSign) && Objects.equals(controllerName, that.controllerName) && Arrays.equals(controllerSign, that.controllerSign) && Objects.equals(chiefEngineerName, that.chiefEngineerName) && Arrays.equals(chiefEngineerSign, that.chiefEngineerSign) && Objects.equals(stageCode, that.stageCode) && Objects.equals(projectName, that.projectName) && Objects.equals(objectAddress, that.objectAddress) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(blueprintCode, blueprintName, designerName, supervisorName, controllerName, chiefEngineerName, stageCode, projectName, objectAddress, releaseDate, company);
        result = 31 * result + Arrays.hashCode(designerSign);
        result = 31 * result + Arrays.hashCode(supervisorSign);
        result = 31 * result + Arrays.hashCode(controllerSign);
        result = 31 * result + Arrays.hashCode(chiefEngineerSign);
        return result;
    }

    public String getBlueprintCode() {
        return blueprintCode;
    }

    public void setBlueprintCode(String blueprintCode) {
        this.blueprintCode = blueprintCode;
    }

    public String getBlueprintName() {
        return blueprintName;
    }

    public void setBlueprintName(String blueprintName) {
        this.blueprintName = blueprintName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public byte[] getDesignerSign() {
        return designerSign;
    }

    public void setDesignerSign(byte[] designerSign) {
        this.designerSign = designerSign;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public byte[] getSupervisorSign() {
        return supervisorSign;
    }

    public void setSupervisorSign(byte[] supervisorSign) {
        this.supervisorSign = supervisorSign;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public byte[] getControllerSign() {
        return controllerSign;
    }

    public void setControllerSign(byte[] controllerSign) {
        this.controllerSign = controllerSign;
    }

    public String getChiefEngineerName() {
        return chiefEngineerName;
    }

    public void setChiefEngineerName(String chiefEngineerName) {
        this.chiefEngineerName = chiefEngineerName;
    }

    public byte[] getChiefEngineerSign() {
        return chiefEngineerSign;
    }

    public void setChiefEngineerSign(byte[] chiefEngineerSign) {
        this.chiefEngineerSign = chiefEngineerSign;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getObjectAddress() {
        return objectAddress;
    }

    public void setObjectAddress(String objectAddress) {
        this.objectAddress = objectAddress;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public DocumentCompany getCompany() {
        return company;
    }

    public void setCompany(DocumentCompany company) {
        this.company = company;
    }
}