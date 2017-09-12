# Part 1

1. My API/design will be flexible by binding itself to the front-end's display.  This way, instead of having to expand the API to include new features that are added, the front-end will be automatically tied to my new features as I add them and will read them the same way as the project evolves.  My internal API will be expansive enough to allow the parser to make commands that can affect the model(all of the turtles, trails, and background properties).

2. My API will encapsulate my implementation by making basic Model object's methods and the Model's property methods public.  This will be my internal API, since the parser needs to make use of these initial methods to define the default command object which make up the slogo language.  My external API will be encapsulated by binding itself to the front-end's display, so that the display updates based on what is being displayed.

3. The majority of the exceptions should be caught by the parser.  I could also see how perhaps a totally valid command might end up trying to affect a turtle which doesn't exist, so that would have to be an exception I should catch.

4. My API/design is good because it makes the model the foundation of the slogo program's behavior, while also not passing unnecessary information to the display.  This is good because this is philosophically what a model should do, hold the raw data of the display, and also efficient since it is directly bound to the display.

# Part 2

1. Use cases:
    * The user types in a nonsense command.  This would be caught by the parser, and would never reach the command queue.  Therefore, I would never see such a command.
    * The user moves a turtle forward.  The parser would make this into a command object and put it into the command queue.  I would then execute this command, which would update my model.  The display would then show the turtle moved forward, since it is bound to my model.
    * The user could try to move a turtle that doesn't exist.  I would catch this error when I tried to execute the command.
    * The user tries to update the background color to a non-existing color.  The parser would catch this in the command's execute method definition.
    * If the user utilizes a default value, the parser would have to be able to interpret it.

2. I plan to implement binding.  I will bind the model to the display, so that unnecessary work does not have to be done by the display.

3. I am excited to work on the model that is to be displayed.

4. I am not excited to help the other back-end guy write possibly hundreds of additional command classes.
