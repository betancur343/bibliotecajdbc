/*Practica #2: JDBC

*/
package bibliotecajdbc;
import java.sql.*;
import java.util.Scanner;

public class Bibliotecajdbc {
        String name, autor, area; String sel_area, sel_act;               
        int añoPubli, code, quanti, ind_default, total_pres;        //Banderas
        Scanner input = new Scanner(System.in);         
void area(){
    do{ System.out.print("| Ingrese el área respectiva: 1.Química |2.Física |3.Tecnología |4.Cálculo |5.Programación |\n| Selección: ");
        sel_area=input.nextLine();
        switch(sel_area){
            case "1": area= "Quimica";
                        ind_default=0;
                        break;
            case "2": area= "Física";
                        ind_default=0;
                        break;                    
            case "3": area= "Tecnología";
                        ind_default=0;
                        break;
            case "4": area= "Cálculo";                    
                        ind_default=0;
                        break;
            case "5": area= "Programación";                    
                        ind_default=0;
                        break;
            default:    ind_default=1; 
                        System.out.print("Advertencia!\n| Opción inválida! |\n");
        }
    }while(ind_default==1);
} 
void registro (){
    System.out.println("\nNuevo Registro");
    System.out.print("| Ingrese nombre: ");
    name=input.nextLine();
    System.out.print("| Ingrese autor: ");
    autor=input.nextLine();
    System.out.print("| Ingrese año de publicación: ");
    añoPubli=input.nextInt();
    System.out.print("| Ingrese código: ");
    code=input.nextInt();  
    System.out.print("| Ingrese cantidad: ");
    quanti=input.nextInt(); 
    input.nextLine();
    area();    
}
void mostrarLibro(){        
        System.out.println("\nFromPC\n| Nombre: "+name+"| Autor: "+autor+"| Publicado en: "+añoPubli+"| Código: "+code+"| Cantidad: "+quanti+"| Área: "+area+" |");          
    }    

