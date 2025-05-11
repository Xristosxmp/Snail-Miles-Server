package com.snailmiles.app.Exceptions.expiredApiKey;

import com.snailmiles.app.Models.User;

public class ExpiredApiKeyException extends RuntimeException {
    private final User user;

    public ExpiredApiKeyException(String message, User user) {
        super(message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
