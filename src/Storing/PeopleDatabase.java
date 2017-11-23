package Storing;

import People.Nationality;
import People.Person;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class PeopleDatabase<TType extends Person> extends HashSet<TType> {
    boolean isCached = false;
    List<TType> sortedPolishList;

    public PeopleDatabase() {
        super();
    }

    @Override
    public boolean add(TType tType) {
        isCached = false;
        return super.add(tType);
    }

    public List<TType> getPeopleWithNationality(Nationality nationality) {
        List<TType> people = this.stream()
                .filter(e -> e.getNationality() == nationality)
                .collect(Collectors.toList());

        return sorted(people, nationality);
    }
    
    public List<TType> getSortedList(Nationality nationality) {
        List<TType> list = new ArrayList<>(this);
        return sorted(list, nationality);
    }

    public List<TType> sorted() {
        List<TType> list = new ArrayList<>(this);
        return sorted(list, Nationality.Polish);
    }

    public List<TType> sorted(List<TType> list) {
        return sorted(list, Nationality.Polish);
    }

    public List<TType> sorted(List<TType> list, Nationality nationality) {
        if(nationality == Nationality.Polish) {
            if (isCached)
                return sortedPolishList;
            else {
                list.sort((p1, p2) -> {
                    Collator collator = Collator.getInstance(nationality.getLocale());
                    collator.setStrength(Collator.PRIMARY);
                    int result = collator.compare(p1.getSurname(), p2.getSurname());
                    if (result == 0) result = collator.compare(p1.getFirstName(), p2.getFirstName());
                    if (result == 0) result = collator.compare(p1.getBirthsDate(), p2.getBirthsDate());
                    return result;
                });
                sortedPolishList = list;
                isCached = true;
            }
        }
        return list;
    }

    public String listToString(Collection<?> list) {
        StringBuilder people = new StringBuilder();
        list.forEach(p -> people.append(p).append("\n") );
        return people.toString();
    }

    @Override
    public String toString() {
        return listToString(this);
    }
}
