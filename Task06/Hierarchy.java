public class Hierarchy {
  static class Vehicle {
    Vehicle() {
      System.out.println("Vehicle constructor");
    }
  }

  static class Car extends Vehicle {
    Car() {
      super();
      System.out.println("Car constructor");
    }
  }

  static class ElectricCar extends Car {
    ElectricCar() {
      super();
      System.out.println("ElectricCar constructor");
    }
  }

  public static void main(String[] args) {
    new ElectricCar();
  }
}
