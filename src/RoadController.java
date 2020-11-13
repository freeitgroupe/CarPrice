import core.*;
import core.Camera;

import java.util.Scanner; //импорт сканнера

public class RoadController {
    private static double passengerCarMaxWeight = 3500.0; // kg, максимамльная вес пассажирского авто
    private static int passengerCarMaxHeight = 2000; // mm, максимамльная высота пассажирского авто
    private static int controllerMaxHeight = 4000; // mm, максимальная высота

    private static int passengerCarPrice = 100; // RUB, стоимость проезда пассажирского авто
    private static int cargoCarPrice = 250; // RUB, карга стоимость
    private static int vehicleAdditionalPrice = 200; // RUB, дополнительная стоимость

    public static void main(String[] args) {
        /*выводим строку на экран*/
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in); //
        int carsCount = scanner.nextInt(); //считываем целое число с консоли

        for (int i = 0; i < carsCount; i++) {
            Car car = Camera.getNextCar();
            System.out.println(car); // выводим данные в консоль

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car);
            if (price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car) {
        int carHeight = car.height; // получаем высоту авто
        int price = 0; // по умолчанию цена 0
        if (carHeight > controllerMaxHeight) {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;

        } else if (carHeight > passengerCarMaxHeight) {
            /* Вес авто */
            double weight = car.weight;

            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight) {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }else {
                price = cargoCarPrice; //
            }
        } else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay() {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason) {
        System.out.println("Проезд невозможен: " + reason);
    }
}