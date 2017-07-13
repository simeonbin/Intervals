import java.util.*;

interface IntervalService {
    
    /**
     * Merges overlapping intervals, the intervals are retrieved using {@link IntervalRepository}
     *
     * @return a list of merged intervals
     */
    
    List<Interval> findAllMerged(ArrayList<Interval> intervals);
}

class Interval 
{
    private int start;
    private int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) 
    {
        start = s;
        end = e;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class IntervalComparator implements Comparator<Interval>
{
    @Override
    public int compare(Interval i1, Interval i2)
    {
        return i1.getStart() - i2.getStart();
    }
}

public class FindAllMergedIntervals implements IntervalService
{   
    public List<Interval> findAllMerged(ArrayList<Interval> intervals) {

        if(intervals.isEmpty() || intervals.size() == 1)
            return intervals;

        Collections.sort(intervals, new IntervalComparator());

        Interval first = intervals.get(0);
        int start = first.getStart();
        int end = first.getEnd();

        ArrayList<Interval> result = new ArrayList<>();

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.getStart() <= end) {
                end = Math.max(current.getEnd(), end);
            } else {
                result.add(new Interval(start, end));
                start = current.getStart();
                end = current.getEnd();
            }
        }

        result.add(new Interval(start, end));
        return result;
    }
    
    public static void main (String[] args) throws java.lang.Exception
    {
        
        FindAllMergedIntervals find = new FindAllMergedIntervals();
        List<Interval> intervalList = new ArrayList<>();

        intervalList.add(new Interval(1, 3));
        intervalList.add(new Interval(2, 6));
        intervalList.add(new Interval(8, 10));
        intervalList.add(new Interval(15, 18));
        intervalList.add(new Interval(17, 20));

        intervalList = find.findAllMerged((ArrayList<Interval>) intervalList);

        for(Interval i : intervalList)
        {
            System.out.println(i.getStart() + " " + i.getEnd());
        }
    }   
   
    
}

