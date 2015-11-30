/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testgroovy

/**
 *
 * @author Ivana Clairine
 */
class NewGroovyClass {
    
    def sayHello() {
    println 'Hello From MyGroovyTest'
    }
    
    static void main(args) {
        def mgt = new NewGroovyClass()
        mgt.sayHello()
    }
}
	

