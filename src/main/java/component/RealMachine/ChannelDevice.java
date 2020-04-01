package component.RealMachine;

public class ChannelDevice {

    public static ChannelDevice channelDevice = null;

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
