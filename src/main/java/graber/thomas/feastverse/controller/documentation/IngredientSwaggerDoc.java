package graber.thomas.feastverse.controller.documentation;

import graber.thomas.feastverse.dto.ingredient.IngredientAdminViewDto;
import graber.thomas.feastverse.dto.ingredient.IngredientPublicViewDto;
import graber.thomas.feastverse.dto.ingredient.IngredientViewDto;
import graber.thomas.feastverse.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static graber.thomas.feastverse.controller.documentation.ApiErrorDoc.*;

public class IngredientSwaggerDoc {
    private static final String USER_VIEW_EXAMPLE = """
            {
                "id": 54,
                "name": "Olive taggiasca",
                "type": {
                    "id": 7,
                    "name": "Spices, oils and condiments"
                },
                "description": "Spécialité italienne, l’olive taggiasca est une olive typique de la Ligurie, une région du nord de l’Italie et frontalière avec la France. Cette variété de petite taille est réputée pour son parfum savoureux. Elle est considérée comme une olive de grande qualité et permet de confectionner une huile absolument délicieuse.",
                "isPublic": true,
                "ownerId": null,
                "imageUrl": "/images/ingredient/fotoliaok.jpg"
            }
            """;

    private static final String ADMIN_VIEW_EXAMPLE= """
            {
                "id": 54,
                "name": "Olive taggiasca",
                "type": {
                    "id": 7,
                    "name": "Spices, oils and condiments"
                },
                "description": "Spécialité italienne, l’olive taggiasca est une olive typique de la Ligurie, une région du nord de l’Italie et frontalière avec la France. Cette variété de petite taille est réputée pour son parfum savoureux. Elle est considérée comme une olive de grande qualité et permet de confectionner une huile absolument délicieuse.",
                "isPublic": true,
                "isDeleted": false,
                "ownerId": null,
                "imageUrl": "/images/ingredient/fotoliaok.jpg",
                "createdDate": "2025-02-02",
                "updatedDate": null
            }
            """;

