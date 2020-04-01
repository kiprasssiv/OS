package component.RealMachine;

import java.util.Scanner;

public class InputDevice {
    Scanner userInput = new Scanner(System.in);
    String fileName;
    public String readingFileInput(){
        fileName = userInput.nextLine();
        return fileName;
    }
}
