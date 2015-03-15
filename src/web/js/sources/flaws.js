angular.module('sources.flaws', ['sources.settings']).
factory('flawSource', ['settingsSource', function(settingsSource) {
	var map = [];
	var i = -1;
	var types = ['GENERAL', 'MORALITY', 'RARITY', 'CLAN', 'BLOODLINE', 'SETTING'];
	var settings = settingsSource.get(0);
	var bi = -1;
	map[++i] = {"name": "Addiction", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Amnesia", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Archaic", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Awkward Mobility", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Bad Sight", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Beacon of the Unholy", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Beast in the Mirror", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Bestial Instinct", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Blood Rot", "ordinal": i, "type": types[0], "rating": 5};
    map[++i] = {"name": "Bound to the Earth", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Blunted Fangs", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Cannot Cross running water", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Careless", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Childlike", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Conspicious Consumption", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Curiousity", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Cursed", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Dark Fate", "ordinal": i, "type": types[0], "rating": 5};
    map[++i] = {"name": "Dark Secret", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Death Sight", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Deep Sleeper", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Disease Carrier", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Dull", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Eerie Presence", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Flesh of the Corpse", "ordinal": i, "type": types[0], "rating": 5};
    map[++i] = {"name": "Fragile Bones", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Gehenna Prophet", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Grip of the Damned", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Hard of Hearing", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Haunted", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Hunted", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Infamous Brood", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Illiterate", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Impatient", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Inept", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Intolerance", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Known to be Dead", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Lesser Generation (1)", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Lesser Generation (2)", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Low Pain Threshold", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Magic Susceptibility", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Methuselah's Thirst", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Nightmares", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Necrophile", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Notoriety", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Obvious Predator", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "One Eye", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Overconfident", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Permanent Wound", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Poseidon's Call", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Pied Piper", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Prey Exclusion", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Repelled by Religion", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Short Attention Span", "ordinal": i, "type": types[0], "rating": 4};
    map[++i] = {"name": "Short Fuse", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Slow Healing", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Slow Reactions", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Stolen Potential", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Thin Blooded", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Thirst for Innocence", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Trouble Magnet", "ordinal": i, "type": types[0], "rating": 1};
    map[++i] = {"name": "Vulerable to Silver", "ordinal": i, "type": types[0], "rating": 2};
    map[++i] = {"name": "Weak Willed", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Weak Stomach", "ordinal": i, "type": types[0], "rating": 3};
    map[++i] = {"name": "Accused of Heresy", "ordinal": i, "type": types[5], "setting": settings[0], "rating": 2};
    map[++i] = {"name": "Forgiven Diablerie", "ordinal": i, "type": types[5], "setting": settings[0], "rating": 1};
    map[++i] = {"name": "Sectarian", "ordinal": i, "type": types[5], "setting": settings[0], "rating": 2};
    map[++i] = {"name": "Tainted Embrace", "ordinal": i, "type": types[5], "setting": settings[0], "rating": 2};
    map[++i] = {"name": "Untrained Combatant", "ordinal": i, "type": types[5], "setting": settings[0], "rating": 3};
    map[++i] = {"name": "Crisis of Faith", "ordinal": i, "type": types[5], "setting": settings[2], "rating": 2};
    map[++i] = {"name": "Mistrusted", "ordinal": i, "type": types[5], "setting": settings[2], "rating": 1};
    map[++i] = {"name": "Soul Shard", "ordinal": i, "type": types[5], "setting": settings[2], "rating": 3};
    map[++i] = {"name": "Tenuous Loyalty", "ordinal": i, "type": types[5], "setting": settings[2], "rating": 3};
    map[++i] = {"name": "Bastard Childe", "ordinal": i, "type": types[5], "setting": settings[1], "rating": 2};
    map[++i] = {"name": "Black Sheep", "ordinal": i, "type": types[5], "setting": settings[1], "rating": 2};
    map[++i] = {"name": "Dubious Loyalties", "ordinal": i, "type": types[5], "setting": settings[1], "rating": 2};
    map[++i] = {"name": "Escaped Shovelhead", "ordinal": i, "type": types[5], "setting": settings[1], "rating": 2};
    map[++i] = {"name": "Once Enslaved", "ordinal": i, "type": types[5], "setting": settings[1], "rating": 2};
	
	return {
		get: function() {
			return map;
		}
	}	
}]);