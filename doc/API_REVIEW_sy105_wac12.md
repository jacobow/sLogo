## Lab_API_CHECKLIST
### wac12, sy105
### PART 1

1. What about your API/design is intended to be flexible?
    - API needs to be basic and complete the most simplest roles since we would not want to change them later on. We need to make it easy to create new commands. Also to read in a complex set of different commands.
2. How is your API/design encapsulating your implementation decisions?
    - Our APIs successfully encapsulates the different classes and their interactions. The other classes don’t have an access or need to know the functions of the other classes.
3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    - The parser just returns the wrong command back to the view. The parser makes sure that the model can execute the commands.

4. Why do you think your API/design is good (also define what your measure of good is)?
    - Our APIs are not particularly good so far. I need more time to talk with my teammates to see that everyone has information from the parser that they need. The API is designed so that other classes will not know how the parser functions but rather would be able to use the functionalities associated with the parser easily.

###PART 2


1.  **Use Cases**
    - error checking
    - encounter a function with incorrect arguments
    - invalid cases for the model
    - parsing multiple commands
    - passing the parsed information to the queue
2. **Advanced Features**
    - We definitely will use binding and expression tree. Binding for linking the elements in the model and the view. Expression tree to parse out the commands into a readable structure for the model.
3. I am excited to tackle the expression tree to make the text inputs readable to the model.
4. I am worried about the parsing of the text inputs to interpret all of the regex in java and create new commands.
