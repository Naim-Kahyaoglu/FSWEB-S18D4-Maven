package com.workintech.s18d1.dao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import java.util.List;

public interface BurgerDao {

    Burger save(Burger burger);

    // Updated: return Burger instead of Optional<Burger>
    Burger findById(Long id);

    List<Burger> findAll();

    Burger update(Burger burger);

    Burger remove(Long id);

    List<Burger> findByBreadType(BreadType breadType);


    List<Burger> findByPrice(int price);

    List<Burger> findByContent(String content);
}
