import java.util.Scanner;

interface Connectable {
    void connect();
    void disconnect();
}

interface InformationProvider {
    String getInfo();
}

abstract class ElectronicDevice implements InformationProvider {
    private String brand;
    protected double price;

    public ElectronicDevice(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Cable extends ElectronicDevice implements Connectable {
    private int length;

    public Cable(String brand, double price, int length) {
        super(brand, price);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void connect() {
        System.out.println("Кабель подключен.");
    }

    @Override
    public void disconnect() {
        System.out.println("Кабель отключен.");
    }

    @Override
    public String getInfo() {
        return "Кабель [бренд=" + getBrand() + ", цена=" + getPrice() + ", длина=" + length + "]";
    }
}

class Capability extends ElectronicDevice implements Connectable, InformationProvider {
    private String feature;

    public Capability(String brand, double price, String feature) {
        super(brand, price);
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public void connect() {
        System.out.println("Возможность подключена.");
    }

    @Override
    public void disconnect() {
        System.out.println("Возможность отключена.");
    }

    @Override
    public String getInfo() {
        return "Возможность [бренд=" + getBrand() + ", цена=" + getPrice() + ", особенность=" + feature + "]";
    }
}

class Case extends ElectronicDevice implements InformationProvider {
    private String material;

    public Case(String brand, double price, String material) {
        super(brand, price);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String getInfo() {
        return "Корпус [бренд=" + getBrand() + ", цена=" + getPrice() + ", материал=" + material + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connectable[] connectableDevices = new Connectable[10];
        InformationProvider[] infoProviderDevices = new InformationProvider[10];
        int count = 0;
        boolean continueInput = true;

        while (continueInput && count < connectableDevices.length) {
            System.out.println("Введите данные для устройства " + (count + 1) + ":");
            System.out.print("Бренд: ");
            String brand = scanner.nextLine();
            System.out.print("Цена: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Выберите тип устройства:");
            System.out.println("1. Кабель");
            System.out.println("2. Возможность");
            System.out.println("3. Корпус");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите длину кабеля: ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    Cable cable = new Cable(brand, price, length);
                    infoProviderDevices[count] = cable;
                    break;
                case 2:
                    System.out.print("Введите особенность устройства: ");
                    String feature = scanner.nextLine();
                    Capability capability = new Capability(brand, price, feature);
                    infoProviderDevices[count] = capability;
                    break;
                case 3:
                    System.out.print("Введите материал корпуса: ");
                    String material = scanner.nextLine();
                    Case caseDevice = new Case(brand, price, material);
                    infoProviderDevices[count] = caseDevice;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1, 2 или 3.");
                    continue;
            }

            count++;

            System.out.print("Продолжить ввод данных? (y/n): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                continueInput = false;
            }
        }

        System.out.println("Информация об устройствах:");
        for (InformationProvider device : infoProviderDevices) {
            if (device != null) {
                System.out.println(device.getInfo());
            }
        }

        scanner.close();
    }
}
