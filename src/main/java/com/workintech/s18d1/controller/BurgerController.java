package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.workintech.s18d1.entity.BreadType;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {

    private final BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    // Save a new burger
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Burger saveBurger(@RequestBody Burger burger) {
        return burgerDao.save(burger);
    }

    // Find a burger by its ID
    @GetMapping("/{id}")
    public Burger getBurgerById(@PathVariable Long id) {
        Burger burger = burgerDao.findById(id);
        if (burger == null) {  // Ideally this case won't occur because findById throws an exception.
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    // Get all burgers
    @GetMapping
    public List<Burger> getAllBurgers() {
        return burgerDao.findAll();
    }

    // Update a burger
    @PutMapping
    public Burger updateBurger(@RequestBody Burger burger) {
        Burger updatedBurger = burgerDao.update(burger);
        if (updatedBurger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return updatedBurger;
    }

    // Delete a burger by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBurger(@PathVariable Long id) {
        burgerDao.remove(id);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> getBurgersByBreadType(@PathVariable BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }



    // Find burgers by price
    @GetMapping("/price/{price}")
    public List<Burger> getBurgersByPrice(@PathVariable int price) {
        return burgerDao.findByPrice(price);
    }

    // Find burgers by content (like "Cheese")
    @GetMapping("/content/{content}")
    public List<Burger> getBurgersByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
