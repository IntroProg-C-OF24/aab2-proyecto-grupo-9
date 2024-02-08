import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Cinemas {
     public static void main(String[] args) {
        HashMap<String, ArrayList<String>> diasFuncion = obtenerDiasDeFuncion("pelis.csv");
        solicitarOpciones(diasFuncion);
    }

    public static HashMap<String, ArrayList<String>> obtenerDiasDeFuncion(String nombreArchivo) {
        HashMap<String, ArrayList<String>> diasFuncion = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            scanner.nextLine();
            System.out.println("Contenido del archivo:");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                
                for (String dato : datos) {
                    System.out.printf("%-25s", dato);
                }
                System.out.println();

                if (datos.length >= 4) {
                    String pelicula = datos[0];
                    String dias = datos[3];
                    ArrayList<String> listaDias = new ArrayList<>();

                    if (dias.contains(",")) {
                        String[] diasArray = dias.split(",");
                        for (String dia : diasArray) {
                            listaDias.add(dia.trim());
                        }
                    } else {
                        listaDias.add(dias.trim());
                    }

                    diasFuncion.put(pelicula, listaDias);
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return diasFuncion;
    }

    public static void solicitarOpciones(HashMap<String, ArrayList<String>> diasFuncion) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> snacksElegidos = new ArrayList<>();
        double precioSnacksTotal = 0;


        System.out.print("\nPor favor, elige una película:");
        String peliculaElegida = scanner.nextLine();
        

        if (diasFuncion.containsKey(peliculaElegida)) {
            ArrayList<String> diasDisponibles = diasFuncion.get(peliculaElegida);

            System.out.println("Días de función para " + peliculaElegida + ": " + diasDisponibles);

            System.out.print("Elige un día de función:");
            String diaFuncion = scanner.nextLine();

            System.out.print("Elige un horario:");
            String horarioElegido = scanner.nextLine();

            System.out.print("Elige una sala (Sala 4k(A) o `Sala Laser(B):");
            String salaElegida = scanner.nextLine();

            System.out.print("Elige el número de boletos:");
            int numBoletos = scanner.nextInt();
            double precioBoletos = calcularPrecio(salaElegida, numBoletos, diaFuncion);
            
        System.out.print("\n¿Desea adquirir Snacks? (Si/No): ");
        scanner.nextLine(); 
        String respuestaSnacks = scanner.nextLine();

        if (respuestaSnacks.equalsIgnoreCase("Si")) {
            do {
                System.out.println("\nMenú de Snacks:");
                System.out.println("1. Combo1 (Canguil mediano,Gaseosa,Dulce) - $6.00");
                System.out.println("2. Combo2 (Nachos, Gaseosa mediana, Dulce) - $6.50");
                System.out.println("3. Combo3 (Hotdog,Gaseosa mediana,Dulce) - $6.50");
                System.out.println("4. Combo4 (2 Hotdog, 2 Gaseosas medianas , Canguil mediano - $11.50");
                System.out.println("5. Combo5 (2 Nachos, 2 Gaseosas medianas , Canguil mediano - $12.00");
                System.out.println("6. Comb6 (2 Granizados, 2 Nachos , Queso extra, Gomitas - $14.00");
                System.out.println("7. Combo7 (3 Canguiles peq, 3 Gaseosas grandes , 2 Nachos, Hotdog - $21.00");
                System.out.println("8. Gaseosa Grande - $3.50");
                System.out.println("9. Gaseosa Mediana - $2.50");
                System.out.println("10. Canguil Grande - $3.50");
                System.out.println("11. Canguil Mediano - $3.00");
                System.out.println("12. Canguil Peq - $2.00");
                System.out.println("13. Nachos - $3.50");
                System.out.println("14. Hotdog - $3.00");
                System.out.println("15. Dulce - $1.25");
                System.out.println("16. Forestea - $1.50");
                System.out.println("17. Agua - $1.00");
                System.out.println("18. Milkshake - $3.50");
                System.out.println("19. Granizado - $2.50");
                System.out.println("20. Extra Queso - $2.00");
                System.out.print("Elige un número de snack: ");

                int opcionSnack = scanner.nextInt();

                switch (opcionSnack) {
                    case 1:
                        snacksElegidos.add("Combo 1");
                        precioSnacksTotal += 6.0;
                        break;
                    case 2:
                        snacksElegidos.add("Combo 2");
                        precioSnacksTotal += 6.5;
                        break;
                    case 3:
                        snacksElegidos.add("Combo 3");
                        precioSnacksTotal += 6.5;
                        break;
                    case 4:
                        snacksElegidos.add("Combo 4");
                        precioSnacksTotal += 11.5;
                        break;
                    case 5:    
                        snacksElegidos.add("Combo 5");
                        precioSnacksTotal += 12.0;
                        break;
                    case 6:
                        snacksElegidos.add("Combo 6");
                        precioSnacksTotal += 14.0;
                        break;
                    case 7:
                        snacksElegidos.add("Combo 7");
                        precioSnacksTotal += 21.0;
                        break;
                    case 8:
                        snacksElegidos.add("Gaseosa Grande");
                        precioSnacksTotal += 3.5;
                        break;
                    case 9:    
                        snacksElegidos.add("Gaseosa Mediana");
                        precioSnacksTotal += 2.5;
                        break;
                    case 10:
                        snacksElegidos.add("Canguil Grande");
                        precioSnacksTotal += 3.5;
                        break;
                    case 11:
                        snacksElegidos.add("Canguil Mediano");
                        precioSnacksTotal += 3.0;
                        break;
                    case 12:
                        snacksElegidos.add("Canguil Peq");
                        precioSnacksTotal += 2.0;
                        break;
                    case 13:    
                        snacksElegidos.add("Nachos");
                        precioSnacksTotal += 3.5;
                        break;
                    case 14:
                        snacksElegidos.add("Hotdog");
                        precioSnacksTotal += 3.0;
                        break;
                    case 15:
                        snacksElegidos.add("Dulce");
                        precioSnacksTotal += 1.25;
                        break;
                    case 16:
                        snacksElegidos.add("Forestea");
                        precioSnacksTotal += 1.5;
                        break;
                    case 17:    
                        snacksElegidos.add("Agua");
                        precioSnacksTotal += 1.0;
                        break;
                    case 18:
                        snacksElegidos.add("Milkshake");
                        precioSnacksTotal += 3.5;
                        break;
                    case 19:
                        snacksElegidos.add("Granizado");
                        precioSnacksTotal += 2.5;
                        break;
                    case 20:
                        snacksElegidos.add("Extra Queso");
                        precioSnacksTotal += 2.0;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige un número válido.");
                        break;
                }

                System.out.print("\n¿Desea escoger otro snack? (Si/No): ");
                scanner.nextLine();
            } while (scanner.nextLine().equalsIgnoreCase("Si"));
            double precioTotal = precioBoletos + precioSnacksTotal;
            System.out.println("\nResumen de Opciones:");
            System.out.printf("Película: %s\nDía de Función: %s\nHorario: %s\nSala: %s\nNúmero de Boletos: %d\nPrecio de Boletos: %.2f\n",
                                peliculaElegida, diaFuncion, horarioElegido, salaElegida, numBoletos, precioBoletos);

            System.out.println("\nSnacks Elegidos:");
            for (String snack : snacksElegidos) {
                System.out.println("- " + snack);
            }


            System.out.printf("\nPrecio Total (Boletos + Snacks): %.2f\n", precioTotal);
        } else {
            System.out.println("\nResumen de Opciones:");
            System.out.printf("Película: %s\nDía de Función: %s\nHorario: %s\nSala: %s\nNúmero de Boletos: %d\nPrecio de Boletos: %.2f\n",
                                peliculaElegida, diaFuncion, horarioElegido, salaElegida, numBoletos, precioBoletos);
        }

        System.out.println("Por favor ingrese sus datos para generar la factura");
        System.out.print("Por favor, ingrese su nombre: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Por favor, ingrese su CI o RUC : ");
        String ciRUC = scanner.nextLine();
        System.out.print("Por favor, ingrese su dirección : ");
        String direccion = scanner.nextLine();
        System.out.print("Por favor, ingrese su telefono : ");
        String telefono = scanner.nextLine();
        System.out.print("Por favor, ingrese su correo : ");
        String correo = scanner.nextLine();
        
        generarFactura(nombreUsuario, ciRUC, direccion,telefono,correo,peliculaElegida, diaFuncion, horarioElegido, salaElegida, numBoletos, precioBoletos, snacksElegidos, precioSnacksTotal, calcularPrecioConIVA(precioBoletos + precioSnacksTotal));


        } else {
            System.out.println("La película seleccionada no tiene días de función especificados.");
        }
    }

    public static double calcularPrecio(String sala, int numBoletos, String diaFuncion) {
        double precioBase = 0;

        if (sala.equalsIgnoreCase("A")) {
            precioBase = 5.0;
        } else if (sala.equalsIgnoreCase("B")) {
            precioBase = 7.0;
        }

        if (diaFuncion.equalsIgnoreCase("Martes") || diaFuncion.equalsIgnoreCase("Jueves")) {
            int boletosPagados = numBoletos / 2;
            return precioBase * boletosPagados;
        } else {
            return precioBase * numBoletos;
        }
    }
    public static double calcularPrecioConIVA(double precio) {
        return precio * 1.14;
    }
    public static void generarFactura(String nombre, String ruc, String direccion, String telefono, String correo, String pelicula, String diaFuncion, String horario, String sala, int numBoletos, double precioBoletos,
            ArrayList<String> snacks, double precioSnacks, double precioTotalConIVA) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("factura.csv"))) {
            writer.println("Nombre;RUC;Direccion;Telefono;Correo;Pelicula;DiaFuncion;Horario;Sala;NumBoletos;PrecioBoletos;Snacks;PrecioTotal+IVA");
            writer.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s;%d;%.2f;", nombre, ruc, direccion, telefono, correo, pelicula, diaFuncion, horario, sala, numBoletos, precioBoletos);

            StringBuilder snacksString = new StringBuilder();
            for (String snack : snacks) {
                snacksString.append(snack).append(",");
            }
            if (snacksString.length() > 0) {
                snacksString.deleteCharAt(snacksString.length() - 1);
            }

            writer.printf("%s;%.2f;%.2f\n", snacksString.toString(), precioSnacks, precioTotalConIVA);

            System.out.println("La factura ha sido creada exitosamente en el archivo 'factura.csv'");
        } catch (IOException e) {
            System.out.println("Error al escribir la factura en el archivo: " + e.getMessage());
        }
    }
    
}
/*Contenido del archivo:
-------------------------------------------------------------------------------------------------------------------
Beekeeper                18:00-19:00              Sala A,Sala B            Lunes,Miercoles          
Interstellar             13:00-20:00              Sala A,Sala B            Martes,Sabado            
Punto de quiebre         15:00-18:00              Sala A,Sala B            Viernes, Jueves          
Club de la pelea         14:00-19:00              Sala A,Sala B            Sabado,Miercoles         
Wonka                    19:00-21:00              Sala A,Sala B            Lunes,Viernes            
Sociedad de las nieves   17:00-19:00              Sala A,Sala B            Jueves, Sabado           
Titanic                  14:00-22:00              Sala A,Sala B            Sabado,Lunes             
Contratiempo             15:00-20:00              Sala A,Sala B            Miercoles,Sabado         
Jack in the box          18:00-20:00              Sala A,Sala B            Viernes,Lunes            
Boyka                    19:00-20:00              Sala A,Sala B            Martes,Viernes           
-------------------------------------------------------------------------------------------------------------------

Por favor, elige una película:Boyka
Días de función para Boyka: [Martes, Viernes]
Elige un día de función:Martes
Elige un horario:19:00
Elige una sala (Sala 4k(A) o `Sala Laser(B):A
Elige el número de boletos:2

¿Desea adquirir Snacks? (Si/No): Si

Menú de Snacks:
1. Combo1 (Canguil mediano,Gaseosa,Dulce) - $6.00
2. Combo2 (Nachos, Gaseosa mediana, Dulce) - $6.50
3. Combo3 (Hotdog,Gaseosa mediana,Dulce) - $6.50
4. Combo4 (2 Hotdog, 2 Gaseosas medianas , Canguil mediano - $11.50
5. Combo5 (2 Nachos, 2 Gaseosas medianas , Canguil mediano - $12.00
6. Comb6 (2 Granizados, 2 Nachos , Queso extra, Gomitas - $14.00
7. Combo7 (3 Canguiles peq, 3 Gaseosas grandes , 2 Nachos, Hotdog - $21.00
8. Gaseosa Grande - $3.50
9. Gaseosa Mediana - $2.50
10. Canguil Grande - $3.50
11. Canguil Mediano - $3.00
12. Canguil Peq - $2.00
13. Nachos - $3.50
14. Hotdog - $3.00
15. Dulce - $1.25
16. Forestea - $1.50
17. Agua - $1.00
18. Milkshake - $3.50
19. Granizado - $2.50
20. Extra Queso - $2.00
Elige un número de snack: 1

¿Desea escoger otro snack? (Si/No): Si

Menú de Snacks:
1. Combo1 (Canguil mediano,Gaseosa,Dulce) - $6.00
2. Combo2 (Nachos, Gaseosa mediana, Dulce) - $6.50
3. Combo3 (Hotdog,Gaseosa mediana,Dulce) - $6.50
4. Combo4 (2 Hotdog, 2 Gaseosas medianas , Canguil mediano - $11.50
5. Combo5 (2 Nachos, 2 Gaseosas medianas , Canguil mediano - $12.00
6. Comb6 (2 Granizados, 2 Nachos , Queso extra, Gomitas - $14.00
7. Combo7 (3 Canguiles peq, 3 Gaseosas grandes , 2 Nachos, Hotdog - $21.00
8. Gaseosa Grande - $3.50
9. Gaseosa Mediana - $2.50
10. Canguil Grande - $3.50
11. Canguil Mediano - $3.00
12. Canguil Peq - $2.00
13. Nachos - $3.50
14. Hotdog - $3.00
15. Dulce - $1.25
16. Forestea - $1.50
17. Agua - $1.00
18. Milkshake - $3.50
19. Granizado - $2.50
20. Extra Queso - $2.00
Elige un número de snack: 16

¿Desea escoger otro snack? (Si/No): No

Resumen de Opciones:
Película: Boyka
Día de Función: Martes
Horario: 19:00
Sala: A
Número de Boletos: 2
Precio de Boletos: 5,00

Snacks Elegidos:
- Combo 1
- Forestea

Precio Total (Boletos + Snacks): 12,50
Por favor ingrese sus datos para generar la factura
Por favor, ingrese su nombre: Ana
Por favor, ingrese su CI o RUC : 1115645615
Por favor, ingrese su dirección : Pitas
Por favor, ingrese su telefono : 0952662453
Por favor, ingrese su correo : ana@gmail.com
La factura ha sido creada exitosamente en el archivo 'factura.csv'*/
