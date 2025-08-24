package fifth;
// 1. Handler (Интерфейс Обработчика)
interface LeaveHandler {
    void setNextHandler(LeaveHandler nextHandler);
    void handleRequest(LeaveRequest request);
}

// 2. Abstract Handler (Абстрактный обработчик)
abstract class AbstractLeaveHandler implements LeaveHandler {
    private LeaveHandler nextHandler;

    @Override
    public void setNextHandler(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(LeaveRequest request) {
        if (canHandleRequest(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Запрос на отпуск не может быть обработан.");
        }
    }

    protected abstract boolean canHandleRequest(LeaveRequest request);
    protected abstract void processRequest(LeaveRequest request);
}

// 3. ConcreteHandler (Конкретные Обработчики)
class Manager extends AbstractLeaveHandler {
    @Override
    protected boolean canHandleRequest(LeaveRequest request) {
        return request.getDays() <= 3;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("Менеджер одобрил запрос на отпуск на " + request.getDays() + " дней.");
    }
}

class DepartmentHead extends AbstractLeaveHandler {
    @Override
    protected boolean canHandleRequest(LeaveRequest request) {
        return request.getDays() <= 7;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("Руководитель отдела одобрил запрос на отпуск на " + request.getDays() + " дней.");
    }
}

class Director extends AbstractLeaveHandler {
    @Override
    protected boolean canHandleRequest(LeaveRequest request) {
        return request.getDays() <= 30;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("Директор одобрил запрос на отпуск на " + request.getDays() + " дней.");
    }
}

// 4. Request (Запрос)
class LeaveRequest {
    private String employeeName;
    private int days;

    public LeaveRequest(String employeeName, int days) {
        this.employeeName = employeeName;
        this.days = days;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getDays() {
        return days;
    }
}

public class Responsibility {
    public static void main(String[] args) {

        LeaveHandler manager = new Manager();
        LeaveHandler departmentHead = new DepartmentHead();
        LeaveHandler director = new Director();

        manager.setNextHandler(departmentHead);
        departmentHead.setNextHandler(director);

        LeaveRequest request1 = new LeaveRequest("Иванов", 2);
        LeaveRequest request2 = new LeaveRequest("Петров", 5);
        LeaveRequest request3 = new LeaveRequest("Сидоров", 15);
        LeaveRequest request4 = new LeaveRequest("Смирнов", 40); // Отклонен

        manager.handleRequest(request1); // Менеджер одобрил запрос на отпуск на 2 дней.
        manager.handleRequest(request2); // Руководитель отдела одобрил запрос на отпуск на 5 дней.
        manager.handleRequest(request3); // Директор одобрил запрос на отпуск на 15 дней.
        manager.handleRequest(request4); // Запрос на отпуск не может быть обработан.
    }
}
