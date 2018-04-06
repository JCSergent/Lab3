package pkgCore;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPokerTest {

	@Test
	public void FourOfAKindTest1() {
		System.out.println("Four of a Kind 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES, eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.TWO, HSP.getHiCard().geteRank());
		assertEquals(1, HSP.getKickers().size());
		assertEquals(eRank.THREE, HSP.getKickers().get(0).geteRank());
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());

	}
	
	@Test
	public void FourOfAKindTest2() {
		System.out.println("Four of a Kind 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.THREE, HSP.getHiCard().geteRank());
		assertEquals(1, HSP.getKickers().size());
		assertEquals(eRank.TWO, HSP.getKickers().get(0).geteRank());
		assertEquals(eSuit.CLUBS, HSP.getKickers().get(0).geteSuit());

	}
	
	@Test
	public void StraightTest1() {
		System.out.println("Straight 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.SIX));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.SEVEN));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		assertEquals(eRank.SEVEN, HSP.getHiCard().geteRank());
		assertNull(HSP.getKickers());

	}
	
	@Test
	public void StraightTest2() {
		System.out.println("Straight 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES, eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TWO));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		assertNull(HSP.getKickers());

	}
	
	@Test
	public void StraightTest3() {
		System.out.println("Straight 3");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.TEN));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.JACK));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.QUEEN));
		hp.AddCard(new Card(eSuit.SPADES, eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.ACE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.Straight, HSP.geteHandStrength());
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		assertNull(HSP.getKickers());

	}
	
	
	@Test
	public void StraightTest4() {
		System.out.println("Straight 4");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS, eRank.ACE));
		hp.AddCard(new Card(eSuit.HEARTS, eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS, eRank.FOUR));
		hp.AddCard(new Card(eSuit.SPADES, eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS, eRank.SIX));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.HighCard, HSP.geteHandStrength());
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		

	}
	
	@Test
	public void ThreeOfAKindTest1() {
		System.out.println("Three of a Kind 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.ThreeOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.TWO, HSP.getHiCard().geteRank());
		assertEquals(2, HSP.getKickers().size());
	}
	
	@Test
	public void ThreeOfAKindTest2() {
		System.out.println("Three of a Kind 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.ThreeOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.THREE, HSP.getHiCard().geteRank());
		assertEquals(2, HSP.getKickers().size());
	}
	
	@Test
	public void TwoPairTest1() {
		System.out.println("Two Pair 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.TwoPair, HSP.geteHandStrength());
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		assertEquals(1, HSP.getKickers().size());
	}
	
	@Test
	public void TwoPairTest2() {
		System.out.println("Two Pair 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.KING));
		hp.AddCard(new Card(eSuit.SPADES,eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.TwoPair, HSP.geteHandStrength());
		assertEquals(eRank.KING, HSP.getHiCard().geteRank());
		assertEquals(1, HSP.getKickers().size());
	}
	
	@Test
	public void FullHouseTest1() {
		System.out.println("Full House 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.FullHouse, HSP.geteHandStrength());
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		assertEquals(0, HSP.getKickers().size());
	}

	@Test
	public void FullHouseTest2() {
		System.out.println("Full House 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.FullHouse, HSP.geteHandStrength());
		assertEquals(eRank.FIVE, HSP.getHiCard().geteRank());
		assertEquals(0, HSP.getKickers().size());
	}
	
	@Test
	public void StraightFlush1() {
		System.out.println("Straight Flush 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SIX));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.StraightFlush, HSP.geteHandStrength());
		assertNull(HSP.getHiCard());
		assertNull(HSP.getKickers());
	}
	
	@Test
	public void StraightFlush2() {
		System.out.println("Straight Flush 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.StraightFlush, HSP.geteHandStrength());
		assertNull(HSP.getHiCard());
		assertNull(HSP.getKickers());
	}
	
	@Test
	public void OnePair1() {
		System.out.println("One Pair 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		assertEquals(eRank.TWO,HSP.getHiCard().geteRank());
		assertEquals(3,HSP.getKickers().size());
	}
	
	@Test
	public void OnePair2() {
		System.out.println("One Pair 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.Pair, HSP.geteHandStrength());
		assertEquals(eRank.SEVEN,HSP.getHiCard().geteRank());
		assertEquals(3,HSP.getKickers().size());
	}
	
	@Test
	public void HighCard1() {
		System.out.println("High Card 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.NINE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.HighCard, HSP.geteHandStrength());
		assertEquals(eRank.NINE,HSP.getHiCard().geteRank());
		assertEquals(5,HSP.getKickers().size());
	}
	
	@Test
	public void HighCard2() {
		System.out.println("High Card 2");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.HighCard, HSP.geteHandStrength());
		assertEquals(eRank.ACE,HSP.getHiCard().geteRank());
		assertEquals(5,HSP.getKickers().size());
	}
	
	@Test
	public void RoyalFlush1() {
		System.out.println("Royal Flush 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.KING));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.JACK));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.ACE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();

		assertEquals(eHandStrength.RoyalFlush, HSP.geteHandStrength());
		assertEquals(eRank.ACE,HSP.getHiCard().geteRank());
		assertNull(HSP.getKickers());
	}
	@Test
	public void Flush1() {
		System.out.println("Flush 1");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.JACK));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		try {
			hp.ScoreHand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HandScorePoker HSP = hp.getHSP();
		
		assertNull(HSP.geteHandStrength());
	}
}
