import javax.swing.*;

public class LaminaTexto extends JPanel {

    JTextArea textoPrincipal;

    public LaminaTexto (){     

        //creamos la instancia que nos crea el espacio para poder escribir.
        textoPrincipal = new JTextArea(20, 50);

        //con JScrollPane nos permitira que nos salga una barra de desplazamiento.
        JScrollPane barraDesplazar = new JScrollPane(textoPrincipal);
        textoPrincipal.setLineWrap(true);
        add(barraDesplazar); 
    }
        
    public String getTexto(){
        return textoPrincipal.getText();
    }

    public void setTexto(String texto){
        textoPrincipal.setText(texto);
    }

    public void habilitar (boolean editar){
        textoPrincipal.setEditable(editar);
    }

}
