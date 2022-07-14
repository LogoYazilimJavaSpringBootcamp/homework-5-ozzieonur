package com.example.moovienetwork.Service;



import com.example.moovienetwork.Dto.SingUpDto;
import com.example.moovienetwork.Dto.UserDto;
import com.example.moovienetwork.Model.Category;
import com.example.moovienetwork.Model.Enums.Role;
import com.example.moovienetwork.Model.Enums.UserType;
import com.example.moovienetwork.Model.Movie;
import com.example.moovienetwork.Model.User;
import com.example.moovienetwork.Repository.MovieRepository;
import com.example.moovienetwork.Repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final MovieRepository movieRepository;
    final ModelMapper modelMapper;

    Random rand = new Random();

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MovieRepository movieRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    public SingUpDto register(SingUpDto request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        return modelMapper.map(userRepository.save(user), SingUpDto.class);
    }

    public UserDto updateUser(UserDto request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userRepository.findByEmail(email);
        if (request.getFullName() != null) foundUser.setFullName(request.getFullName());
        if (request.getPassword() != null) foundUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        if(request.getRole() != null) foundUser.setRole(request.getRole());
        if(request.getUserType() != null) foundUser.setUserType(request.getUserType());
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
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    public UserDto getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);

        return modelMapper.map(user,UserDto.class);
    }
}
