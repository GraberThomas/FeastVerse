# FeastVerse project

University project for the course of Java Spring boot.

## Description

Allows to create and manage cooking recipes. Contains a list of ingredients, types, units. Complete JWT authentication management system with Users and Roles.

Users can create their own resources, moderators have access to features to process reports and we have partial access.

Administrators have full powers and can do whatever they want.

## Resources

- 'users' - Users : CRUD ✅
- 'ingredients' - Ingredients ✅
- 'ingredientTypes' - Ingredient types ✅
- 'comments' - Comments ✅
- 'likes' - Likes ✅
- 'recipes' - Recipes ⚙️ (in progress, read only)
- 'recipeSteps' - Recipe steps ⚙️ (in progress, read only)
- 'reports' - Reports ✅

## Features
- Creation of accounts
- JWT authentication
- 700+ ingredients with images
- 10+ ingredient types
- 20+ units
- 12 recipes for testings
- 3 roles: User, Moderator, Admin
- Full support for CRUD operations except for recipes and recipe steps
- Ownable resources can be public or private
- Full support of likes and comments
- Full support of reports and management of them by moderators and admins
- All resources are paginated, sorted and filtered by many parameters
- Image upload for new ingredients

## Features to come
- Create, update and delete recipes and recipe steps
- Better endpoints for moderators : filter by number of reports, sort by number of reports
- Rating
- Collections of recipes (done, to do, etc.)

## Environment
The project is configured in development mode. The database is PostgreSQL. The database is created and populated at startup. The database is not persistent, it is recreated at each startup.

⚠️ (Duration too short to implement all features)

## Technologies
- Java Spring Boot
- Spring Security
- JWT
- PostgreSQL

## Seed data
They are data loaded in startup by java. It contains 700+ ingredients, somes users, somes recipes, some reports, likes and comments.

# Test case
In Postman:
Don't forget to execute this requests, tokens are saved in collections variables:
- Create an account (Authentication/Create User)
- Login (Authentication/Sign-in standard)
- Login (Authentication/Sign-in admin)
- Login (Authentication/Sign-in moderator)

In folder of postman, they are explanations resources and permissions for each role.

⚠️⚠️ Many requests use variables extract from other variables ⚠️⚠️

It's important to execute requests in order to avoid errors.

Conerned requests are:
- All **GET resources by ID** requests.
- All **DELETE resources by ID** requests.
- All **PUT resources by ID** requests.

⚠️⚠️ POST and PATCH on **ingredients** need a Multipart form⚠️⚠️

The export of json will not save my images examples. So you have to take your own images.

In postman, you can:
- USER
  - Create and log all user roles,
  - Test READ in User and see the differences in return schema between roles,
  - Try patch user, change username/email
  - Call My liked recipes will not return results until you like a recipe
  - Delete user as admin. You will need to recreate and login the standard user to continue testing.
- INGREDIENTS
  - Test READ methods on endpoints, play with pagination, sorting and filtering
  - Test powerful search possibilities on 700+ ingredients
  - Test CREATE, DELETE and PATCH on ingredients (with roles)
  - Try to create an ingredient with a role and try to delete it with another role
  - Delete an ingredient and as admin verify that it is only soft deleted
  - Perform a hard delete on an ingredient
- INGREDIENT TYPES
  - Test READ methods on endpoints, play with pagination, sorting and filtering
  - INGREDIENT TYPES is a fixed resource, you can't create, delete or patch them
- RECIPE
  - Test READ methods on endpoints, play with pagination, sorting
  - Test powerful search possibilities of recipes, from cooking time, type, withIngredients, etc.
  - CRUD is not yet implemented, so you can't create, delete or patch them
  - As admin, CRUD the recipe type
- REPORT
  - As a user, create a report on a User, comment, recipe.
  - As a moderator, read all reports, set resolved
  - As an admin, read all reports, set resolved, delete a report
- COMMENT
  - As a user, create a comment on a recipe
  - AS a user, get all comments of recipes or recipes_steps
  - As a moderator, delete a comment (soft delete)
  - AS an admin, hard delete a comment or soft delete a comment
- LIKE
  - As a user, like a recipe, unlike a recipe





