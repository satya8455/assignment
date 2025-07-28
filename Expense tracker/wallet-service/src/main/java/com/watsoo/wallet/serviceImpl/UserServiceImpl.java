package com.watsoo.wallet.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.watsoo.wallet.dto.LoginRequest;
import com.watsoo.wallet.dto.LoginResponseDto;
import com.watsoo.wallet.dto.RegistrationDto;
import com.watsoo.wallet.dto.Response;
import com.watsoo.wallet.dto.UserDto;
import com.watsoo.wallet.entity.User;
import com.watsoo.wallet.enums.Role;
import com.watsoo.wallet.repository.UserRepository;
import com.watsoo.wallet.security.CustomizedUserDetailsService;
import com.watsoo.wallet.service.JwtService;
import com.watsoo.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CustomizedUserDetailsService customizedUserDetailsService ;

	@Override
	public Response<Object> generateToken(LoginRequest loginRequest) {
		try {
			Optional<User> userOptional = userRepository.findByEmail(loginRequest.getUsername());
			if (userOptional.isPresent() && userOptional.get().getIsActive()) {
				User user = userOptional.get();

				if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.USER)
						|| user.getRole().equals(Role.USER)) {
					if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

						String token = jwtService.generateToken(loginRequest.getUsername());
						LoginResponseDto response = new LoginResponseDto();
						response.setToken(token);
						response.setUserName(user.getName());
						response.setEmail(user.getEmail());
						response.setRoleType(user.getRole());
						return new Response<>(HttpStatus.OK.value(), "Login Success.", response);
					}
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid credentials.", null);
				}
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "invalid credential", null);
			}
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "invalid credential", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "something went wrong", null);
		}
	}

	@Override
	public Response<Object> registerUser(RegistrationDto registrationDto) {
		try {

			if (registrationDto.getId() == null) {
				Optional<User> userOptional = userRepository.findByEmailOrPhoneNumber(registrationDto.getEmail(),
						registrationDto.getPhoneNumber());
				if (userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User already exists", null);
				}
				User user = new User();
				user.setName(registrationDto.getName());
				user.setEmail(registrationDto.getEmail());
				user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
				user.setPhoneNumber(registrationDto.getPhoneNumber());
				user.setRole(Role.USER);
				user.setIsActive(true);
				user.setCreatedAt(new Date());
				User savedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Created successfully",
						"userId :" + savedUser.getId());
			} else {
				Optional<User> userOptional = userRepository.findById(registrationDto.getId());
				if (!userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
				}
				User user = userOptional.get();
				if (registrationDto.getName() != null) {
					user.setName(registrationDto.getName());
				}
				if (registrationDto.getEmail() != null) {
					user.setEmail(registrationDto.getEmail());
				}
				if (registrationDto.getPhoneNumber() != null) {
					user.setPhoneNumber(registrationDto.getPhoneNumber());
				}
				user.setUpdatedAt(new Date());
				User updatedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Updated successfully",
						"userId :" + updatedUser.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "something went wrong", null);
		}
	}

	@Override
	public Response<Object> registerAdmin(RegistrationDto registrationDto) {
		try {

			if (registrationDto.getId() == null) {
				Optional<User> userOptional = userRepository.findByEmailOrPhoneNumber(registrationDto.getEmail(),
						registrationDto.getPhoneNumber());
				if (userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User already exists", null);
				}
				User user = new User();
				user.setName(registrationDto.getName());
				user.setEmail(registrationDto.getEmail());
				user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
				user.setPhoneNumber(registrationDto.getPhoneNumber());
				user.setRole(Role.ADMIN);
				user.setIsActive(true);
				user.setCreatedAt(new Date());
				User savedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Created successfully",
						"userId :" + savedUser.getId());
			} else {
				Optional<User> userOptional = userRepository.findById(registrationDto.getId());
				if (!userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
				}
				User user = userOptional.get();
				if (registrationDto.getName() != null) {
					user.setName(registrationDto.getName());
				}
				if (registrationDto.getEmail() != null) {
					user.setEmail(registrationDto.getEmail());
				}
				if (registrationDto.getPhoneNumber() != null) {
					user.setPhoneNumber(registrationDto.getPhoneNumber());
				}
				user.setUpdatedAt(new Date());
				User updatedUser = userRepository.save(user);
				return new Response<>(HttpStatus.CREATED.value(), "Updated successfully",
						"userId :" + updatedUser.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "something went wrong", null);
		}
	}

	
	@Override
		public Response<?> getAllUsers(Long id) {
			try {
				if(id == null) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User-ID is required", null);
				}
				Optional<User> userOptional = userRepository.findById(id);
				if (!userOptional.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "User does not exist", null);
				}
	 
				if (userOptional.get().getRole().equals(Role.ADMIN)) {
					List<User> users = userRepository.findAll();
					List<UserDto> userDtos = users.stream().map(e -> e.convertToDto()).collect(Collectors.toList());
					return new Response<>(HttpStatus.OK.value(), "OK", userDtos);
				}
	 
				return new Response<>(HttpStatus.OK.value(), "You don't have permission for this route", null);
			} catch (Exception e) {
				e.printStackTrace();
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "something went wrong", null);
			}
		}
	 
	 

	@Override
	public Response<?> getUserById(Long id) {
		try {
			// TODO Auto-generated method stub
			if(id==null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(),"Please provide userid",null);
			}
			Optional<User> optUsers = userRepository.findById(id);
			if (optUsers.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Users not found", null);
			}

			UserDto userDtos = optUsers.get().convertToDto();
			return new Response<>(HttpStatus.OK.value(), "Success", userDtos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);

		}

	}
}
