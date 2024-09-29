package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("Entre com os dados do contrato: ");
            System.out.print("Número: ");
            int num = sc.nextInt();
            System.out.print("Data (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(sc.next(),fmt);
            System.out.print("Valor do contrato: ");
            double totalValue = sc.nextDouble();
            Contract contract = new Contract(num,date,totalValue);
            System.out.print("Entre com o numero de parcelas: ");
            int numParcelas = sc.nextInt();
            ContractService contractService = new ContractService(new PaypalService());
            contractService.processContarct(contract,numParcelas);
            System.out.println("Parcelas:");

            for (Installment installment: contract.getInstallments()){
                System.out.println(installment);
            }

        }
    }
}
