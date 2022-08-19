import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


public class MaquinaAlimentos {
    static Scanner input = new Scanner(System.in);
    static int deuda = 0;
    private Function funcion;

    public static void main(String[] args) {


        int[]opciones = {270, 340, 390};
        int[]monedas = {10, 50, 100};
        List<String>dinero = List.of("10", "50", "100");
        List<String>menu = List.of("Alimento A", "Alimento B", "Alimento C");
        boolean still;

        do{ //compramos y aumentamos deuda
            System.out.println("Menu");
            menu.forEach(opcion -> System.out.println(menu.indexOf(opcion)+".-\t"+opcion+"\t"+opciones[menu.indexOf(opcion)]+"$"));
            comprarProducto(opciones, establecerControl(solicitarValor("Seleccione una opcion disponible:\t"),menu.size(), 0));
            still = (solicitarValor("1.-Continuar \t0.-Pagar")==1);
        }while(still);

        dinero.forEach(moneda -> System.out.println(dinero.indexOf(moneda)+".-\t$"+moneda));
        do{ //Pagamos y reducimos deuda hasta llegar a 0
            pagarProducto(monedas ,establecerControl(solicitarValor("seleccione una moneda, restante\t"+deuda+"$\t"),dinero.size(), 0));
        }while(deuda>=0);


        ArrayList<String> dineroCajero = new ArrayList<String>(dinero);
        Collections.reverse(dineroCajero);

        int devolver = Math.abs(deuda);
        if(devolver>0){
            System.out.println("Se le debe: "+devolver);
            devolverCambio(devolver,dineroCajero);
        }

        System.out.println("Gracias por comprar");

    }
    public static void pagarProducto(int[]monedas, int seleccion){
        //Metodo control de validez
        deuda-=monedas[seleccion];
    }

    public static void comprarProducto(int[]opciones, int seleccion){
        //Agregar un metodo para controlar validez
        deuda+=opciones[seleccion];
    }

    public static int devolverCambio(int i, ArrayList<String>opciones){
        int resultado = i;

        for (String valor: opciones) {
            int resto = Integer.parseInt(valor);
            if(resultado>=resto){
                resultado-=resto;
                System.out.println("cambio con moneda de: "+resto);
                break;
            }
        }
        return (i<=0)?resultado:devolverCambio(resultado,opciones);
    }

    public static int solicitarValor(String text){ //Metodo para introducir numeros en funcion a una orden
        System.out.print(text);
        return input.nextInt();
    }
    /*
    public static <T> T  establecerControl(Function<T,T> funcion, T valor, T valor2, T valor3){
        T apply = funcion.apply(valor, valor2, valor3);
        return apply;
    }
    */
    public static int establecerControl(int in, int max, int min){ //Este metodo se usa para evitar que el usuario coloque valores no validos en el menu
        int output = in;
        while(output>max || output<min){
            output = solicitarValor("Error en los datos introducidos, por favor introduzca un valor entre "+max+" y "+min);
        }
        return output;
    }

}
