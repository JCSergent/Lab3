package pkgCore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import pkgConstants.*;
import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPoker extends Hand {

	private ArrayList<CardRankCount> CRC = null;
	private HandScorePoker HSP;

	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}
	
	public HandScorePoker getHSP() {
		return (HandScorePoker) this.getHS();

	}

	@Override
	public HandScore ScoreHand() throws HandException {
		
		Collections.sort(super.getCards());
		Frequency();
		HSP = new HandScorePoker();
		
		if (super.getCards().size() != 5) {
			throw new HandException("Not five cards", this);
		}
		
		try {
			Class<?> c = Class.forName("pkgCore.HandPoker");
			
			for (eHandStrength hstr : eHandStrength.values()) {
				Method meth = c.getDeclaredMethod(hstr.getEvalMethod(), null);
				meth.setAccessible(true);
				Object o = meth.invoke(this, null);
				
				if ((Boolean) o) {
					break;
				}
			}

			Method methGetHandScorePoker = c.getDeclaredMethod("getHSP", null);
			HSP = (HandScorePoker) methGetHandScorePoker.invoke(this, null);

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return HSP;
	}		

	public boolean isRoyalFlush() {
		boolean bisRoyalFlush = false;
		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;
		int a = 0;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt) {
			if((super.getCards().get(0).geteRank() == eRank.ACE) && (super.getCards().get(1).geteRank() == eRank.KING)) {
				a = 1;
				bisRoyalFlush = true;
				for (;a<super.getCards().size()-1;a++) {
					if(super.getCards().get(a).geteRank().getiRankNbr()-1 != super.getCards().get(a+1).geteRank().getiRankNbr()) {
						bisRoyalFlush = false;
					}
				}
			}
			if(bisRoyalFlush) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.RoyalFlush);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(null);
				this.setHS(HSP);
			}
		}
		else
			bisRoyalFlush = false;
		
		return bisRoyalFlush;
	}

	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		int a = 0;
		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;
		Card highCard = null;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt) {
			bisStraightFlush = true;
			if(super.getCards().get(0).geteRank() == eRank.ACE) {
				a = 1;
			}
			for (;a<super.getCards().size()-1;a++) {
				if(super.getCards().get(a).geteRank().getiRankNbr()-1 != super.getCards().get(a+1).geteRank().getiRankNbr()) {
					highCard = super.getCards().get(0);
					bisStraightFlush = false;
				}
			}
			
			if((super.getCards().get(1).geteRank() != eRank.FIVE) && (super.getCards().get(a).geteRank().getiRankNbr()-1 != super.getCards().get(a+1).geteRank().getiRankNbr())) {
				highCard = super.getCards().get(1);
				bisStraightFlush = false;
			}
			if(bisStraightFlush) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.StraightFlush);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(highCard);
				HSP.setLoCard(null);
				HSP.setKickers(null);
				this.setHS(HSP);
			}
		}
		else
			bisStraightFlush = false;
		
		return bisStraightFlush;
	}
	
	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		
		if(CRC.size() == 2) {
			if((CRC.get(0).getiCnt() == 4) && (CRC.get(1).getiCnt() == 1)) {
				bisFourOfAKind = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FourOfAKind);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
			}
		}
		
		return bisFourOfAKind;
	}

	public boolean isFullHouse() {
		boolean bisFullHouse = false;
		
		if(CRC.size() == 2) {
			if((CRC.get(0).getiCnt() == 3) && (CRC.get(1).getiCnt() == 2)) {
				bisFullHouse = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FullHouse);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(this.getCards().get(this.getCRC().get(1).getiCardPosition()));
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
			}
		}
		
		return bisFullHouse;

	}

	public boolean isFlush() {
		boolean bisFlush = false;

		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt)
			bisFlush = true;
		else
			bisFlush = false;

		return bisFlush;
	}

	public boolean isStraight() {
		boolean bisStraight = true;
		int a = 0;
		Card highCard = null;
		highCard = super.getCards().get(0);

		if (super.getCards().get(0).geteRank() == eRank.ACE) {
			a = 1;
		}
		for (; a < super.getCards().size() - 1; a++) {
			if (super.getCards().get(a).geteRank().getiRankNbr() - 1 != super.getCards().get(a + 1).geteRank()
					.getiRankNbr()) {

				bisStraight = false;
			}
		}

		if ((super.getCards().get(0).geteRank() == eRank.ACE) && (super.getCards().get(1).geteRank() == eRank.KING)) {
			
		}
		else if ((super.getCards().get(1).geteRank() != eRank.FIVE) && (super.getCards().get(0).geteRank() == eRank.ACE)) {

			bisStraight = false;
		} else if ((super.getCards().get(1).geteRank() == eRank.FIVE)
				&& (super.getCards().get(0).geteRank() == eRank.ACE)) {
			highCard = super.getCards().get(1);
		}

		if (bisStraight) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.Straight);
			HSP.setHiCard(highCard);
			HSP.setLoCard(null);
			HSP.setKickers(null);
			this.setHS(HSP);
		}

		return bisStraight;
	}

	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == Constants.THREE_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
			}
		}
		return bisThreeOfAKind;
	}

	public boolean isTwoPair() {
		boolean bisTwoPair = false;
		
		if(CRC.size() == 3) {
			if((CRC.get(0).getiCnt() == 2) && (CRC.get(1).getiCnt() ==2)) {
				bisTwoPair = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.TwoPair);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
			}
		}
		return bisTwoPair;
	}

	public boolean isPair() {
		boolean bisPair = false;
		
		if(CRC.size() == 4) {
			if(CRC.get(0).getiCnt() == 2) {
				bisPair = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.Pair);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
			}
		}
		return bisPair;
	}

	public boolean isHighCard() {
		boolean bisHighCard = false;
		
		if(CRC.size() == 5) {
			bisHighCard = true;
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.HighCard);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		
		return bisHighCard;
	}

	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC) {
		ArrayList<Card> kickers = new ArrayList<Card>();

		for (CardRankCount crcCheck : CRC) {
			if (crcCheck.getiCnt() == 1) {
				kickers.add(this.getCards().get(crcCheck.getiCardPosition()));
			}
		}

		return kickers;
	}

	private void Frequency() {
		CRC = new ArrayList<CardRankCount>();
		int iCnt = 0;
		int iPos = 0;
		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}
		Collections.sort(CRC);
	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}

}
