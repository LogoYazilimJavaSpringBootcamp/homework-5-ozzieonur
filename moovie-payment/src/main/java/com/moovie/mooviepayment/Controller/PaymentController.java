package com.moovie.mooviepayment.Controller;

import com.moovie.mooviepayment.Dto.PaymentDto;
import com.moovie.mooviepayment.Service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentDto createPayment(@RequestBody PaymentDto paymentDto){
        return paymentService.createPayment(paymentDto);
    }
}
