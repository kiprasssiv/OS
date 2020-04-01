package model;

import component.VirtualMachine.CommandHandler;

import java.nio.ByteBuffer;

public enum Command {
    MTA("MTA") { @Override public void handle() { CommandHandler.getInstance().handleMTA(); } },
    MTB("MTB") { @Override public void handle() { CommandHandler.getInstance().handleMTB(); } },
    ATM("ATM") { @Override public void handle() { CommandHandler.getInstance().handleATM(); } },
    BTM("BTM") { @Override public void handle() { CommandHandler.getInstance().handleBTM(); } },
    AD("AD") { @Override public void handle(RegisterType arg1, RegisterType arg2) { CommandHandler.getInstance().handleAD(arg1, arg2); } },
    SB("SB") { @Override public void handle(RegisterType arg1, RegisterType arg2) { CommandHandler.getInstance().handleSB(arg1, arg2); } },
    CM("CM") { @Override public void handle(RegisterType arg1, RegisterType arg2) { CommandHandler.getInstance().handleCM(arg1, arg2); } },
    SJMP("SJMP") { @Override public void handle() { CommandHandler.getInstance().handleSJMP(); } },
    IJMP("IJMP") { @Override public void handle() { CommandHandler.getInstance().handleIJMP(); } },
    BJMP("BJMP") { @Override public void handle() { CommandHandler.getInstance().handleBJMP(); } },
    AJMP("AJMP") { @Override public void handle() { CommandHandler.getInstance().handleAJMP(); } },
    GET("GET") { @Override public void handle(RegisterType arg) { CommandHandler.getInstance().handleGET(arg); } },
    WRT("WRT") { @Override public void handle(RegisterType arg) { CommandHandler.getInstance().handleWRT(arg); } },
    LOAD("LOAD") { @Override public void handle() { CommandHandler.getInstance().handleLOAD(); } },
    LD("LD") { @Override public void handle(RegisterType arg1, RegisterType arg2) { CommandHandler.getInstance().handleLD(arg1, arg2); } },
    HALT("HALT") { @Override public void handle() { CommandHandler.getInstance().handleHALT(); } };

    private int value;

    Command(String name) {
        this.value = getByteValue(name);
    }

    public int getValue() { return this.value; }

    private int getByteValue(String command) {
        StringBuilder sb = new StringBuilder(command);
        for (int i = command.length(); i < 4; i++)
            sb.append(" ");

        if (command.length() == 2)
            return (ByteBuffer.wrap(sb.toString().getBytes()).getInt() & 0xffff0000);
        else if (command.length() == 3)
            return (ByteBuffer.wrap(sb.toString().getBytes()).getInt() & 0xffffff00);
        else
            return (ByteBuffer.wrap(sb.toString().getBytes()).getInt() & 0xffffffff);
    }

    public void handle() {}

    public void handle(RegisterType arg) {}

    public void handle(RegisterType arg1, RegisterType arg2) {}
}
