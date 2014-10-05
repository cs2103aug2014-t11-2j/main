package logic;

import java.util.LinkedList;
import java.util.Random;

public class Quotes {

	private static final String QUOTE_1 = "Both positive and negative thinking are contagious ~ Stephen Richards";
	private static final String QUOTE_2 = "The key is in not spending time, but in investing it. ~ Stephen R. Covey";
	private static final String QUOTE_3 = "Make use of time, let not advantage slip. ~ William Shakespeare";
	private static final String QUOTE_4 = "It’s not enough to be busy, so are the ants. The question is, what are we busy about? ~ Henry David Thoreau";
	private static final String QUOTE_5 = "Procrastination is the foundation of all disasters. ~ Pandora Poikilo";
	private static final String QUOTE_6 = "If you want to make good use of your time, you’ve got to know what’s most important and then give it all you’ve got.~ Lee Iacocca";
	private static final String QUOTE_7 = "Take care of the minutes and the hours will take care of themselves. ~ Lord Chesterfield";
	private static final String QUOTE_8 = "You’re writing the story of your life one moment at a time. ~ Doc Childre and Howard Martin";
	private static final String QUOTE_9 = "Never let yesterday use up today. ~ Richard H. Nelson";
	private static final String QUOTE_10 = "You cannot do a kindness too soon, for you never know how soon it will be too late. ~ Ralph Waldo Emerson";
	private static final String QUOTE_11 = "It’s how we spend our time here and now, that really matters. If you are fed up with the way you have come to interact with time, change it. ~ Marcia Wieder";
	private static final String QUOTE_12 = "One man gets only a week’s value out of a year while another man gets a full year’s value out of a week. ~ Charles Richards";
	private static final String QUOTE_13 = "Spend some time this weekend on home improvement; improve your attitude toward your family. ~ Bo Bennett";
	private static final String QUOTE_14 = "Life is not a spectator sport. If you're going to spend your whole life in the grandstand just watching what goes on, in my opinion you're wasting your life. ~ Jackie Robinson";
	private static final String QUOTE_15 = "We need to internalize this idea of excellence. Not many folks spend a lot of time trying to be excellent. ~ Barack Obama";
	private static final String QUOTE_16 = "It is by acts and not by ideas that people live. ~ Anatole France";
	private static final String QUOTE_17 = "It is kind of fun to do the impossible. ~ Walt Disney";
	private static final String QUOTE_18 = "It is not what you look at that matters, it is what you see. ~ Henry David Thoreau";
	private static final String QUOTE_19 = "The more you like yourself, the less you are like anyone else, which makes you unique. ~ Walt Disney";
	private static final String QUOTE_20 = "Be the change you want to see. ~ Mahatma Gandhi";

	public static String quote() {

		Random rnd = new Random();
		int rndInt = (int) rnd.nextInt(20);

		switch (rndInt) {

		case 1:
			return QUOTE_1;
		case 2:
			return QUOTE_2;
		case 3:
			return QUOTE_3;
		case 4:
			return QUOTE_4;
		case 5:
			return QUOTE_5;
		case 6:
			return QUOTE_6;
		case 7:
			return QUOTE_7;
		case 8:
			return QUOTE_8;
		case 9:
			return QUOTE_9;
		case 10:
			return QUOTE_10;
		case 11:
			return QUOTE_11;
		case 12:
			return QUOTE_12;
		case 13:
			return QUOTE_13;
		case 14:
			return QUOTE_14;
		case 15:
			return QUOTE_15;
		case 16:
			return QUOTE_16;
		case 17:
			return QUOTE_17;
		case 18:
			return QUOTE_18;
		case 19:
			return QUOTE_19;
		case 20:
			return QUOTE_20;
		default:
			return QUOTE_1;
		}
	}
}
