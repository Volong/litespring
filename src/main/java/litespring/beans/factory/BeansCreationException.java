package litespring.beans.factory;

import litespring.beans.BeansException;

public class BeansCreationException extends BeansException {

    public BeansCreationException(String msg) {
        super(msg);
    }

    public BeansCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
