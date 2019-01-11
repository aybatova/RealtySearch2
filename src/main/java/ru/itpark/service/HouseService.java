package ru.itpark.service;

import ru.itpark.comparator.PriceAscComparator;
import ru.itpark.domain.House;
import ru.itpark.repository.HouseRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseService {
    private final HouseRepository repository;

    public HouseService(HouseRepository repository) {
        this.repository = repository;
    }

    public void add(House... items) {
        repository.add(items);
    }

    public List<House> findByDistricts(String... districts) {
        // throw new UnsupportedOperationException(); // говорит, что операция не реализована
        // IllegalArgumentException()
        List<House> result = new ArrayList<>();
        List<String> districtsList = Arrays.asList(districts);

        for (House house : repository.getAll()) {
            // List -> contains -> equals() -> String
            if (districtsList.contains(house.getDistrict())) {
                result.add(house);
            }
        }

        return result;
    }

    public List<House> findByPrice(int minPrice, int maxPrice) {
        List<House> result = new ArrayList<>();

        for (House house : repository.getAll()) {
            int housePrice = house.getPrice();


            if (housePrice >= minPrice && housePrice<= maxPrice) {
                result.add(house);
            }
        }

        result.sort(new PriceAscComparator());
        return result;
    }
}
