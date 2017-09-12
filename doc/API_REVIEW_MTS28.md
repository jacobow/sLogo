API Review
========
Michael Seaberg mts28
Front-End


## Part 1
	1) How to save and load simulation states and define specific settings in the model. How to display errors, and how to receive the result of the command from the backend. Using alot of getter methods. The specific configuration of the GUI is designed to be modular and flexible.
	2) The External API is limited in its function due to the fact that we are doing all the processing in the back end. The Internal API is more extensive. It is composed of basic functions that represent each part of the GUI. For example, you can use a method to create a new simulation display box or a new command input box. Any combination of these commands can be used to place any combination of boxes on the screen. The front-end is not going to be responsible for any of the model manipulation, just updating the visuals given a model retrieved from the back end. 
	3) Mishandled/wrong commands, turtle going off the screen (possible error)
	4) I think that our front-end API is good because it is very closed, and the user does not have control over alot of aspects of the design. It is also good because it is small and each function performs a crucial role in the program.


## Part 2
	1)
		* Saving and Loading a Simulation
		* Display Errors
		* Reset or Clear the workspace
		* Change the pen color
		* Change the image of the turtle
	2) Using binding to make a new command appear in the history window as soon as the user presses enter in the command line. The history window will have to be binded to the command line "enter" press, and retreive the interpreted command from the back end.
	3) I am most excited to work on the development and layout of the GUI for this project.
	4) Trying to get the elements to work together while continuing to write clean code.