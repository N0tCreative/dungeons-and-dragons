package dungeon;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import jerad.*;

public class MainTest {
	public static void main(String [] args) {

		System.out.println("===============DoorTest===================");
		Result result1 = JUnitCore.runClasses(DoorTest.class);
		System.out.println("Failed Tests:");
        //Print out failure tests
        List <Failure> failedList1 = result1.getFailures();
		failedList1.forEach(f->{System.out.println(f);});
		System.out.println("Number of Failed Tests = " + failedList1.size());
		
        System.out.println("===============ChamberTest====================");
		Result result2 = JUnitCore.runClasses(ChamberTest.class);
		System.out.println("Failed Tests:");
		List <Failure> failedList2 = result2.getFailures();
		failedList2.forEach(f->{System.out.println(f);});
		System.out.println("Number of Failed Tests = " + failedList2.size());

		System.out.println("===============PassageTest====================");
		Result result3 = JUnitCore.runClasses(PassageTest.class);
		System.out.println("Failed Tests:");
		List <Failure> failedList3 = result3.getFailures();
		System.out.println("Number of Failed Tests = " + failedList3.size());
		failedList3.forEach(f->{System.out.println(f);});

		System.out.println("===============PassageSectionTest==============");

		Result result4 = JUnitCore.runClasses(PassageSectionTest.class);
		List <Failure> failedList4 = result4.getFailures();
		System.out.println("Failed Tests:");
		System.out.println("Number of Failed Tests = " + failedList4.size());
        failedList4.forEach(f->{System.out.println(f);});
        
	}
}