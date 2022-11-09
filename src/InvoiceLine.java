import java.text.DecimalFormat;

public class InvoiceLine {
    private int cantidadDeItem;
    private String codigoDeItem;



    private String descripcionDeItem;
    private float precioDeItem;
    private float porcentajeDescuentoDeItem;

    public static final String LINEA="-------------------------------------";
    private static final String FORMATO_DEL_STRING="| %9s | %10s | %30s | %13s | %8s | %13s |";
    public static final String FORMATO_ENCABEZADO=LINEA+"\n"+ String.format(FORMATO_DEL_STRING, "QTY","item #","DESCRIPCION","PRECIO","DESCUENTO","TOTAL")+"\n" +LINEA;




    public InvoiceLine(int cantidadDeItem, String codigoDeItem, String descripcionDeItem, float precioDeItem, float porcentajeDescuentoDeItem) {
        this.cantidadDeItem = cantidadDeItem;
        this.codigoDeItem = codigoDeItem;
        this.descripcionDeItem = descripcionDeItem;
        this.precioDeItem = precioDeItem;
        this.porcentajeDescuentoDeItem = porcentajeDescuentoDeItem/100;
    }
public static String customFormat(String pattern,double value){
    DecimalFormat myFormatter = new DecimalFormat(pattern);
    String output= myFormatter.format(value);
    return output;
}

    public Float getTotalLinea(){
        return cantidadDeItem* precioDeItem*(1-porcentajeDescuentoDeItem);
    }

    @Override
    public String toString() {
        String s=String.format("| %9s | %10s | %30s | %13s | %8s | %13s |",
                customFormat("###,###,###", cantidadDeItem),
                codigoDeItem,
                descripcionDeItem,
                customFormat("$#,###,###.00",precioDeItem),
                customFormat("##.00%",porcentajeDescuentoDeItem),
                customFormat("$#,###,###.00",getTotalLinea()));
        return s;
    }
}
