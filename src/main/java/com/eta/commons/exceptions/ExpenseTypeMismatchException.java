package com.eta.commons.exceptions;

import org.hibernate.TypeMismatchException;

public class ExpenseTypeMismatchException extends TypeMismatchException {

    public ExpenseTypeMismatchException(String message) {
        super(message);
    }
}
