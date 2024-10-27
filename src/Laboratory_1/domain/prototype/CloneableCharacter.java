package Laboratory_1.domain.prototype;

import Laboratory_1.domain.models.character.Character;

public interface CloneableCharacter extends Character, Cloneable{
    CloneableCharacter clone();
}
