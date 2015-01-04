package com.dstevens.characters;

import java.time.Clock;
import java.util.Date;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.characters.status.PlayerStatus;
import com.dstevens.characters.status.PlayerStatusChange;
import com.dstevens.troupes.Troupe;
import com.dstevens.troupes.TroupeRepository;
import com.dstevens.users.User;
import com.dstevens.users.UserRepository;

@Service
public class PlayerCharacterService {

	private final PlayerCharacterRepository repository;
	private final PlayerCharacterFactory factory;
	private final UserRepository userRepository;
	private final TroupeRepository troupeRepository;
	private final Supplier<Clock> clockSupplier;

	@Autowired
	public PlayerCharacterService(PlayerCharacterRepository repository, PlayerCharacterFactory factory,
			                      UserRepository userRepository, TroupeRepository troupeRepository,
			                      Supplier<Clock> clockSupplier) {
		this.repository = repository;
		this.factory = factory;
		this.userRepository = userRepository;
		this.troupeRepository = troupeRepository;
		this.clockSupplier = clockSupplier;
	}
	
	public PlayerCharacter createCharacter(User user, Troupe troupe, String characterName) {
		PlayerCharacter character = repository.update(factory.createPlayerCharacter(characterName, troupe.getSetting()).
				                               beginCreation().
				                               changeActivityStatus(secondaryCharacterStatus()));
		userRepository.save(user.withCharacter(character));
		troupeRepository.save(troupe.withCharacter(character));
		return character;
	}

	private PlayerStatusChange secondaryCharacterStatus() {
		return new PlayerStatusChange(PlayerStatus.SECONDARY, Date.from(clockSupplier.get().instant()));
	}
	
	public PlayerCharacter getCharacter(String id) {
		return repository.findWithId(id);
	}

	public void save(PlayerCharacter character) {
		repository.update(character);
	}
	
}
