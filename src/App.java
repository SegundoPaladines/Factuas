import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class App extends Application{

    public List <Plato> platos= new ArrayList<>();
    public TabPane tabRestaurante = new TabPane();
    public JTable tabla;

    public  void start(Stage primaryStage){
        
         //tab formulario
         Tab tab_crear_plato = new Tab("Creacion Plato");
         tab_crear_plato.setContent(this.getTabPlato());
          //tab formulario
          Tab tab_pedido = new Tab("Pedido");
          tab_pedido.setContent(this.getTabPedido());
          //meter todo en el tab pane
          tabRestaurante.getTabs().addAll(tab_crear_plato ,tab_pedido);


        //presentacion del esenario
        Scene esena = new Scene(tabRestaurante,650,410);
        primaryStage.setTitle("Restaurante ACME");
        
        esena.getStylesheets().add(getClass().getResource("/archivos/css/estilos-generales.css").toExternalForm());
        esena.getStylesheets().add(getClass().getResource("/archivos/css/estilos-especificos.css").toExternalForm());
        primaryStage.setScene(esena);
        primaryStage.show();

    }
    private GridPane getTabPlato(){
        GridPane gp_plato= new GridPane();
        gp_plato.setGridLinesVisible(false);

         //Controles del nombre plato
         Label lb_nombre_plato= new Label("Nombre:");
         TextField tf_nombre_plato = new TextField();
         tf_nombre_plato.setMinWidth(200);
         tf_nombre_plato.setMaxWidth(200);
         Label lb_tipo_plato= new Label("Tipo Plato:");
        
         gp_plato.add(lb_nombre_plato, 0, 0);
         gp_plato.add(tf_nombre_plato, 1, 0);
         gp_plato.add(lb_tipo_plato, 2, 0);

        //Controles del precio plato
        Label lb_precio_plato= new Label("Precio $:");
        TextField tf_precio_plato = new TextField();
        tf_nombre_plato.setMinWidth(200);
        tf_nombre_plato.setMaxWidth(200);
        
        //radio botones para sellecionar tipo de comida
        ToggleGroup tg_tipo_plato=new ToggleGroup();
        RadioButton rb_entrada = new RadioButton("Entrada");
        rb_entrada.setSelected(true);
        rb_entrada.setToggleGroup(tg_tipo_plato);
        RadioButton rb_platofuerte = new RadioButton("Plato Fuerte");
        rb_platofuerte.setToggleGroup(tg_tipo_plato);
        RadioButton rb_postre = new RadioButton("Postre");
        rb_postre.setToggleGroup(tg_tipo_plato);
        RadioButton rb_bebida = new RadioButton("Bebida");
        rb_bebida.setToggleGroup(tg_tipo_plato);


        gp_plato.add(lb_precio_plato, 0, 1);
        gp_plato.add(tf_precio_plato, 1, 1);
        gp_plato.add(rb_entrada, 2, 1);
        gp_plato.add(rb_platofuerte, 3, 1);
        gp_plato.add(rb_postre, 4, 1);
        gp_plato.add(rb_bebida, 5, 1);

        Button btn_crearplato= new Button("Crear Plato");
        btn_crearplato.setMinWidth(180);
        gp_plato.add(btn_crearplato, 1, 2);

        Label lb_platos_existentes= new Label("Platos Existentes:");
        lb_platos_existentes.getStyleClass().addAll("label_platos");
        gp_plato.add(lb_platos_existentes, 1, 3);

        //text area
        TextArea ta_lista_platillos=new TextArea();
        ta_lista_platillos.setMaxSize(500, 400);
        ta_lista_platillos.setEditable(false);
        ta_lista_platillos.getStyleClass().addAll("menu");
         
        gp_plato.add(ta_lista_platillos,1,4,6,1);
        
        gp_plato.setHgap(20);
        gp_plato.setVgap(20);
        gp_plato.setPadding(new Insets(10, 10, 10, 10));


        btn_crearplato.setOnAction(event->{
            
            String nombre=tf_nombre_plato.getText().toString();
            RadioButton chk = (RadioButton)rb_entrada.getToggleGroup().getSelectedToggle();
            String tipo=chk.getText();

            Alert alerta = new Alert(AlertType.ERROR);
            Alert alerta2 = new Alert(AlertType.CONFIRMATION);

            if((!nombre.equalsIgnoreCase("")) && (!tf_precio_plato.getText().toString().equalsIgnoreCase(""))){

                Boolean bandera=true;
                for(int i=0; i<this.platos.size();i++){
                    if(platos.get(i).nombre_plato.equalsIgnoreCase(nombre)){
                        bandera=false;
                    }

                }
                if(bandera){
                    try {
                        double precio = Double.parseDouble(tf_precio_plato.getText().toString());
                        if(precio>0){
                            Plato plato1=new Plato(nombre, tipo, precio);
                            this.platos.add(plato1);
        
                            tf_nombre_plato.setText("");
                            tf_precio_plato.setText("");
                            alerta2.setTitle("Exito");
                            alerta2.setHeaderText("Plato Registrado Con Exito");
                            alerta2.setContentText("El plato: "+nombre+" $"+precio+" De Tipo "+tipo+" Se Agregó Al Menú");
                            alerta2.show();
                            tf_nombre_plato.requestFocus();

                            tabRestaurante.getTabs().remove(1);
                            Tab tab_pedido = new Tab("Pedido");
                            tab_pedido.setContent(this.getTabPedido());
                            tabRestaurante.getTabs().add(tab_pedido);
                        }else{
                            alerta.setContentText("El Precio Debe Ser Un Numero Positivo");
                            alerta.setTitle("Error");
                            alerta.setHeaderText("No Se Puede Crear El Plato");
                            alerta.showAndWait();
                        }
                            
                        }catch (Exception e) {
                        alerta.setContentText("El Precio Debe Ser Un Numero");
                        alerta.setTitle("Error");
                        alerta.setHeaderText("No Se Puede Crear El Plato");
                        alerta.showAndWait();
                    } 
                }else{
                alerta.setTitle("Error");
                alerta.setHeaderText("No Se Puede Crear El Plato");
                alerta.setContentText("El Nombre Del Plato Ya Esta Registrado");
                alerta.showAndWait();
                }
                
            }else{
                alerta.setTitle("Error");
                alerta.setHeaderText("No Se Puede Crear El Plato");
                alerta.setContentText("El Precio o El Nombre No Son Validos");
                alerta.showAndWait();
            }

            ta_lista_platillos.setText(Plato.ListarPlatos(this.platos));

        });

        Image imgnombres = new Image("/archivos/imagenes/nombres.png",180, 138, false, false);
        ImageView vewnombres= new ImageView(imgnombres);
        gp_plato.add(vewnombres,3,4,3,1);
        return gp_plato;

    }

    private GridPane getTabPedido(){
        GridPane gp_pedido= new GridPane();
        gp_pedido.setGridLinesVisible(false);

        Label lb_entrada = new Label("Entrada: ");
        lb_entrada.getStyleClass().add("label_pedidos");
        ComboBox <String> entradas = new ComboBox<>();
        entradas.setMaxWidth(150);
        entradas.setMinWidth(150);
        entradas.setMinHeight(30);
        entradas.getStyleClass().add("combobox");
        Label lb_platofuerte = new Label("Plato Fuerte: ");
        lb_platofuerte.getStyleClass().add("label_pedidos");
        ComboBox <String> platosfuertes = new ComboBox<>();
        platosfuertes.setMaxWidth(150);
        platosfuertes.setMinWidth(150);
        platosfuertes.setMinHeight(30);
        platosfuertes.getStyleClass().add("combobox");
        Label lb_postre = new Label("Postre: ");
        lb_postre.getStyleClass().add("label_pedidos");
        ComboBox <String> postres = new ComboBox<>();
        postres.setMaxWidth(150);
        postres.setMinWidth(150);
        postres.setMinHeight(30);
        postres.getStyleClass().add("combobox");
        Label lb_bebida = new Label("Bebida: ");
        lb_bebida.getStyleClass().add("label_pedidos");
        ComboBox <String> bebidas = new ComboBox<>();
        bebidas.setMaxWidth(150);
        bebidas.setMinWidth(150);
        bebidas.setMinHeight(30);
        bebidas.getStyleClass().add("combobox");
        
        entradas.getItems().clear();
        platosfuertes.getItems().clear();
        postres.getItems().clear();
        bebidas.getItems().clear();
        for(int i=0; i<platos.size(); i++){

            String tipo=platos.get(i).tipo_plato;

            switch(tipo){

                case "Entrada":
                    entradas.getItems().add(platos.get(i).nombre_plato);
                 break;

                 case "Plato Fuerte":
                    platosfuertes.getItems().add(platos.get(i).nombre_plato);
                 break;

                 case "Postre":
                    postres.getItems().add(platos.get(i).nombre_plato);
                 break;

                 case "Bebida":
                    bebidas.getItems().add(platos.get(i).nombre_plato);
                 break;
            }
        }

        gp_pedido.add(lb_entrada, 0,0,2,1);
        gp_pedido.add(lb_platofuerte, 2,0,2,1);
        gp_pedido.add(lb_postre, 4,0,2,1);
        gp_pedido.add(lb_bebida, 6,0,2,1);

        gp_pedido.add(entradas, 0,1,2,1);
        gp_pedido.add(platosfuertes, 2,1,2,1);
        gp_pedido.add(postres, 4,1,2,1);
        gp_pedido.add(bebidas, 6,1,2,1);

        Label num_entradas = new Label("Cantidad: ");
        num_entradas.getStyleClass().add("label_pedidos");

        Label num_platosfuertes = new Label("Cantidad: ");
        num_platosfuertes.getStyleClass().add("label_pedidos");

        Label num_postres = new Label("Cantidad: ");
        num_postres.getStyleClass().add("label_pedidos");

        Label num_bebidas = new Label("Cantidad: ");
        num_bebidas.getStyleClass().add("label_pedidos");

        TextField cantidad_entradas = new TextField();
        cantidad_entradas.setMinWidth(70);
        cantidad_entradas.setMaxWidth(70);

        TextField cantidad_platosfuertes = new TextField();
        cantidad_platosfuertes.setMinWidth(70);
        cantidad_platosfuertes.setMaxWidth(70);

        TextField cantidad_postres = new TextField();
        cantidad_postres.setMinWidth(70);
        cantidad_postres.setMaxWidth(70);

        TextField cantidad_bebidas = new TextField();
        cantidad_bebidas.setMinWidth(70);
        cantidad_bebidas.setMaxWidth(70);

        gp_pedido.add(num_entradas, 0,2);
        gp_pedido.add(num_platosfuertes, 2,2);
        gp_pedido.add(num_postres, 4,2);
        gp_pedido.add(num_bebidas, 6,2);

        gp_pedido.add(cantidad_entradas, 1,2);
        gp_pedido.add(cantidad_platosfuertes, 3,2);
        gp_pedido.add(cantidad_postres, 5,2);
        gp_pedido.add(cantidad_bebidas, 7,2);

        CheckBox clientefiel = new CheckBox("Cliente Fiel");
        clientefiel.getStyleClass().add("label_pedidos");
        clientefiel.setIndeterminate(false);

        Button btn_crearpedido= new Button("Facturar Pedido");
        btn_crearpedido.setMinWidth(160);

        gp_pedido.add(clientefiel, 0,4,2,1);
        gp_pedido.add(btn_crearpedido, 2,4,2,1);

        Label nombres = new Label("By: Segundo Paladines \n"+
                                  "       David Pasaje");
        nombres.setId("idnombres");
        TextArea ta_factura=new TextArea();
        ta_factura.setMinSize(600, 200);
        ta_factura.setMaxSize(600, 200);
        ta_factura.setEditable(false);
        ta_factura.getStyleClass().addAll("menu");
         
        gp_pedido.add(ta_factura,0,5,8,1);

        gp_pedido.setHgap(10);
        gp_pedido.setVgap(10);
        gp_pedido.setPadding(new Insets(10, 10, 10, 10));
        gp_pedido.getStyleClass().add("menu");

        btn_crearpedido.setOnAction(evet->{

           Plato plato1=null;
           Plato plato2=null;
           Plato plato3=null;
           Plato plato4=null;


            int cantidad1=0;
            int cantidad2=0;
            int cantidad3=0;
            int cantidad4=0;
            try {
                plato1 = new Plato(entradas.getValue().toString(), "Entrada", Plato.ObtenerPrecio(entradas.getValue().toString(), platos));
                cantidad1=Integer.parseInt(cantidad_entradas.getText().toString());
            } catch (Exception e) {}
            try {
                plato2 = new Plato(platosfuertes.getValue().toString(), "Plato Fuerte", Plato.ObtenerPrecio(platosfuertes.getValue().toString(), platos));
                cantidad2=Integer.parseInt(cantidad_platosfuertes.getText().toString());
                
            } catch (Exception e) {}
            try {
                plato3 = new Plato(postres.getValue().toString(), "Postre", Plato.ObtenerPrecio(postres.getValue().toString(), platos));
                cantidad3=Integer.parseInt(cantidad_postres.getText().toString());
                
            } catch (Exception e) {}
            try {
                plato4 = new Plato(bebidas.getValue().toString(), "Bebida", Plato.ObtenerPrecio(bebidas.getValue().toString(), platos));
                cantidad4=Integer.parseInt(cantidad_bebidas.getText().toString());
            } catch (Exception e) {}

            Boolean fiel = clientefiel.isSelected();
            String factura=Pedido.HacerPedido(plato1,cantidad1,plato2,cantidad2,plato3,cantidad3,plato4,cantidad4, fiel);
            ta_factura.clear();
            ta_factura.setText(factura);
           
        });

        return gp_pedido;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
