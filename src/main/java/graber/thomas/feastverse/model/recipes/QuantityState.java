package graber.thomas.feastverse.model.recipes;

public enum QuantityState {
    WHOLE,          // par ex. : une gousse d'ail entière, une noix de muscade entière, etc.
    CHOPPED,        // coupé grossièrement en morceaux
    DICED,          // coupé en petits cubes
    MINCED,         // haché très finement (similaire à "haché")
    GRATED,         // râpé à l'aide d'une râpe (ex. fromage, carottes)
    SHREDDED,       // déchiré ou coupé en fines lanières
    GROUND,         // réduit en poudre (ex. épices moulues)
    CRUSHED,        // écrasé ou pilé grossièrement (ail, grains de poivre, etc.)
    SLICED,         // coupé en tranches fines
    FLAKED,         // découpé ou cassé en flocons (similaire à "copeaux")
    DRIED,          // déshydraté (ex. herbes séchées, fruits secs)
    POWDERED,       // totalement réduit en poudre (ex. cacao en poudre)
    RIBBONED,       // coupé en fins rubans (ex. rubans de courgette)
    PEELED,         // peau extérieure retirée, mais entier par ailleurs
    LIQUID,         // déjà à l'état liquide (ex. fondu, ou huile)
    CRUSY,          // écrouté
    MASHED,         // purée
    FRESH,          // frais,
    MELTED,         // fondu
    FROZEN          // congelé
}
