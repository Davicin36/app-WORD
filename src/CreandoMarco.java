import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CreandoMarco extends JFrame {

    public CreandoMarco (){

        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = miPantalla.getScreenSize();

        int alturaPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;

        setBounds(anchoPantalla/4, alturaPantalla/4, anchoPantalla/2, alturaPantalla/2);

        setTitle("Ejercicio 11: Leer y guardar ficheros");

        setLayout (new BorderLayout());

        //--------------POSICIONAMIENTO Y CREACION DE MENUBARRA Y SUS LISTENER------------------------------------------

        JMenuBar Barra = new JMenuBar();
        setJMenuBar(Barra);

        JMenu menuArchivo = new JMenu("Archivo");

        JMenuItem menuNuevo = new JMenuItem("Crear Archivo");
        menuNuevo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                crearTNuevo();
            
            }
        });

        JMenuItem menuAbrirArchivo = new JMenuItem("Abrir archivo");
        menuAbrirArchivo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
           
        });

        JMenuItem menuGuardar =  new JMenuItem("Guardar");
        menuGuardar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {  
               guardarArchivo();
            }
            
        });

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });

        JSeparator otraCosa = new JSeparator();
 
        Barra.add(menuArchivo);

        menuArchivo.add(menuNuevo);
        menuArchivo.add(menuAbrirArchivo);
        menuArchivo.add(menuGuardar);
        menuArchivo.add(otraCosa);
        menuArchivo.add(menuSalir);   
        
        //------------------POSICIONAMIENTO Y CREACION DE CUADRO DE TEXTO----------------------------------------

        escribirTexto = new LaminaTexto();
        escribirTexto.habilitar(false);
        add (escribirTexto, BorderLayout.CENTER);
        
        //----------------------POSICIONAMIENTO Y CREACION BOTONES Y SUS LISTENER------------------

        laminaBotones = new JPanel();

        btnTNuevo = new JButton ("Nuevo Texto");
        btnTNuevo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                escribirTexto.setTexto (" ");
                crearTNuevo();
            }
            
        });

        /*intanciamos la lamina que hemos creado en la clase y le ponemos add para que se vea en nuestra app */
        btnGuardar = new JButton("Guardar");
        btnGuardar.setEnabled(false);

        btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
            
        });

        laminaBotones.add (btnGuardar);
        laminaBotones.add (btnTNuevo);

        add(laminaBotones, BorderLayout.SOUTH);
        btnGuardar.setEnabled(false);
    }
        //--------------CREAION DE LOS METODOS----------------------

        private void crearTNuevo(){

            JFileChooser elegirSitio = new JFileChooser();
            int seleccion = elegirSitio.showOpenDialog(this);

            if(seleccion == JFileChooser.APPROVE_OPTION){
                File archivo = elegirSitio.getSelectedFile();
           
                try {
                    FileWriter escribir = new FileWriter(archivo);
                    BufferedWriter bufferEscribir = new BufferedWriter(escribir);

                    bufferEscribir.write(" ");
                    bufferEscribir.close();

                    escribirTexto.habilitar(true);
                    btnGuardar.setEnabled(true);

                    JOptionPane.showMessageDialog(this, "El archivo ha sido creado en " + archivo.getAbsolutePath());

                } catch (IOException ex){
                    JOptionPane.showMessageDialog(this, "Ha habido un error al crear el archivo", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        private void guardarArchivo (){
            String archivo = escribirTexto.getTexto();
    
            JFileChooser archivoElegido = new JFileChooser();
    
            int resultado = archivoElegido.showSaveDialog(this);
    
            if(resultado == JFileChooser.APPROVE_OPTION){
                File archSave = archivoElegido.getSelectedFile();
    
                try {
                    BufferedWriter escribir = new BufferedWriter (new FileWriter (archSave));
                    escribir.write(archivo);

                    escribirTexto.setTexto (" ");
                    escribirTexto.habilitar(false);
                    btnGuardar.setEnabled(false);
                    
                    escribir.close();
    
                    JOptionPane.showMessageDialog(this, "Texto guardado con exito en " + archSave.getAbsolutePath());
    
                } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(this, "Error al guardar el texto en " + ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
    
                }
            }
        }

        private void abrirArchivo (){

            JFileChooser archivoElegido = new JFileChooser();
    
            int resultado = archivoElegido.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File leerArchivo = archivoElegido.getSelectedFile();
        
            try {
                BufferedReader leer = new BufferedReader(new FileReader(leerArchivo));
                StringBuilder contenidoArchivo = new StringBuilder();
                String linea;

                while ((linea = leer.readLine()) != null){
                    contenidoArchivo.append(linea).append ("\n");

                }

                leer.close();

                escribirTexto.habilitar(true);
                btnGuardar.setEnabled(true);

                escribirTexto.setTexto(contenidoArchivo.toString());
            } catch (IOException ex){

                JOptionPane.showMessageDialog(this, "El archivo no se puede abrir por " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private JPanel laminaBotones;
    private JButton btnGuardar, btnTNuevo; 
    private LaminaTexto escribirTexto;
      
}
