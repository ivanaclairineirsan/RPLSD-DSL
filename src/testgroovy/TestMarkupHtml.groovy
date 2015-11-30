/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testgroovy

import groovy.xml.MarkupBuilder

/**
 *
 * @author Ivana Clairine
 */
class TestMarkupHtml {
    
    static main (args) {
        //Map map = [Jim:"Knopf", Thomas:"Edison"]
        def date = new Date()
        StringWriter writer = new StringWriter()
        MarkupBuilder builder = new MarkupBuilder(writer)
    
        def htmlFile = new File("invoice.html")
        PrintWriter printWriter = new PrintWriter(htmlFile)
        
        //init data
        def titleweb = "Ivana Clairine"
        def maxCol = 1
        def restoName = "LineLineLineLineLineLine"
    
    
        def theBuilder = builder.html {
            head { title titleweb }
            body {
                form{
                    table (style: "border: 1px dotted #ccc"){
                        tr {
                            td (colspan: maxCol){
                                div (style: "font-weight: bold; font-size: 18px; text-align: center;",  restoName) 
                            }
                        }
                        tr {
                            td (colspan: maxCol){
                                div (style: "font-weight: bold; font-size: 15px; text-align: center;",  "alamat " + restoName) 
                            }
                        }
                        tr{ // nomor struk
                            td ("No. " + "1" )
                        }
                        tr { // 
                            td ("Kasir: " + "namakasir")
                        }
                        tr {
                            td {
                                table (class: "order-list"){
                                    tr{ 
                                        th ("No.")
                                        th ("Pesanan")
                                        th ("Jlh")
                                        th ("Harga")
                                        th ("Sub-total")
                                    }
                                    tr{ 
                                        td ("1.")
                                        td (){
                                            input( type: "text")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    tr{ 
                                        td ("2.")
                                        td (){
                                            input( type: "text")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                    tr{ 
                                        td ("3.")
                                        td (){
                                            input( type: "text")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input (type: "number")
                                        }
                                        td (){
                                            input(type: "number", disabled: true)
                                        }
                                    }
                                }
                            }
                        }
                
                        tr{
                            td (style: "text-align: right"){
                                label("Total: ")
                                input(type: "number", disabled: true)
                            }
                        }
                        tr{
                            td (style: "text-align: right"){
                                label("Service charge (5%): ")
                                input(type: "number", disabled: true)
                            }
                        }
                        tr{
                            td (style: "text-align: right"){
                                label("Tax (10%): ")
                                input(type: "number", disabled: true)
                            }
                        }
                        tr{
                            td (style: "text-align: right"){
                                label("Diskon (10%): ")
                                input(type: "number", disabled: true)
                            }
                        }
                        tr{
                            td (style: "text-align: right"){
                                label("Grand Total: ")
                                input(type: "number", disabled: true)
                            }
                        }
                
                        tr{
                            td (style: "text-align: center", "Insert random good quote here" )
                        }
                    }
                }
            }
        }
        
//        print writer.toString()
        printWriter.println(writer.toString())
        printWriter.close()
    }
} 

