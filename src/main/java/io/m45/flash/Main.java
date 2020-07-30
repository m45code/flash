
package io.m45.flash;

import java.util.stream.Stream;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author tim
 */
public class Main {
    
    private final static String COM_PORT = "COM11";
    
    private final static int BAUD = 115200;
    private final static int STOP_BITS = 1;
    private final static int DATA_BITS = 8;
    private final static int PARITY_BITS = 0;
    
    private final static int[] parameters = {   
                                        000,    000,    000,    000,    000,
                                        010,    010,    010,    010,    010,
                                        000,    000,    000,    000,    000,
                                        -1
                                };
    
    public static void main(String[] data) {
        
        String[] portNames = SerialPortList.getPortNames();
        
        Stream.of(portNames).forEach(System.out::println);
        
        System.out.println("Sending parameters");
        
        SerialPort serialPort = new SerialPort(COM_PORT);
        
        try {
            boolean portOpened = serialPort.openPort();
            serialPort.setParams(BAUD, DATA_BITS, STOP_BITS, PARITY_BITS);
            serialPort.writeIntArray(parameters);
        } catch (SerialPortException ex) {
            System.out.println("Caught exception");
            ex.printStackTrace();
        }
        
    }
    
}
