class InvoiceDslTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukan pilihan test case (1-6): ");
        int choice = scanner.nextInt();
        while (choice <= 0 || choice > 6) {
            System.out.println("Masukan salah, silahkan ulangi!");
            System.out.print("Masukan pilihan test case (1-6): ");
            choice = scanner.nextInt();
        }

        switch (choice) {
            case 1:
                InvoiceDsl.make {
                    id "1"
                    restaurant_name "2", "RM Sedap Malam"
                    address "3", "Jalan Ganesha"
                    cashier_name "4"
                    order_row 10
                    order_no "5-1"
                    order_name "5-2"
                    order_qty "5-3"
                    order_price "5-5"
                    order_subtotal "5-4"
                    total "6"
                    discount "7"
                    tax "8"
                    service_charge "9"
                    item_total "10"
                    grand_total "11"
                    thankyou_notes "12", "Terima kasih atas kunjungan Anda. Silakan datang kembali"
                    html
                }
                break;
            case 2:
                InvoiceDsl.make {
                    id "1"
                    restaurant_name "2", "RM Sedap Malam"
                    cashier_name "3"
                    order_row 5
                    order_no "4-1"
                    order_name "4-2"
                    order_qty "4-3"
                    order_subtotal "4-4"
                    total "5"
                    service_charge "6"
                    grand_total "7"
                    html
                }
                break;
            case 3:
                InvoiceDsl.make {
                    id "3"
                    restaurant_name "1", "RM Sejahtera"
                    address "2", "Jalan Ganesha"
                    order_row 5
                    order_name "4-1"
                    order_qty "4-2"
                    order_price "4-3"
                    order_subtotal "4-4"
                    total "5"
                    discount "6"
                    item_total "7"
                    grand_total "8"
                    thankyou_notes "9", "Selamat menikmati"
                    html
                }
                break;
            case 4:
                InvoiceDsl.make {
                    id "3"
                    restaurant_name "1", "RM Sejahtera"
                    address "2", "Jalan Ganesha"
                    order_row 5
                    order_name "5-1"
                    order_qty "5-2"
                    order_price "5-3"
                    order_subtotal "5-4"
                    total "6"
                    tax "7"
                    service_charge "8"
                    item_total "9"
                    grand_total "4"
                    thankyou_notes "10", "Selamat menikmati"
                    html
                }
                break;
            case 5:
                InvoiceDsl.make {
                    restaurant_name "1", "RM Sejahtera"
                    address "2", "Jalan Ganesha"
                    order_row 3
                    order_no "3-1"
                    order_name "3-2"
                    order_qty "3-3"
                    order_price "3-4"
                    order_subtotal "3-5"
                    total "4"
                    html
                }
                break;
            case 6:
                InvoiceDsl.make {
                    id "1"
                    restaurant_name "2", "RM Sedap Malam"
                    address "3", "Jalan Ganesha"
                    order_row 10
                    order_no "5-1"
                    order_name "5-2"
                    order_qty "5-3"
                    order_price "5-5"
                    order_subtotal "5-4"
                    total "6"
                    discount "7"
                    tax "8"
                    service_charge "9"
                    item_total "10"
                    grand_total "11"
                    thankyou_notes "12", "Terima kasih atas kunjungan Anda. Silakan datang kembali"
                    html
                }
                break;
        }

    }
}
