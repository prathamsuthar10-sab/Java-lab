abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract int monthlySalary();
}

class FullTime extends Employee {
    int salary;

    FullTime(String name, int id, int salary) {
        super(name, id);
        this.salary = salary;
    }

    @Override
    int monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {
    int hoursWorked;
    int hourRate;

    PartTime(String name, int id, int hoursWorked, int hourRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourRate = hourRate;
    }

    @Override
    int monthlySalary() {
        return hoursWorked * hourRate;
    }
}

class Intern extends Employee {
    int stipend;

    Intern(String name, int id, int stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    int monthlySalary() {
        return stipend;
    }
}

public class Payroll {
    public static void main(String[] args) {

        Employee[] employees = {
            new FullTime("Rishit", 101, 50000),
            new PartTime("keval", 102, 80, 200),
            new Intern("shreyansh", 103, 10000),
            new FullTime("hasti", 104, 45000),
            new PartTime("Henil", 105, 100, 150)
        };

        int total = 0;

        for (Employee employee : employees) {

            int salary = employee.monthlySalary();

            System.out.println(
                employee.name + " (ID: " + employee.id + ") Salary: " + salary
            );

            total += salary;

            if (employee instanceof Intern) {
                System.out.println("Note: This employee is an intern.");
            }
        }

        System.out.println("Total Payroll: " + total);
    }
}