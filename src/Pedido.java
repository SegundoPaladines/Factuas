import java.text.DecimalFormat;

public class Pedido{

    public String nombre_plato;
    public String tipo_plato;
    public double precio_plato;
    public int cantidad;

    public Pedido (String nombre,String tipo, double precio, int cantidad){

        this.nombre_plato=nombre;
        this.tipo_plato=tipo;
        this.precio_plato=precio;
        this.cantidad=cantidad;

    }

    public static String HacerPedido(Plato plato1, int cantidad1, Plato plato2, int cantidad2, Plato plato3, int cantidad3, Plato plato4,
            int cantidad4, boolean clientefiel) {
                String factura ="";
                double subt=0;

                DecimalFormat formato = new DecimalFormat("#,###.##");
                if((cantidad1>0)&&(plato1!=null)){
                    factura=factura+"| "+plato1.nombre_plato+" | "+plato1.tipo_plato+" | Precio P/U: $"+formato.format(plato1.precio_plato)+" | Cantidad: "+cantidad1+" | Precio Entradas: $"+formato.format(plato1.precio_plato*cantidad1)+" | \n";
                    subt=subt+plato1.precio_plato*cantidad1;
                }
                if((cantidad2>0)&&(plato2!=null)){
                    factura=factura+"| "+plato2.nombre_plato+" | "+plato2.tipo_plato+" | Precio P/U: $"+formato.format(plato2.precio_plato)+" | Cantidad: "+cantidad2+" | Precio Platos Fuertes: $"+formato.format(plato2.precio_plato*cantidad2)+" | \n";
                    subt=subt+plato2.precio_plato*cantidad2;
                }
                if((cantidad3>0)&&(plato3!=null)){
                    factura=factura+"| "+plato3.nombre_plato+" | "+plato3.tipo_plato+" | Precio P/U: $"+formato.format(plato3.precio_plato)+" | Cantidad: "+cantidad3+" | Precio Postres: $"+formato.format(plato3.precio_plato*cantidad3)+" | \n";
                    subt=subt+plato3.precio_plato*cantidad3;
                }
                if((cantidad4>0)&&(plato4!=null)){
                    factura=factura+"| "+plato4.nombre_plato+" | "+plato4.tipo_plato+" | Precio P/U: $"+formato.format(plato4.precio_plato)+" | Cantidad: "+cantidad4+" | Precio Bebidas: $"+formato.format(plato4.precio_plato*cantidad4)+" | \n";
                    subt=subt+plato4.precio_plato*cantidad4;
                }

                    factura=factura+"                     Sobtotal a Pagar: $"+formato.format(subt)+"\n";
                if(clientefiel){
                    factura=factura+"                     Cliente Fiel : \n";
                    factura=factura+"                     Descuento sobre el Sobtotal a Pagar 12% : $"+formato.format(subt*0.12)+"\n";
                    factura=factura+"                     Total a Pagar Con Descuento Aplicado : $"+formato.format((subt-(subt*0.12)))+"\n";
                }else{
                    factura=factura+"                     No Es Cliente Fiel : \n";
                    factura=factura+"                     Descuento sobre el Sobtotal a Pagar 0% : $"+0+"\n";
                    factura=factura+"                     Total a Pagar Con Descuento Aplicado : $"+formato.format(subt)+"\n";
                }
                return factura;

    }
}
