package fifth;
// 1. Product (Продукт)
class Computer {
    private String cpu;
    private String ram;
    private String hdd;

    // Конструктор теперь private, чтобы его можно было вызывать только из Builder
    private Computer(String cpu, String ram, String hdd) {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                '}';
    }

    // 2. Builder (Вложенный класс)
    public static class Builder {
        private String cpu;
        private String ram;
        private String hdd;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this; // Возвращаем Builder для цепочки вызовов
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setHdd(String hdd) {
            this.hdd = hdd;
            return this;
        }

        public Computer build() {
            // Простая валидация (можно сделать более сложной)
            if (cpu == null || ram == null || hdd == null) {
                throw new IllegalArgumentException("CPU, RAM, и HDD обязательны!");
            }
            return new Computer(cpu, ram, hdd);
        }
    }
}

// 3. Client (Клиент)
public class Builder {
    public static void main(String[] args) {
        // Используем Builder для создания объекта Computer
        Computer computer = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("8GB")
                .setHdd("1TB")
                .build();

        System.out.println(computer);
    }
}
