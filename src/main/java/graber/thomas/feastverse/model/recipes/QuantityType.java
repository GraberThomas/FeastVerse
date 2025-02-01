package graber.thomas.feastverse.model.recipes;

/**
 * Représente les différentes unités de mesure ou formats
 * qu'un ingrédient peut avoir dans une recette.
 */
public enum QuantityType {
    MG,         // milligramme
    G,          // gramme
    KG,         // kilogramme
    LB,         // livre (unité de masse anglo-saxonne)
    OZ,         // once (unité de poids)
    ML,         // millilitre
    CL,         // centilitre
    L,          // litre
    CUP,        // tasse (mesure anglo-saxonne ~ 236 ml)
    TBSP,       // cuillère à soupe
    TSP,        // cuillère à café
    FL_OZ,      // once liquide (unité de volume anglo-saxonne)
    UNIT,       // unité/pièce
    SLICE,      // tranche
    PACK,       // paquet
    CAN,        // boîte de conserve
    BOTTLE,     // bouteille
    PINCH,      // pincée
    DASH,       // goutte (très petite quantité)
    HEART,      // coeur
    CLOVE,      // Gousse
    LEAF,       // feuilles
    NONE        // aucune unité spécifique
}
