package app.cards;

import java.util.*;

public class CardApp
{
	private Drawer drawer;

	private ArrayList<Card> cards = new ArrayList<>();
	private ArrayList<Card> learnt = new ArrayList<>();

	private Card currentCard;

	public CardApp(Drawer drawer)
	{
		this.drawer = drawer;
	}

	public void init(int cardAmt, BitSet activeBoxes)
	{
		if(cardAmt > drawer.getCardAmt(activeBoxes)) throw new ArrayIndexOutOfBoundsException("Cannot pick more cards than available!");
		int boxAmt = activeBoxes.stream().sum();
		ArrayList<Integer> boxIndices = new ArrayList<>();
		for (int i = activeBoxes.nextSetBit(0); i != -1; i = activeBoxes.nextSetBit(i + 1))
		{
			boxIndices.add(i);
		}
		Random rnd = new Random();
		for (int i = 0; i < cardAmt; i++)
		{
			int boxIndex = boxIndices.get(rnd.nextInt(0, boxAmt));
			Box b = drawer.getBox(boxIndex);
			Card c = b.getCard(rnd.nextInt(0, b.getCardAmt()));
			c.setBox(b);
			cards.add(c);
			b.removeCard(c);
		}
	}

	public String getWord()
	{
		return cards.get(0).getWord();
	}

	public String getTranslation()
	{
		return cards.get(0).getTranslation();
	}

	public void translationCorrect()
	{
		learnt.add(cards.remove(0));
	}

	public void translationWrong()
	{
		cards.add(cards.remove(0));
	}

	/**
	 * Judges if a string answer matches the translation
	 * @param answer user translated word
	 * @return AnswerQuality.CORRECT if there are no mistakes in the translation, AnswerQuality.CASE_ERROR if only the case is off, else AnswerQuality.WRONG is returned
	 */
	public AnswerQuality checkAnswer(String answer)
	{
		return (answer.equals(getTranslation()) ? AnswerQuality.CORRECT : (answer.equalsIgnoreCase(getTranslation()) ? AnswerQuality.CASE_ERROR : AnswerQuality.WRONG));
	}
}
