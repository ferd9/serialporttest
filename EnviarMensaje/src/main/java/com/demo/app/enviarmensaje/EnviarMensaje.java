/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.demo.app.enviarmensaje;
import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author Lynkos
 */
public class EnviarMensaje {

    public static void main(String[] args) {       
		
	// Obtener la lista de puertos seriales disponibles
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort selectedPort = null;
        for (SerialPort port : ports) {
            if (port.getSystemPortName().equals("COM2")) { 
                selectedPort = port;
                break;
            }
        }  
        
       // Abrir el puerto serial seleccionado
        if (selectedPort.openPort()) {
            System.out.println("Puerto serial abierto correctamente.");
            
            // Configurar los parámetros del puerto serial (baudrate, bits de datos, paridad, bits de parada)
            selectedPort.setBaudRate(9600);
            selectedPort.setNumDataBits(8);
            selectedPort.setParity(SerialPort.NO_PARITY);
            selectedPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
            
            // Obtener el OutputStream para enviar datos
            OutputStream outputStream = selectedPort.getOutputStream();
            
            System.out.println("Ingrese el mensaje a enviar.");
            Scanner leer = new Scanner(System.in);
            // Mensaje a enviar
            String mensaje = leer.nextLine();
            
            try {
                // Escribir el mensaje en el OutputStream
                outputStream.write(mensaje.getBytes());
                System.out.println("Mensaje enviado correctamente.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerrar el OutputStream y el puerto serial
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                selectedPort.closePort();
            }
        } else {
            System.err.println("No se pudo abrir el puerto serial.");
        }
    }
}
