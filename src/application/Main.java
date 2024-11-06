package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.next();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();

            String line = br.readLine();

            while (line != null){
                String[] vect = line.split(",");
                list.add(new Employee(vect[0], vect[1], Double.parseDouble(vect[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();

            List<String> emails = list.stream()
                    .filter(x -> x.getSalary() > salary)
                    .map(Employee::getEmail)
                    .sorted()
                    .toList();

            System.out.println("Email of people whose salary is more than "+ salary);
            emails.forEach(System.out::println);

            Double sum = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.println("Sum of salary of people whose name starts with 'M': " + sum);



        } catch (IOException e){
            System.out.println("Erro: "+ e.getMessage());
        }


        sc.close();
    }
}