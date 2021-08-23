package src.model;

public class Department implements Cloneable {
    private String code;
    private String name;
    private Employee employees[];
    private final int MAX = 100;
    private int count;

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
        count = 0;
        employees = new Employee[MAX];
    }

    public void addEmployee(Employee e) {
        if (count < MAX) {
            employees[count] = e;
            count++;
        }
    }

    public void removeEmployee(String id) {
        Employee e = searchEmployeeById(id);
        if (e != null) {
            e = null;
        }
    }

    public Employee searchEmployeeById(String id) {
        for (int i = 0; i < count; i++)
            if (employees[i].getId().equals(id))
                return employees[i];
        return null;
    }

    public Employee searchEmployeeByName(String name) {
        for (int i = 0; i < count; i++)
            if (employees[i].getName().equals(name))
                return employees[i];
        return null;
    }

    public Employee searchEmployeeByCost(double min, double max) {
        for (int i = 0; i < count; i++) {
            double s = employees[i].calculateSalary();
            if (s > min && s < max)
                return employees[i];
        }
        return null;
    }

    public String searchTechnician() {
        String all = "";
        for (int i = 0; i < count; i++) {
            if (employees[i] instanceof Technician)
                all += getEmployeeInfo(employees[i]) + "\n";
        }
        return all;
    }

    public String searchPermanent() {
        String all = "";
        for (int i = 0; i < count; i++) {
            if (employees[i] instanceof Permanent)
                all += getEmployeeInfo(employees[i]) + "\n";
        }
        return all;
    }

    public String searchSubstitute() {
        String all = "";
        for (int i = 0; i < count; i++) {
            if (employees[i] instanceof Substitute)
                all += getEmployeeInfo(employees[i]) + "\n";
        }
        return all;
    }

    public String searchProfessor() {
        String all = "";
        for (int i = 0; i < count; i++) {
            if (employees[i] instanceof Professor)
                all += getEmployeeInfo(employees[i]) + "\n";
        }
        return all;
    }

    public String report() {
        return getCode() + " - " + getName() + " " + count + " funcionários(as) " + "R$"
                + String.format("%.2f", totalCost());
    }

    public String getEmployeeInfo(Employee e) {
        String all = "";
        all += e.getId() + " " + e.getName() + " " + String.format("%.2f", e.calculateSalary()) + " " + e.getLevel()
                + " ";
        if (e instanceof Technician) {
            Technician t = (Technician) e;
            all += t.getFunction();
        } else {
            Professor prof = (Professor) e;
            all += prof.getDegree() + " ";
            if (prof instanceof Permanent) {
                Permanent ef = (Permanent) prof;
                all += ef.getArea();
            } else {
                Substitute subs = (Substitute) prof;
                all += Double.toString(subs.getWorkLoad());
            }
        }
        return all;
    }

    public String getAllEmployees() {
        String all = "";
        for (int i = 0; i < count; i++) {
            Employee e = employees[i];
            if (e != null)
                all += getEmployeeInfo(e) + "\n";
        }
        return all;
    }

    public double totalCost() {
        double cost = 0.0;
        for (int i = 0; i < count; i++)
            cost += employees[i].calculateSalary();
        return cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public Department clone() throws CloneNotSupportedException {
        return (Department) super.clone();
    }
}