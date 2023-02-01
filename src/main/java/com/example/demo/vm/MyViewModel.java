package com.example.demo.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class MyViewModel {
    private String name = "";
    public final static String SUBMIT_COMMAND = "submit";

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() { return String.format("Hello %s!", name);}

    @Command(SUBMIT_COMMAND)
    @NotifyChange("response")
    public void submit() {}
}
