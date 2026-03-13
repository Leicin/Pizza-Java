import java.util.Arrays;
import java.util.Scanner;

// 1. Modelo de Datos
class Pizza {
    private String nombre;
    private String[] ingredientes; // Arreglo fijo de 3

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "Pizza: " + nombre + " | Ingredientes: " + Arrays.toString(ingredientes);
    }
}

// 2. Nodo para la Lista Ligada
class Nodo {
    Pizza pizza;
    Nodo siguiente;

    public Nodo(Pizza pizza) {
        this.pizza = pizza;
        this.siguiente = null;
    }
}

