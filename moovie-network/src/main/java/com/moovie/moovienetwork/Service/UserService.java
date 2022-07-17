package com.moovie.moovienetwork.Service;



import com.moovie.moovienetwork.Client.PaymentClient;
import com.moovie.moovienetwork.Dto.PaymentDto;
import com.moovie.moovienetwork.Dto.SingUpDto;
import com.moovie.moovienetwork.Dto.UserDto;
import com.moovie.moovienetwork.Model.Category;
import com.moovie.moovienetwork.Model.Enums.Role;
import com.moovie.moovienetwork.Model.Enums.UserType;
import com.moovie.moovienetwork.Model.Movie;
import com.moovie.moovienetwork.Model.User;
import com.moovie.moovienetwork.Repository.MovieRepository;
import com.moovie.moovienetwork.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
@Slf4j
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final PaymentClient paymentClient;

    final MovieRepository movieRepository;
    final ModelMapper modelMapper;

    Random rand = new Random();

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, PaymentClient paymentClient, MovieRepository movieRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.paymentClient = paymentClient;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    public SingUpDto register(SingUpDto request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        log.info(user.getFullName() + " adlı kullanıcı sisteme kayıt oldu.");

        return modelMapper.map(userRepository.save(user), SingUpDto.class);
    }

    public UserDto updateUser(UserDto request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userRepository.findByEmail(email);

        if (request.getFullName() != null) foundUser.setFullName(request.getFullName());
        if (request.getPassword() != null) foundUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        if(request.getRole() != null) foundUser.setRole(request.getRole());
        if(request.getUserType() != null){
            PaymentDto paymentDto = new PaymentDto(foundUser.getEmail(), LocalDate.now(),request.getUserType().toString(), 0.00);

            if(request.getUserType() == UserType.MONTHLY){
                paymentDto.setDate(LocalDate.now().plusMonths(1));
                paymentDto.setAmount(100.00);
            }
            else if(request.getUserType() == UserType.HALFYEARLY){
                paymentDto.setDate(LocalDate.now().plusMonths(6));
                paymentDto.setAmount(300.00);
            }
            else if(request.getUserType() == UserType.YEARLY){
                paymentDto.setDate(LocalDate.now().plusMonths(12));
                paymentDto.setAmount(500.00);
            }
            else if(request.getUserType() == UserType.INFINITELY){
                paymentDto.setDate(LocalDate.now().plusYears(50));
                paymentDto.setAmount(1000.00);
            }

            paymentClient.createPayment(paymentDto);
            foundUser.setUserType(request.getUserType());
        }

        log.info(foundUser.getFullName() + " adlı kullanıcı güncellendi.");
        return modelMapper.map(userRepository.save(foundUser), UserDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(usernameOrEmail);
    }


    public String likeMovie(String movieTitle) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user.getLikedMovies().size() >= 3 && user.getUserType() == UserType.FREE && user.getRole() != Role.ADMIN) { // Admin olmayan ve ücretsiz üye olan kullanıcılar yalnızca 3 film beğenebilir.
            return "Ücretsiz üye olan kullanıcılar maksimum 3 adet film beğenebilir.";
        }
        else {
            Movie movie = movieRepository.findByTitle(movieTitle);
            user.getLikedMovies().add(movie);

            userRepository.save(user);

            log.info(user.getFullName() + " adlı kullanıcı " + movieTitle + " filmini beğendi.");

            Category genre =  movie.getGenres().stream().toList().get(rand.nextInt(movie.getGenres().size()));
            StringBuilder recommends = new StringBuilder();

            for (Movie movieIndex: movieRepository.findAll()){
                if(movieIndex.getGenres().contains(genre)){
                    recommends.append(movieIndex.getTitle()).append("\n");
                }
            }

            return movie.getTitle() + " filmini beğendiniz. \n" + "İşte size önerebileceğimiz bazı filmler: \n" + recommends;
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Tüm kullanıcılar görüntülendi.");
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    public UserDto getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);
        log.info("Tüm kullanıcılar görüntülendi.");
        return modelMapper.map(user,UserDto.class);
    }
}
