package com.workintech.s18d1.dao;


import com.workintech.s18d1.entity.BreadType;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;

    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        return entityManager.createQuery("FROM Burger", Burger.class).getResultList();
    }

    @Override
    public Burger update(Burger burger) {
        Burger updated = entityManager.merge(burger);
        if (updated == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return updated;
    }

    @Override
    public Burger remove(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if (burger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        entityManager.remove(burger);
        return burger;  // Return the removed burger
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        return entityManager.createQuery("FROM Burger b WHERE b.breadType = :breadType", Burger.class)
                .setParameter("breadType", breadType.name())
                .getResultList();
    }

    @Override
    public List<Burger> findByPrice(int price) {
        return entityManager.createQuery("FROM Burger b WHERE b.price > :price ORDER BY b.price DESC", Burger.class)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        return entityManager.createQuery("FROM Burger b WHERE b.contents LIKE :content", Burger.class)
                .setParameter("content", "%" + content + "%")
                .getResultList();
    }
}
