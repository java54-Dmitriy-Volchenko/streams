package telran.streams.students;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

class ColledgeTests {
private static final String NAME1 = "Vasya";
private static final String NAME2 = "Petya";
private static final String NAME3 = "Masha";
private static final String NAME4 = "Dora";
private static final String NAME5 = "Riva";
private static final int HOURS1 = 100;
private static final int HOURS2 = 100;
private static final int HOURS3 = 150;
private static final int HOURS4 = 130;

private static final int[] MARKS1 = {60, 70, 80};//70
private static final int[] MARKS2 = {60, 60, 60};//60
private static final int[] MARKS4 = {60, 90, 100};//83.33

Student st1 = new Student(NAME1, HOURS1, MARKS1);
Student st2 = new Student(NAME2, HOURS2, MARKS2);
Student st3 = new Student(NAME3, HOURS3, MARKS2);
Student st4 = new Student(NAME4, HOURS4, MARKS4);
Student st5 = new Student(NAME5, HOURS4, MARKS1);

Colledge colledge = new Colledge(new Student[] {st1, st2, st3, st4, st5});




	@Test
	void sortTest() {
		Student[] expected = {
				st4, //Dora, 130, 83.33
				st5,  //Riva, 130, 70				
				st1, //Vasya, 100, 70
				st3, //Masha, 150, 60
				st2 //Petya, 100, 60
				
				
			
				};
		assertArrayEquals(expected, sortStudents(colledge));
	}
	@Test
	void summaryStatisticsHoursTest () {
		IntSummaryStatistics iss = getHoursStatistics(colledge);
		assertEquals(100, iss.getMin());
		assertEquals(150, iss.getMax());
		assertEquals(350, iss.getSum());
	}
	@Test 
	void summaryStatisticsMarks() {
		IntSummaryStatistics iss = getMarksStatistics(colledge);
		assertEquals(60, iss.getMin());
		assertEquals(80, iss.getMax());
	}
	private static IntSummaryStatistics getMarksStatistics(Colledge coll) {
		// TODO Auto-generated method stub
		//returns summary statistics for marks of all colledge's students
		return null;
	}
	static private IntSummaryStatistics getHoursStatistics(Colledge col) {
		// TODO Auto-generated method stub
		//returns IntSummaryStatistics of hours for all colledge's students
		return null;
	}
	private static Student[] sortStudents(Colledge col) {	
	
		 return studentStream(col)
		            .sorted(Comparator.comparingDouble((Student student) ->
		                    Arrays.stream(student.marks()).average().orElse(0))
		                    .thenComparing(Comparator.comparingInt(Student::hours)).reversed())
		            .toArray(Student[]::new);
	   
	   		// TODO
			//consider getting stream from Iterable
			//returns array of students sorted in descending order of the average marks
			//in the case average marks are equaled there will be compared hours
			//one code line
	   
		    }

	  private static Stream<Student> studentStream(Colledge col) {
		        return StreamSupport.stream(col.spliterator(), false);
		    }
	

}