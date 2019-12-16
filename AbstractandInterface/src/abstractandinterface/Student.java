/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractandinterface;

/**
 *
 * @author ADDIX.01
 */
public abstract class Student {
    abstract void Student1();


public static void main(String[] args) {
       Student student = new StudentC1();
       student.Student1();
    }
}