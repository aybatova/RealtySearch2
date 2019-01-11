package ru.itpark.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itpark.domain.House;
import ru.itpark.repository.HouseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseServiceTest {
    private HouseService service;

    @BeforeEach
    void setUp() { // Перед каждым тестом будет вызывать этот метод
        // A-A-A
        // Arrange (подготовка данных)
        HouseRepository repository = new HouseRepository();
        repository.add(
                new House(15136, 50, 250, "Ленинский"),
                new House(77894, 15, 140, "Звениговский"),
                new House(15645, 45, 360, "Сармановский"),
                new House(16487, 28, 280, "Ленинский"),
                new House(52165, 10, 70, "Сармановский")
        );
        service = new HouseService(repository);
    }

    @Test
    public void findByPriceWithZeroResults() {
        {
            List<House> result = service.findByPrice(0, 69);
            assertEquals(0, result.size());
        }
        {
            List<House> result = service.findByPrice(400, 500);
            assertEquals(0, result.size());
        }
    }

    @Test
    public void findByPriceWithOneResults() {
        {

            int minPrice = 300;
            int maxPrice = 400;
            List<House> result = service.findByPrice(minPrice, maxPrice);
            assertEquals(1, result.size());

            int price = result.get(0).getPrice(); // result.get(0) - получение первого элемента списка
            assertTrue(price >= minPrice); // Ctrl + Alt + V
            assertTrue(price <= maxPrice);

        }
    }

    @Test
    public void findByPriceWithMultipleResults() {
        {

            int minPrice = 200;
            int maxPrice = 300;
            List<House> result = service.findByPrice(minPrice, maxPrice);
            assertEquals(2, result.size());

            for (House house : result) {
                int price = house.getPrice();
                assertTrue(price >= minPrice); // Ctrl + Alt + V
                assertTrue(price <= maxPrice);
            }

        }

    }

    @Test
    public void findByDistrictsWithZeroResult() {
        List<House> result = service.findByDistricts("Лаишевский", "Приволжский");

        assertEquals(0, result.size());
    }

    @Test
    public void findByDistrictsWithMultiplyResults() {
        List<House> result = service.findByDistricts("Ленинский", "Звениговский");

        assertEquals(3, result.size());
        assertTrue(
                result
                        .stream()
                        .allMatch(e -> List.of("Ленинский", "Звениговский").contains(e.getDistrict()))
        );
    }
}
