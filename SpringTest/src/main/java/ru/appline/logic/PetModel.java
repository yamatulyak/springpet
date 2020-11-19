package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class PetModel implements Serializable {
    private  static final PetModel instance = new PetModel();

    private final Map<Integer,Pet> model;

    public PetModel(){
        model = new HashMap<Integer, Pet>();
    }
    public static PetModel getInstance(){
        return instance;
    }

    public void add(Pet pet, int id){
        model.put(id,pet);
    }

    public Pet getFromList(int id) {
        return model.get(id);
    }
    public void del(int id){
        for (Iterator<Integer> iterator = model.keySet().iterator(); iterator.hasNext(); ) {
            Integer key = iterator.next();
            if (key != 0) {
                if (key == id){iterator.remove();}
            }
        }
    }

    public void update(Pet pet, int id){
        model.replace(id,pet);
    }

    public Map<Integer,Pet> getAll(){
        return model;
    }
}
