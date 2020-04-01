package component.RealMachine;

public class ChannelDevice {

    public static ChannelDevice channelDevice = null;

    public static Register AX,BX,CX,DX,PTR;

    public static ChannelDevice getInstance() {
        if (channelDevice == null) {
            return new ChannelDevice();
        }

        return channelDevice;
    }

    private ChannelDevice() {}

    public void initializeRegisters(){

    }

}
