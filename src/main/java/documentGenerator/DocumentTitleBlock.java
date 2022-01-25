package documentGenerator;

import java.util.Arrays;
import java.util.Objects;

public class DocumentTitleBlock {
    private String documentCode;
    private String documentName;
    private String designerName;
    private byte[] designerSign;
    private String supervisorName;
    private byte[] supervisorSign;
    private String controllerName;
    private byte[] controllerSign;
    private String chiefEngineerName;
    private byte[] chiefEngineerSign;
    private String stage;
    private String projectName;
    private String objectAddress;
    private String releaseDate;
    private DocumentCompany company;
    private int volumeNumber;
    private String volumeName;

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public DocumentTitleBlock(String documentCode, String documentName, String designerName, byte[] designerSign, String supervisorName, byte[] supervisorSign, String controllerName, byte[] controllerSign, String chiefEngineerName, byte[] chiefEngineerSign, String stage, String projectName, String objectAddress, String releaseDate, DocumentCompany company, int volumeNumber, String volumeName) {
        this.documentCode = documentCode;
        this.documentName = documentName;
        this.designerName = designerName;
        this.designerSign = designerSign;
        this.supervisorName = supervisorName;
        this.supervisorSign = supervisorSign;
        this.controllerName = controllerName;
        this.controllerSign = controllerSign;
        this.chiefEngineerName = chiefEngineerName;
        this.chiefEngineerSign = chiefEngineerSign;
        this.stage = stage;
        this.projectName = projectName;
        this.objectAddress = objectAddress;
        this.releaseDate = releaseDate;
        this.company = company;
        this.volumeNumber = volumeNumber;
        this.volumeName = volumeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentTitleBlock that)) return false;
        return Objects.equals(documentCode, that.documentCode) && Objects.equals(documentName, that.documentName) && Objects.equals(designerName, that.designerName) && Arrays.equals(designerSign, that.designerSign) && Objects.equals(supervisorName, that.supervisorName) && Arrays.equals(supervisorSign, that.supervisorSign) && Objects.equals(controllerName, that.controllerName) && Arrays.equals(controllerSign, that.controllerSign) && Objects.equals(chiefEngineerName, that.chiefEngineerName) && Arrays.equals(chiefEngineerSign, that.chiefEngineerSign) && Objects.equals(stage, that.stage) && Objects.equals(projectName, that.projectName) && Objects.equals(objectAddress, that.objectAddress) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(documentCode, documentName, designerName, supervisorName, controllerName, chiefEngineerName, stage, projectName, objectAddress, releaseDate, company);
        result = 31 * result + Arrays.hashCode(designerSign);
        result = 31 * result + Arrays.hashCode(supervisorSign);
        result = 31 * result + Arrays.hashCode(controllerSign);
        result = 31 * result + Arrays.hashCode(chiefEngineerSign);
        return result;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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