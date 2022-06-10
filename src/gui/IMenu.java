/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gui;

/**
 *
 * @author MSI
 */
public interface IMenu {
    int size();
    void add(String newOption);
    void show();
//    void detail(int choice);
    int getChoice();
}
