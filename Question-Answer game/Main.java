package P5;

/* This file includes:
 * 	1. Solution to P3
 *  2. Questions for P4. Comments starting with REQ represent the questions.
 *  
 * Features:
 * 	- We have from 1 to 3 players
 *  - We have many questions. Each player may be asked more than one question.
 *  - User can play many rounds of the game. 
 * 
 * Focus: 
 * 	- Arrays and Methods
 * 
 * Aim:
 * 	- Organize code and avoid code redundancy by the use of methods
 */

public class Main {				
	static Game game;			
	
	//Two arrays for questions and answers (both are global, i.e., accessible by all code in the class).
	//REQ1:	Replace array values with real questions/answers
	static String[][] questions = {{"question1", "question11", "question111"},
			   					   {"question2", "question22", "question222"},
			   					   {"question3", "question33", "question333"}};
	static String[][] answers =   {{"answer1", "answer11", "answer111"},   
								   {"answer2", "answer22", "answer222"},
								   {"answer3", "answer33", "answer333"}};

	public static void main(String[] args) {
		String ans;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			int numPlayers = game.askForInt("How many players", 1, 3);

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + i + " name?");
				game.addPlayer(name);				
			}
			
			//REQ2:	Call a method to shuffle questions and answers. For that, you need to create a method with the header: void shuffleQuestions();
			
			//REQ3:	- Calculate the maximum number of rounds (each player is asked one question per round). The maximum number of rounds should be equal to the number of available questions divided by numPlayers. Store this value in a variable maxRounds
			//	- Ask the user about the number of rounds. The value read from the user should not exceed maxRounds. Store this value in a variable numRounds.
			//		- Ask each player the next unanswered question (e.g., player 0 gets the first question. If it is answered correctly, then player1 gets the next question in the array, otherwise player1 gets the same question again, and so on). 
			// 		  Assume that an incorrectly answered question will keep popping up until it is correctly answered or we finish all the rounds.
			//		  Hint: you need to create a for loop that repeats the below code block numRounds times.
			//		  Hint: you need to have a variable that keeps track of the next question to be offered. 
			int maxRounds=9/numPlayers;
			int numRounds=game.askForInt("How many rounds ?(each player gets one question per round)", 1, maxRounds);
			int category = game.askForInt("Choose a category: 1-First 2-Second 3- Third", 1, 3);
			shuffleQuestions(questions,answers,category);
			int counter=0;
			
			for (int a = 0; a < numRounds; a++) {
			
			for (int i = 0; i < numPlayers; i++) {
				
				game.setCurrentPlayer(i);//draw rectangle around player 0, and currentPlayer = player0
				category = game.askForInt("Choose a category: 1-First 2-Second 3- Third", 1, 3);
				String answer = game.askForText(questions[category-1][counter]);
				if(answers[category-1][counter].equals(answer))
					{game.correct();
					counter++;
					}//display "Correct", increment score, change frame color to green
				else
					game.incorrect();	//display "incorrect", change frame color of player to red
				
			}	
			}
			//Do you want to play again? make sure you get valid input
			ans = game.askForText("Play again? (Y/N)"); 
			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
				ans = game.askForText("Invalid input. Play again? (Y/N)");
		}while(ans.toUpperCase().equals("Y"));	//play again if the user answers "Y" or "y"

		System.exit(1); 	//This statement terminates the program75.8
		
	}
	public static void shuffleQuestions(String[][]list1,String[][]list2,int category) {
		
		for(int i=0; i<list1.length;i++) {
			int index=(int)(Math.random()*list1.length);
			String temp=list1[category][i];
			list1[category][i]=list1[category][index];
			list1[category][index]=temp;
			
			String temp1=list2[category][i];
			list2[category][i]=list2[category][index];
			list2[category][index]=temp1;
		}
	}
}