    private static final String PUBLIC_LIST_USER_VIEW= """
            {
                "content": [
                    {
                        "id": 656,
                        "name": "Abricot",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Fruit de l'abricotier, l'abricot est un fruit rond et orangé, doté d'un sillon en son milieu qui permet de l'ouvrir facilement pour en dégager son noyau. Celui-ci renferme une amande au goût amer. Sa chair est assez juteuse, sucrée et parfumée quand il est mûr et de bonne qualité. Sa peau va du jaune à l'orangé soutenu, parfois avec des traces rouges, selon les variétés. Le nom botanique de l'abricot estprunus armeniaca, soit « prune d'Arménie », bien qu'il ne soit pas une prune et ne vienne pas d'Arménie.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/abricot_031.jpg"
                    },
                    {
                        "id": 449,
                        "name": "Agar-agar",
                        "type": {
                            "id": 10,
                            "name": "Autres",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Obtenu à partir d'algues rouges, l'agar agar est un produit gélifiant naturel. Ce produit texturant souvent employé comme additif alimentaire (E 406), se présente sous la forme de fibres ou en poudre. Contrairement aux gélifiants d'origine animale qui doivent être trempées dans de l'eau avant utilisation, l'agar agar doit être chauffé à haute température pour être soluble.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/fotolia_41708254_subscription_xl.jpg"
                    },
                    {
                        "id": 661,
                        "name": "Agneau",
                        "type": {
                            "id": 3,
                            "name": "Meats, poultry and delicatessen products",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’agneau est un très jeune mouton, le petit de la brebis et du bélier. Qu’il soit mâle ou femelle, un agneau doit nécessairement être âgé de moins de 300 jours. Le mouton ‒ et donc l’agneau ‒ est élevé sur tous les continents de la planète.En France, on distingue différents types d’agneaux :Agnelet ou agneau de lait :Abattu, non sevré, entre 5 et 6 semaines lorsqu’il pèse de 6 à 10 kg, il a été uniquement nourri au lait de brebis. Sa viande, très blanche, est tendre et un peu douceâtre.Agneau de boucherie ou agneau blanc :Nourri au lait (de vache ou de brebis) et aux céréales, il est abattu entre 3 et 5 mois, et pèse de 16 à 25 kg. Sa chair est plus colorée, tendre, avec un goût plus prononcé.Agneau gris d’herbages ou broutard :Sevré et nourri d’herbe et de céréales, il est abattu entre 6 et 10 mois, et pèse de 20 à 30 kg. Sa chair est rouge clair, plus grasse, et a un goût bien plus affirmé.Dans cette catégorie, les agneaux de prés salés , ceux du Mont-Saint-Michel et de la baie de Somme en France, ceux de l’île Verte au Québec, ceux de l’île Salt Spring en Colombie-Britannique, broutent les plantes salées par les embruns de la mer et ont, de ce fait, une chair particulièrement savoureuse.Les trois types d’agneaux existent dans d’autres pays, mais sous des dénominations différentes. Les plus fréquentes sont : agneau de lait, agneau moyen et agneau lourd.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/agneau_000.jpg"
                    },
                    {
                        "id": 452,
                        "name": "Agrumes",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Le terme général d’\\"agrumes\\" regroupe toute une famille de fruits qui ont tous comme points communs une écorce épaisse et odorante, car elle est chargée d’huiles essentielles, et une chair juteuse divisée en quartiers, contenant des pépins et plus ou moins acide. Les agrumes sont les fruits les plus cultivés sur la planète.À l’origine, il y avait des arbres sauvages qui produisaient des citrons, des pamplemousses, des oranges, des mandarines, etc. Au fil des siècles, soit naturellement, soit par la main de l’homme, ces espèces sauvages ont fait place à un grand nombre de variétés d’agrumes, classées en familles et dont les membres sont plus ou moins nombreux : lescitrons, lesclémentines, l'orange, lepomélo, etc.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/agrumes_000.jpg"
                    },
                    {
                        "id": 448,
                        "name": "Ail",
                        "type": {
                            "id": 1,
                            "name": "Vegetables",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’ail est un bulbe de la même famille botanique (liliacées) que l’oignon, l’échalote, la cive (ou cébette) et la ciboulette. Ce bulbe, ou tête, est composé de 10 à 12 cayeux ou gousses. Originaire de l’Asie centrale, l’ail est cultivé et consommé depuis des millénaires tant pour ses vertus médicinales que pour son intérêt culinaire. Les Chinois, 3 000 ans avant Jésus-Christ, l’utilisaient comme condiment. Les Égyptiens en distribuaient aux ouvriers qui bâtissaient les pyramides, pour leur donner de la force. Pour la même raison, les Hébreux, les Grecs et les Romains en consommaient beaucoup. Ces derniers le diffusèrent partout au fil de leurs conquêtes. En France, Charlemagne imposa sa culture dans tous les jardins. Grâce à Christophe Colomb, qui l’implanta dans l’île de la Dominique, il se dissémina ensuite dans toute l’Amérique du Sud et l’Amérique centrale. Mais ce n’est qu’au XIXe siècle qu’il conquit l’Amérique du Nord. Dans toutes les civilisations, l’ail a toujours été considéré comme une panacée. Il est présent dans presque toutes les cuisines du monde, et plus particulièrement dans celle du Bassin méditerranéen.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/ail_000.jpg"
                    },
                    {
                        "id": 503,
                        "name": "Airelle",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’airelle est une petite baie rouge. Il en existe plusieurs variétés, comme l'airelle rouge (ou airelle vigne du mont Ida ou lingonberry), qui est petite, rouge et acide, légèrement farineuse. On la trouve sur les étals des primeurs en août et septembre.La canneberge (ou cranberry ou atoca), la \\"grande airelle rouge d’Amérique du Nord\\" est moyenne, rouge et acide. On la trouve de septembre à décembre. Les airelles à gros fruits ou canneberges à gros atocas donnent elles des baies de 1 à 2 cm de diamètre, rouge foncé et acidulée. Ces variétés sont cultivées industriellement au Canada et aux États-Unis.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/fotolia_61296169_subscription_xl.jpg"
                    },
                    {
                        "id": 450,
                        "name": "Amande",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’amande est le fruit de l’amandier. Il est composé d’une bogue ovale de 3 à 6 cm, vert pâle et veloutée au toucher, qui protège une coque ligneuse. Celle-ci renferme une ou deux graines (amandons) de couleur ivoire et recouvertes d’une fine peau brune. Deux grandes sortes d’amandes existent : l’amande amère, qui provient d’amandiers sauvages. Elle est riche en amygdaline, qui se transforme en acide cyanhydrique (cyanure) toxique. L'autre type est l'amande douce.Il en existe une cinquantaine de variétés dont les plus connues sont l’aï (France, Grèce), la california (Californie), la sfax (Tunisie), la jordan et la marcona (Espagne), la nonpareil (Californie), et l’avola (Italie). La Californie est le premier producteur mondial, suivie par la Syrie, l’Iran, l’Espagne, l’Italie, les pays du Maghreb, la Turquie et la Grèce.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/amande_000.jpg"
                    },
                    {
                        "id": 451,
                        "name": "amaretto",
                        "type": {
                            "id": 9,
                            "name": "Drinks",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L'amaretto est une liqueur aux amandes amères. Elle contient généralement 28° d'alcool. Son nom bient du mot italien \\"amaro\\" qui signifie \\"amer\\".",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/amaretto.png"
                    },
                    {
                        "id": 453,
                        "name": "Ananas",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’ananas est le fruit d’une plante tropicale qui porte le même nom. Il a la forme d’une énorme pomme de pin coiffée d’un toupet de feuilles vertes et dures. Son écorce, composée d’écailles, renferme une chair jaune plus ou moins juteuse, sucrée et acidulée selon les variétés, parfois ponctuée d’« yeux » noirs. Au centre de la chair se trouve une tige ligneuse, cœur dur et fibreux.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/ananas_000.jpg"
                    },
                    {
                        "id": 504,
                        "name": "Anchois",
                        "type": {
                            "id": 4,
                            "name": "Fish and seafood",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Le terme générique d’\\"anchois\\" regroupe un grand nombre de petits poissons de la famille des engraulidés. Ils mesurent de 10 à 20 cm et vivent en bancs dans presque toutes les mers du monde, près des côtes. Leur dos est bleu-vert, leurs flancs argentés, et ils sont couverts de grandes écailles. Ils se reproduisent très vite et très souvent : deux à trois fois par an. Ils grandissent rapidement : à l’âge de 1 an, ils sont adultes. Mais ils vivent peu de temps car, dans les mers européennes, ils sont surpêchés et sont devenus une espèce en danger.",
                        "isPublic": true,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/anchois_000.jpg"
                    }
                ],
                "pageable": {
                    "pageNumber": 0,
                    "pageSize": 10,
                    "sort": {
                        "sorted": true,
                        "empty": false,
                        "unsorted": false
                    },
                    "offset": 0,
                    "paged": true,
                    "unpaged": false
                },
                "last": false,
                "totalElements": 713,
                "totalPages": 72,
                "first": true,
                "size": 10,
                "number": 0,
                "sort": {
                    "sorted": true,
                    "empty": false,
                    "unsorted": false
                },
                "numberOfElements": 10,
                "empty": false
            }
            """ ;

