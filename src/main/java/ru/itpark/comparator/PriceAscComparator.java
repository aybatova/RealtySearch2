package ru.itpark.comparator;

import ru.itpark.domain.House;

import java.util.Comparator;

public class PriceAscComparator implements Comparator<House> {
        @Override
        public int compare(House o1, House o2) {
            return o1.getPrice() - o2.getPrice();
        }

    }


