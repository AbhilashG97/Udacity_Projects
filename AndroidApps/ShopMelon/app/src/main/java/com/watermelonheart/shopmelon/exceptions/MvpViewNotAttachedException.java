package com.watermelonheart.shopmelon.exceptions;

public class MvpViewNotAttachedException extends RuntimeException {
    public MvpViewNotAttachedException() {
        super("MvpView is not attached!");
    }
}
