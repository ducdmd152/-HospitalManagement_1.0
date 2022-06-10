/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import tools.Console;

/**
 *
 * @author MSI
 */
public class Menu implements IMenu { /// slides File IO cua oop
    private List<String> optionList;
    public Menu() {
        this.optionList = new ArrayList<String>();
    }

    public Menu(List<String> optionList) {
        this.optionList = optionList;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
    
    @Override
    public void add(String newOption) {
        optionList.add(newOption);
    }

    @Override
    public void show() {
        for(int i=0;i<optionList.size();i++) {
            System.out.println(optionList.get(i));
        }
    }

    @Override
    public int getChoice() {
        try {
            return (int) Console.inputNumber("");
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int size() {
        return optionList.size();
    }
}
