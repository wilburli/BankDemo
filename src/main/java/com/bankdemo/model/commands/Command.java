package com.bankdemo.model.commands;

import com.bankdemo.exceptions.InterruptOperationException;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
public interface Command {
    void execute() throws InterruptOperationException;
}