    private static final String ADMIN_LIST_USER_VIEW= """
            {
                "content": [
                    {
                        "id": 656,
                        "name": "Abricot",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Fruit de l'abricotier, l'abricot est un fruit rond et orangé, doté d'un sillon en son milieu qui permet de l'ouvrir facilement pour en dégager son noyau. Celui-ci renferme une amande au goût amer. Sa chair est assez juteuse, sucrée et parfumée quand il est mûr et de bonne qualité. Sa peau va du jaune à l'orangé soutenu, parfois avec des traces rouges, selon les variétés. Le nom botanique de l'abricot estprunus armeniaca, soit « prune d'Arménie », bien qu'il ne soit pas une prune et ne vienne pas d'Arménie.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/abricot_031.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 449,
                        "name": "Agar-agar",
                        "type": {
                            "id": 10,
                            "name": "Autres",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Obtenu à partir d'algues rouges, l'agar agar est un produit gélifiant naturel. Ce produit texturant souvent employé comme additif alimentaire (E 406), se présente sous la forme de fibres ou en poudre. Contrairement aux gélifiants d'origine animale qui doivent être trempées dans de l'eau avant utilisation, l'agar agar doit être chauffé à haute température pour être soluble.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/fotolia_41708254_subscription_xl.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 661,
                        "name": "Agneau",
                        "type": {
                            "id": 3,
                            "name": "Meats, poultry and delicatessen products",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’agneau est un très jeune mouton, le petit de la brebis et du bélier. Qu’il soit mâle ou femelle, un agneau doit nécessairement être âgé de moins de 300 jours. Le mouton ‒ et donc l’agneau ‒ est élevé sur tous les continents de la planète.En France, on distingue différents types d’agneaux :Agnelet ou agneau de lait :Abattu, non sevré, entre 5 et 6 semaines lorsqu’il pèse de 6 à 10 kg, il a été uniquement nourri au lait de brebis. Sa viande, très blanche, est tendre et un peu douceâtre.Agneau de boucherie ou agneau blanc :Nourri au lait (de vache ou de brebis) et aux céréales, il est abattu entre 3 et 5 mois, et pèse de 16 à 25 kg. Sa chair est plus colorée, tendre, avec un goût plus prononcé.Agneau gris d’herbages ou broutard :Sevré et nourri d’herbe et de céréales, il est abattu entre 6 et 10 mois, et pèse de 20 à 30 kg. Sa chair est rouge clair, plus grasse, et a un goût bien plus affirmé.Dans cette catégorie, les agneaux de prés salés , ceux du Mont-Saint-Michel et de la baie de Somme en France, ceux de l’île Verte au Québec, ceux de l’île Salt Spring en Colombie-Britannique, broutent les plantes salées par les embruns de la mer et ont, de ce fait, une chair particulièrement savoureuse.Les trois types d’agneaux existent dans d’autres pays, mais sous des dénominations différentes. Les plus fréquentes sont : agneau de lait, agneau moyen et agneau lourd.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/agneau_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 452,
                        "name": "Agrumes",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Le terme général d’\\"agrumes\\" regroupe toute une famille de fruits qui ont tous comme points communs une écorce épaisse et odorante, car elle est chargée d’huiles essentielles, et une chair juteuse divisée en quartiers, contenant des pépins et plus ou moins acide. Les agrumes sont les fruits les plus cultivés sur la planète.À l’origine, il y avait des arbres sauvages qui produisaient des citrons, des pamplemousses, des oranges, des mandarines, etc. Au fil des siècles, soit naturellement, soit par la main de l’homme, ces espèces sauvages ont fait place à un grand nombre de variétés d’agrumes, classées en familles et dont les membres sont plus ou moins nombreux : lescitrons, lesclémentines, l'orange, lepomélo, etc.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/agrumes_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 448,
                        "name": "Ail",
                        "type": {
                            "id": 1,
                            "name": "Vegetables",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’ail est un bulbe de la même famille botanique (liliacées) que l’oignon, l’échalote, la cive (ou cébette) et la ciboulette. Ce bulbe, ou tête, est composé de 10 à 12 cayeux ou gousses. Originaire de l’Asie centrale, l’ail est cultivé et consommé depuis des millénaires tant pour ses vertus médicinales que pour son intérêt culinaire. Les Chinois, 3 000 ans avant Jésus-Christ, l’utilisaient comme condiment. Les Égyptiens en distribuaient aux ouvriers qui bâtissaient les pyramides, pour leur donner de la force. Pour la même raison, les Hébreux, les Grecs et les Romains en consommaient beaucoup. Ces derniers le diffusèrent partout au fil de leurs conquêtes. En France, Charlemagne imposa sa culture dans tous les jardins. Grâce à Christophe Colomb, qui l’implanta dans l’île de la Dominique, il se dissémina ensuite dans toute l’Amérique du Sud et l’Amérique centrale. Mais ce n’est qu’au XIXe siècle qu’il conquit l’Amérique du Nord. Dans toutes les civilisations, l’ail a toujours été considéré comme une panacée. Il est présent dans presque toutes les cuisines du monde, et plus particulièrement dans celle du Bassin méditerranéen.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/ail_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 503,
                        "name": "Airelle",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’airelle est une petite baie rouge. Il en existe plusieurs variétés, comme l'airelle rouge (ou airelle vigne du mont Ida ou lingonberry), qui est petite, rouge et acide, légèrement farineuse. On la trouve sur les étals des primeurs en août et septembre.La canneberge (ou cranberry ou atoca), la \\"grande airelle rouge d’Amérique du Nord\\" est moyenne, rouge et acide. On la trouve de septembre à décembre. Les airelles à gros fruits ou canneberges à gros atocas donnent elles des baies de 1 à 2 cm de diamètre, rouge foncé et acidulée. Ces variétés sont cultivées industriellement au Canada et aux États-Unis.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/fotolia_61296169_subscription_xl.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 450,
                        "name": "Amande",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’amande est le fruit de l’amandier. Il est composé d’une bogue ovale de 3 à 6 cm, vert pâle et veloutée au toucher, qui protège une coque ligneuse. Celle-ci renferme une ou deux graines (amandons) de couleur ivoire et recouvertes d’une fine peau brune. Deux grandes sortes d’amandes existent : l’amande amère, qui provient d’amandiers sauvages. Elle est riche en amygdaline, qui se transforme en acide cyanhydrique (cyanure) toxique. L'autre type est l'amande douce.Il en existe une cinquantaine de variétés dont les plus connues sont l’aï (France, Grèce), la california (Californie), la sfax (Tunisie), la jordan et la marcona (Espagne), la nonpareil (Californie), et l’avola (Italie). La Californie est le premier producteur mondial, suivie par la Syrie, l’Iran, l’Espagne, l’Italie, les pays du Maghreb, la Turquie et la Grèce.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/amande_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 451,
                        "name": "amaretto",
                        "type": {
                            "id": 9,
                            "name": "Drinks",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L'amaretto est une liqueur aux amandes amères. Elle contient généralement 28° d'alcool. Son nom bient du mot italien \\"amaro\\" qui signifie \\"amer\\".",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/amaretto.png",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 453,
                        "name": "Ananas",
                        "type": {
                            "id": 2,
                            "name": "Fruits",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "L’ananas est le fruit d’une plante tropicale qui porte le même nom. Il a la forme d’une énorme pomme de pin coiffée d’un toupet de feuilles vertes et dures. Son écorce, composée d’écailles, renferme une chair jaune plus ou moins juteuse, sucrée et acidulée selon les variétés, parfois ponctuée d’« yeux » noirs. Au centre de la chair se trouve une tige ligneuse, cœur dur et fibreux.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/ananas_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    },
                    {
                        "id": 504,
                        "name": "Anchois",
                        "type": {
                            "id": 4,
                            "name": "Fish and seafood",
                            "description": null,
                            "imageUrl": null
                        },
                        "description": "Le terme générique d’\\"anchois\\" regroupe un grand nombre de petits poissons de la famille des engraulidés. Ils mesurent de 10 à 20 cm et vivent en bancs dans presque toutes les mers du monde, près des côtes. Leur dos est bleu-vert, leurs flancs argentés, et ils sont couverts de grandes écailles. Ils se reproduisent très vite et très souvent : deux à trois fois par an. Ils grandissent rapidement : à l’âge de 1 an, ils sont adultes. Mais ils vivent peu de temps car, dans les mers européennes, ils sont surpêchés et sont devenus une espèce en danger.",
                        "isPublic": true,
                        "isDeleted": false,
                        "ownerId": null,
                        "imageUrl": "/images/ingredient/anchois_000.jpg",
                        "createdDate": "2025-02-02",
                        "updatedDate": null
                    }
                ],
                "pageable": {
                    "pageNumber": 0,
                    "pageSize": 10,
                    "sort": {
                        "sorted": true,
                        "empty": false,
                        "unsorted": false
                    },
                    "offset": 0,
                    "paged": true,
                    "unpaged": false
                },
                "last": false,
                "totalElements": 713,
                "totalPages": 72,
                "first": true,
                "size": 10,
                "number": 0,
                "sort": {
                    "sorted": true,
                    "empty": false,
                    "unsorted": false
                },
                "numberOfElements": 10,
                "empty": false
            }
            """;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all types of ingredients.",
            description = "Get all types of ingredients. Results is paginated. No need to be authenticated. You can filter by name.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredients type retrieved successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                     schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_401_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface IngredientGetAllTypeSwaggerDoc {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get ingredient types by id.",
            description = "Get details of an ingredients type, by id. No need to be authenticated.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredients type retrieved successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_401_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ingredient type not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface IngredientGetTypeByIdSwaggerDoc {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get all ingredient",
            description = "Get all the ingredients available in database. Result is paginated and many filters are available. Merge filter 'typeId' and 'typeName' is not allowed. Only Administrators can filter on visibility and deletedStatus. For other, ingredients that is not your own and are private is invisible. Ingredients can be soft deleted for not loose recipe if a ingredient is employed in them.\nResult field depends of user rôle.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredients retrieved successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface IngredientGetAllIngredientsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Get detailed information of one ingredient. ADMINISTRATOR can see more fields, like deletedStatus.",
            description = "Get detailed information of",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredient retrieved successfully",
                            content = @Content(
                                    schema = @Schema(implementation = IngredientViewDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = USER_VIEW_EXAMPLE
                                            ),
                                            @ExampleObject(
                                                    name = "Administrator view",
                                                    value = ADMIN_VIEW_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ingredient not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface IngredientGetOneIngredientsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Create new ingredient",
            description = "Create new ingredient. Authentication is needed. Require a multipart form value, with on requested json for data and a Multipart file for a file upload.  OpenAPI don't allow schema for multipart. Schema : String name;\\n\" +\n" +
                    "                    \"    Long type;\\n\" +\n" +
                    "                    \"    @Length(max = 5000)\\n\" +\n" +
                    "                    \"    String description;\\n\" +\n" +
                    "                    \"    Boolean isPublic;\\n\" +\n" +
                    "                    \"    UUID ownerId;\",",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredient created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = IngredientViewDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = USER_VIEW_EXAMPLE
                                            ),
                                            @ExampleObject(
                                                    name = "Administrator view",
                                                    value = ADMIN_VIEW_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ingredient, ingredient type or owner user not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface IngredientCreateIngredientsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Update an ingredient.",
            description = "Perform a patch in ingredient. If a file value is set but null, old image is erased. If image is not present, old image will no be changed. OpenAPI don't allow schema for multipart. Schema : String name;\n" +
                    "    Long type;\n" +
                    "    @Length(max = 5000)\n" +
                    "    String description;\n" +
                    "    Boolean isPublic;\n" +
                    "    UUID ownerId;",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ingredient created successfully",
                            content = @Content(
                                    schema = @Schema(implementation = IngredientViewDto.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Standard view",
                                                    value = USER_VIEW_EXAMPLE
                                            ),
                                            @ExampleObject(
                                                    name = "Administrator view",
                                                    value = ADMIN_VIEW_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = ApiErrorDoc.API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized. Wrong credentials.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ingredient, ingredient type or owner user not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_404_EXAMPLE
                                    )
                            )
                    )
            }
    )
    public @interface IngredientUpdateIngredientsSwaggerDoc{}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = "Delete an ingredient.",
            description = "Require to be authenticated. Only ADMINISTRATOR can perform hardDelete. Other can soft delete only their own ingredients.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content. Ingredient is deleted"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Errors in validation of body, parameters, or wrong value.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            value = API_400_EXAMPLE
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_401_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden access.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_403_EXAMPLE
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ingredient not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = API_404_EXAMPLE
                                            )
                                    }
                            )
                    )
            }
    )
    public @interface IngredientDeleteIngredientsSwaggerDoc{}

}

