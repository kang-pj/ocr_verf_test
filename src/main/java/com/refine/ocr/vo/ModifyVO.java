package com.refine.ocr.vo;

public class ModifyVO {
    private String ocrRsltNo;
    private String modifyType;
    private String originalValue;
    private String modifiedValue;
    private String modifyReason;
    private String modifyUsrId;
    private String modifyDttm;
    
    // Getters and setters
    public String getOcrRsltNo() { return ocrRsltNo; }
    public void setOcrRsltNo(String ocrRsltNo) { this.ocrRsltNo = ocrRsltNo; }
    
    public String getModifyType() { return modifyType; }
    public void setModifyType(String modifyType) { this.modifyType = modifyType; }
    
    public String getOriginalValue() { return originalValue; }
    public void setOriginalValue(String originalValue) { this.originalValue = originalValue; }
    
    public String getModifiedValue() { return modifiedValue; }
    public void setModifiedValue(String modifiedValue) { this.modifiedValue = modifiedValue; }
    
    public String getModifyReason() { return modifyReason; }
    public void setModifyReason(String modifyReason) { this.modifyReason = modifyReason; }
    
    public String getModifyUsrId() { return modifyUsrId; }
    public void setModifyUsrId(String modifyUsrId) { this.modifyUsrId = modifyUsrId; }
    
    public String getModifyDttm() { return modifyDttm; }
    public void setModifyDttm(String modifyDttm) { this.modifyDttm = modifyDttm; }
}