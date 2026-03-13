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

// 3. Implementación de Pila Manual
class PilaManual {
    private Nodo tope;

    public void push(Pizza pizza) {
        Nodo nuevo = new Nodo(pizza);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    //Deshacer 
    public Pizza pop() {
        if (isEmpty()) return null;
        Pizza pizza = tope.pizza;
        tope = tope.siguiente;
        return pizza;
    }

    public Pizza peek() {
        return (isEmpty()) ? null : tope.pizza;
    }

    public boolean isEmpty() {
        return tope == null;
    }
}

