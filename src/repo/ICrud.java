/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repo;

import java.util.HashMap;

/**
 *
 * @author MSI
 */
public interface ICrud<K,V> {
    int create(V newItem);
    HashMap<K,V> read();
    V details(K id);
    int update(V editItem);
    int delete(K id);
}
