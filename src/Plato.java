import java.text.DecimalFormat;
import java.util.List;

public class Plato {

    public String nombre_plato;
    public String tipo_plato;
    public double precio_plato;

    public Plato (String nombre,String tipo, double precio){

        this.nombre_plato=nombre;
        this.tipo_plato=tipo;
        this.precio_plato=precio;

    }

    public static double ObtenerPrecio (String nombre, List<Plato> platos){

        double precio =0;

        for(int i=0; i<platos.size(); i++){

            if(nombre!=null){
                if(nombre.equalsIgnoreCase(platos.get(i).nombre_plato)){
                    precio=platos.get(i).precio_plato;
                }
            }

        }

        return precio;
    }

    public static String ListarPlatos(List <Plato> lista_platos){
        String platos="";

        for(int i=0; i<lista_platos.size(); i++){
            DecimalFormat formato = new DecimalFormat("#,###.##");
            String valorFormateado = formato.format(lista_platos.get(i).precio_plato);
            platos= platos+lista_platos.get(i).nombre_plato+" ; "+lista_platos.get(i).tipo_plato+" ; $"+valorFormateado+"\n";
        }

        return platos;
    }

}
