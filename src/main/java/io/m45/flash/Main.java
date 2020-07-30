
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
    
    private final static String COM_PORT = "COM3";
    
    private final static int BAUD = 9600;
    private final static int STOP_BITS = 1;
    private final static int DATA_BITS = 8;
    private final static int PARITY_BITS = 0;
    
    private final static byte[] parameters = {   
                                        00,    00,    00,    00,    00,
                                        00,    100,    100,    100,    00,
                                        00,    00,    00,    00,    00,
                                        99
                                };
    
    public static void main(String[] data) {
        
        String[] portNames = SerialPortList.getPortNames();
        
        Stream.of(portNames).forEach(System.out::println);
        
        System.out.println("Sending parameters");
        
        SerialPort serialPort = new SerialPort(COM_PORT);
        
        try {
            boolean portOpened = serialPort.openPort();
            serialPort.setParams(BAUD, DATA_BITS, STOP_BITS, PARITY_BITS, false, false);
            
            Thread.sleep(1000);
            
            for( int i = 0; i < parameters.length; i++) {
                serialPort.writeByte((byte)parameters[i]);
            }
            
//            serialPort.writeBytes(parameters);
            serialPort.closePort();
        } catch (Exception ex) {
            System.out.println("Caught exception");
            ex.printStackTrace();
        }
        
        
        
    }
    
}
