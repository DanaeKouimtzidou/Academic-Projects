package ui;

import java.util.List;
import java.util.Scanner;

import exception.InvalidAgeException;
import exception.InvalidEmailException;
import exception.InvalidNameException;
import exception.InvalidSalaryException;
import model.Employee;
import service.EmployeeService;
import service.ValidationService;
import model.Department;
import service.DepartmentService;

public class ConsoleMenu {

	private Scanner scanner;
	private EmployeeService employeeService;
	private ValidationService validationService;
	private DepartmentService departmentService;

    public ConsoleMenu() {

        scanner = new Scanner(System.in);
        employeeService = new EmployeeService();
        validationService = new ValidationService();
        departmentService = new DepartmentService();

    }

    public void start() {

        int choice;

        do {

            printMenu();

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid choice.");
                scanner.nextLine();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

            case 1:
                addEmployee();
                break;

            case 2:
                showEmployees();
                break;

            case 3:
                findEmployee();
                break;

            case 4:
                deleteEmployee();
                break;

            case 5:
                updateSalary();
                break;

            case 6:
                departmentMenu();
                break;

            case 7:
                System.out.println("Application closed.");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        } while (choice != 7);
        
    }

    private void printMenu() {

        System.out.println("\n==============================");
        System.out.println(" Employee Management System");
        System.out.println("==============================");
        System.out.println("1. Add Employee");
        System.out.println("2. Show Employees");
        System.out.println("3. Find Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Update Salary");
        System.out.println("6. Departments");
        System.out.println("7. Exit");
        System.out.print("Choice: ");

    }

    private void addEmployee() {

        String firstName;

        while (true) {

            try {

                System.out.print("First Name: ");
                firstName = scanner.nextLine();

                validationService.validateName(firstName);

                break;

            } catch (InvalidNameException e) {

                System.out.println(e.getMessage());

            }

        }

        String lastName;

        while (true) {

            try {

                System.out.print("Last Name: ");
                lastName = scanner.nextLine();

                validationService.validateName(lastName);

                break;

            } catch (InvalidNameException e) {

                System.out.println(e.getMessage());

            }

        }

        String email;

        while (true) {

            try {

                System.out.print("Email: ");
                email = scanner.nextLine();

                validationService.validateEmail(email);

                break;

            } catch (InvalidEmailException e) {

                System.out.println(e.getMessage());

            }

        }

        int age;

        while (true) {

            try {

                System.out.print("Age: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                    System.out.print("Age: ");
                }

                age = scanner.nextInt();
                scanner.nextLine();

                validationService.validateAge(age);

                break;

            } catch (InvalidAgeException e) {

                System.out.println(e.getMessage());

            }

        }

        double salary;

        while (true) {

            try {

                System.out.print("Salary: ");

                while (!scanner.hasNextDouble()) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                    System.out.print("Salary: ");
                }

                salary = scanner.nextDouble();
                scanner.nextLine();

                validationService.validateSalary(salary);

                break;

            } catch (InvalidSalaryException e) {

                System.out.println(e.getMessage());

            }

        }

        System.out.println("\nAvailable Departments:");

        List<Department> departments = departmentService.getAllDepartments();

        if (departments.isEmpty()) {

            System.out.println("No departments found.");
            return;

        }

        for (Department department : departments) {

            System.out.println(department.getId() + ". " + department.getName());

        }

        Department selectedDepartment = null;

        while (selectedDepartment == null) {

            System.out.print("Department Id: ");

            while (!scanner.hasNextInt()) {

                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                System.out.print("Department Id: ");

            }

            int departmentId = scanner.nextInt();
            scanner.nextLine();

            selectedDepartment = departmentService.findDepartmentById(departmentId);

            if (selectedDepartment == null) {

                System.out.println("Department not found.");

            }

        }

        Employee employee = new Employee(
                0,
                firstName,
                lastName,
                email,
                salary,
                age,
                selectedDepartment
        );

        employeeService.addEmployee(employee);

        System.out.println("Employee added successfully.");

    }

    private void showEmployees() {

        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

    private void findEmployee() {

        System.out.print("Id: ");
        int id = scanner.nextInt();

        Employee employee = employeeService.findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println(employee);
        }

    }

    private void deleteEmployee() {

        System.out.print("Email: ");
        int id = scanner.nextInt();

        employeeService.deleteEmployee(id);

        System.out.println("Employee deleted.");
    }

    private void updateSalary() {

        System.out.print("Email: ");
        int id = scanner.nextInt();

        double salary;

        while (true) {

            try {

                System.out.print("New Salary: ");

                while (!scanner.hasNextDouble()) {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                    System.out.print("New Salary: ");
                }

                salary = scanner.nextDouble();
                scanner.nextLine();

                validationService.validateSalary(salary);

                break;

            } catch (InvalidSalaryException e) {

                System.out.println(e.getMessage());

            }

        }

        employeeService.updateSalary(id, salary);

        System.out.println("Salary updated.");
    }
    
    private void departmentMenu() {

        int choice;

        do {

            System.out.println("\n===== Departments =====");
            System.out.println("1. Add Department");
            System.out.println("2. Show Departments");
            System.out.println("3. Delete Department");
            System.out.println("4. Back");
            System.out.print("Choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid choice.");
                scanner.nextLine();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addDepartment();
                    break;

                case 2:
                    showDepartments();
                    break;

                case 3:
                    deleteDepartment();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid choice.");

            }

        } while (choice != 4);

    }
    
    private void addDepartment() {

        System.out.print("Department Name: ");
        String name = scanner.nextLine();

        Department department = new Department(0, name);

        departmentService.addDepartment(department);

        System.out.println("Department added successfully.");

    }
    
    private void showDepartments() {

        List<Department> departments = departmentService.getAllDepartments();

        if (departments.isEmpty()) {

            System.out.println("No departments found.");
            return;

        }

        for (Department department : departments) {
            System.out.println(department);
        }

    }
    
    private void deleteDepartment() {

        System.out.print("Department Name: ");
        String name = scanner.nextLine();

        departmentService.deleteDepartment(name);

        System.out.println("Department deleted.");

    }

}