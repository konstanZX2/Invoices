import java.util.Date;

public class Invoice {
    private static final double GST=0.21;
    public static final int MAX_INVOICE_LINES=10;
    private static final  String LINES = "--------------------------------------";
    private int numInvoice;
    private Date date;
    private String to;
    private String shipTo;



    private String salesPerson;
    private String shipmentMethod;
    private String shippingTerms;
    private Date deliveryDate;
    private String paymentTerms;
    private Date dueDate;
    private float totalDiscountPercent;

    private InvoiceLine[] invoiceLines;

    public Invoice(int numInvoice, Date date, String to, String shipTo, String salesPerson,
                   String shipmentMethod, String shippingTerms, Date deliveryDate,
                   String paymentTerms, Date dueDate, float totalDiscountPercent) {
        this.numInvoice = numInvoice;
        this.date = date;
        this.to = to;
        this.shipTo = shipTo;
        this.salesPerson = salesPerson;
        this.shipmentMethod = shipmentMethod;
        this.shippingTerms = shippingTerms;
        this.deliveryDate = deliveryDate;
        this.paymentTerms = paymentTerms;
        this.dueDate = dueDate;
        this.totalDiscountPercent = totalDiscountPercent/100;
        invoiceLines = new InvoiceLine[MAX_INVOICE_LINES];
        for (int i = 0; i <MAX_INVOICE_LINES ; i++) {
            invoiceLines[i]=null;

        }
    }
    public void addInvoiceLine(InvoiceLine line){
        int i=0;
        while(i< invoiceLines.length && invoiceLines[i] != null){
            i++;
        }
        if(i < invoiceLines.length){
            invoiceLines[i] = line;
        }
    }
    public void print() {
        System.out.println("Invoice number:"+numInvoice);
                System.out.format("%100s %tB %td, %tY %n" , "DATE:", date, date, date);
        System.out.println(" TO: "+ to);
        System.out.println("SHIP TO: "+shipTo);
                System.out.println();
        System.out.println(LINES);
        System.out.format("| %20s | %20s | %20s | %15s | %20s | %10s |%n",
                "SALES PERSON ", "SHIPPING METHOD",
                "SHIPPING TERMS", "DELIVERY DATE",
                "PAYMENT TERMS", "DUE DATE");
        System.out.println(LINES);
        System.out.format("| %20s | %20s | %20s | %15tD | %20s | %10tD |%n",
                salesPerson, shipmentMethod, shippingTerms, deliveryDate,
                paymentTerms, dueDate);
        System.out.println(LINES);
        System.out.println();
        int i = 0;
        System.out.println(InvoiceLine.FORMATO_ENCABEZADO);
        while (i < invoiceLines.length && invoiceLines[i] != null) {
            System.out.println(invoiceLines[i]);
            i++;
        }
        System.out.println(InvoiceLine.LINEA);
        printFooter();
    }
    private void printFooter() {
        double totalLines = 0;
        int i = 0;
        while ((i<invoiceLines.length) && (invoiceLines[i]!=null)) {
            totalLines = totalLines + invoiceLines[i].getTotalLinea();
            i++;
        }
        double totalDiscount = 0;
        System.out.format("%73s | %8s | %13s |%n",
                "TOTAL DISCOUNT",
                InvoiceLine.customFormat("##.00%", totalDiscountPercent) ,
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * totalDiscountPercent));
        System.out.format("%102s%n", "----------------------------");
        System.out.format("%84s | %13s |%n",
                "SUBTOTAL",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscountPercent)));
        System.out.format("%102s%n", "-----------------");
        System.out.format("%84s | %13s |%n",
                "GST",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscountPercent) * GST));
        System.out.format("%102s%n", "-----------------");
        System.out.format("%84s | %13s |%n",
                "TOTAL",
                InvoiceLine.customFormat("$#,###,###.00",
                        totalLines * (1 - totalDiscountPercent) +
                                totalLines * (1 - totalDiscountPercent) * GST));
        System.out.format("%102s%n", "-----------------");
    }
}


