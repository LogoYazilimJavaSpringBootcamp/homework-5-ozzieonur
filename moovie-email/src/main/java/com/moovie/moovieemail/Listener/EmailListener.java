package com.moovie.moovieemail.Listener;


import com.moovie.moovieemail.Dto.EmailDto;
import com.moovie.moovieemail.Model.Email;
import com.moovie.moovieemail.Repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailListener {

    final
    EmailRepository emailRepository;

    final
    ModelMapper modelMapper;

    public EmailListener(EmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }


    @RabbitListener(queues = "moovie-network.email")
    public void emailListener(EmailDto emailDto) {
        log.info("email address: {}", emailDto.getToSend());

        Email email = modelMapper.map(emailDto, Email.class); // Dto-Entity dönüşümünü uygulayan modelMapper methodu.
        emailRepository.save(email); // MongoDB veritabanına kaydeden method.
        // TO DO mail at
    }
}
