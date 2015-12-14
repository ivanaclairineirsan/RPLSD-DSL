import groovy.xml.MarkupBuilder

class InvoiceDsl {

    private Set<String> errorMessages = new HashSet<>()
    private Map<Integer, String> invoiceOrdering = new TreeMap<>()
    private String restaurantName = ""
    private String address = ""
    private int orderRow = -1
    private String thankYouNotes = ""

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

    def getHtml() {
        for (int i = 1; i <= invoiceOrdering.size(); ++i) {
            if (!invoiceOrdering.containsKey(i)) {
                errorMessages.add("Ordering must start from 1 and ordered")
            }
        }
        for (Map.Entry<Integer, String> entry : invoiceOrdering) {
            String[] split = entry.getValue().split(",")
            if (split.length > 1) {
                for (int i = 0; i < split.length; ++i) {
                    if (split[i].equals("")) {
                        errorMessages.add("Table ordering must start from 1 and ordered")
                    }
                }
                if (!entry.getValue().contains("name")) {
                    errorMessages.add("Table order_name must be entered")
                }
                if (!entry.getValue().contains("qty")) {
                    errorMessages.add("Table order_quantity must be entered")
                }
//                if (!entry.getValue().contains("price")) {
//                    errorMessages.add("Table order_price must be entered")
//                }
                if (!entry.getValue().contains("subtotal")) {
                    errorMessages.add("Table order_subtotal must be entered")
                }
            }
        }

//        if (!invoiceOrdering.containsValue("id")) {
//            errorMessages.add("Invoice ID must be entered")
//        }
        if (!invoiceOrdering.containsValue("restaurant_name") || restaurantName.equals("")) {
            errorMessages.add("Restaurant name must be entered")
        }
        if (orderRow == -1) {
            errorMessages.add("Order row must be entered and greater than 0")
        }
        if (!invoiceOrdering.containsValue("total")) {
            errorMessages.add("Total must be entered")
        }
//        if (!invoiceOrdering.containsValue("grand_total")) {
//            errorMessages.add("Grand total must be entered")
//        }

        if (errorMessages.size() == 0) {
            generateHtml(this)
            System.out.println("Invoice berhasil dibuat. File dapat diakses di folder data/invoice.html")
        } else {
            for (String s : errorMessages) {
                println(s)
            }
        }
    }

    private static void generateHtml(InvoiceDsl invoiceDsl) {
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)

        def htmlFile = new File("data/invoice.html")
        PrintWriter printWriter = new PrintWriter(htmlFile)

        int rowCounter = 0
        boolean isThereNo = false
        builder.html {
            head {
                title "Invoice " + invoiceDsl.restaurantName
                link(href: "style.css", rel: "stylesheet")
                link(href: "bootstrap.min.css", rel: "stylesheet")
            }
            body(style: "font-family: sans-serif") {
                form {
                    table(style: "border: 1px dotted #ccc") {
                        for (Map.Entry<Integer, String> entry : invoiceDsl.invoiceOrdering.entrySet()) {
                            def value = entry.getValue()
                            String[] arrayValue = String.valueOf(value).split(",");
                            if (arrayValue.size() == 1) {
                                switch (arrayValue[0]) {
                                    case "id":
                                        tr {
                                            td(class: "id-invoice", "No. 299")
                                        }
                                        break;
                                    case "restaurant_name":
                                        tr {
                                            td(colspan: arrayValue.size()) {
                                                div(class: "restaurant_name", invoiceDsl.restaurantName)
                                            }
                                        }
                                        break;
                                    case "address":
                                        tr {
                                            td(colspan: arrayValue.size()) {
                                                div(class: "address", invoiceDsl.address)
                                            }
                                        }
                                        break;
                                    case "cashier_name":
                                        tr(style: "padding: 15px") {
                                            td("Kasir: ") {
//                                                p("Kasir")
                                                input(type: "text", class: "small-input")
                                            }
                                        }
                                        break;
                                    case "thankyou_notes":
                                        tr {
                                            td(class: "thankyou-note", invoiceDsl.thankYouNotes)
                                        }
                                        break;
                                    case "grand_total":
                                        tr {
                                            td(style: "text-align: right") {
                                                label("Grand Total: ")
                                                input(type: "number", disabled: true)
                                            }
                                        }
                                        break;
                                    case "item_total":
                                        tr {
                                            td() {
                                                label("Total: ")
                                                input(type: "number", style: "max-width: 35px;", disabled: true)
                                                label(" item")
                                            }
                                        }
                                        break;
                                    case "service_charge":
                                        tr {
                                            td(style: "text-align: right") {
                                                label("Servis (")
                                                input(type: "number", style: "max-width: 35px;")
                                                label("%): ")
                                                input(type: "number", disabled: true)
                                            }
                                        }
                                        break;
                                    case "tax":
                                        tr {
                                            td(style: "text-align: right") {
                                                label("Pajak (")
                                                input(type: "number", style: "max-width: 35px;")
                                                label("%): ")
                                                input(type: "number", disabled: true)
                                            }
                                        }
                                        break;
                                    case "discount":
                                        tr {
                                            td(style: "text-align: right") {
                                                label("Diskon (")
                                                input(type: "number", style: "max-width: 35px;")
                                                label("%): ")
                                                input(type: "number", disabled: true)
                                            }
                                        }
                                        break;
                                    case "total":
                                        tr {
                                            td(style: "text-align: right") {
                                                label("Total: ")
                                                input(type: "number", disabled: true)
                                            }
                                        }
                                        break;
                                }
                            } else {
                                td {
                                    table {
                                        tr {
                                            for (int i = 0; i < arrayValue.size(); i++) {
                                                switch (arrayValue[i]) {
                                                    case "no":
                                                    case "order_no":
                                                        isThereNo = true;
                                                        th("No.")
                                                        rowCounter++;
                                                        break;
                                                    case "name":
                                                    case "order_name":
                                                        th("Pesanan")
                                                        rowCounter++;
                                                        break;
                                                    case "qty":
                                                    case "order_qty":
                                                        th("Jumlah")
                                                        rowCounter++;
                                                        break;
                                                    case "price":
                                                    case "order_price":
                                                        th("Harga")
                                                        rowCounter++;
                                                        break;
                                                    case "subtotal":
                                                        th("Subtotal")
                                                        rowCounter++;
                                                        break;
                                                }
                                            }
                                        }

                                        for (int a = 0; a < invoiceDsl.orderRow; a++) {
                                            tr {
                                                for (int b = 0; b < rowCounter; b++) {
                                                    td {
                                                        if (isThereNo && b == 0) {
                                                            input(type: "text", width: "30px")
                                                        } else
                                                            input(type: "text")
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        printWriter.println(writer.toString())
        printWriter.close()
    }

    private void parse(String order, String methodName) {
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
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                array[i] = ""
            }
        }
        invoiceOrdering.put(Integer.parseInt(split[0]), array.join(","))
    }

    def methodMissing(String methodName, args) {
        errorMessages.add("Method " + methodName + " is not recognized")
    }

}
