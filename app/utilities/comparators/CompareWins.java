package utilities.comparators;

import entities.Football.FootballClub;

import java.util.Comparator;

public class CompareWins implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub club1, FootballClub club2) {
        if (club1.getNumWins() > club2.getNumWins()) return 1;
        else if (club1.getNumWins() < club2.getNumWins()) return -1;
        else return 0;
    }
}
