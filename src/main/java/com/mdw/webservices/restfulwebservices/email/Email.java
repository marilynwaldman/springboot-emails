package com.mdw.webservices.restfulwebservices.email;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mdw.webservices.restfulwebservices.Sender.Sender;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the email. ")
@Entity

public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Past(message="Date cannot be in the future")
    @ApiModelProperty(notes="Birth date should be in the past")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE, d MMM yyyy HH:mm:ss Z")
    private Date logDate;

    @Past(message="Date cannot be in the future")
    @ApiModelProperty(notes="Birth date should be in the past")
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE, d MMM yyyy HH:mm:ss Z")
    private Date emailDate;


    private String emailId;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String senderName;

    private String subject;

    private String file;

    private String folder;


    //@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    //@JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Sender sender;

    public Email() {
        super();
    }

    public Email(@Past(message = "Date cannot be in the future") Date logDate, @Past(message = "Date cannot be in the future") Date emailDate, String emailId, @Size(min = 2, message = "Title should have at least two characters") String senderName, String subject, String file, String folder) {
        super();
        this.logDate = logDate;
        this.emailDate = emailDate;
        this.emailId = emailId;
        this.senderName = senderName;
        this.subject = subject;
        this.file = file;
        this.folder = folder;
    }

    public Email(@Past(message = "Date cannot be in the future") Date logDate, @Past(message = "Date cannot be in the future") Date emailDate, String emailId, @Size(min = 2, message = "Title should have at least two characters") String senderName, String subject, String file, String folder, Sender sender) {
         super();
        this.logDate = logDate;
        this.emailDate = emailDate;
        this.emailId = emailId;
        this.senderName = senderName;
        this.subject = subject;
        this.file = file;
        this.folder = folder;
        this.sender = sender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Date getEmailDate() {
        return emailDate;
    }

    public void setEmailDate(Date emailDate) {
        this.emailDate = emailDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", logDate=" + logDate +
                ", emailDate=" + emailDate +
                ", emailId='" + emailId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", subject='" + subject + '\'' +
                ", file='" + file + '\'' +
                ", folder='" + folder + '\'' +
                ", sender=" + sender +
                '}';
    }
}
