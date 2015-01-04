package com.dstevens.web.user.controllers;

import java.util.function.Function;

import com.dstevens.characters.traits.changes.TraitChange;
import com.dstevens.characters.traits.changes.TraitChangeFactory;

public class RawTraitChange {

	public int trait;
	public int value;
	
	public static Function<RawTraitChange, TraitChange<?>> toTraitChangeUsing(final TraitChangeFactory traitChangeFactory) {
		return (RawTraitChange t) -> TraitChanges.values()[t.trait].using(traitChangeFactory, t.value);
	}
	
}
