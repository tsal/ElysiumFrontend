package com.dstevens.event;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.Role;
import com.dstevens.user.User;
import com.dstevens.web.config.RequestingUserProvider;
import com.dstevens.web.config.ServerConfiguration;
import com.google.gson.Gson;

@Controller
public class EventController {

	private final TroupeRepository troupeRepository;
	private final EventRepository eventRepository;
	private final ServerConfiguration serverConfiguration;
	private final RequestingUserProvider userProvider;

	@Autowired
	public EventController(EventRepository eventRepository, TroupeRepository troupeRepository, ServerConfiguration serverConfiguration, RequestingUserProvider userProvider) {
		this.eventRepository = eventRepository;
		this.troupeRepository = troupeRepository;
		this.serverConfiguration = serverConfiguration;
		this.userProvider = userProvider;
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public @ResponseBody String getEvents() {
		List<DisplayableEvent> collect = StreamSupport.stream(eventRepository.findAll().spliterator(), false).
				             map((Event t) -> DisplayableEvent.reference(t)).
				             sorted().
				             collect(Collectors.toList());
		return new Gson().toJson(collect);
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public @ResponseBody String getEvent(@PathVariable Integer id) {
		Event event = eventRepository.findWithId(id);
		DisplayableEvent listable = DisplayableEvent.from(event);
		return new Gson().toJson(listable);
	}
	
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public @ResponseBody String  addEvent(HttpServletRequest request, HttpServletResponse response, @RequestBody final DisplayableEvent event) {
		Event eventToCreate = event.to(troupeRepository);
		validateThatUserCanCreate(eventToCreate);
		
		Event savedEvent = eventRepository.save(eventToCreate);
		return serverConfiguration.getHost() + "/events/" + savedEvent.getId(); 
	}

	private void validateThatUserCanCreate(Event event) {
		if(event instanceof TroupeEvent) {
			validateThatUserCanCreateTroupeEvent((TroupeEvent) event);
		}
		if(event instanceof VenueEvent) {
			validateThatUserCanCreateVenueEvent((VenueEvent) event);
		}
	}

	private void validateThatUserCanCreateVenueEvent(VenueEvent event) {
		User user = userProvider.get();
		if(!user.getRoles().contains(Role.ADMIN)) {
			throw new IllegalArgumentException("Current user cannot create a venue event");
		}
	}

	private void validateThatUserCanCreateTroupeEvent(TroupeEvent event) {
		User user = userProvider.get();
		if(!user.getRoles().contains(Role.ADMIN) && ! event.getTroupe().getStorytellers().contains(user)) {
			throw new IllegalArgumentException("Current user cannot create a troupe event for troupe " + event.getTroupe().getId());
		}
	}
}
