package imc;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Persona {
    String nombre;
    double peso;
    double altura; // en centímetros

    public Persona(String nombre, double peso, double altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularIMC() {
        return peso / ((altura / 100) * (altura / 100)); // Convertimos altura a metros
    }

    public String obtenerEstadoNutricional() {
        double ims = calcularIMC();
        if (ims < 18.5) {
            return "Bajo peso";
        } else if (ims >= 18.5 && ims < 25) {
            return "Peso Normal o Aceptable";
        } else {
            return "Sobrepeso";
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Peso: " + peso + " kg, Altura: " + altura + " cm";
    }
}

public class IMC {
    public static void main(String[] args) {
        
         
        List<Persona> personas = new ArrayList<>();

        while (true) {
            
            String[] opciones = {"Agregar Persona", "Actualizar Peso", "Mostrar Lista", "Calcular IMS", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Gestor de IMS",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    agregarPersona(personas);
                    break;
                case 1:
                    actualizarPeso(personas);
                    break;
                case 2:
                    mostrarLista(personas);
                    break;
                case 3:
                    calcularIMS(personas);
                    break;
                case 4:
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }

    private static void agregarPersona(List<Persona> personas) {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        double peso = obtenerNumeroValido("Peso (kg):");
        double altura = obtenerNumeroValido("Altura (cm):");
        personas.add(new Persona(nombre, peso, altura));
        JOptionPane.showMessageDialog(null, "Persona agregada correctamente.");
    }

    private static void actualizarPeso(List<Persona> personas) {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.");
            return;
        }

        String nombreActualizar = JOptionPane.showInputDialog("Ingrese el nombre de la persona a actualizar:");
        boolean encontrado = false;

        for (Persona persona : personas) {
            if (persona.nombre.equalsIgnoreCase(nombreActualizar)) {
                double nuevoPeso = obtenerNumeroValido("Nuevo Peso (kg):");
                persona.peso = nuevoPeso;
                JOptionPane.showMessageDialog(null, "Peso actualizado correctamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Persona no encontrada.");
        }
    }

    private static void mostrarLista(List<Persona> personas) {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.");
            return;
        }

        StringBuilder listaPersonas = new StringBuilder("Lista de Personas:\n");
        for (Persona persona : personas) {
            listaPersonas.append(persona.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaPersonas.toString());
    }

    private static void calcularIMS(List<Persona> personas) {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personas registradas.");
            return;
        }

        StringBuilder imsResultados = new StringBuilder("IMS de Personas:\n");
        for (Persona persona : personas) {
            double ims = persona.calcularIMC();
            String estadoNutricional = persona.obtenerEstadoNutricional();
            imsResultados.append(persona.nombre).append(": IMS = ").append(ims).append(", Estado: ").append(estadoNutricional).append("\n");
        }
        JOptionPane.showMessageDialog(null, imsResultados.toString());
    }

    private static double obtenerNumeroValido(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(mensaje);
            try {
                double numero = Double.parseDouble(input);
                return numero;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido.");
            }
        }
    }
}