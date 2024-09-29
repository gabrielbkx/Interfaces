package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }
    public void processContarct(Contract contract,int mounths) {

        double basiclQuota = contract.getTotalValue() / mounths;

        for (int i = 1 ; i <= mounths ; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            double interest = onlinePaymentService.interest(basiclQuota,i);
            double fee = onlinePaymentService.paymentFee(basiclQuota + interest);
            double quota = basiclQuota + interest + fee;
            contract.getInstallments().add(new Installment(dueDate,quota));
        }

    }
}
