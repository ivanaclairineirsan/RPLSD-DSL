package testgroovy
import groovy.xml.MarkupBuilder

class InvoiceDsl {

    private static Map<Integer, String> invoiceOrdering = new HashMap<>()
    private static String restaurantName
    private static String address
    private static int orderRow
    private static String thankYouNotes

    public InvoiceDsl() {
    }

    def static make(closure) {
        InvoiceDsl invoiceDsl = new InvoiceDsl()
        closure.delegate = invoiceDsl
        closure()
    }

    def id(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "id")
    }

    def restaurant_name(String positionID, String restaurantNameText) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "restaurant_name")

        this.restaurantName = restaurantNameText
    }

    def address(String positionID, String addressText) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "address")

        this.address = addressText
    }

    def cashier_name(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "cashier_name")
    }

    def total(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "total")
    }

    def discount(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "discount")
    }

    def tax(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "tax")
    }

    def service_charge(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "service_charge")
    }

    def item_total(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "item_total")
    }

    def grand_total(String positionID) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "grand_total")
    }

    def thankyou_notes(String positionID, String thankYouNotes) {
        int pos = Integer.parseInt(positionID)
        invoiceOrdering.put(pos, "thankyou_notes")

        this.thankYouNotes = thankYouNotes;
    }

    def order_row(int orderRow) {
        this.orderRow = orderRow
    }

    def order_no(String order) {
        parse(order, "no")
    }

    def order_name(String order) {
        parse(order, "name")
    }

    def order_qty(String order) {
        parse(order, "qty")
    }

    def order_price(String order) {
        parse(order, "price")
    }

    def order_subtotal(String order) {
        parse(order, "subtotal")
    }

    static void generateHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
    
        def htmlFile = new File("invoice.html")
        PrintWriter printWriter = new PrintWriter(htmlFile)
        
        TreeMap orderedMap = new TreeMap (this.invoiceOrdering);
        //init data
        def maxCol = 1
        def rowCounter = 0;
        def isThereNo = false;
        def theBuilder = builder.html {
            head { title "Invoice " + restaurantName}
            body {
                form{
                    table (style: "border: 1px dotted #ccc"){
                        for (Map.Entry<Integer, String> entry : orderedMap.entrySet()){
                            def order = entry.getKey()
                            def value = entry.getValue()
                            def arrayValue = ((String) value).split(",");
                            if (arrayValue.size() == 1){
                                switch (arrayValue.getAt(0)){
                                case "id":
                                    tr{ // nomor struk
                                        td ("No. 299")
                                    }
                                    break;
                                case "restaurant_name":
                                    tr {
                                        td (colspan: arrayValue.size()){
                                            div (style: "font-weight: bold; font-size: 18px; text-align: center;",  restaurantName) 
                                        }
                                    }
                                    break;
                                case "address":
                                    tr {
                                        td (colspan: arrayValue.size()){
                                            div (style: "font-weight: bold; font-size: 15px; text-align: center;",  address) 
                                        }
                                    }
                                    break;
                                case "cashier_name":
                                    tr { // 
                                        td ("Kasir: "){
                                            input (type: "text")
                                        }
                                    }
                                    break;
                                case "thankyou_notes":
                                    tr{
                                        td (style: "text-align: center", this.thankYouNotes )
                                    }
                                    break;
                                case "grand_total":
                                    td (style: "text-align: right"){
                                        label("Grand Total: ")
                                        input(type: "number", disabled: true)
                                    }
                                    break;
                                case "item_total":
                                    tr{
                                        td(){
                                            label("Total: ") 
                                            input(type: "number", style: "max-width: 35px;", disabled: true) 
                                            label(" item")
                                        }
                                    }
                                    break;
                                case "service_charge":
                                    tr{
                                        td (style: "text-align: right"){
                                            label("Servis (") 
                                            input(type: "number", style: "max-width: 35px;")
                                            label("%): ")
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    break;
                                case "tax": 
                                    tr{
                                        td (style: "text-align: right"){
                                            label("Pajak (") 
                                            input(type: "number", style: "max-width: 35px;")
                                            label("%): ")
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    break;
                                case "discount":
                                    tr{
                                        td (style: "text-align: right"){
                                            label("Diskon (") 
                                            input(type: "number", style: "max-width: 35px;")
                                            label("%): ")
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    break;
                                case "total":
                                    tr{
                                        td (style: "text-align: right"){
                                            label("Total: ")
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    break;
                                default:
                                    break;
                                }     
                                
                            }
                            else {
                                td{
                                    table {
                                        tr {
                                            for(int i = 0; i < arrayValue.size(); i++){
                                                switch(arrayValue.getAt(i)){
                                                    case "order_row":
                                                        break;
                                                    case "order_no":
                                                        isThereNo = true;
                                                        th ("No.")
                                                        rowCounter++;
                                                        break;
                                                    case "no":
                                                        isThereNo = true;
                                                        th ("No.")
                                                        rowCounter++;
                                                        break;
                                                    case "order_name":
                                                        th ("Pesanan")
                                                        rowCounter++;
                                                        break;
                                                    case "name":
                                                        th ("Pesanan")
                                                        rowCounter++;
                                                        break;
                                                    case "order_qty":
                                                        th ("Jlh")
                                                        rowCounter++;
                                                        break;
                                                    case "qty":
                                                        th ("Jlh")
                                                        rowCounter++;
                                                        break;
                                                    case "order_price":
                                                        th ("Harga")
                                                        rowCounter++;
                                                        break;
                                                    case "price":
                                                        th ("Harga")
                                                        rowCounter++;
                                                        break;
                                                    case "subtotal":
                                                        th ("Subtotal")
                                                        rowCounter++;
                                                        break;
                                                    case "order_subtotal":
                                                        th ("Subtotal")
                                                        rowCounter++;
                                                        break;
                                                }
                                            }
                                        }
                                        for (int a = 0; a < this.orderRow; a++){
                                            tr {
                                                for (int b = 0; b < rowCounter; b++){
                                                    td{
                                                        if(isThereNo && b==0)
                                                        {
                                                             input(type: "text", width: "30px")
                                                        }
                                                        else
                                                        input(type: "text")
                                                    }
                                                    
                                                }
                                            }
                                        }
                                    }
                                }
                                //utk tabel harga
                            }
                        }
                    }
                }
            }
        }
        
        //        print writer.toString()
        printWriter.println(writer.toString())
        printWriter.close()
        
    }
    
    public void parse(String order, String methodName) {
        String[] split = order.split("-")

        String[] array = new String[0]
        if (invoiceOrdering.containsKey(Integer.parseInt(split[0]))) {
            String[] tArray = invoiceOrdering.get(Integer.parseInt(split[0])).split(",")
            if (Integer.parseInt(split[1]) < tArray.length) {
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != null) {
                        tArray[i] = array[i];
                    }
                }
                array = tArray
            } else {
                array = new String[Integer.parseInt(split[1])];
                for (int i = 0; i < tArray.length; ++i) {
                    if (tArray[i] != null) {
                        array[i] = tArray[i];
                    }
                }
            }
        } else {
            array = new String[Integer.parseInt(split[1])];
        }
        array[(Integer.parseInt(split[1])) - 1] = methodName
        invoiceOrdering.put(Integer.parseInt(split[0]), array.join(","))
    }

    def methodMissing(String methodName, args) {

    }

    static void main(String[] args) {
        make {
            id "1"
            restaurant_name "2", "RM Sedap Malam"
            address "3", "Jalan Ganesha"
            cashier_name "4"
            order_row 10
            order_no "5-1"
            order_name "5-2"
            order_qty "5-3"
            order_price "5-4"
            order_subtotal "5-5"
            total "6"
            discount "7"
            tax "8"
            service_charge "9"
            item_total "10"
            grand_total "11"
            thankyou_notes "12", "Terima kasih"
        }

        System.out.println("restaurantName " + InvoiceDsl.restaurantName)
        System.out.println("address " + InvoiceDsl.address)
        System.out.println("thankYouNotes " + InvoiceDsl.thankYouNotes)
        System.out.println("orderRow " + InvoiceDsl.orderRow)
        for (Map.Entry<Integer, String> entry : InvoiceDsl.invoiceOrdering.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue())
        }
        
        this.generateHtml();
    }

}
