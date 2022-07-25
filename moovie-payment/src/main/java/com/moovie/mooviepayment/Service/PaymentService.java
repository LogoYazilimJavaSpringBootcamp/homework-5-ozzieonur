package com.moovie.mooviepayment.Service;

import com.moovie.mooviepayment.Dto.PaymentDto;
import com.moovie.mooviepayment.Model.Payment;
import com.moovie.mooviepayment.Repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    final PaymentRepository paymentRepository;

    final ModelMapper modelMapper;

    public PaymentService(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);

        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }
}
