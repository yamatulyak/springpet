package ru.appline.controller;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.bind.annotation.*;
import ru.appline.logic.PetModel;
import ru.appline.logic.Pet;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet(@RequestBody Pet pet){
        if (newId.get() == 1) {
            petModel.add(pet, newId.getAndIncrement());
            return "Поздравляю, Вы создали своего первого питомца!";
        }
        else {
            petModel.add(pet, newId.getAndIncrement());
            return "Поздравляю, Вы создали питомца!";
        }


    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll(){
        return petModel.getAll();
    }


    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String,Integer> id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/delPet", consumes = "application/json")
    public String delPet(@RequestBody Map<String,Integer> id){
         petModel.del(id.get("id"));
         return "Пользователь с id=" + id.get("id") + " был удален";
    }

    @PutMapping(value = "/putPet", consumes = "application/json", produces = "application/json")
    public Pet putPet(@RequestBody   Map<String,String> id) {
        Pet pet = new Pet(id.get("name"),id.get("type"),Integer.parseInt(id.get("age")));
        petModel.update(pet, Integer.parseInt(id.get("id")));
        return petModel.getFromList(Integer.parseInt(id.get("id")));
    }



}