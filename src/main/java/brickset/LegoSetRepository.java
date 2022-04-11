package brickset;

import com.fasterxml.jackson.core.sym.Name;
import repository.Repository;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /*Method 1
    * Returns whether there is at least one Legoset with Pieces = 92;
    *
    * Return Boolean
    * */
    public boolean PrintmatchPieces(){
        return getAll().stream()
                .anyMatch(legoSet -> legoSet.getPieces() ==92);
    }
    /*Method2
    * Return Tags of Legosets which is not null and number is "4899"
    *
    * Return String
    * */
    public void PrintTagwithnumbercontain4899(){
        getAll().stream()
             .filter(LegoSet-> LegoSet.getTags() != null && LegoSet.getNumber().contains("4899"))
             .flatMap(LegoSet -> LegoSet.getTags().stream())
             .distinct()
             .forEach(System.out::println);
}
    /*Method3
    * Return a number of Legosets with the Dimensions is null.
    *
    * Return Integer
    * */
    public long printSumOfLegoPieces() {
        return getAll().stream()
                .filter(LegoSet-> LegoSet.getDimensions() == null)
                .map(LegoSet::getPieces)
                .reduce(0,(a,b) -> a+b);

    }
    /*Method4
    * Return number and name
    *
    * Return map with legosets number = content of Name
    * */
    public Map<String, String> getNumberName() {
        return getAll().stream()
                .collect(Collectors.toMap(LegoSet::getNumber, LegoSet::getName));
    }
    /*Method5
    * Return one number of each PackagingType with the Dimensions not null
    *
    * return Map<PackagingTyoe, long >object each Legoset whether used Package with a frequency
    * */
    public Map<PackagingType, Long> printMapwithDimensionsnotnull() {
        return getAll().stream()
                .filter(LegoSet->LegoSet.getDimensions() != null)
                .collect(Collectors.groupingBy(LegoSet::getPackagingType, Collectors.counting()));
    }
    /*Main method*/
    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println("Method 1:");
        System.out.println(repository.PrintmatchPieces());
        System.out.println("Method 2:");
        repository.PrintTagwithnumbercontain4899();
        System.out.println("Method 3:");
        System.out.println(repository.printSumOfLegoPieces());
        repository.printSumOfLegoPieces();
        System.out.println("Method 4:");
        System.out.println(repository.getNumberName());
        System.out.println("Method 5:");
        System.out.println(repository.printMapwithDimensionsnotnull());
    }
}
