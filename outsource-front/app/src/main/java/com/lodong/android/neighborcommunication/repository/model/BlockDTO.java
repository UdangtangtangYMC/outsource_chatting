package com.lodong.android.neighborcommunication.repository.model;

public class BlockDTO {
    private String id;
    private String subject;

    public BlockDTO(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "BlockDTO{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
