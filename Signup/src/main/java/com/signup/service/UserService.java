package com.signup.service;

import com.signup.dto.LoginDto;
import com.signup.dto.UserDto;
import com.signup.enities.User;
import com.signup.reposatory.UserReposatory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
   @Autowired
     UserReposatory userRepository;
   @Autowired
     ModelMapper modelMapper;
   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


   public void saveUser(UserDto userDto) {



       userRepository.save(userDtotouser(userDto));
    }

    public UserDto signupByEmail(String emailId){

       User user = this.userRepository.findByEmailId(emailId);

       return userTouserDto(user);

   }

   //@Bean
    public String login(LoginDto loginDto) {

        User user = this.userRepository.findOneByIgnoreCaseEmailIdAndPassword(loginDto.getEmailId(), loginDto.getPassword());

        if(user==null)
            return "Not Login";
        else
            return "logged In";
   }




//        public List<UserDto> getAllEmployee() {
//
//            List<User> listEmployee = this.userRepository.findAll();
//
//            List<UserDto> userDtoList = listEmployee.stream().map(emp -> this.userTouserDto(emp)).collect(Collectors.toList());
//
//            //employeeReposatory.findAll().forEach(l1->listEmployee.add(l1));
//
//            return userDtoList;
//        }


    public UserDto userTouserDto(User user) {
   UserDto userDto=this.modelMapper.map(user,UserDto.class);

//            user.setFirstName(userDto.getFirstName());
//            user.setLastName(userDto.getLastName());
//            user.setEmail(user.getEmail());
//            user.setPassword(user.getPassword());
        return userDto;
    }

    public User userDtotouser(UserDto userDto) {
        User user =this.modelMapper.map(userDto,User.class);

//            userDto.setFirstName(user.getFirstName());
//            userDto.setLastName(user.getLastName());
//            userDto.setEmail(user.getEmail());
//            userDto.setPassword(user.getPassword());
        return user;
    }

}

//        private Role checkRoleExist() {
//            Role role = new Role();
//            role.setName("ROLE_ADMIN");
//            return roleRepository.save(role);
//        }


