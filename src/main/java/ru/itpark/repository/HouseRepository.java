package ru.itpark.repository;

import ru.itpark.domain.House;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseRepository {
private List<House> items = new ArrayList <House>();
    public void add(House... items) {
        this.items.addAll(Arrays.asList(items));
    }
    public List<House> getAll() {
        return items;
    }
}
