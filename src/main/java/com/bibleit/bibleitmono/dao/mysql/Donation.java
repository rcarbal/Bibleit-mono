package com.bibleit.bibleitmono.dao.mysql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sessionId;
    private BigDecimal amount;
    private String currencyType;
    private long personId;
    private String comment;
    private String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private String status;

    protected Donation(){}

    public Donation(String sessionId, BigDecimal amount, String currencyType, long personId, String comment, String timeStamp, String status) {
        this.sessionId = sessionId;
        this.amount = amount;
        this.currencyType = currencyType;
        this.personId = personId;
        this.comment = comment;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", amount=" + amount +
                ", currencyType='" + currencyType + '\'' +
                ", personId=" + personId +
                ", comment='" + comment + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}