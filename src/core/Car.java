package core;

public class Car {
    /*Свойства объекта*/
    public String number; //номер
    public int height; // Высота
    public double weight; // Вес
    public boolean hasVehicle; // Транспортное средство или нет.
    public boolean isSpecial; // Спец. транспорт
    /* Свойство которое выводит нформацию о автоиранспорте */
    public String toString() {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }
}