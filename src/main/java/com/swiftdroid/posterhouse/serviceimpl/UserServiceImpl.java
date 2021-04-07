package com.swiftdroid.posterhouse.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftdroid.posterhouse.model.AuthenticationProvider;
import com.swiftdroid.posterhouse.model.Role;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.model.UserRole;
import com.swiftdroid.posterhouse.model.UserShipping;
import com.swiftdroid.posterhouse.repo.RoleRepostiory;
import com.swiftdroid.posterhouse.repo.UserRepository;
import com.swiftdroid.posterhouse.repo.UserShippingRepository;
import com.swiftdroid.posterhouse.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepostiory roleRepostiory;
	@Autowired
	UserShippingRepository userShippingRepository;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional

	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByUsername(user.getUsername());

		if (localUser != null) {
			// throw new Exception("User already exists.Nothing will be done.");
			// LOG.info("User {} already exists.Nothing will be done.",user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepostiory.save(ur.getRole());
			}

		}

		user.getUserRoles().addAll(userRoles);

		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.setUser(user);

		user.setShoppingCart(shoppingCart);

		user.setUserShippingList(new ArrayList<UserShipping>());

		/// user.setUserPaymentList(new ArrayList<UserPayment>());
		localUser = userRepository.save(user);

		return localUser;

	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	@Transactional

	public User createNewUserAfterSuccessHandler(String email, String name, AuthenticationProvider provider) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(email);
		user.setFirstName(name);
		user.setCretedBy(name);
		user.setCretedDate(new Date());
		user.setUsername(email);
		user.setAuthProvider(provider);

		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");

		Set<UserRole> userRoles = new HashSet<>();

		for (UserRole ur : userRoles) {
			roleRepostiory.save(ur.getRole());
		}

		userRoles.add(new UserRole(user, role));
		user.getUserRoles().addAll(userRoles);
		ShoppingCart shoppingCart = new ShoppingCart();

		shoppingCart.setUser(user);

		user.setShoppingCart(shoppingCart);

		user.setUserShippingList(new ArrayList<UserShipping>());

		User localUser = userRepository.save(user);

		return localUser;
	}

	@Override
	public void updateExistingUserAfterAuth2User(User user, String name, AuthenticationProvider provider) {
		// TODO Auto-generated method stub
		user.setFirstName(name);
		user.setAuthProvider(provider);
		user.setModifiedBy(name);
		user.setModifiedDate(new Date());

		save(user);
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		// TODO Auto-generated method stub
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		System.out.println("**************************************************************"
				+ user.getUserShippingList().add(userShipping));
		save(user);
	}

	@Override
	public void setUserDefaultShipping(Long defaultShippingId, User user) {
		// TODO Auto-generated method stub
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
		for (UserShipping userShipping : userShippingList) {
			if (userShipping.getId() == defaultShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);

			}
		}
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}

}
