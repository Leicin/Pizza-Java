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


public class PizzaTrack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PilaManual pilaUndo = new PilaManual();
        PilaManual pilaRedo = new PilaManual();
        
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- PIZZA-TRACK (Gestión de Pedidos) ---");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Ver pedido actual (Peek)");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la pizza: ");
                    String nombre = sc.nextLine();
                    String[] ings = new String[3];
                    for(int i=0; i<3; i++) {
                        System.out.print("Ingrediente " + (i+1) + ": ");
                        ings[i] = sc.nextLine();
                    }
                    pilaUndo.push(new Pizza(nombre, ings));

                    // Al escribir nuevo, se limpia la posibilidad de rehacer
                    while(!pilaRedo.isEmpty()) pilaRedo.pop(); 
                    break;

                case 2:
                    if (!pilaUndo.isEmpty()) {
                        //Elimino de la pila principal y la guardo en la secundaria por medio del push
                        Pizza p = pilaUndo.pop();
                        pilaRedo.push(p);
                        System.out.println("Deshecho: " + p.getNombre());
                    } else System.out.println("Nada que deshacer.");
                    break;

                case 3:
                    //Si la fila es diferente de vacio no tendria logica consultar algo que no existe, por lo tanto se valida que no este vacia para luego hacer el proceso de rehacer
                    if (!pilaRedo.isEmpty()) {
                        //Aqui hago la inversa del proceso de deshacer, es decir, elimino de la pila secundaria y lo guardo en la principal por medio del push
                        Pizza p = pilaRedo.pop();
                        pilaUndo.push(p);
                        System.out.println("Rehecho: " + p.getNombre());
                    } else System.out.println("Nada que rehacer.");
                    break;

                case 4:

                //Peek: Muestra el pedido actual sin modificar las pilas lo que esta registraddo
                    Pizza actual = pilaUndo.peek();
                    System.out.println(actual != null ? "En preparación: " + actual : "No hay pedidos.");
                    break;
            }
        }
    }
}

