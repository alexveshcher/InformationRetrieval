import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;

import static java.lang.System.out;


public class Dictionary {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static String message;

    public static void main(String[] args) throws AWTException, UnknownHostException, SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        shell();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);

        try {
            serverSocket = new ServerSocket(4444); // Server socket
            out.println("Server started. Listening to the port 4444");

        } catch (IOException e) {
            out.println("Could not listen on port: 4444");
        }


        while (true) {
            try {

                clientSocket = serverSocket.accept(); // accept the client connection
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader); // get the client message
                message = bufferedReader.readLine();
                out.println(message);
                inputStreamReader.close();
                clientSocket.close();
                Robot robot = new Robot();
                if (message.equals("space")){
                    robot.keyPress(KeyEvent.VK_SPACE);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                }
                if(message.equals("altright")){
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                }
                if(message.equals("altleft")){
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                }
                if (message.equals("left")){
                    robot.keyPress(KeyEvent.VK_LEFT);
                    robot.keyRelease(KeyEvent.VK_LEFT);
                }
                if (message.equals("right")){
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                }
                if (message.equals("esc")){
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                }
                if (message.equals("volume up")){
                    float volume = Audio.getMasterOutputVolume();
                    if (volume < 0.9f)
                        Audio.setMasterOutputVolume(volume+0.1f);
                }
                if (message.equals("volume down")){
                    float volume = Audio.getMasterOutputVolume();
                    if (volume > 0.1f)
                        Audio.setMasterOutputVolume(volume-0.1f);
                }
                if (message.equals("mute")){
                    Audio.setMasterOutputVolume(0.0f);
                }
                if (message.equals("double tap")){
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = (int) screenSize.getWidth()/2;
                    int height = (int) screenSize.getHeight()/2;
                    robot.mouseMove(width, height);
                    robot.mousePress( InputEvent.BUTTON1_MASK );
                    robot.mouseRelease( InputEvent.BUTTON1_MASK );
                    Thread.sleep(50);
                    robot.mousePress( InputEvent.BUTTON1_MASK );
                    robot.mouseRelease( InputEvent.BUTTON1_MASK );
                }

            } catch (Exception ex) {
                out.println("Problem in message reading");
            }


        }

    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s\n", inetAddress);
        }
        out.printf("\n");
    }

    static void shell(){
        //start of all commands should look like /bin/bash -c echo "12wsd12"| sudo -S
        //file path /sys/devices/pci0000:00/0000:00:02.0/drm/card0/card0-LVDS-1/intel_backlight/brightness
        StringBuilder sb = new StringBuilder();
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("ls");
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }


        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        try {
            while ((line = reader.readLine())!= null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }

}
