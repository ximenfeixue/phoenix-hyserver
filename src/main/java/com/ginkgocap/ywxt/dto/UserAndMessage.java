package com.ginkgocap.ywxt.dto;

import com.ginkgocap.ywxt.user.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cinderella
 * @version 2017/12/14
 */
public class UserAndMessage implements Serializable{

    private static final long serialVersionUID = -2785964146747347431L;

    private User user;

    private SendMessage sendMessage;

    public UserAndMessage() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
