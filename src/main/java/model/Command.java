package model;

import component.VirtualMachine.CommandHandler;

import java.nio.ByteBuffer;
import java.util.List;

public enum Command {
    VT("VT") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleVT(RegisterType.valueOf(parameters.get(0)), parameters.get(1));
        }
    },
    MTA("MTA") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleMTA();} },
    MTB("MTB") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleMTB(); } },
    ATM("ATM") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleATM(); } },
    BTM("BTM") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleBTM(); } },
    AD("AD") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleAD(
                RegisterType.valueOf(parameters.get(0)),
                RegisterType.valueOf(parameters.get(1))
            );
        }
    },
    SB("SB") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleSB(
                RegisterType.valueOf(parameters.get(0)),
                RegisterType.valueOf(parameters.get(1))
            );
        }
    },
    CM("CM") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleCM(
                RegisterType.valueOf(parameters.get(0)),
                RegisterType.valueOf(parameters.get(1))
            );
        }
    },
    SJMP("SJMP") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleSJMP(); } },
    IJMP("IJMP") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleIJMP(); } },
    BJMP("BJMP") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleBJMP(); } },
    AJMP("AJMP") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleAJMP(); } },
    GET("GET") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleGET(RegisterType.valueOf(parameters.get(0)));
        }
    },
    WRT("WRT") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleWRT(RegisterType.valueOf(parameters.get(0)));
        }
    },
    LOAD("LOAD") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleLOAD(); } },
    LD("LD") {
        @Override public void handle(List<String> parameters) {
            CommandHandler.getInstance().handleLD(
                RegisterType.valueOf(parameters.get(0)),
                RegisterType.valueOf(parameters.get(1))
            );
        }
    },
    HALT("HALT") { @Override public void handle(List<String> parameters) { CommandHandler.getInstance().handleHALT(); } };

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

    public void handle(List<String> parameters) {}
}
