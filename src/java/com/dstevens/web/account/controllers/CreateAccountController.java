package com.dstevens.web.account.controllers;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.Year;
import java.util.Date;
import java.util.function.Supplier;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dstevens.config.Authorization;
import com.dstevens.mail.MailMessageFactory;
import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.user.ElysiumUserDetailsService;
import com.dstevens.user.Role;
import com.dstevens.user.User;
import com.dstevens.user.UserDao;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronagePaymentReceipt;
import com.dstevens.user.patronage.PaymentType;

import static com.dstevens.collections.Sets.set;
 
@Controller
public class CreateAccountController {
	
	private final UserDao userDao;
	private final MailMessageFactory messageFactory;
	private final ClockSupplier clockSupplier;
	private final Supplier<PasswordEncoder> passwordEncoderSupplier;
	private final ElysiumUserDetailsService userService;

	@Autowired
	public CreateAccountController(UserDao userDao, Supplier<PasswordEncoder> passwordEncoderSupplier, ElysiumUserDetailsService userService, 
			                       MailMessageFactory messageFactory, ClockSupplier clockSupplier) {
		this.userDao = userDao;
		this.passwordEncoderSupplier = passwordEncoderSupplier;
		this.userService = userService;
		this.messageFactory = messageFactory;
		this.clockSupplier = clockSupplier;
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.GET)
	public ModelAndView createAccountPage() {
		return new ModelAndView("/account/createAccount");
	}
	
	@RequestMapping(value = { "/createAccount"}, method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request, HttpServletResponse response, 
			                          @RequestParam(value = "email") String email,
			                          @RequestParam(value = "password") String password,
			                          @RequestParam(value = "firstName", required=false) String firstName,
			                          @RequestParam(value = "lastName", required=false) String lastName,
			                          @RequestParam(value = "originalUsername", required=false) String originalUsername,
			                          @RequestParam(value = "paymentReceiptIdentifier", required=false) String paymentReceiptIdentifier) {
		if(userDao.findWithEmail(email) != null) {
			ModelAndView model = new ModelAndView("/account/createAccount");
			model.addObject("error", "An account already exists for user with email address " + email);
			return model;
		}
		User user = new User(email, passwordEncoderSupplier.get().encode(password), set(Role.USER)).withFirstName(firstName).withLastName(lastName);
		if(originalUsername != null && !originalUsername.trim().isEmpty()) {
			Instant now = clockSupplier.get().instant();
			user = user.withPatronage(new Patronage(Year.now(clockSupplier.get()).getValue(), Date.from(now), originalUsername));
			if(paymentReceiptIdentifier != null && !paymentReceiptIdentifier.trim().isEmpty()) {
				user.getPatronage().getPayments().add(new PatronagePaymentReceipt(PaymentType.PAYPAL, new BigDecimal("20.00"), paymentReceiptIdentifier, Date.from(now)));
			}
		}
		User newUser = userDao.save(user);
		sendConfirmatoryEmailTo(email);
		sendAdminEmailFor(newUser);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(newUser, null, newUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
			
		Authorization authorization = userService.authorize(authentication);
		response.addHeader("AUTHORIZATION", authorization.getToken());
		Cookie cookie = new Cookie("AUTHORIZATION", authorization.getToken());
		cookie.setPath("/");
		response.addCookie(cookie);
			
		return new ModelAndView(new RedirectView("/user/main"));
	}

	private void sendAdminEmailFor(User user) {
		StringBuilder body = new StringBuilder();
		body.append(user.getEmail() + " has just created an account on the database.");
		if(user.getPatronage() != null) {
			body.append("\nTheir original username in the old database is " + user.getPatronage().getOriginalUsername());
			if(!user.getPatronage().getPayments().isEmpty()) {
				body.append("\nTheir paypal receipt id for paying for patronage is " + user.getPatronage().getPayments().get(0).getPaymentReceiptIdentifier());
			}
		}
		if(sendEmail()) {
			messageFactory.message().
			from("database@undergroundtheater.org", "UT Database").
			to("board@undergroundtheater.org").
			subject("A new Underground Theater User Account has been created").
			body(body.toString()).
			send();		
		}
	}

	private boolean sendEmail() {
		return Boolean.getBoolean(System.getProperty("email", "false"));
	}

	private void sendConfirmatoryEmailTo(String email) {
		if(sendEmail()) {
			messageFactory.message().
				from("database@undergroundtheater.org", "UT Database Admin").
				to(email).
				subject("Your Underground Theater User Account has been created").
				body("Thank you for creating an account with Underground Theater's character database.").
				send();
		}
	}
}