    public static void main(String[] args) {            
            String sel, sel3, sel_act, ind_name, ind_ced, ind_quanti;
            int oneP, ind_equal, ind_default;
            Scanner input = new Scanner(System.in);
            Bibliotecajdbc reg = new Bibliotecajdbc ();
        String user="root"; //xampp
        String password="991";  //xampp
//        String user2="betancur343";//db4free.net
//        String password2="991991";//db4free.net  
        String nombre, apellido, telefono;
        try {
            System.out.println("Aviso!\n| Intentando conectar a la base de datos... |");        
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/contactos_jcbj", user, password);
//            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/contactos_jcbj", user2, password2);            
            System.out.println("Aviso!\n| Conexión exitosa. |");            
            Statement estado = con.createStatement();       //estado -> busqueda, eliminar,...               
            ResultSet resultado;
            resultado = estado.executeQuery("SELECT * FROM `Libros_Registrados` ");
            System.out.println("\nRegistro de Libros - BD");
            while(resultado.next()){
               System.out.println("| "+resultado.getString("Nombre")+"\t| "+resultado.getString("Autor")+"\t| "+resultado.getString("AñoPublicacion")+"\t| "+resultado.getString("Codigo")+"\t| "+resultado.getString("Cantidad")+"\t| "+resultado.getString("Area")+" |");               
            }
            
            do{
            System.out.println("\nMenú Principal");
            System.out.print("| 1.Ingresar libro\t |2.Actualizar libro\t\t|3.Eliminar libro\t\t|4.Buscar libro |\n| "
                    + "5.Préstamo de libro\t |6.Devolución libros prestados |7. Mostrar libros prestados\t|8. Salir |\n| Selección: ");            
            sel=input.nextLine();            
            switch (sel){
            // <editor-fold desc="case1_nuevo registro">    
            case "1":   reg.registro(); 
//                        reg.mostrarLibro();
                        estado.executeUpdate("INSERT INTO `Libros_Registrados` VALUES ('"+reg.name+"', '"+reg.autor+"', '"+reg.añoPubli+"', '"+reg.code+"', '"+reg.quanti+"', '"+reg.area+"')");           
                        System.out.println("\nAviso!\n| Registrado con éxito. |");   
//                        resultado = estado.executeQuery("SELECT * FROM `Libros_Registrados` ");
//                        System.out.println("\nRegistro de Libros - BD");
//                        while(resultado.next()){
//                           System.out.println("| "+resultado.getString("Nombre")+"\t| "+resultado.getString("Autor")+"\t| "+resultado.getString("AñoPublicacion")+"\t| "+resultado.getString("Codigo")+"\t| "+resultado.getString("Codigo")+"\t| "+resultado.getString("Codigo")+" |");               
//                        }
                        break;
            // </editor-fold> 
            // <editor-fold desc="case2_actualizacion">  
            case "2":   System.out.println("\nActualización ");
                        System.out.print("| Ingrese nombre del libro: ");
                        ind_name=input.nextLine();                    
                        resultado = estado.executeQuery("SELECT * FROM `Libros_Registrados` WHERE `Nombre` LIKE '"+ind_name+"'"); // Concatenar...para buscar con "Ingresar nombre"...  
//                        System.out.println("\nFromDB");
                        if(resultado.next()){
                            System.out.println("Aviso! Búsqueda Exitosa. |\n| "+resultado.getString("Nombre")+"|"+resultado.getString("Autor")+"| "+resultado.getString("AñoPublicacion")+"| "+resultado.getString("Codigo")+"|"+resultado.getString("Cantidad")+"|"+resultado.getString("Area")+" |");                          
                    // <editor-fold desc="¿Que desea actualizar?">                         
                            do{ ind_default=0;
                                System.out.print("\n¿Qué desea actualizar?\n| 1.Nombre |2.Autor |3.Publicación |4.Código |5.Cantidad |6.Área |\nSelección: ");          
                                sel_act=input.nextLine();         
                                switch(sel_act){
                                case "1":   System.out.print("Ingrese nombre: ");
                                            reg.name=input.nextLine();
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `Nombre` = '"+reg.name+"' WHERE `Libros_Registrados`.`Nombre` = '"+resultado.getString("Nombre")+"';"); 
                                            ind_default=0;
                                            break;
                                case "2":   System.out.print("Ingrese autor: ");
                                            reg.autor=input.nextLine();
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `Autor` = '"+reg.autor+"' WHERE `Libros_Registrados`.`Autor` = '"+resultado.getString("Autor")+"';"); 
                                            ind_default=0;
                                            break;
                                case "3":   System.out.print("Ingrese año de publicación: ");
                                            reg.añoPubli=input.nextInt();
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `AñoPublicacion` = '"+reg.añoPubli+"' WHERE `Libros_Registrados`.`AñoPublicacion` = '"+resultado.getString("AñoPublicacion")+"';"); 
                                            ind_default=0;
                                            break;
                                case "4":   System.out.print("Ingrese código: ");
                                            reg.code=input.nextInt();
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `Codigo` = '"+reg.code+"' WHERE `Libros_Registrados`.`Codigo` = '"+resultado.getString("Codigo")+"';");  
                                            ind_default=0;
                                        break;
                                case "5":   System.out.print("Ingrese cantidad: ");
                                            reg.quanti=input.nextInt();
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `Cantidad` = '"+reg.quanti+"' WHERE `Libros_Registrados`.`Cantidad` = '"+resultado.getString("Cantidad")+"';");  
                                            ind_default=0;
                                            break;
                                case "6":   System.out.print("\n");
                                            reg.area();                                            
                                            estado.executeUpdate("UPDATE `contactos_jcbj`.`Libros_Registrados` SET `Area` = '"+reg.area+"' WHERE `Libros_Registrados`.`Area` = '"+resultado.getString("Area")+"';"); 
                                            ind_default=0;
                                            break;    
                                default:    System.out.println("\nAdvertencia!\n| ¡ERROR! Actualización NO válida. |");                        
                                            ind_default=1;
                                            break;   
                                }
                            }while(ind_default==1);
                    // </editor-fold>                           
                            System.out.println("\n¡Aviso!\n| Actualización exitosa. |");                    
                        }else {
                           System.out.println("\n¡Advertencia!\n| ¡ERROR! El registro no existe. |");                           
                        } 
                       
                        break;
            // </editor-fold> 
            // <editor-fold desc="case3_limpieza">  
            case "3":   System.out.println("\nLimpieza de Registros");         //Hacer prueba con 3 libros
                        System.out.print("| Ingrese nombre del libro: ");
                        ind_name = input.nextLine();
                        estado.executeUpdate("DELETE FROM `contactos_jcbj`.`libros_registrados` WHERE `libros_registrados`.`Nombre` = \'"+ind_name+"\'");
                        resultado = estado.executeQuery("SELECT * FROM `Libros_Registrados`");
                        System.out.println("\nAviso!\n| Borrado con éxito. |");
//                        System.out.println("\nFromDB");
//                        while(resultado.next()){
//                           System.out.println("| "+resultado.getString("Nombre")+"\t| "+resultado.getString("Autor")+"\t| "+resultado.getString("AñoPublicacion")+"\t| "+resultado.getString("Codigo")+"\t| "+resultado.getString("Codigo")+"\t| "+resultado.getString("Codigo")+" |");               
//                        }
//                        break;
             // </editor-fold>
            // <editor-fold desc="case4 busqueda">     
            case "4":   System.out.println("\nBúsqueda ");
                        System.out.print("| Ingrese nombre del libro: ");
                        ind_name=input.nextLine();
                        resultado = estado.executeQuery("SELECT * FROM `Libros_Registrados` WHERE `Nombre` LIKE '"+ind_name+"'"); // Concatenar...para buscar con "Ingresar nombre"...  
//                        System.out.println("\nFromDB");
                        if(resultado.next()){
                            System.out.println("\nAviso!\n| Búsqueda Exitosa. |\n| "+resultado.getString("Nombre")+"|"+resultado.getString("Autor")+"| "+resultado.getString("AñoPublicacion")+"| "+resultado.getString("Codigo")+"|"+resultado.getString("Cantidad")+"|"+resultado.getString("Area")+" |");                          
                        }else {
                           System.out.println("\nAdvertencia!\n| ¡ERROR! El registro no existe. |");                           
                        } 
                        break;
            
             // </editor-fold>   
            // <editor-fold desc="case5 prestamo">     
            case "5":   System.out.println("\nPréstamo ");
                        System.out.print("| Ingrese su cédula: ");
                        ind_ced=input.nextLine();
                        System.out.println("\nBúsqueda ");
                        System.out.print("| Ingrese nombre del libro: ");
                        ind_name=input.nextLine();
                        resultado = estado.executeQuery("SELECT * FROM `libros_registrados` WHERE `Nombre` LIKE '"+ind_name+"'"); // Concatenar...para buscar con "Ingresar nombre"...  
                        
                        if(resultado.next()){
                            
                            ind_quanti=resultado.getString("Cantidad"); int aInt = Integer.parseInt(ind_quanti);
                            ind_quanti= Integer.toString(aInt-1);
                            System.out.println("->"+ind_quanti);
                            estado.executeUpdate("INSERT INTO `libros_prestados` VALUES ('"+ind_ced+"','"+resultado.getString("Nombre")+"', '"+resultado.getString("Autor")+"', '1')");           
                            System.out.println("\nAviso!\n| Registro de préstamo exitoso. |");
                            
                        }else {
                           System.out.println("\nAdvertencia!\n| ¡ERROR! El registro no existe. |");                           
                        } 
                        
                        break;
            
             // </editor-fold> 
            // <editor-fold desc="case6_Devoluciones">  
            case "6":   System.out.println("\nDevolución");         //Hacer prueba con 3 libros
                        System.out.print("| Ingrese nombre del libro: ");
                        ind_name = input.nextLine();
                        estado.executeUpdate("DELETE FROM `contactos_jcbj`.`libros_prestados` WHERE `libros_prestados`.`Nombre` = \'"+ind_name+"\'");
                        System.out.println("\nAviso!\n| Borrado con éxito. |");
                        break;
             // </editor-fold>  
            // <editor-fold desc="case7 Libros prestados">  
            case "7":   resultado = estado.executeQuery("SELECT * FROM `libros_prestados` ");
                        System.out.println("\nRegistro de Libros Prestados - BD");
                        while(resultado.next()){
                           System.out.println("| "+resultado.getString("Cc")+"\t\t| "+resultado.getString("Nombre")+"\t\t| "+resultado.getString("Autor")+"\t\t| "+resultado.getString("Cantidad")+" |");               
                        }            
                        break;
             // </editor-fold>    
            default:    System.out.println("\nAdvertencia!\n| Opción inválida! |");
                        break;    
            }             
            System.out.print("\n\n¿Qué desea hacer?\n| 1.Regresar al Menú Principal | 2.Salir |\n| Selección: ");
            sel3=input.nextLine(); 
            ind_equal=sel3.compareTo("1");
            }while(ind_equal==0);            
            
            
            
        } catch (SQLException ex) {
             System.out.println("\nAdvertencia!\n| Error de mysql.");
        } catch (Exception e) {
             System.out.println("\nAdvertencia!\n| Error :"+e.getMessage());
        }   
    }
    
}
