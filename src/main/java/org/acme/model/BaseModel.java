package org.acme.model;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseModel {

    private String Id;

    private LocalDateTime createdTimestamp;

    private String createdBy;

    private LocalDateTime updatedTimestamp;

    private String updatedBy;

    private ModificationInfo modificationInfo;

    public BaseModel() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ModificationInfo getModificationInfo() {
        return modificationInfo;
    }

    public void setModificationInfo(ModificationInfo modificationInfo) {
        this.modificationInfo = modificationInfo;
    }

    public static class ModificationInfo{
        private boolean success;

        private String statusCode;

        private String statusMessage;

        public ModificationInfo(boolean success, String statusCode, String statusMessage) {
            this.success = success;
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
        }

        public ModificationInfo() {
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
        }
    }
}
