using System;
using System.IO.Ports;

class ReceptorMensaje
{

    // creacion el puerto serie con una configuracion basica.
    private SerialPort port = new SerialPort("COM3", 9600, Parity.None, 8, StopBits.One);

    [STAThread]
    static void Main(string[] args)
    {
        // instanciamos nuestro receptor de mensajes 
        ReceptorMensaje receptorMensaje = new ReceptorMensaje();
        receptorMensaje.abrirPuerto();        
    }

    public void abrirPuerto()
    {
        Console.WriteLine("Dato de entrada:");
        // Adjunta un método que se llamará cuando haya datos esperando en el búfer del puerto.       
        port.DataReceived += new SerialDataReceivedEventHandler(port_DataReceived);
        // Iniciamos la comunicacion 
        port.Open();        
        //Mantenemos vivo el hilo de esta aplicacion.
        Console.ReadLine();
    }

    public void port_DataReceived(object sender, SerialDataReceivedEventArgs e)
    {
        // Mostrar todos los datos entrantes en el buffer del puerto.
        Console.WriteLine(port.ReadExisting());
    }
}
